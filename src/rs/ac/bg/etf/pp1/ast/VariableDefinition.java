// generated with ast extension for cup
// version 0.8
// 16/0/2018 23:39:59


package rs.ac.bg.etf.pp1.ast;

public class VariableDefinition extends VarDecl {

    private Type type;
    private VarDeclList varDeclList;

    public VariableDefinition (Type type, VarDeclList varDeclList) {
        this.type=type;
        if(type!=null) type.setParent(this);
        this.varDeclList=varDeclList;
        if(varDeclList!=null) varDeclList.setParent(this);
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type=type;
    }

    public VarDeclList getVarDeclList() {
        return varDeclList;
    }

    public void setVarDeclList(VarDeclList varDeclList) {
        this.varDeclList=varDeclList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(type!=null) type.accept(visitor);
        if(varDeclList!=null) varDeclList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(type!=null) type.traverseTopDown(visitor);
        if(varDeclList!=null) varDeclList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(type!=null) type.traverseBottomUp(visitor);
        if(varDeclList!=null) varDeclList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("VariableDefinition(\n");

        if(type!=null)
            buffer.append(type.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(varDeclList!=null)
            buffer.append(varDeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [VariableDefinition]");
        return buffer.toString();
    }
}
