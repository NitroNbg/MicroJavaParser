// generated with ast extension for cup
// version 0.8
// 16/0/2018 23:40:0


package rs.ac.bg.etf.pp1.ast;

public class WhileLoopUnmatched implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    public UnmatchedWhile unmatchedwhile = null;

    private Unmatched unmatched;
    private Condition condition;

    public WhileLoopUnmatched (Unmatched unmatched, Condition condition) {
        this.unmatched=unmatched;
        if(unmatched!=null) unmatched.setParent(this);
        this.condition=condition;
        if(condition!=null) condition.setParent(this);
    }

    public Unmatched getUnmatched() {
        return unmatched;
    }

    public void setUnmatched(Unmatched unmatched) {
        this.unmatched=unmatched;
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
        if(unmatched!=null) unmatched.accept(visitor);
        if(condition!=null) condition.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(unmatched!=null) unmatched.traverseTopDown(visitor);
        if(condition!=null) condition.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(unmatched!=null) unmatched.traverseBottomUp(visitor);
        if(condition!=null) condition.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("WhileLoopUnmatched(\n");

        if(unmatched!=null)
            buffer.append(unmatched.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(condition!=null)
            buffer.append(condition.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [WhileLoopUnmatched]");
        return buffer.toString();
    }
}
