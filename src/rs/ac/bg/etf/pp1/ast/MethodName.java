// generated with ast extension for cup
// version 0.8
// 16/0/2018 23:40:0


package rs.ac.bg.etf.pp1.ast;

public class MethodName implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private TypeVoid typeVoid;
    private String I2;

    public MethodName (TypeVoid typeVoid, String I2) {
        this.typeVoid=typeVoid;
        if(typeVoid!=null) typeVoid.setParent(this);
        this.I2=I2;
    }

    public TypeVoid getTypeVoid() {
        return typeVoid;
    }

    public void setTypeVoid(TypeVoid typeVoid) {
        this.typeVoid=typeVoid;
    }

    public String getI2() {
        return I2;
    }

    public void setI2(String I2) {
        this.I2=I2;
    }

    public SyntaxNode getParent() {
        return parent;
    }

    public void setParent(SyntaxNode parent) {
        this.parent=parent;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line=line;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(typeVoid!=null) typeVoid.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(typeVoid!=null) typeVoid.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(typeVoid!=null) typeVoid.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MethodName(\n");

        if(typeVoid!=null)
            buffer.append(typeVoid.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(" "+tab+I2);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MethodName]");
        return buffer.toString();
    }
}
