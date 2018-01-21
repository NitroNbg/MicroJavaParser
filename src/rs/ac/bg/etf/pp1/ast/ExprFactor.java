// generated with ast extension for cup
// version 0.8
// 16/0/2018 23:39:59


package rs.ac.bg.etf.pp1.ast;

public class ExprFactor extends Factor {

    private OpenExpression openExpression;
    private Expr expr;
    private CloseExpression closeExpression;

    public ExprFactor (OpenExpression openExpression, Expr expr, CloseExpression closeExpression) {
        this.openExpression=openExpression;
        if(openExpression!=null) openExpression.setParent(this);
        this.expr=expr;
        if(expr!=null) expr.setParent(this);
        this.closeExpression=closeExpression;
        if(closeExpression!=null) closeExpression.setParent(this);
    }

    public OpenExpression getOpenExpression() {
        return openExpression;
    }

    public void setOpenExpression(OpenExpression openExpression) {
        this.openExpression=openExpression;
    }

    public Expr getExpr() {
        return expr;
    }

    public void setExpr(Expr expr) {
        this.expr=expr;
    }

    public CloseExpression getCloseExpression() {
        return closeExpression;
    }

    public void setCloseExpression(CloseExpression closeExpression) {
        this.closeExpression=closeExpression;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(openExpression!=null) openExpression.accept(visitor);
        if(expr!=null) expr.accept(visitor);
        if(closeExpression!=null) closeExpression.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(openExpression!=null) openExpression.traverseTopDown(visitor);
        if(expr!=null) expr.traverseTopDown(visitor);
        if(closeExpression!=null) closeExpression.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(openExpression!=null) openExpression.traverseBottomUp(visitor);
        if(expr!=null) expr.traverseBottomUp(visitor);
        if(closeExpression!=null) closeExpression.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ExprFactor(\n");

        if(openExpression!=null)
            buffer.append(openExpression.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(expr!=null)
            buffer.append(expr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(closeExpression!=null)
            buffer.append(closeExpression.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ExprFactor]");
        return buffer.toString();
    }
}
