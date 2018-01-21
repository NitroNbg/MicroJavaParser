// generated with ast extension for cup
// version 0.8
// 16/0/2018 23:39:59


package rs.ac.bg.etf.pp1.ast;

public class CondFactRelation extends CondFact {

    private CondFact condFact;
    private Relop relop;
    private Expr expr;

    public CondFactRelation (CondFact condFact, Relop relop, Expr expr) {
        this.condFact=condFact;
        if(condFact!=null) condFact.setParent(this);
        this.relop=relop;
        if(relop!=null) relop.setParent(this);
        this.expr=expr;
        if(expr!=null) expr.setParent(this);
    }

    public CondFact getCondFact() {
        return condFact;
    }

    public void setCondFact(CondFact condFact) {
        this.condFact=condFact;
    }

    public Relop getRelop() {
        return relop;
    }

    public void setRelop(Relop relop) {
        this.relop=relop;
    }

    public Expr getExpr() {
        return expr;
    }

    public void setExpr(Expr expr) {
        this.expr=expr;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(condFact!=null) condFact.accept(visitor);
        if(relop!=null) relop.accept(visitor);
        if(expr!=null) expr.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(condFact!=null) condFact.traverseTopDown(visitor);
        if(relop!=null) relop.traverseTopDown(visitor);
        if(expr!=null) expr.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(condFact!=null) condFact.traverseBottomUp(visitor);
        if(relop!=null) relop.traverseBottomUp(visitor);
        if(expr!=null) expr.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("CondFactRelation(\n");

        if(condFact!=null)
            buffer.append(condFact.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(relop!=null)
            buffer.append(relop.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(expr!=null)
            buffer.append(expr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [CondFactRelation]");
        return buffer.toString();
    }
}
