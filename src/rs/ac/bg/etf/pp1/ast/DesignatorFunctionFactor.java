// generated with ast extension for cup
// version 0.8
// 16/0/2018 23:39:59


package rs.ac.bg.etf.pp1.ast;

public class DesignatorFunctionFactor extends Factor {

    private Designator designator;
    private ActParamsOpt actParamsOpt;

    public DesignatorFunctionFactor (Designator designator, ActParamsOpt actParamsOpt) {
        this.designator=designator;
        if(designator!=null) designator.setParent(this);
        this.actParamsOpt=actParamsOpt;
        if(actParamsOpt!=null) actParamsOpt.setParent(this);
    }

    public Designator getDesignator() {
        return designator;
    }

    public void setDesignator(Designator designator) {
        this.designator=designator;
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
        if(designator!=null) designator.accept(visitor);
        if(actParamsOpt!=null) actParamsOpt.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(designator!=null) designator.traverseTopDown(visitor);
        if(actParamsOpt!=null) actParamsOpt.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(designator!=null) designator.traverseBottomUp(visitor);
        if(actParamsOpt!=null) actParamsOpt.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesignatorFunctionFactor(\n");

        if(designator!=null)
            buffer.append(designator.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(actParamsOpt!=null)
            buffer.append(actParamsOpt.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesignatorFunctionFactor]");
        return buffer.toString();
    }
}
