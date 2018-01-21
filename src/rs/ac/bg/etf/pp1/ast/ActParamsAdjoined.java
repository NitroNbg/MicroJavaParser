// generated with ast extension for cup
// version 0.8
// 16/0/2018 23:39:59


package rs.ac.bg.etf.pp1.ast;

public class ActParamsAdjoined extends ActParams {

    private ActParams actParams;
    private Expr expr;

    public ActParamsAdjoined (ActParams actParams, Expr expr) {
        this.actParams=actParams;
        if(actParams!=null) actParams.setParent(this);
        this.expr=expr;
        if(expr!=null) expr.setParent(this);
    }

    public ActParams getActParams() {
        return actParams;
    }

    public void setActParams(ActParams actParams) {
        this.actParams=actParams;
    }

    public Expr getExpr() {
        return expr;
    }

    public void setExpr(Expr expr) {
        this.expr=expr;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(actParams!=null) actParams.accept(visitor);
        if(expr!=null) expr.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(actParams!=null) actParams.traverseTopDown(visitor);
        if(expr!=null) expr.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(actParams!=null) actParams.traverseBottomUp(visitor);
        if(expr!=null) expr.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ActParamsAdjoined(\n");

        if(actParams!=null)
            buffer.append(actParams.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(expr!=null)
            buffer.append(expr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ActParamsAdjoined]");
        return buffer.toString();
    }
}
