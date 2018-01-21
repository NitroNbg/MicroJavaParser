// generated with ast extension for cup
// version 0.8
// 16/0/2018 23:40:0


package rs.ac.bg.etf.pp1.ast;

public class GlobalDeclarations extends GlobalList {

    private GlobalList globalList;
    private GlobalDecl globalDecl;

    public GlobalDeclarations (GlobalList globalList, GlobalDecl globalDecl) {
        this.globalList=globalList;
        if(globalList!=null) globalList.setParent(this);
        this.globalDecl=globalDecl;
        if(globalDecl!=null) globalDecl.setParent(this);
    }

    public GlobalList getGlobalList() {
        return globalList;
    }

    public void setGlobalList(GlobalList globalList) {
        this.globalList=globalList;
    }

    public GlobalDecl getGlobalDecl() {
        return globalDecl;
    }

    public void setGlobalDecl(GlobalDecl globalDecl) {
        this.globalDecl=globalDecl;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(globalList!=null) globalList.accept(visitor);
        if(globalDecl!=null) globalDecl.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(globalList!=null) globalList.traverseTopDown(visitor);
        if(globalDecl!=null) globalDecl.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(globalList!=null) globalList.traverseBottomUp(visitor);
        if(globalDecl!=null) globalDecl.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("GlobalDeclarations(\n");

        if(globalList!=null)
            buffer.append(globalList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(globalDecl!=null)
            buffer.append(globalDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [GlobalDeclarations]");
        return buffer.toString();
    }
}
