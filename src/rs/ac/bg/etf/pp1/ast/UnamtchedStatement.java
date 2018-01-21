// generated with ast extension for cup
// version 0.8
// 16/0/2018 23:39:59


package rs.ac.bg.etf.pp1.ast;

public class UnamtchedStatement extends Statement {

    private Unmatched unmatched;

    public UnamtchedStatement (Unmatched unmatched) {
        this.unmatched=unmatched;
        if(unmatched!=null) unmatched.setParent(this);
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
        if(unmatched!=null) unmatched.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(unmatched!=null) unmatched.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(unmatched!=null) unmatched.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("UnamtchedStatement(\n");

        if(unmatched!=null)
            buffer.append(unmatched.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [UnamtchedStatement]");
        return buffer.toString();
    }
}
