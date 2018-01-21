// generated with ast extension for cup
// version 0.8
// 16/0/2018 23:40:0


package rs.ac.bg.etf.pp1.ast;

public class GlobalConst extends GlobalDecl {

    private ConstDecl constDecl;

    public GlobalConst (ConstDecl constDecl) {
        this.constDecl=constDecl;
        if(constDecl!=null) constDecl.setParent(this);
    }

    public ConstDecl getConstDecl() {
        return constDecl;
    }

    public void setConstDecl(ConstDecl constDecl) {
        this.constDecl=constDecl;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(constDecl!=null) constDecl.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(constDecl!=null) constDecl.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(constDecl!=null) constDecl.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("GlobalConst(\n");

        if(constDecl!=null)
            buffer.append(constDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [GlobalConst]");
        return buffer.toString();
    }
}
