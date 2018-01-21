// generated with ast extension for cup
// version 0.8
// 16/0/2018 23:40:0


package rs.ac.bg.etf.pp1.ast;

public class AddExpr extends TermList {

    private TermList termList;
    private Addop addop;
    private Term term;

    public AddExpr (TermList termList, Addop addop, Term term) {
        this.termList=termList;
        if(termList!=null) termList.setParent(this);
        this.addop=addop;
        if(addop!=null) addop.setParent(this);
        this.term=term;
        if(term!=null) term.setParent(this);
    }

    public TermList getTermList() {
        return termList;
    }

    public void setTermList(TermList termList) {
        this.termList=termList;
    }

    public Addop getAddop() {
        return addop;
    }

    public void setAddop(Addop addop) {
        this.addop=addop;
    }

    public Term getTerm() {
        return term;
    }

    public void setTerm(Term term) {
        this.term=term;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(termList!=null) termList.accept(visitor);
        if(addop!=null) addop.accept(visitor);
        if(term!=null) term.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(termList!=null) termList.traverseTopDown(visitor);
        if(addop!=null) addop.traverseTopDown(visitor);
        if(term!=null) term.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(termList!=null) termList.traverseBottomUp(visitor);
        if(addop!=null) addop.traverseBottomUp(visitor);
        if(term!=null) term.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("AddExpr(\n");

        if(termList!=null)
            buffer.append(termList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(addop!=null)
            buffer.append(addop.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(term!=null)
            buffer.append(term.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [AddExpr]");
        return buffer.toString();
    }
}
