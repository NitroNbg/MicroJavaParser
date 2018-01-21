// generated with ast extension for cup
// version 0.8
// 16/0/2018 23:40:0


package rs.ac.bg.etf.pp1.ast;

public class ActualParams extends ActParamsOpt {

    private ActParams actParams;

    public ActualParams (ActParams actParams) {
        this.actParams=actParams;
        if(actParams!=null) actParams.setParent(this);
    }

    public ActParams getActParams() {
        return actParams;
    }

    public void setActParams(ActParams actParams) {
        this.actParams=actParams;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(actParams!=null) actParams.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(actParams!=null) actParams.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(actParams!=null) actParams.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ActualParams(\n");

        if(actParams!=null)
            buffer.append(actParams.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ActualParams]");
        return buffer.toString();
    }
}
