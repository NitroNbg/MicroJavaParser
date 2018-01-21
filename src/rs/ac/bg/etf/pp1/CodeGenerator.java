package rs.ac.bg.etf.pp1;

import java.util.LinkedList;
import java.util.List;
import rs.ac.bg.etf.pp1.ast.BoolRef;
import rs.ac.bg.etf.pp1.ast.CharRef;
import rs.ac.bg.etf.pp1.ast.ConstFactor;
import rs.ac.bg.etf.pp1.ast.Designator;
import rs.ac.bg.etf.pp1.ast.DesignatorArrayElement;
import rs.ac.bg.etf.pp1.ast.DesignatorExpression;
import rs.ac.bg.etf.pp1.ast.DesignatorFactor;
import rs.ac.bg.etf.pp1.ast.DesignatorFunctionFactor;
import rs.ac.bg.etf.pp1.ast.DesignatorFunctionParams;
import rs.ac.bg.etf.pp1.ast.DesignatorIdent;
import rs.ac.bg.etf.pp1.ast.DesignatorPostDecrement;
import rs.ac.bg.etf.pp1.ast.DesignatorPostIncrement;
import rs.ac.bg.etf.pp1.ast.DesignatorStatementDeclaration;
import rs.ac.bg.etf.pp1.ast.DivExp;
import rs.ac.bg.etf.pp1.ast.EndExp;
import rs.ac.bg.etf.pp1.ast.IntRef;
import rs.ac.bg.etf.pp1.ast.MethodBody;
import rs.ac.bg.etf.pp1.ast.MethodName;
import rs.ac.bg.etf.pp1.ast.MinusOp;
import rs.ac.bg.etf.pp1.ast.ModExp;
import rs.ac.bg.etf.pp1.ast.MulExp;
import rs.ac.bg.etf.pp1.ast.NewArgumentArrayFactor;
import rs.ac.bg.etf.pp1.ast.NewArgumentFactor;
import rs.ac.bg.etf.pp1.ast.PlusOp;
import rs.ac.bg.etf.pp1.ast.PrintStatement;
import rs.ac.bg.etf.pp1.ast.ProgramImpl;
import rs.ac.bg.etf.pp1.ast.ReadStatement;
import rs.ac.bg.etf.pp1.ast.ReturnStatement;
import rs.ac.bg.etf.pp1.ast.ReturnVoidStatement;
import rs.ac.bg.etf.pp1.ast.StartExp;
import rs.ac.bg.etf.pp1.ast.TypeConst;
import rs.ac.bg.etf.pp1.ast.VisitorAdaptor;
import rs.ac.bg.etf.pp1.util.ASTUtils;
import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;

public class CodeGenerator extends VisitorAdaptor {
	private boolean errorOccurred = false;
	private byte[] methodBuffer = null;
	private int methodPointer = -1;
	private String methodName = null;
	private List<RPNParser> arithmeticOpcode = new LinkedList<RPNParser>();
	private int arrayDesignatorPointer = -1;
	private int arrayDeclarationWidth = -1;
	private boolean delayOpStack = false;
	private int currentArithmeticScope = 0;

	public CodeGenerator() {
		arithmeticOpcode.add(new RPNParser());
	}
	
	public boolean isErrorOccurred() {
		return errorOccurred;
	}

	public void setErrorOccurred(boolean errorOccurred) {
		this.errorOccurred = errorOccurred;
	}
	
	@Override
	public void visit(ProgramImpl Program) {
		Code.dataSize = Program.getProgId().obj.getLocalSymbols().size();
	}
	
	@Override
	public void visit(MethodBody methodBody) {
		if (methodBody.getMethodName().getI2().equals("main")) {
			Code.mainPc = Code.pc;
		}
		methodBody.obj.setAdr(Code.pc);
		Code.put(Code.enter);
		Code.put(methodBody.obj.getLevel());
		Code.put(methodBody.obj.getLocalSymbols().size());
		for(int i = 0; i < methodPointer; i++) {
			Code.put(methodBuffer[i]);
		}
		if (methodBody.obj.getType() != Tab.noType) {
			Code.put(Code.trap);
			Code.put(1);
		}
		else {
			Code.put(Code.exit);
			Code.put(Code.return_);
		}
		methodBuffer = null;
		methodPointer = -1;
		methodName = null;
	}
	
	@Override
	public void visit(MethodName MethodName) {
		methodBuffer = new byte[8192];
		methodPointer = 0;
		methodName = MethodName.getI2();
	}
	
	@Override
	public void visit(StartExp StartExp) {
		currentArithmeticScope++;
		arithmeticOpcode.add(new RPNParser());
	}
	
	@Override
	public void visit(EndExp EndExp) {
		arithmeticOpcode.get(currentArithmeticScope--).outputTo(arithmeticOpcode.get(currentArithmeticScope));
		arithmeticOpcode.remove(currentArithmeticScope + 1);
	}
	
	@Override
	public void visit(DesignatorExpression expr) {
		if (currentArithmeticScope == 0) {
			methodPointer = arithmeticOpcode.get(currentArithmeticScope).outputTo(methodBuffer, methodPointer);
		}
	}
	
	@Override
	public void visit(DesignatorStatementDeclaration designatorStatementDeclaration) {
		if (designatorStatementDeclaration.getDesignatorStatementSuffix() instanceof DesignatorPostIncrement) {
			load(designatorStatementDeclaration.getDesignator().obj);
			put(Code.const_1);
			put(Code.add);
			store(designatorStatementDeclaration.getDesignator().obj);
		}
		else if (designatorStatementDeclaration.getDesignatorStatementSuffix() instanceof DesignatorPostDecrement) {
			load(designatorStatementDeclaration.getDesignator().obj);
			put(Code.const_1);
			put(Code.sub);
			store(designatorStatementDeclaration.getDesignator().obj);
		}
		else if (designatorStatementDeclaration.getDesignatorStatementSuffix() instanceof DesignatorFunctionParams) {
			Designator functionDesignator = designatorStatementDeclaration.getDesignator();
			Obj node = functionDesignator.obj;
			if (node == Tab.noObj) {
				errorOccurred = true;
				System.err.println("GreÅ¡ka u generisanju koda - Ne postoji Ä�vor u AST-u za metodu " + ASTUtils.getAbstractNodeIdentifier(functionDesignator));
//				put(Code.exit);
			}
			else {
				put(Code.call);
				if (node.getName().equals(methodName)) {
					//Recursive calls!
					put2(-methodPointer - 2);
				}
				else {
					put2(node.getAdr() - (methodPointer + Code.pc) - 2);					
				}
			}
		}
		else if (designatorStatementDeclaration.getDesignatorStatementSuffix() instanceof DesignatorExpression) {
			if (currentArithmeticScope == 0) {
				arithmeticOpcode.get(currentArithmeticScope).outputTo(methodBuffer, methodPointer);
			}
			if (designatorStatementDeclaration.getDesignator() instanceof DesignatorArrayElement) {
				DesignatorArrayElement array = (DesignatorArrayElement)designatorStatementDeclaration.getDesignator();
				if (array.getDesignator().obj.getType().getElemType() == Tab.intType) {
					put(Code.astore);
				}
				else {
					put(Code.bastore);
				}
			}
			else if (designatorStatementDeclaration.getDesignator() instanceof DesignatorIdent) {
				store(designatorStatementDeclaration.getDesignator().obj);
//				if (designatorStatementDeclaration.getDesignator().obj.getType().getKind() == Struct.Array) {
//					// Discard load array instructions and fix instruction stack
//					for(int i = arrayDesignatorPointer; i < methodPointer - arrayDeclarationWidth; i++) {
//						methodBuffer[i] = methodBuffer[i + arrayDeclarationWidth];
//					}
//					methodPointer = methodPointer - arrayDeclarationWidth;
//				}
			}
		}
	}
	
	@Override
	public void visit(DesignatorIdent DesignatorIdent) {
		if (DesignatorIdent.obj.getType().getKind() == Struct.Array) {
//			arrayDesignatorPointer = methodPointer;
//			if (DesignatorIdent.obj.getLevel() == 0) {
//				arrayDeclarationWidth = 3;
//			}
//			else if (0 <= DesignatorIdent.obj.getAdr() && DesignatorIdent.obj.getAdr() <= 3) {
//				arrayDeclarationWidth = 1;
//			}
//			else {
//				arrayDeclarationWidth = 2;
//			}
//			if (currentArithmeticScope == 0 && arithmeticOpcode.get(currentArithmeticScope).isEmpty()) {
//				arithmeticOpcode.get(currentArithmeticScope).load(DesignatorIdent.obj);
//			}
//			else {
//				currentArithmeticScope++;
//				arithmeticOpcode.add(new RPNParser());
//				arithmeticOpcode.get(currentArithmeticScope).load(DesignatorIdent.obj);
//			}
			if (DesignatorIdent.getParent() instanceof DesignatorArrayElement) {
				arithmeticOpcode.get(currentArithmeticScope).load(DesignatorIdent.obj);
				currentArithmeticScope++;
				arithmeticOpcode.add(new RPNParser());
			}
		}
	}
	
	@Override
	public void visit(DesignatorFactor DesignatorFactor) {
		if (DesignatorFactor.struct.getKind() == Struct.Array) {
			arithmeticOpcode.get(currentArithmeticScope).newToken(DesignatorFactor.struct.getElemType() == Tab.intType ? Code.aload : Code.baload, false);
		}
		else {
			Obj node = DesignatorFactor.getDesignator().obj;
			arithmeticOpcode.get(currentArithmeticScope).load(node);
		}
//		if (delayOpStack) {
//			delayOpStack = false;
//		}
//		else if (!arithmeticOpcode.get(currentArithmeticScope).isEmpty()) {
//			put(arithmeticOpcode.get(currentArithmeticScope).pop());
//		}
	}
	
	@Override
	public void visit(DesignatorFunctionFactor DesignatorFunctionFactor) {
		Obj node = DesignatorFunctionFactor.getDesignator().obj;
		if (node == Tab.noObj) {
			errorOccurred = true;
			System.err.println("GreÅ¡ka u generisanju koda - Ne postoji Ä�vor u AST-u za metodu " + ASTUtils.getAbstractNodeIdentifier(DesignatorFunctionFactor.getDesignator()));
//			put(Code.exit);
		}
		else {
			arithmeticOpcode.get(currentArithmeticScope).newToken(Code.call, false);
			if (node.getName().equals(methodName)) {
				//Recursive calls!
				arithmeticOpcode.get(currentArithmeticScope).callDestination(-methodPointer);
			}
			else {
				arithmeticOpcode.get(currentArithmeticScope).callDestination(node.getAdr() - (methodPointer + Code.pc));
			}
		}
//		if (delayOpStack) {
//			delayOpStack = false;
//		}
//		else if (!arithmeticOpcode.get(currentArithmeticScope).isEmpty()) {
//			put(arithmeticOpcode.get(currentArithmeticScope).pop());
//		}
	}
	
	@Override
	public void visit(DesignatorArrayElement DesignatorArrayElement) {
		arithmeticOpcode.get(currentArithmeticScope--).outputTo(arithmeticOpcode.get(currentArithmeticScope));
		arithmeticOpcode.remove(currentArithmeticScope + 1);
	}
	
	@Override
	public void visit(ConstFactor ConstFactor) {
		TypeConst con = ConstFactor.getTypeConst();
		if (con instanceof BoolRef) {
			if (((BoolRef)con).getB1()) {
				arithmeticOpcode.get(currentArithmeticScope).loadConst(1);
			}
			else {
				arithmeticOpcode.get(currentArithmeticScope).loadConst(0);
			}
		}
		else if (con instanceof IntRef) {
			arithmeticOpcode.get(currentArithmeticScope).loadConst(((IntRef)con).getN1().intValue());
		}
		else if (con instanceof CharRef) {
			arithmeticOpcode.get(currentArithmeticScope).loadConst(((CharRef)con).getC1());
		}
		else {
			errorOccurred = true;
			System.err.println("GreÅ¡ka u generisanju koda - nelegalan tip konstante");
		}
//		if (delayOpStack) {
//			delayOpStack = false;
//		}
//		else if (!arithmeticOpcode.get(currentArithmeticScope).isEmpty()) {
//			put(arithmeticOpcode.get(currentArithmeticScope).pop());
//		}
	}
	
	@Override
	public void visit(NewArgumentFactor NewArgumentFactor) {
		// Currently unsupported
	}
	
	@Override
	public void visit(NewArgumentArrayFactor NewArgumentArrayFactor) {
		methodPointer = arithmeticOpcode.get(0).outputTo(methodBuffer, methodPointer);
		put(Code.newarray);
		if (NewArgumentArrayFactor.getType().struct == Tab.charType) {
			put(0);
		}
		else {
			put(1);
		}
	}
	
	@Override
	public void visit(ReturnStatement ReturnStatement) {
		methodPointer = arithmeticOpcode.get(0).outputTo(methodBuffer, methodPointer);
		put(Code.exit);
		put(Code.return_);
	}
	
	@Override
	public void visit(ReturnVoidStatement ReturnVoidStatement) {
		put(Code.exit);
		put(Code.return_);
	}
	
	@Override
	public void visit(PrintStatement PrintStatement) {
		methodPointer = arithmeticOpcode.get(0).outputTo(methodBuffer, methodPointer);
		if (PrintStatement.getExpr().struct == Tab.intType) {
			loadConst(5);
			put(Code.print);
		}
		else {
			loadConst(1);
			put(Code.bprint);
		}
	}
	
	@Override
	public void visit(ReadStatement ReadStatement) {
		methodPointer = arithmeticOpcode.get(0).outputTo(methodBuffer, methodPointer);
		if (ReadStatement.getDesignator().obj.getType() == Tab.intType) {
			put(Code.read);
		}
		else {
			put(Code.bread);
		}
		store(ReadStatement.getDesignator().obj);
	}
	
	@Override
	public void visit(PlusOp PlusOp) {
		arithmeticOpcode.get(currentArithmeticScope).newToken(Code.add, true);
	}
	
	@Override
	public void visit(MinusOp MinusOp) {
		arithmeticOpcode.get(currentArithmeticScope).newToken(Code.sub, true);
	}
	
	@Override
	public void visit(MulExp MulExp) {
		arithmeticOpcode.get(currentArithmeticScope).newToken(Code.mul, true);
	}
	
	@Override
	public void visit(DivExp DivExp) {
		arithmeticOpcode.get(currentArithmeticScope).newToken(Code.div, true);
	}
	
	@Override
	public void visit(ModExp ModExp) {
		arithmeticOpcode.get(currentArithmeticScope).newToken(Code.rem, true);
	}
	
	private void store(Obj o) {
	  	switch (o.getKind()) {

	      case Obj.Var:
	        if (o.getLevel()==0) { // global variable 
	            put(Code.putstatic); put2(o.getAdr()); 
	            break;
	        }
	        // local variable 
	        if (0 <= o.getAdr() && o.getAdr() <= 3) 
	            put(Code.store_n + o.getAdr());
	        else { 
	        	  put(Code.store); put(o.getAdr()); 
	        } 
	        break;

	      case Obj.Fld:
	        put(Code.putfield); put2(o.getAdr()); 
	        break;
	        
	      case Obj.Elem:
	        if (o.getType().getKind() == Struct.Char) put(Code.bastore);
	        else put(Code.astore); 
	        break;
	      
	      default:
	    	  	errorOccurred = true;
	    	  	System.err.println("GreÅ¡ka: Na levoj strani dodele mora biti promenljiva!");
	    }
	  }
	
	private void load(Obj o) {
		if (methodBuffer == null) {
			Code.load(o);
		}
		else {
			switch (o.getKind()) {
	    	
		      case Obj.Con:
		        if (o.getType() == Tab.nullType) 
		            put(Code.const_n + 0);
		        else 
		            loadConst(o.getAdr()); 
		        break;
		        
		      case Obj.Var:
		        if (o.getLevel()==0) { // global variable 
		        	  put(Code.getstatic); put2(o.getAdr()); 
		        	  break; 
		        }
		        // local variable
		        if (0 <= o.getAdr() && o.getAdr() <= 3) 
		            put(Code.load_n + o.getAdr());
		        else { 
		        	 put(Code.load); put(o.getAdr()); 
		        } 
		        break;
		        
		      case Obj.Fld:
		        put(Code.getfield); put2(o.getAdr()); 
		        break;

		      case Obj.Elem:
		        if (o.getType().getKind() == Struct.Char) put(Code.baload);
		        else put(Code.aload);
		        break;

		      default:
		    	  	errorOccurred = true;
		        System.err.println("GreÅ¡ka: nelegalan operand u Code.load");
		    }
		}
	}
	
	private void loadConst (int n) {
		if (methodBuffer == null) {
			Code.loadConst(n);
			return;
		}
	    if (0<=n&&n<=5) {
	    		put(Code.const_n+n);
	    }
	    else if (n==-1) {
	    		put(Code.const_m1);
	    }
	    else {
	    		put(Code.const_);
	    		put4 (n);
	    	}
	}
	
	private void put(int x) {
		if (methodBuffer == null) {
			Code.put(x);
		}
		else {
			methodBuffer[methodPointer++] = (byte)x;
		}
	}
	
	private void put2(int x) {
		put(x>>8);
		put(x);
	}
	
	private void put4(int x) {
		put2(x>>16);
		put2(x);
	}
}
