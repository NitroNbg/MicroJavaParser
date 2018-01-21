package rs.ac.bg.etf.pp1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;


import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import java_cup.runtime.Symbol;
import rs.ac.bg.etf.pp1.ast.Program;
import rs.ac.bg.etf.pp1.util.ASTUtils;
import rs.ac.bg.etf.pp1.util.Log4JUtils;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;

public class MJTest {

	static {
		DOMConfigurator.configure(Log4JUtils.instance().findLoggerConfigFile());
		Log4JUtils.instance().prepareLogFile(Logger.getRootLogger());
	}
	
	public static void main(String[] args) throws IOException {
		Logger log = Logger.getLogger(MJTest.class);
		Reader br = null;
		try {
			
//			File sourceCode = new File("test/program - javni semantika.mj");
//			File sourceCode = new File("test/program.mj");
			File sourceCode = new File("test/test301.mj");
//			File sourceCode = new File("test/test302.mj");
//			File sourceCode = new File("test/test303.mj");
			log.info("Prevođenje izvorne datoteke: " + sourceCode.getAbsolutePath());
			
			br = new BufferedReader(new FileReader(sourceCode));
			
			Yylex lexer = new Yylex(br);
			MJParser parser = new MJParser(lexer);
			Symbol s = parser.debug_parse();
			Tab.init();
			Tab.insert(Obj.Type, "bool", ASTUtils.boolType);
			SemanticAnalyzer semanticAnalyzer = new SemanticAnalyzer(s);
			System.out.println("==================SEMANTICKA OBRADA==================");
			((Program)s.value).traverseBottomUp(semanticAnalyzer);
			Tab.dump();
			if(semanticAnalyzer.isErrorOccurred()) {
				log.error("Parsiranje neuspešno!");
			}
			else {
				log.info("Parsiranje uspešno!");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if (br != null) try { br.close(); } catch (IOException e1) { log.error(e1.getMessage(), e1); }
		}
	}
	
}
