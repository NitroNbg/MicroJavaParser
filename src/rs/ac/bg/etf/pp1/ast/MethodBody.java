// generated with ast extension for cup
// version 0.8
// 16/0/2018 23:39:59


package rs.ac.bg.etf.pp1.ast;

public class MethodBody extends MethodDecl {

    private MethodName methodName;
    private FormParsOpt formParsOpt;
    private LocalVars localVars;
    private StatementList statementList;

    public MethodBody (MethodName methodName, FormParsOpt formParsOpt, LocalVars localVars, StatementList statementList) {
        this.methodName=methodName;
        if(methodName!=null) methodName.setParent(this);
        this.formParsOpt=formParsOpt;
        if(formParsOpt!=null) formParsOpt.setParent(this);
        this.localVars=localVars;
        if(localVars!=null) localVars.setParent(this);
        this.statementList=statementList;
        if(statementList!=null) statementList.setParent(this);
    }

    public MethodName getMethodName() {
        return methodName;
    }

    public void setMethodName(MethodName methodName) {
        this.methodName=methodName;
    }

    public FormParsOpt getFormParsOpt() {
        return formParsOpt;
    }

    public void setFormParsOpt(FormParsOpt formParsOpt) {
        this.formParsOpt=formParsOpt;
    }

    public LocalVars getLocalVars() {
        return localVars;
    }

    public void setLocalVars(LocalVars localVars) {
        this.localVars=localVars;
    }

    public StatementList getStatementList() {
        return statementList;
    }

    public void setStatementList(StatementList statementList) {
        this.statementList=statementList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(methodName!=null) methodName.accept(visitor);
        if(formParsOpt!=null) formParsOpt.accept(visitor);
        if(localVars!=null) localVars.accept(visitor);
        if(statementList!=null) statementList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(methodName!=null) methodName.traverseTopDown(visitor);
        if(formParsOpt!=null) formParsOpt.traverseTopDown(visitor);
        if(localVars!=null) localVars.traverseTopDown(visitor);
        if(statementList!=null) statementList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(methodName!=null) methodName.traverseBottomUp(visitor);
        if(formParsOpt!=null) formParsOpt.traverseBottomUp(visitor);
        if(localVars!=null) localVars.traverseBottomUp(visitor);
        if(statementList!=null) statementList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MethodBody(\n");

        if(methodName!=null)
            buffer.append(methodName.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(formParsOpt!=null)
            buffer.append(formParsOpt.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(localVars!=null)
            buffer.append(localVars.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(statementList!=null)
            buffer.append(statementList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MethodBody]");
        return buffer.toString();
    }
}
