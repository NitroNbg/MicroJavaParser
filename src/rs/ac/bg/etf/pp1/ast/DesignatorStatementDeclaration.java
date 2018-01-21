// generated with ast extension for cup
// version 0.8
// 16/0/2018 23:39:59


package rs.ac.bg.etf.pp1.ast;

public class DesignatorStatementDeclaration extends DesignatorStatement {

    private Designator designator;
    private DesignatorStatementSuffix designatorStatementSuffix;

    public DesignatorStatementDeclaration (Designator designator, DesignatorStatementSuffix designatorStatementSuffix) {
        this.designator=designator;
        if(designator!=null) designator.setParent(this);
        this.designatorStatementSuffix=designatorStatementSuffix;
        if(designatorStatementSuffix!=null) designatorStatementSuffix.setParent(this);
    }

    public Designator getDesignator() {
        return designator;
    }

    public void setDesignator(Designator designator) {
        this.designator=designator;
    }

    public DesignatorStatementSuffix getDesignatorStatementSuffix() {
        return designatorStatementSuffix;
    }

    public void setDesignatorStatementSuffix(DesignatorStatementSuffix designatorStatementSuffix) {
        this.designatorStatementSuffix=designatorStatementSuffix;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(designator!=null) designator.accept(visitor);
        if(designatorStatementSuffix!=null) designatorStatementSuffix.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(designator!=null) designator.traverseTopDown(visitor);
        if(designatorStatementSuffix!=null) designatorStatementSuffix.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(designator!=null) designator.traverseBottomUp(visitor);
        if(designatorStatementSuffix!=null) designatorStatementSuffix.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesignatorStatementDeclaration(\n");

        if(designator!=null)
            buffer.append(designator.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(designatorStatementSuffix!=null)
            buffer.append(designatorStatementSuffix.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesignatorStatementDeclaration]");
        return buffer.toString();
    }
}
