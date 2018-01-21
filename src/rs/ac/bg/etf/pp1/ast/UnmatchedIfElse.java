// generated with ast extension for cup
// version 0.8
// 16/0/2018 23:40:0


package rs.ac.bg.etf.pp1.ast;

public class UnmatchedIfElse extends Unmatched {

    private Condition condition;
    private Matched matched;
    private Unmatched unmatched;

    public UnmatchedIfElse (Condition condition, Matched matched, Unmatched unmatched) {
        this.condition=condition;
        if(condition!=null) condition.setParent(this);
        this.matched=matched;
        if(matched!=null) matched.setParent(this);
        this.unmatched=unmatched;
        if(unmatched!=null) unmatched.setParent(this);
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

    public Unmatched getUnmatched() {
        return unmatched;
    }

    public void setUnmatched(Unmatched unmatched) {
        this.unmatched=unmatched;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(condition!=null) condition.accept(visitor);
        if(matched!=null) matched.accept(visitor);
        if(unmatched!=null) unmatched.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(condition!=null) condition.traverseTopDown(visitor);
        if(matched!=null) matched.traverseTopDown(visitor);
        if(unmatched!=null) unmatched.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(condition!=null) condition.traverseBottomUp(visitor);
        if(matched!=null) matched.traverseBottomUp(visitor);
        if(unmatched!=null) unmatched.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("UnmatchedIfElse(\n");

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

        if(unmatched!=null)
            buffer.append(unmatched.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [UnmatchedIfElse]");
        return buffer.toString();
    }
}
