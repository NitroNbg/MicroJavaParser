// generated with ast extension for cup
// version 0.8
// 16/0/2018 23:39:59


package rs.ac.bg.etf.pp1.ast;

public class TermListExpr extends Expr {

    private TermList termList;

    public TermListExpr (TermList termList) {
        this.termList=termList;
        if(termList!=null) termList.setParent(this);
    }

    public TermList getTermList() {
        return termList;
    }

    public void setTermList(TermList termList) {
        this.termList=termList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(termList!=null) termList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(termList!=null) termList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(termList!=null) termList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("TermListExpr(\n");

        if(termList!=null)
            buffer.append(termList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [TermListExpr]");
        return buffer.toString();
    }
}
