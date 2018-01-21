package rs.ac.bg.etf.pp1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

import java_cup.runtime.Symbol;
import rs.ac.bg.etf.pp1.util.ASTUtils;
import rs.ac.bg.etf.pp1.ast.Program;
import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;

public class Compiler {

	public static void main(String[] args) {
		if (args.length < 2) {
			System.err.println("Morate unetu putanju do izvorne datoteke kao i putanju za izlaznu .obj datoteku");
			System.exit(1);
		}
		String argFilePath = args[0];
		File sourceFile = new File(argFilePath);
		if (sourceFile.exists()) {
			System.out.println("PrevoÄ‘enje izvorne datoteke: " + sourceFile.getAbsolutePath());
			try {
				BufferedReader br = new BufferedReader(new FileReader(sourceFile));
				Yylex lexer = new Yylex(br);
				MJParser parser = new MJParser(lexer);
				Symbol s = parser.parse();
				if (!parser.errorOccurred) {
					System.out.println("Parsiranje izvorne datoteke uspeÅ¡no zavrÅ¡eno");
					System.out.println("==========SadrÅ¾aj AST==========");
					System.out.print(((Program)s.value).toString());
					System.out.println();
				}
				else {
					System.err.println("UoÄ�ena je greÅ¡ka prilikom sintaksne analize, prevoÄ‘enje je prekinuto");
					return;
				}
				System.out.println("==============SEMANTÄ�IKA ANALIZA==============");
				Tab.init();
				Tab.insert(Obj.Type, "bool", ASTUtils.boolType);
				SemanticAnalyzer semanticAnalyzer = new SemanticAnalyzer(s);
				((Program)s.value).traverseBottomUp(semanticAnalyzer);
				if (semanticAnalyzer.isErrorOccurred()) {
					System.err.println("UoÄ�ena je greÅ¡ka prilikom semantiÄ�ke analize, prevoÄ‘enje je prekinuto");
					return;
				}
				else {
					System.out.println("SemantiÄ�ka analiza zadovoljena");
					tsdump();
				}
				CodeGenerator codeGenerator = new CodeGenerator();
				((Program)s.value).traverseBottomUp(codeGenerator);
				if (codeGenerator.isErrorOccurred() || Code.greska) {
					System.err.println("UoÄ�ena je greÅ¡ka prilikom generisanja koda, prevoÄ‘enje je prekinuto");
					return;
				}
				FileOutputStream output = new FileOutputStream(args[1]);
				Code.write(output);
			}
			catch (IOException e) {
				System.err.println("DoÅ¡lo je do greÅ¡ke prilikom Ä�itanja izvorne datoteke");
				e.printStackTrace();
			}
			catch (Exception e) {
				System.err.println("DoÅ¡lo je do greÅ¡ke prilikom prevoÄ‘enja izvorne datoteke");
				e.printStackTrace();
			}
		}
		else {
			System.err.println("Nije pronaÄ‘ena datoteka na putanji " + argFilePath);
			System.exit(2);
		}
	}
	
	public static void tsdump() {
		Tab.dump();
	}
}
