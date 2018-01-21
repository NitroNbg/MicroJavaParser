// generated with ast extension for cup
// version 0.8
// 16/0/2018 23:40:0


package rs.ac.bg.etf.pp1.ast;

public class VarDeclarations extends VarDeclList {

    private VarDeclList varDeclList;
    private Var var;

    public VarDeclarations (VarDeclList varDeclList, Var var) {
        this.varDeclList=varDeclList;
        if(varDeclList!=null) varDeclList.setParent(this);
        this.var=var;
        if(var!=null) var.setParent(this);
    }

    public VarDeclList getVarDeclList() {
        return varDeclList;
    }

    public void setVarDeclList(VarDeclList varDeclList) {
        this.varDeclList=varDeclList;
    }

    public Var getVar() {
        return var;
    }

    public void setVar(Var var) {
        this.var=var;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(varDeclList!=null) varDeclList.accept(visitor);
        if(var!=null) var.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(varDeclList!=null) varDeclList.traverseTopDown(visitor);
        if(var!=null) var.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(varDeclList!=null) varDeclList.traverseBottomUp(visitor);
        if(var!=null) var.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("VarDeclarations(\n");

        if(varDeclList!=null)
            buffer.append(varDeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(var!=null)
            buffer.append(var.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [VarDeclarations]");
        return buffer.toString();
    }
}
