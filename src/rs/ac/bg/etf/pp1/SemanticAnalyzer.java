package rs.ac.bg.etf.pp1;

import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

import java_cup.runtime.Symbol;
import rs.ac.bg.etf.pp1.ast.ActParams;
import rs.ac.bg.etf.pp1.ast.ActParamsAdjoined;
import rs.ac.bg.etf.pp1.ast.ActParamsOpt;
import rs.ac.bg.etf.pp1.ast.ActualParams;
import rs.ac.bg.etf.pp1.ast.ActualType;
import rs.ac.bg.etf.pp1.ast.AddExpr;
import rs.ac.bg.etf.pp1.ast.Array;
import rs.ac.bg.etf.pp1.ast.BoolRef;
import rs.ac.bg.etf.pp1.ast.CharRef;
import rs.ac.bg.etf.pp1.ast.ClassDefinition;
import rs.ac.bg.etf.pp1.ast.ConstDeclaration;
import rs.ac.bg.etf.pp1.ast.ConstFactor;
import rs.ac.bg.etf.pp1.ast.Designator;
import rs.ac.bg.etf.pp1.ast.DesignatorArrayElement;
import rs.ac.bg.etf.pp1.ast.DesignatorExpression;
import rs.ac.bg.etf.pp1.ast.DesignatorFactor;
import rs.ac.bg.etf.pp1.ast.DesignatorField;
import rs.ac.bg.etf.pp1.ast.DesignatorFunctionFactor;
import rs.ac.bg.etf.pp1.ast.DesignatorFunctionParams;
import rs.ac.bg.etf.pp1.ast.DesignatorIdent;
import rs.ac.bg.etf.pp1.ast.DesignatorPostDecrement;
import rs.ac.bg.etf.pp1.ast.DesignatorPostIncrement;
import rs.ac.bg.etf.pp1.ast.DesignatorStatementDeclaration;
import rs.ac.bg.etf.pp1.ast.DesignatorStatementSuffix;
import rs.ac.bg.etf.pp1.ast.ErrorConstDeclaration;
import rs.ac.bg.etf.pp1.ast.ErrorConstantDeclarationsComma;
import rs.ac.bg.etf.pp1.ast.ErrorDesignatorStatementSyntax;
import rs.ac.bg.etf.pp1.ast.ErrorVarArrayDeclaration;
import rs.ac.bg.etf.pp1.ast.ErrorVarDeclaration;
import rs.ac.bg.etf.pp1.ast.ExprFactor;
import rs.ac.bg.etf.pp1.ast.ExtendsBaseClass;
import rs.ac.bg.etf.pp1.ast.ExtendsObject;
import rs.ac.bg.etf.pp1.ast.FactorTerm;
import rs.ac.bg.etf.pp1.ast.FormPars;
import rs.ac.bg.etf.pp1.ast.FormParsOpt;
import rs.ac.bg.etf.pp1.ast.FormalParameters;
import rs.ac.bg.etf.pp1.ast.FormalParams;
import rs.ac.bg.etf.pp1.ast.IntRef;
import rs.ac.bg.etf.pp1.ast.LocalVariables;
import rs.ac.bg.etf.pp1.ast.LocalVars;
import rs.ac.bg.etf.pp1.ast.MethodBody;
import rs.ac.bg.etf.pp1.ast.MethodName;
import rs.ac.bg.etf.pp1.ast.MulTerm;
import rs.ac.bg.etf.pp1.ast.NegativeTermListExpr;
import rs.ac.bg.etf.pp1.ast.NewArgumentArrayFactor;
import rs.ac.bg.etf.pp1.ast.NewArgumentFactor;
import rs.ac.bg.etf.pp1.ast.NoActualParams;
import rs.ac.bg.etf.pp1.ast.NoLocals;
import rs.ac.bg.etf.pp1.ast.PrintStatement;
import rs.ac.bg.etf.pp1.ast.ProgId;
import rs.ac.bg.etf.pp1.ast.Program;
import rs.ac.bg.etf.pp1.ast.ProgramImpl;
import rs.ac.bg.etf.pp1.ast.ReadStatement;
import rs.ac.bg.etf.pp1.ast.ReturnStatement;
import rs.ac.bg.etf.pp1.ast.Term;
import rs.ac.bg.etf.pp1.ast.TermExpr;
import rs.ac.bg.etf.pp1.ast.TermList;
import rs.ac.bg.etf.pp1.ast.TermListExpr;
import rs.ac.bg.etf.pp1.ast.TypeConst;
import rs.ac.bg.etf.pp1.ast.TypeIdentifier;
import rs.ac.bg.etf.pp1.ast.VarDeclList;
import rs.ac.bg.etf.pp1.ast.VarDeclarations;
import rs.ac.bg.etf.pp1.ast.Variable;
import rs.ac.bg.etf.pp1.ast.VariableDefinition;
import rs.ac.bg.etf.pp1.ast.VisitorAdaptor;
import rs.ac.bg.etf.pp1.ast.VoidType;
import rs.ac.bg.etf.pp1.util.ASTUtils;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;
import rs.etf.pp1.symboltable.structure.SymbolDataStructure;

public class SemanticAnalyzer extends VisitorAdaptor {
	private boolean errorOccurred = false;
	private Program root = null;
	private Struct varType = Tab.noType;
	private Struct methodReturnType = Tab.noType;
	private String currentMethodName = null;
	private int currentMethodFormalParamCount = 0;
	private int currentMethodLocalCount = 0;
	private boolean returnExpressionFound = false;
	private final Logger logger;
	private Obj treeRootNode = null;
	private int currentScopeLevel = 0;
	private List<Designator> recursiveCalls = null;
	
	public SemanticAnalyzer(Symbol rootNode) {
		if (rootNode == null || !(rootNode.value instanceof Program)) {
			System.err.println("Morate proslediti koreni Ä�vor stabla (Program) semantiÄ�kom analizatoru kako bi poÄ�eo sa radom.");
		}
		this.setRoot((Program)rootNode.value);
		logger = Logger.getLogger(SemanticAnalyzer.class);
	}
	
	public boolean isErrorOccurred() {
		return errorOccurred;
	}

	public void setErrorOccurred(boolean errorOccurred) {
		this.errorOccurred = errorOccurred;
	}
	
	public void reportError(String message) {
		errorOccurred = true;
		logger.error(message);
	}
	
	public Program getRoot() {
		return root;
	}

	public void setRoot(Program root) {
		this.root = root;
	}
	
	public void startTraversing() {
		if (this.root == null) {
			System.err.println("Ne postoji koren stabla koje bi se obradilo.");
			return;
		}
		System.out.println("==================SEMANTICKA OBRADA==================");
		this.root.traverseBottomUp(this);
	}

	@Override
	public void visit(ProgramImpl Program) {
		treeRootNode.setLocals(Tab.currentScope().getLocals());
		if (Tab.find("main").getKind() != Obj.Meth) {
			errorOccurred = true;
			System.err.println("GreÅ¡ka - program ne sadrÅ¾i funkciju main()");
		}
		Tab.closeScope();
		currentScopeLevel--;
	}
	
	@Override
	public void visit(ProgId ProgId) {
		treeRootNode = Tab.insert(Obj.Prog, ProgId.getId(), Tab.noType);
		ProgId.obj = treeRootNode;
		currentScopeLevel++;
		Tab.openScope();
	}
	
	@Override
	public void visit(ConstDeclaration ConstDeclaration) {
		TypeConst type = ConstDeclaration.getTypeConst();
		Obj objNode = Tab.noObj;
		if (type instanceof BoolRef) {
			if (varType != ASTUtils.boolType) {
				errorOccurred = true;
				System.err.println("GreÅ¡ka na " + ConstDeclaration.getLine() + ": tip i vrednost konstante se ne poklapaju");
			}
			else {
				objNode = Tab.insert(Obj.Con, ConstDeclaration.getI1(), ASTUtils.boolType);
				objNode.setAdr(((BoolRef)type).getB1() ? 1 : 0);
			}
		}
		else if (type instanceof IntRef) {
			if (varType != Tab.intType) {
				errorOccurred = true;
				System.err.println("GreÅ¡ka na " + ConstDeclaration.getLine() + ": tip i vrednost konstante se ne poklapaju");
			}
			else {
				objNode = Tab.insert(Obj.Con, ConstDeclaration.getI1(), Tab.intType);
				objNode.setAdr(((IntRef)type).getN1().intValue());
			}
		}
		else if (type instanceof CharRef) {
			if (varType != Tab.charType) {
				errorOccurred = true;
				System.err.println("GreÅ¡ka na " + ConstDeclaration.getLine() + ": tip i vrednost konstante se ne poklapaju");
			}
			else {
				objNode = Tab.insert(Obj.Con, ConstDeclaration.getI1(), Tab.charType);
				objNode.setAdr(((CharRef)type).getC1());
			}
		}
		else {
			System.err.println("GreÅ¡ka na "+ type.getLine() + "Nedozvoljena vrednost " + type.toString() + " za konstantu. Dozvoljeni su samo primitivni tipovi.");
		}
		
		if (objNode != Tab.noObj) {
			System.out.println("Red " + ConstDeclaration.getLine() + ": deklarisana konstanta " + ConstDeclaration.getI1() + " sa vrednoÅ¡Ä‡u " + ASTUtils.getConstantNodeValue(type));
			Tab.currentScope.addToLocals(objNode);
		}
	}
	
	@Override
	public void visit(Variable var) {
		Obj objNode = Tab.find(var.getI1());
		if (objNode == Tab.noObj || objNode.getLevel() < currentScopeLevel) {
			objNode = Tab.insert(Obj.Var, var.getI1(), varType);
			if (objNode.getLevel() != 0) {
				System.out.println("Red " + var.getLine() + ": deklarisana lokalna promenljiva " + ASTUtils.getStructIdentifier(varType) +  " " + var.getI1());			
			}
			else {
				System.out.println("Red " + var.getLine() + ": deklarisana globalna promenljiva " + ASTUtils.getStructIdentifier(varType) +  " " + var.getI1());
			}
			Tab.currentScope.addToLocals(objNode);
		}
		else {
			System.out.println("GreÅ¡ka na " + var.getLine() + ": prethodno je veÄ‡ deklarisana promenljiva " + var.getI1());
		}
	}
	
	@Override
	public void visit(ClassDefinition ClassDefinition) {
		Tab.closeScope();
		currentScopeLevel--;
		String identifier = ClassDefinition.getI1();
		Obj node = Tab.find(identifier);
		if (node != Tab.noObj && node.getKind() == Obj.Type) {
			errorOccurred = true;
			System.err.println("GreÅ¡ka na " + ClassDefinition.getLine() + ": klasa " + identifier + " je prethodno veÄ‡ deklarisana");
		}
		else {
			node = Tab.insert(Obj.Type, identifier, new Struct(Struct.Class));
		}
	}
	
	@Override
	public void visit(ExtendsBaseClass ExtendsBaseClass) {
		currentScopeLevel++;
		Tab.openScope();
	}
	
	@Override
	public void visit(ExtendsObject ExtendsObject) {
		currentScopeLevel++;
		Tab.openScope();
	}
	
	@Override
	public void visit(Array Array) {
		Obj objNode = Tab.find(Array.getI1());
		if (objNode == Tab.noObj || objNode.getLevel() < currentScopeLevel) {
			objNode = Tab.insert(Obj.Var, Array.getI1(), new Struct(Struct.Array, varType));
			if (objNode.getLevel() != 0) {
				System.out.println("Red " + Array.getLine() + ": deklarisan lokalni niz " + ASTUtils.getStructIdentifier(varType) +  " " + Array.getI1());			
			}
			else {
				System.out.println("Red " + Array.getLine() + ": deklarisana globalni niz " + ASTUtils.getStructIdentifier(varType) +  " " + Array.getI1());
			}
		}
		else {
			System.out.println("GreÅ¡ka na " + Array.getLine() + ": prethodno je veÄ‡ deklarisana promenljiva " + Array.getI1());
		}
	}
	
	@Override
	public void visit(ActualType methodType) {
		Obj objNode = Tab.noObj;
		if (methodType.getType() instanceof TypeIdentifier) {
			objNode = Tab.find(((TypeIdentifier)methodType.getType()).getI1());
		}
		if (objNode == Tab.noObj) {
			errorOccurred = true;
			System.out.println("GreÅ¡ka na " + methodType.getLine() + ": ne postoji tip " + ((TypeIdentifier)methodType.getType()).getI1());
		}
		else if (objNode.getKind() != Obj.Type) {
			errorOccurred = true;
			System.out.println("GreÅ¡ka na " + methodType.getLine() + ": " + ((TypeIdentifier)methodType.getType()).getI1() + " nije tip");
		}
		methodReturnType = methodType.getType().struct;
		returnExpressionFound = false;
		currentScopeLevel++;
		Tab.openScope();
	}
	
	@Override
	public void visit(VoidType VoidType) {
		methodReturnType = Tab.noType;
		returnExpressionFound = false;
		currentScopeLevel++;
		Tab.openScope();
	}
	
	@Override
	public void visit(ReturnStatement ReturnStatement) {
		if (currentScopeLevel == 1) {
			errorOccurred = true;
			System.err.println("GreÅ¡ka na " + ReturnStatement.getLine() + ": return naredba mora biti unutar tela metode odnosno globalne funkcije");
		}
		returnExpressionFound = true;
		Struct returnStruct = ReturnStatement.getExpr().struct;
		if (!returnStruct.assignableTo(methodReturnType)) {
			errorOccurred = true;
			System.err.println("GreÅ¡ka na " + ReturnStatement.getLine() + ": neodgovarajuÄ‡i povratni tip");
		}
	}
	
	@Override
	public void visit(MethodName MethodName) {
		currentMethodName = MethodName.getI2();
	}
	
	public int getFormParsCount(FormParsOpt formParsOpt) {
		currentMethodFormalParamCount = 0;
		if (formParsOpt instanceof FormalParams) {
			FormPars parameters = ((FormalParams)formParsOpt).getFormPars();
			while(parameters instanceof FormalParameters) {
				parameters = ((FormalParameters)parameters).getFormPars();
				currentMethodFormalParamCount++;
			}
			currentMethodFormalParamCount++;
		}
		return currentMethodFormalParamCount;
	}
	
	public void checkParamCount(ActParamsOpt actParamsOpt, int line) {
		int actParamCount = 0;
		int formalParamCount = 0;
		Designator function;
		if (actParamsOpt.getParent() instanceof DesignatorFunctionFactor) {
			function = ((DesignatorFunctionFactor)actParamsOpt.getParent()).getDesignator();
		}
		else {
			function = ((DesignatorStatementDeclaration)actParamsOpt.getParent().getParent()).getDesignator();
		}
		String identifier = ASTUtils.getAbstractNodeIdentifier(function);
		if (identifier.equals(currentMethodName)) {
			formalParamCount = currentMethodFormalParamCount;
		}
		else {
			Obj node = Tab.find(ASTUtils.getAbstractNodeIdentifier(function));
			if (node.getKind() != Obj.Meth) {
				errorOccurred = true;
				System.err.println("GreÅ¡ka na " + actParamsOpt.getLine() + ": " + ASTUtils.getAbstractNodeIdentifier(function) + " nije definisana metoda");
			}
			else {
				formalParamCount = node.getLevel();
			}
		}
		if (actParamsOpt instanceof NoActualParams) {
			actParamCount = 0;
		}
		else {
			ActParams actualParams = ((ActualParams)actParamsOpt).getActParams();
			actParamCount++;
			while (actualParams instanceof ActParamsAdjoined) {
				actualParams = ((ActParamsAdjoined)actualParams).getActParams();
				actParamCount++;
			}
		}
		if (formalParamCount != actParamCount) {
			errorOccurred = true;
			System.err.println("GreÅ¡ka na " + line + ": broj stvarnih parametara ne odgovara broju formalnih parametara");
		}
	}
	
	@Override
	public void visit(LocalVars localVars) {
		if (localVars instanceof NoLocals) {
			currentMethodLocalCount = 0;
		}
		else {
			LocalVariables locals = (LocalVariables)localVars;
			while (locals.getLocalVars() instanceof LocalVariables) {
				VariableDefinition variableDeclarations = (VariableDefinition)locals.getVarDecl();
				VarDeclList list = variableDeclarations.getVarDeclList();
				while (list instanceof VarDeclarations) {
					currentMethodLocalCount++;
					list = ((VarDeclarations)list).getVarDeclList();
				}
				currentMethodLocalCount++;
				locals = (LocalVariables)locals.getLocalVars();
			}
		}
	}
	
	@Override
	public void visit(MethodBody MethodBody) {
		if (!returnExpressionFound && methodReturnType != Tab.noType) {
			errorOccurred = true;
			System.err.println("GreÅ¡ka na " + MethodBody.getLine() + ": metoda ima povratni tip ali nije pronaÄ‘ena return naredba");
		}
		if(MethodBody.getMethodName().getI2().equals("main") && methodReturnType != Tab.noType) {
			errorOccurred = true;
			System.err.println("GreÅ¡ka na " + MethodBody.getLine() + ": metoda main mora biti void");
		}
		SymbolDataStructure locals = Tab.currentScope.getLocals();
		Tab.closeScope();
		currentScopeLevel--;
		Obj node = Tab.find(MethodBody.getMethodName().getI2());
		if (node.getKind() != Obj.Meth) {
			MethodBody.obj = Tab.insert(Obj.Meth, MethodBody.getMethodName().getI2(), methodReturnType);
			MethodBody.obj.setLocals(locals);
			for (Obj item: MethodBody.obj.getLocalSymbols()) {
				if (item.getName().equals("")) {
					MethodBody.obj.getLocalSymbols().remove(item);
				}
			}
			MethodBody.obj.setLevel(getFormParsCount(MethodBody.getFormParsOpt()));
			if (MethodBody.getMethodName().getI2().equals("main") && MethodBody.obj.getLevel() != 0) {
				errorOccurred = true;
				System.err.println("GreÅ¡ka na " + MethodBody.getLine() + ": metoda main ne sme imati formalne parametre");
			}
			if (recursiveCalls != null) {
				for(Designator d : recursiveCalls) {
					d.obj = MethodBody.obj;
				}
			}
		}
		else {
			errorOccurred = true;
			System.err.println("GreÅ¡ka na " + MethodBody.getLine() + ": metoda " + MethodBody.getMethodName().getI2() + " je veÄ‡ definisana");
		}
		currentMethodName = null;
		recursiveCalls = null;
	}
	
	@Override
	public void visit(DesignatorStatementDeclaration designatorStatement) {
		Obj objNode = Tab.noObj;
		Designator designator = designatorStatement.getDesignator();
		DesignatorStatementSuffix suffix = designatorStatement.getDesignatorStatementSuffix();
		if (suffix instanceof DesignatorFunctionParams) {
			if (designator instanceof DesignatorIdent) {
				objNode = Tab.find(((DesignatorIdent)designator).getI1());
				if (objNode.getKind() != Obj.Meth && !(((DesignatorIdent)designator).getI1().equals(currentMethodName))) {
					errorOccurred = true;
					System.err.println("GreÅ¡ka na " + designatorStatement.getLine() + ": metoda " + ((DesignatorIdent)designator).getI1() + " nije definisana");
				}
				else {
					checkParamCount(((DesignatorFunctionParams)suffix).getActParamsOpt(), designator.getLine());
					if (objNode == Tab.noObj) {
						if (recursiveCalls == null) {
							recursiveCalls = new LinkedList<Designator>();
						}
					recursiveCalls.add(designator);
					}
				}
//				else {
//					designator.obj = objNode;
//				}
				if (((DesignatorIdent) designator).getI1().equals("chr") && ASTUtils.getFunctionParamsType((DesignatorFunctionParams)suffix) != Struct.Int) {
					errorOccurred = true;
					System.err.println("GreÅ¡ka na " + designatorStatement.getLine() + ": metoda chr(e) prihvata samo parametar int tipa");
				}
				else if (((DesignatorIdent) designator).getI1().equals("ord") && ASTUtils.getFunctionParamsType((DesignatorFunctionParams)suffix) != Struct.Char) {
					errorOccurred = true;
					System.err.println("GreÅ¡ka na " + designatorStatement.getLine() + ": metoda ord(c) prihvata samo parametar char tipa");
				}
				else if (((DesignatorIdent) designator).getI1().equals("len") && ASTUtils.getFunctionParamsType((DesignatorFunctionParams)suffix) != Struct.Array) {
					errorOccurred = true;
					System.err.println("GreÅ¡ka na " + designatorStatement.getLine() + ": metoda len(a) prihvata samo niz kao parametar");
				}
				System.out.println("Poziv metode " + ((DesignatorIdent)designator).getI1() + " u redu " + designator.getLine());
			}
			else if (designator instanceof DesignatorField) {
				String fullIdentifier = "";
				Designator parent = ((DesignatorField)designator).getDesignator();
				while (!(parent instanceof DesignatorIdent)) {
					if (parent instanceof DesignatorField) {
						parent = ((DesignatorField)parent).getDesignator();
						fullIdentifier = String.format("%s.%s", ((DesignatorField)parent).getI2(), fullIdentifier);
					}
					else {
						parent = ((DesignatorArrayElement)parent).getDesignator();
						fullIdentifier = String.format("%s.%s", ((DesignatorIdent)parent).getI1(), fullIdentifier);
					}
					
				}
				System.out.println("Poziv metode " + String.format("%s.%s", fullIdentifier, ((DesignatorField)designator).getI2()) + " u redu " + designator.getLine());
			}
		}
		else if (suffix instanceof DesignatorExpression) {
			Struct exprType = ((DesignatorExpression)suffix).getExpr().struct;
			String identifier = ASTUtils.getAbstractNodeIdentifier(designator);
			objNode = Tab.find(identifier);
			if (objNode == Tab.noObj) {
				errorOccurred = true;
				System.err.println("GreÅ¡ka na " + designatorStatement.getLine() + ": nedefinisana promenljiva " + identifier);
			}
			else {
				designator.obj = objNode;
			}
			if (exprType == null || !((exprType.assignableTo(objNode.getType())) || (objNode.getType().getKind() == Struct.Array && exprType.assignableTo(objNode.getType().getElemType())))) {
				errorOccurred = true;
				System.err.println("GreÅ¡ka na " + designatorStatement.getLine() + ": tip promenljive i izraza dodele nisu kompatibilni");
			}
		}
		else if (suffix instanceof DesignatorPostDecrement) {
			objNode = Tab.find(ASTUtils.getAbstractNodeIdentifier(designator));
			if(objNode == Tab.noObj || objNode.getType() != Tab.intType || ((objNode.getType().getKind() == Struct.Array) && (objNode.getType().getElemType() != Tab.intType))) {
				errorOccurred = true;
				System.err.println("GreÅ¡ka na " + designator.getLine() + ": operand -- naredbe mora biti int");
			}
			else {
				designator.obj = objNode;
			}
		}
		else if (suffix instanceof DesignatorPostIncrement) {
			objNode = Tab.find(ASTUtils.getAbstractNodeIdentifier(designator));
			if(objNode == Tab.noObj || objNode.getType() != Tab.intType || ((objNode.getType().getKind() == Struct.Array) && (objNode.getType().getElemType() != Tab.intType))) {
				errorOccurred = true;
				System.err.println("GreÅ¡ka na " + designator.getLine() + ": operand ++ naredbe mora biti int");
			}
			else {
				designator.obj = objNode;
			}
		}
	}
	
	@Override
	public void visit(DesignatorArrayElement DesignatorArrayElement) {
		String identifier = ASTUtils.getAbstractNodeIdentifier(DesignatorArrayElement.getDesignator());
		Obj node = Tab.find(identifier);
		if (node.getType().getKind() != Struct.Array) {
			errorOccurred = true;
			System.err.println("GreÅ¡ka na " + DesignatorArrayElement.getLine() + ": promenljiva " + identifier + " nije niz");
		}
		else {
			DesignatorArrayElement.getDesignator().obj = node;
		}
		if (DesignatorArrayElement.getExpr().struct != Tab.intType) {
			errorOccurred = true;
			System.err.println("GreÅ¡ka na " + DesignatorArrayElement.getLine() + ": indeks niza mora biti tipa int");
		}
	}
	
	@Override
	public void visit(ReadStatement ReadStatement) {
		Designator designator = ReadStatement.getDesignator();
		Obj node = Tab.find(ASTUtils.getAbstractNodeIdentifier(designator));
		if ((node.getKind() != Obj.Var) && (node.getKind() != Obj.Elem) && (node.getKind() != Obj.Fld)) {
			errorOccurred = true;
			System.err.println("GreÅ¡ka na " + ReadStatement.getLine() + ": operand read naredbe mora biti promenljiva, element niza ili polje unutar objekta");
		}
		else {
			Struct type = node.getType();
			designator.obj = node;
			if (type.getKind() == Struct.Array) {
				type = type.getElemType();
				designator.obj = new Obj(node.getKind(), node.getName(), type);
			}
			if ((type != Tab.intType) && (type != Tab.charType) && (type != ASTUtils.boolType)) {
				errorOccurred = true;
				System.err.println("GreÅ¡ka na " + ReadStatement.getLine() + ": operand read naredbe mora biti int, char ili bool tipa");
			}
		}
	}
	
	@Override
	public void visit(PrintStatement PrintStatement) {
		if ((PrintStatement.getExpr().struct != Tab.intType) && (PrintStatement.getExpr().struct != Tab.charType) && (PrintStatement.getExpr().struct != ASTUtils.boolType)) {
			errorOccurred = true;
			System.err.println("GreÅ¡ka na " + PrintStatement.getLine() + ": argument print naredbe mora biti int, char ili bool tipa");
		}
	}
	
	@Override
	public void visit(DesignatorIdent DesignatorIdent) {
		Obj node = Tab.find(DesignatorIdent.getI1());
		if (node == Tab.noObj && !DesignatorIdent.getI1().equals(currentMethodName)) {
			errorOccurred = true;
			System.err.println("GreÅ¡ka na " + DesignatorIdent.getLine() + ": promenljiva " + DesignatorIdent.getI1() + " nije deklarisana");
		}
		else {
			DesignatorIdent.obj = node;
			if (node.getKind() == Obj.Var) {
				System.out.println("KoriÅ¡Ä‡enje promenljive " + node.getName() + " u redu " + DesignatorIdent.getLine() + "; " + outputNode(node));
			}
			else if (node.getKind() == Obj.Con && !(node.getName().equals(""))) {
				System.out.println("KoriÅ¡Ä‡enje konstante " + node.getName() + " u redu " + DesignatorIdent.getLine() + "; " + outputNode(node));
			}
		}
	}
	
	@Override
	public void visit(VariableDefinition VariableDefinition) {
//		Type type = VariableDefinition.getType();
//		
//		VarDeclList list = VariableDefinition.getVarDeclList();
//		if (list instanceof VarDeclarations) {
//			VarDeclList resto = list;
//			Var var = ((VarDeclarations)list).getVar();
//			while (!(resto instanceof VarDeclaration)) {
//				String identifier = ASTUtils.getAbstractNodeIdentifier(var);
//				Obj node = Tab.find(identifier);
//				if ((node == Tab.noObj) || (node.getLevel() < currentScopeLevel)) {
//					Tab.insert(Obj.Var, identifier, type.struct);
//				}
//				else {
//					errorOccurred = true;
//					System.err.println("GreÅ¡ka na " + var.getLine() + ": promenljiva " + identifier + " je prethodno veÄ‡ deklarisana");
//				}
//				resto = ((VarDeclarations) list).getVarDeclList();
//			}
//		}
//		else if(list instanceof VarDeclaration) {
//			Var var = ((VarDeclaration)list).getVar();
//			String identifier = ASTUtils.getAbstractNodeIdentifier(var);
//			Obj node = Tab.find(identifier);
//			if (node == Tab.noObj) {
//				Tab.insert(Obj.Var, identifier, type.struct);
//			}
//			else {
//				errorOccurred = true;
//				System.err.println("GreÅ¡ka na " + var.getLine() + ": promenljiva " + identifier + " je prethodno veÄ‡ deklarisana");
//			}
//		}
	}
	
	@Override
	public void visit(TypeIdentifier type) {
		Obj node = Tab.find(type.getI1());
		if (node == Tab.noObj || node.getKind() != Obj.Type) {
			errorOccurred = true;
			System.err.println("GreÅ¡ka na " + type.getLine() + ": " + type.getI1() + " nije primitivan niti definisan tip");
		}
		else {
			type.struct = node.getType();
			varType = type.struct;
		}
	}
	
	@Override
	public void visit(FactorTerm FactorTerm) {
		if (FactorTerm.getFactor().struct != null && FactorTerm.getFactor().struct.getKind() == Struct.Array) {
			FactorTerm.struct = FactorTerm.getFactor().struct.getElemType();
		}
		else {
			FactorTerm.struct = FactorTerm.getFactor().struct;
		}
	}
	
	@Override
	public void visit(DesignatorFactor DesignatorFactor) {
		String identifier = ASTUtils.getAbstractNodeIdentifier((Designator)(DesignatorFactor.getDesignator()));
		Obj node = Tab.find(identifier);
		if (node == Tab.noObj) {
//			errorOccurred = true;
//			System.err.println("GreÅ¡ka na " + DesignatorFactor.getLine() + " promenljiva " + identifier + " nije deklarisana");
		}
		else {
			DesignatorFactor.struct = node.getType();
			DesignatorFactor.getDesignator().obj = node;
		}
	}
	
	@Override
	public void visit(DesignatorFunctionFactor DesignatorFunctionFactor) {
		String identifier = ASTUtils.getAbstractNodeIdentifier(DesignatorFunctionFactor.getDesignator());
		Obj funcNode = Tab.find(identifier);
		if (funcNode.getKind() != Obj.Meth) {
			errorOccurred = true;
			System.err.println("GreÅ¡ka na " + DesignatorFunctionFactor.getLine() + ": pozvana funkcija " + identifier + " nije prethodno definisana");
		}
		else {
			checkParamCount(DesignatorFunctionFactor.getActParamsOpt(), DesignatorFunctionFactor.getLine());			
		}
		DesignatorFunctionFactor.struct = funcNode.getType();
	}
	
	@Override
	public void visit(ConstFactor ConstFactor) {
		TypeConst type = ConstFactor.getTypeConst();
		if (type instanceof BoolRef) {
			ConstFactor.struct = ASTUtils.boolType;
		}
		else if (type instanceof IntRef) {
			ConstFactor.struct = Tab.intType;
			Obj node = Tab.insert(Obj.Con, "", ConstFactor.struct);
			node.setAdr(((IntRef)type).getN1().intValue());
		}
		else if (type instanceof CharRef) {
			ConstFactor.struct = Tab.charType;
		}
		else {
			ConstFactor.struct = Tab.noType;
		}
	}
	
	@Override
	public void visit(NewArgumentFactor NewArgumentFactor) {
		NewArgumentFactor.struct = NewArgumentFactor.getType().struct;
	}
	
	@Override
	public void visit(NewArgumentArrayFactor NewArgumentArrayFactor) {
		if (NewArgumentArrayFactor.getExpr().struct != Tab.intType) {
			errorOccurred = true;
			System.err.println("GreÅ¡ka na " + NewArgumentArrayFactor.getLine() + ": izraz za veliÄ�inu niza mora biti int tipa");
		}
		NewArgumentArrayFactor.struct = NewArgumentArrayFactor.getType().struct;
	}
	
	@Override
	public void visit(TermListExpr TermListExpr) {
		TermList list = TermListExpr.getTermList();
		if (list instanceof AddExpr) {
			TermListExpr.struct = ((AddExpr)list).struct;
		}
		else if (list instanceof TermExpr) {
			TermListExpr.struct = ((TermExpr)list).struct;
		}
	}
	
	@Override
	public void visit(ExprFactor ExprFactor) {
		if (ExprFactor.getExpr().struct.getKind() == Struct.Array) {
			ExprFactor.struct = ExprFactor.getExpr().struct.getElemType();
		}
		else {
			ExprFactor.struct = ExprFactor.getExpr().struct;
		}
	}
	
	@Override
	public void visit(NegativeTermListExpr NegativeTermListExpr) {
		TermList list = NegativeTermListExpr.getTermList();
		if (list instanceof AddExpr) {
			NegativeTermListExpr.struct = ((AddExpr)list).struct;
		}
		else if (list instanceof TermExpr) {
			NegativeTermListExpr.struct = ((TermExpr)list).struct;
		}
		if (NegativeTermListExpr.struct != Tab.intType) {
			errorOccurred = true;
			System.err.println("GreÅ¡ka na " + NegativeTermListExpr.getLine() + ": parametar Term u izrazu \"-Term\" mora biti int tipa");
		}
	}
	
	@Override
	public void visit(AddExpr AddExpr) {
		AddExpr.struct = AddExpr.getTerm().struct;
	}
	
	@Override
	public void visit(TermExpr TermExpr) {
		if (TermExpr.getTerm().struct.getKind() == Struct.Array) {
			TermExpr.struct = TermExpr.getTerm().struct.getElemType();
		}
		else {
			TermExpr.struct = TermExpr.getTerm().struct;
		}
	}
	
	@Override
	public void visit(Term term) {
		if (term instanceof MulTerm) {
			term.struct = ((MulTerm)term).struct;
		}
		else if (term instanceof FactorTerm) {
			term.struct = ((FactorTerm)term).struct;
		}
		if (term.struct.getKind() == Struct.Array) {
			term.struct = term.struct.getElemType();
		}
	}
	
	@Override
	public void visit(MulTerm MulTerm) {
		MulTerm.struct = MulTerm.getTerm().struct;
	}
	
	@Override
	public void visit(IntRef IntRef) {
		IntRef.struct = Tab.intType;
	}
	
	@Override
	public void visit(CharRef CharRef) {
		CharRef.struct = Tab.charType;
	}
	
	@Override
	public void visit(BoolRef BoolRef) {
		BoolRef.struct = ASTUtils.boolType;
	}
	
	
	// Error detection
	@Override
	public void visit(ErrorDesignatorStatementSyntax ErrorDesignatorStatementSyntax) {
		errorOccurred = true;
		System.err.println("GreÅ¡ka na " + ErrorDesignatorStatementSyntax.getLine() + ": neispravan izraz za dodelu vrednosti");
	}
	
	@Override
	public void visit(ErrorConstantDeclarationsComma ErrorConstantDeclarationsComma) {
		errorOccurred = true;
		System.err.println("GreÅ¡ka na " + ErrorConstantDeclarationsComma.getLine() + ": neispravno ime konstante");
	}
	
	@Override
	public void visit(ErrorConstDeclaration ErrorConstDeclaration) {
		errorOccurred = true;
		System.err.println("GreÅ¡ka na " + ErrorConstDeclaration.getLine() + ": neispravno ime konstante");
	}
	
	@Override
	public void visit(ErrorVarArrayDeclaration ErrorVarArrayDeclaration) {
		errorOccurred = true;
		System.err.println("GreÅ¡ka na " + ErrorVarArrayDeclaration.getLine() + ": neispravno ime niza");
	}
	
	@Override
	public void visit(ErrorVarDeclaration ErrorVarDeclaration) {
		errorOccurred = true;
		System.err.println("GreÅ¡ka na " + ErrorVarDeclaration.getLine() + ": neispravno ime promenljive");
	}
	
	public String outputNode(Obj node) {
		StringBuffer output = new StringBuffer();
		switch (node.getKind()) {
		case Obj.Con:
			output.append("Con");
			break;
		case Obj.Elem:
			output.append("Elem");
			break;
		case Obj.Fld:
			output.append("Fld");
			break;
		case Obj.Meth:
			output.append("Meth");
			break;
		case Obj.Prog:
			output.append("Prog");
			break;
		case Obj.Type:
			output.append("Type");
			break;
		case Obj.Var:
			output.append("Var");
			break;
		}
		output.append(" ").append(node.getName()).append(": ");
		switch (node.getType().getKind()) {
		case Struct.Array:
			output.append("Array");
			break;
		case Struct.Bool:
			output.append("Bool");
			break;
		case Struct.Char:
			output.append("Char");
			break;
		case Struct.Class:
			output.append("Class");
			break;
		case Struct.Int:
			output.append("Int");
			break;
		case Struct.None:
			output.append("None");
			break;
		}
		output.append(" ").append(node.getLevel()).append(" ");
		output.append(node.getAdr());
		return output.toString();
	}
}
