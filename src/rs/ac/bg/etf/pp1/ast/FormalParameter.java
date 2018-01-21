// generated with ast extension for cup
// version 0.8
// 16/0/2018 23:39:59


package rs.ac.bg.etf.pp1.ast;

public class FormalParameter extends FormPars {

    private FormalParam formalParam;

    public FormalParameter (FormalParam formalParam) {
        this.formalParam=formalParam;
        if(formalParam!=null) formalParam.setParent(this);
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
        if(formalParam!=null) formalParam.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(formalParam!=null) formalParam.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(formalParam!=null) formalParam.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("FormalParameter(\n");

        if(formalParam!=null)
            buffer.append(formalParam.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [FormalParameter]");
        return buffer.toString();
    }
}
