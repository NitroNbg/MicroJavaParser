// generated with ast extension for cup
// version 0.8
// 16/0/2018 23:40:0


package rs.ac.bg.etf.pp1.ast;

public class MatchedIf extends Matched {

    private Condition condition;
    private Matched matched;
    private Matched matched1;

    public MatchedIf (Condition condition, Matched matched, Matched matched1) {
        this.condition=condition;
        if(condition!=null) condition.setParent(this);
        this.matched=matched;
        if(matched!=null) matched.setParent(this);
        this.matched1=matched1;
        if(matched1!=null) matched1.setParent(this);
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition=condition;
    }

    public Matched getMatched() {
        return matched;
    }

    public void setMatched(Matched matched) {
        this.matched=matched;
    }

    public Matched getMatched1() {
        return matched1;
    }

    public void setMatched1(Matched matched1) {
        this.matched1=matched1;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(condition!=null) condition.accept(visitor);
        if(matched!=null) matched.accept(visitor);
        if(matched1!=null) matched1.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(condition!=null) condition.traverseTopDown(visitor);
        if(matched!=null) matched.traverseTopDown(visitor);
        if(matched1!=null) matched1.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(condition!=null) condition.traverseBottomUp(visitor);
        if(matched!=null) matched.traverseBottomUp(visitor);
        if(matched1!=null) matched1.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MatchedIf(\n");

        if(condition!=null)
            buffer.append(condition.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(matched!=null)
            buffer.append(matched.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(matched1!=null)
            buffer.append(matched1.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MatchedIf]");
        return buffer.toString();
    }
}
