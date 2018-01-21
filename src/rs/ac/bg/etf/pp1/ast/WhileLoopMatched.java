// generated with ast extension for cup
// version 0.8
// 16/0/2018 23:40:0


package rs.ac.bg.etf.pp1.ast;

public class WhileLoopMatched implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    public MatchedWhile matchedwhile = null;

    private Matched matched;
    private Condition condition;

    public WhileLoopMatched (Matched matched, Condition condition) {
        this.matched=matched;
        if(matched!=null) matched.setParent(this);
        this.condition=condition;
        if(condition!=null) condition.setParent(this);
    }

    public Matched getMatched() {
        return matched;
    }

    public void setMatched(Matched matched) {
        this.matched=matched;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition=condition;
    }

    public SyntaxNode getParent() {
        return parent;
    }

    public void setParent(SyntaxNode parent) {
        this.parent=parent;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line=line;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(matched!=null) matched.accept(visitor);
        if(condition!=null) condition.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(matched!=null) matched.traverseTopDown(visitor);
        if(condition!=null) condition.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(matched!=null) matched.traverseBottomUp(visitor);
        if(condition!=null) condition.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("WhileLoopMatched(\n");

        if(matched!=null)
            buffer.append(matched.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(condition!=null)
            buffer.append(condition.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [WhileLoopMatched]");
        return buffer.toString();
    }
}
