// generated with ast extension for cup
// version 0.8
// 16/0/2018 23:40:0


package rs.ac.bg.etf.pp1.ast;

public class LocalVariables extends LocalVars {

    private LocalVars localVars;
    private VarDecl varDecl;

    public LocalVariables (LocalVars localVars, VarDecl varDecl) {
        this.localVars=localVars;
        if(localVars!=null) localVars.setParent(this);
        this.varDecl=varDecl;
        if(varDecl!=null) varDecl.setParent(this);
    }

    public LocalVars getLocalVars() {
        return localVars;
    }

    public void setLocalVars(LocalVars localVars) {
        this.localVars=localVars;
    }

    public VarDecl getVarDecl() {
        return varDecl;
    }

    public void setVarDecl(VarDecl varDecl) {
        this.varDecl=varDecl;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(localVars!=null) localVars.accept(visitor);
        if(varDecl!=null) varDecl.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(localVars!=null) localVars.traverseTopDown(visitor);
        if(varDecl!=null) varDecl.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(localVars!=null) localVars.traverseBottomUp(visitor);
        if(varDecl!=null) varDecl.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("LocalVariables(\n");

        if(localVars!=null)
            buffer.append(localVars.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(varDecl!=null)
            buffer.append(varDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [LocalVariables]");
        return buffer.toString();
    }
}
