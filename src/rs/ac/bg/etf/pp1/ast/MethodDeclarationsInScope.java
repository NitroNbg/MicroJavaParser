// generated with ast extension for cup
// version 0.8
// 16/0/2018 23:40:0


package rs.ac.bg.etf.pp1.ast;

public class MethodDeclarationsInScope extends MethodDeclListOpt {

    private MethodDeclList methodDeclList;

    public MethodDeclarationsInScope (MethodDeclList methodDeclList) {
        this.methodDeclList=methodDeclList;
        if(methodDeclList!=null) methodDeclList.setParent(this);
    }

    public MethodDeclList getMethodDeclList() {
        return methodDeclList;
    }

    public void setMethodDeclList(MethodDeclList methodDeclList) {
        this.methodDeclList=methodDeclList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(methodDeclList!=null) methodDeclList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(methodDeclList!=null) methodDeclList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(methodDeclList!=null) methodDeclList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MethodDeclarationsInScope(\n");

        if(methodDeclList!=null)
            buffer.append(methodDeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MethodDeclarationsInScope]");
        return buffer.toString();
    }
}
