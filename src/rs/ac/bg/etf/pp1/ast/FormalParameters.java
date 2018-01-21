// generated with ast extension for cup
// version 0.8
// 16/0/2018 23:39:59


package rs.ac.bg.etf.pp1.ast;

public class FormalParameters extends FormPars {

    private FormPars formPars;
    private FormalParam formalParam;

    public FormalParameters (FormPars formPars, FormalParam formalParam) {
        this.formPars=formPars;
        if(formPars!=null) formPars.setParent(this);
        this.formalParam=formalParam;
        if(formalParam!=null) formalParam.setParent(this);
    }

    public FormPars getFormPars() {
        return formPars;
    }

    public void setFormPars(FormPars formPars) {
        this.formPars=formPars;
    }

    public FormalParam getFormalParam() {
        return formalParam;
    }

    public void setFormalParam(FormalParam formalParam) {
        this.formalParam=formalParam;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(formPars!=null) formPars.accept(visitor);
        if(formalParam!=null) formalParam.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(formPars!=null) formPars.traverseTopDown(visitor);
        if(formalParam!=null) formalParam.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(formPars!=null) formPars.traverseBottomUp(visitor);
        if(formalParam!=null) formalParam.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("FormalParameters(\n");

        if(formPars!=null)
            buffer.append(formPars.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(formalParam!=null)
            buffer.append(formalParam.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [FormalParameters]");
        return buffer.toString();
    }
}
