// generated with ast extension for cup
// version 0.8
// 16/0/2018 23:40:0


package rs.ac.bg.etf.pp1.ast;

public class ConstDeclaration extends ConstDeclValue {

    private String I1;
    private TypeConst typeConst;

    public ConstDeclaration (String I1, TypeConst typeConst) {
        this.I1=I1;
        this.typeConst=typeConst;
        if(typeConst!=null) typeConst.setParent(this);
    }

    public String getI1() {
        return I1;
    }

    public void setI1(String I1) {
        this.I1=I1;
    }

    public TypeConst getTypeConst() {
        return typeConst;
    }

    public void setTypeConst(TypeConst typeConst) {
        this.typeConst=typeConst;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(typeConst!=null) typeConst.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(typeConst!=null) typeConst.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(typeConst!=null) typeConst.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ConstDeclaration(\n");

        buffer.append(" "+tab+I1);
        buffer.append("\n");

        if(typeConst!=null)
            buffer.append(typeConst.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConstDeclaration]");
        return buffer.toString();
    }
}
