// generated with ast extension for cup
// version 0.8
// 16/0/2018 23:40:0


package rs.ac.bg.etf.pp1.ast;

public class PrintStatement extends Matched {

    private Expr expr;
    private PrintConstArguments printConstArguments;

    public PrintStatement (Expr expr, PrintConstArguments printConstArguments) {
        this.expr=expr;
        if(expr!=null) expr.setParent(this);
        this.printConstArguments=printConstArguments;
        if(printConstArguments!=null) printConstArguments.setParent(this);
    }

    public Expr getExpr() {
        return expr;
    }

    public void setExpr(Expr expr) {
        this.expr=expr;
    }

    public PrintConstArguments getPrintConstArguments() {
        return printConstArguments;
    }

    public void setPrintConstArguments(PrintConstArguments printConstArguments) {
        this.printConstArguments=printConstArguments;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(expr!=null) expr.accept(visitor);
        if(printConstArguments!=null) printConstArguments.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(expr!=null) expr.traverseTopDown(visitor);
        if(printConstArguments!=null) printConstArguments.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(expr!=null) expr.traverseBottomUp(visitor);
        if(printConstArguments!=null) printConstArguments.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("PrintStatement(\n");

        if(expr!=null)
            buffer.append(expr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(printConstArguments!=null)
            buffer.append(printConstArguments.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [PrintStatement]");
        return buffer.toString();
    }
}
