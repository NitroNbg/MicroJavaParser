// generated with ast extension for cup
// version 0.8
// 16/0/2018 23:40:0


package rs.ac.bg.etf.pp1.ast;

public class DesignatorFunctionParams extends DesignatorStatementSuffix {

    private ActParamsOpt actParamsOpt;

    public DesignatorFunctionParams (ActParamsOpt actParamsOpt) {
        this.actParamsOpt=actParamsOpt;
        if(actParamsOpt!=null) actParamsOpt.setParent(this);
    }

    public ActParamsOpt getActParamsOpt() {
        return actParamsOpt;
    }

    public void setActParamsOpt(ActParamsOpt actParamsOpt) {
        this.actParamsOpt=actParamsOpt;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(actParamsOpt!=null) actParamsOpt.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(actParamsOpt!=null) actParamsOpt.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(actParamsOpt!=null) actParamsOpt.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesignatorFunctionParams(\n");

        if(actParamsOpt!=null)
            buffer.append(actParamsOpt.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesignatorFunctionParams]");
        return buffer.toString();
    }
}
