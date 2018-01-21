// generated with ast extension for cup
// version 0.8
// 16/0/2018 23:40:0


package rs.ac.bg.etf.pp1.ast;

public class MatchedWhile extends Matched {

    private WhileLoopMatched whileLoopMatched;

    public MatchedWhile (WhileLoopMatched whileLoopMatched) {
        this.whileLoopMatched=whileLoopMatched;
        if(whileLoopMatched!=null) whileLoopMatched.setParent(this);
    }

    public WhileLoopMatched getWhileLoopMatched() {
        return whileLoopMatched;
    }

    public void setWhileLoopMatched(WhileLoopMatched whileLoopMatched) {
        this.whileLoopMatched=whileLoopMatched;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(whileLoopMatched!=null) whileLoopMatched.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(whileLoopMatched!=null) whileLoopMatched.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(whileLoopMatched!=null) whileLoopMatched.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MatchedWhile(\n");

        if(whileLoopMatched!=null)
            buffer.append(whileLoopMatched.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MatchedWhile]");
        return buffer.toString();
    }
}
