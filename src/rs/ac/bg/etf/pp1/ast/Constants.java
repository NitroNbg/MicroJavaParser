// generated with ast extension for cup
// version 0.8
// 16/0/2018 23:40:0


package rs.ac.bg.etf.pp1.ast;

public class Constants extends ConstDeclarations {

    private ConstDeclarations constDeclarations;
    private ConstDeclValue constDeclValue;

    public Constants (ConstDeclarations constDeclarations, ConstDeclValue constDeclValue) {
        this.constDeclarations=constDeclarations;
        if(constDeclarations!=null) constDeclarations.setParent(this);
        this.constDeclValue=constDeclValue;
        if(constDeclValue!=null) constDeclValue.setParent(this);
    }

    public ConstDeclarations getConstDeclarations() {
        return constDeclarations;
    }

    public void setConstDeclarations(ConstDeclarations constDeclarations) {
        this.constDeclarations=constDeclarations;
    }

    public ConstDeclValue getConstDeclValue() {
        return constDeclValue;
    }

    public void setConstDeclValue(ConstDeclValue constDeclValue) {
        this.constDeclValue=constDeclValue;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(constDeclarations!=null) constDeclarations.accept(visitor);
        if(constDeclValue!=null) constDeclValue.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(constDeclarations!=null) constDeclarations.traverseTopDown(visitor);
        if(constDeclValue!=null) constDeclValue.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(constDeclarations!=null) constDeclarations.traverseBottomUp(visitor);
        if(constDeclValue!=null) constDeclValue.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Constants(\n");

        if(constDeclarations!=null)
            buffer.append(constDeclarations.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(constDeclValue!=null)
            buffer.append(constDeclValue.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Constants]");
        return buffer.toString();
    }
}
