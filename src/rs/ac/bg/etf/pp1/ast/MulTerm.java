// generated with ast extension for cup
// version 0.8
// 16/0/2018 23:39:59


package rs.ac.bg.etf.pp1.ast;

public class MulTerm extends Term {

    private Term term;
    private Mulop mulop;
    private Factor factor;

    public MulTerm (Term term, Mulop mulop, Factor factor) {
        this.term=term;
        if(term!=null) term.setParent(this);
        this.mulop=mulop;
        if(mulop!=null) mulop.setParent(this);
        this.factor=factor;
        if(factor!=null) factor.setParent(this);
    }

    public Term getTerm() {
        return term;
    }

    public void setTerm(Term term) {
        this.term=term;
    }

    public Mulop getMulop() {
        return mulop;
    }

    public void setMulop(Mulop mulop) {
        this.mulop=mulop;
    }

    public Factor getFactor() {
        return factor;
    }

    public void setFactor(Factor factor) {
        this.factor=factor;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(term!=null) term.accept(visitor);
        if(mulop!=null) mulop.accept(visitor);
        if(factor!=null) factor.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(term!=null) term.traverseTopDown(visitor);
        if(mulop!=null) mulop.traverseTopDown(visitor);
        if(factor!=null) factor.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(term!=null) term.traverseBottomUp(visitor);
        if(mulop!=null) mulop.traverseBottomUp(visitor);
        if(factor!=null) factor.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MulTerm(\n");

        if(term!=null)
            buffer.append(term.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(mulop!=null)
            buffer.append(mulop.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(factor!=null)
            buffer.append(factor.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MulTerm]");
        return buffer.toString();
    }
}
