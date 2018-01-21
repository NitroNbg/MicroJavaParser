// generated with ast extension for cup
// version 0.8
// 16/0/2018 23:39:59


package rs.ac.bg.etf.pp1.ast;

public class ProgramImpl extends Program {

    private ProgId progId;
    private GlobalList globalList;
    private MethodDeclList methodDeclList;

    public ProgramImpl (ProgId progId, GlobalList globalList, MethodDeclList methodDeclList) {
        this.progId=progId;
        if(progId!=null) progId.setParent(this);
        this.globalList=globalList;
        if(globalList!=null) globalList.setParent(this);
        this.methodDeclList=methodDeclList;
        if(methodDeclList!=null) methodDeclList.setParent(this);
    }

    public ProgId getProgId() {
        return progId;
    }

    public void setProgId(ProgId progId) {
        this.progId=progId;
    }

    public GlobalList getGlobalList() {
        return globalList;
    }

    public void setGlobalList(GlobalList globalList) {
        this.globalList=globalList;
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
        if(progId!=null) progId.accept(visitor);
        if(globalList!=null) globalList.accept(visitor);
        if(methodDeclList!=null) methodDeclList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(progId!=null) progId.traverseTopDown(visitor);
        if(globalList!=null) globalList.traverseTopDown(visitor);
        if(methodDeclList!=null) methodDeclList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(progId!=null) progId.traverseBottomUp(visitor);
        if(globalList!=null) globalList.traverseBottomUp(visitor);
        if(methodDeclList!=null) methodDeclList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ProgramImpl(\n");

        if(progId!=null)
            buffer.append(progId.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(globalList!=null)
            buffer.append(globalList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(methodDeclList!=null)
            buffer.append(methodDeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ProgramImpl]");
        return buffer.toString();
    }
}
