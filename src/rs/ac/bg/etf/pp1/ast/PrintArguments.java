// generated with ast extension for cup
// version 0.8
// 16/0/2018 23:40:0


package rs.ac.bg.etf.pp1.ast;

public class PrintArguments extends PrintConstArguments {

    private PrintConstArguments printConstArguments;
    private Integer N2;

    public PrintArguments (PrintConstArguments printConstArguments, Integer N2) {
        this.printConstArguments=printConstArguments;
        if(printConstArguments!=null) printConstArguments.setParent(this);
        this.N2=N2;
    }

    public PrintConstArguments getPrintConstArguments() {
        return printConstArguments;
    }

    public void setPrintConstArguments(PrintConstArguments printConstArguments) {
        this.printConstArguments=printConstArguments;
    }

    public Integer getN2() {
        return N2;
    }

    public void setN2(Integer N2) {
        this.N2=N2;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(printConstArguments!=null) printConstArguments.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(printConstArguments!=null) printConstArguments.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(printConstArguments!=null) printConstArguments.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("PrintArguments(\n");

        if(printConstArguments!=null)
            buffer.append(printConstArguments.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(" "+tab+N2);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [PrintArguments]");
        return buffer.toString();
    }
}
