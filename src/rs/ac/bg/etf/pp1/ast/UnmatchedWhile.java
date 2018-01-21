// generated with ast extension for cup
// version 0.8
// 16/0/2018 23:40:0


package rs.ac.bg.etf.pp1.ast;

public class UnmatchedWhile extends Unmatched {

    private WhileLoopUnmatched whileLoopUnmatched;

    public UnmatchedWhile (WhileLoopUnmatched whileLoopUnmatched) {
        this.whileLoopUnmatched=whileLoopUnmatched;
        if(whileLoopUnmatched!=null) whileLoopUnmatched.setParent(this);
    }

    public WhileLoopUnmatched getWhileLoopUnmatched() {
        return whileLoopUnmatched;
    }

    public void setWhileLoopUnmatched(WhileLoopUnmatched whileLoopUnmatched) {
        this.whileLoopUnmatched=whileLoopUnmatched;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(whileLoopUnmatched!=null) whileLoopUnmatched.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(whileLoopUnmatched!=null) whileLoopUnmatched.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(whileLoopUnmatched!=null) whileLoopUnmatched.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("UnmatchedWhile(\n");

        if(whileLoopUnmatched!=null)
            buffer.append(whileLoopUnmatched.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [UnmatchedWhile]");
        return buffer.toString();
    }
}
