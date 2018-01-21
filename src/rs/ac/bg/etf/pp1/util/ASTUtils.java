package rs.ac.bg.etf.pp1.util;

import rs.ac.bg.etf.pp1.ast.ActParamsAdjoined;
import rs.ac.bg.etf.pp1.ast.ActParamsOpt;
import rs.ac.bg.etf.pp1.ast.ActualParams;
import rs.ac.bg.etf.pp1.ast.Array;
import rs.ac.bg.etf.pp1.ast.BoolRef;
import rs.ac.bg.etf.pp1.ast.CharRef;
import rs.ac.bg.etf.pp1.ast.Designator;
import rs.ac.bg.etf.pp1.ast.DesignatorArrayElement;
import rs.ac.bg.etf.pp1.ast.DesignatorField;
import rs.ac.bg.etf.pp1.ast.DesignatorFunctionParams;
import rs.ac.bg.etf.pp1.ast.DesignatorIdent;
import rs.ac.bg.etf.pp1.ast.ErrorVarArrayDeclaration;
import rs.ac.bg.etf.pp1.ast.ErrorVarDeclaration;
import rs.ac.bg.etf.pp1.ast.ExprParam;
import rs.ac.bg.etf.pp1.ast.IntRef;
import rs.ac.bg.etf.pp1.ast.NoActualParams;
import rs.ac.bg.etf.pp1.ast.TypeConst;
import rs.ac.bg.etf.pp1.ast.Var;
import rs.ac.bg.etf.pp1.ast.Variable;
import rs.etf.pp1.symboltable.concepts.Struct;

public class ASTUtils {
	public static Struct boolType = new Struct(Struct.Bool);
	
	public static String getAbstractNodeIdentifier(Var node) {
		if (node instanceof Variable) {
			return ((Variable)node).getI1();
		}
		if (node instanceof ErrorVarDeclaration) {
			return ((ErrorVarDeclaration)node).getI1();
		}
		if (node instanceof Array) {
			return ((Array)node).getI1();
		}
		if (node instanceof ErrorVarArrayDeclaration) {
			return ((ErrorVarArrayDeclaration)node).getI1();
		}
		return "unidentifiedNode";
	}
	
	public static String getAbstractNodeIdentifier(Designator node) {
		if (node instanceof DesignatorField) {
			return ((DesignatorField)node).getI2();
		}
		if (node instanceof DesignatorArrayElement) {
			return ASTUtils.getAbstractNodeIdentifier(((DesignatorArrayElement)node).getDesignator());
		}
		if (node instanceof DesignatorIdent) {
			return ((DesignatorIdent)node).getI1();
		}
		return "unidentifiedNode";
	}
	
	public static String getStructIdentifier(Struct struct) {
		if (struct.getKind() == Struct.Int) {
			return "int";
		}
		if (struct.getKind() == Struct.Char) {
			return "char";
		}
		if (struct.getKind() == Struct.Bool) {
			return "bool";
		}
		if (struct.getKind() == Struct.Array) {
			return "array";
		}
		if (struct.getKind() == Struct.Class) {
			return "object";
		}
		return "noType";
	}
	
	public static String getConstantNodeValue(TypeConst node) {
		if (node instanceof BoolRef) {
			return ((BoolRef)node).toString();
		}
		if (node instanceof IntRef) {
			return String.valueOf(((IntRef)node).getN1().intValue());
		}
		if (node instanceof CharRef) {
			return String.valueOf(((CharRef)node).getC1());
		}
		return "null";
	}

	public static int getFunctionParamsType(DesignatorFunctionParams params) {
		ActParamsOpt actParams = params.getActParamsOpt();
		if (actParams instanceof NoActualParams) {
			return Struct.None;
		}
		ActualParams actualParams = (ActualParams) actParams;
		if (actualParams.getActParams() instanceof ActParamsAdjoined) {
			return ((ActParamsAdjoined)actualParams.getActParams()).getExpr().struct.getKind();
		}
		else {
			return ((ExprParam)actualParams.getActParams()).getExpr().struct.getKind();
		}
	}
}
