// generated with ast extension for cup
// version 0.8
// 16/0/2018 23:39:59


package rs.ac.bg.etf.pp1.ast;

public class ClassDefinition extends ClassDecl {

    private String I1;
    private ExtendsClass extendsClass;
    private LocalVars localVars;
    private MethodDeclListOpt methodDeclListOpt;

    public ClassDefinition (String I1, ExtendsClass extendsClass, LocalVars localVars, MethodDeclListOpt methodDeclListOpt) {
        this.I1=I1;
        this.extendsClass=extendsClass;
        if(extendsClass!=null) extendsClass.setParent(this);
        this.localVars=localVars;
        if(localVars!=null) localVars.setParent(this);
        this.methodDeclListOpt=methodDeclListOpt;
        if(methodDeclListOpt!=null) methodDeclListOpt.setParent(this);
    }

    public String getI1() {
        return I1;
    }

    public void setI1(String I1) {
        this.I1=I1;
    }

    public ExtendsClass getExtendsClass() {
        return extendsClass;
    }

    public void setExtendsClass(ExtendsClass extendsClass) {
        this.extendsClass=extendsClass;
    }

    public LocalVars getLocalVars() {
        return localVars;
    }

    public void setLocalVars(LocalVars localVars) {
        this.localVars=localVars;
    }

    public MethodDeclListOpt getMethodDeclListOpt() {
        return methodDeclListOpt;
    }

    public void setMethodDeclListOpt(MethodDeclListOpt methodDeclListOpt) {
        this.methodDeclListOpt=methodDeclListOpt;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(extendsClass!=null) extendsClass.accept(visitor);
        if(localVars!=null) localVars.accept(visitor);
        if(methodDeclListOpt!=null) methodDeclListOpt.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(extendsClass!=null) extendsClass.traverseTopDown(visitor);
        if(localVars!=null) localVars.traverseTopDown(visitor);
        if(methodDeclListOpt!=null) methodDeclListOpt.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(extendsClass!=null) extendsClass.traverseBottomUp(visitor);
        if(localVars!=null) localVars.traverseBottomUp(visitor);
        if(methodDeclListOpt!=null) methodDeclListOpt.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ClassDefinition(\n");

        buffer.append(" "+tab+I1);
        buffer.append("\n");

        if(extendsClass!=null)
            buffer.append(extendsClass.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(localVars!=null)
            buffer.append(localVars.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(methodDeclListOpt!=null)
            buffer.append(methodDeclListOpt.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ClassDefinition]");
        return buffer.toString();
    }
}
