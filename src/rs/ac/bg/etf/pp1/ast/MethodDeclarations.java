// generated with ast extension for cup
// version 0.8
// 16/0/2018 23:40:0


package rs.ac.bg.etf.pp1.ast;

public class MethodDeclarations extends MethodDeclList {

    private MethodDeclList methodDeclList;
    private MethodDecl methodDecl;

    public MethodDeclarations (MethodDeclList methodDeclList, MethodDecl methodDecl) {
        this.methodDeclList=methodDeclList;
        if(methodDeclList!=null) methodDeclList.setParent(this);
        this.methodDecl=methodDecl;
        if(methodDecl!=null) methodDecl.setParent(this);
    }

    public MethodDeclList getMethodDeclList() {
        return methodDeclList;
    }

    public void setMethodDeclList(MethodDeclList methodDeclList) {
        this.methodDeclList=methodDeclList;
    }

    public MethodDecl getMethodDecl() {
        return methodDecl;
    }

    public void setMethodDecl(MethodDecl methodDecl) {
        this.methodDecl=methodDecl;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(methodDeclList!=null) methodDeclList.accept(visitor);
        if(methodDecl!=null) methodDecl.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(methodDeclList!=null) methodDeclList.traverseTopDown(visitor);
        if(methodDecl!=null) methodDecl.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(methodDeclList!=null) methodDeclList.traverseBottomUp(visitor);
        if(methodDecl!=null) methodDecl.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MethodDeclarations(\n");

        if(methodDeclList!=null)
            buffer.append(methodDeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(methodDecl!=null)
            buffer.append(methodDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MethodDeclarations]");
        return buffer.toString();
    }
}
