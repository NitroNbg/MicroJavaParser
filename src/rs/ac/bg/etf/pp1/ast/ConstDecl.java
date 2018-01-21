// generated with ast extension for cup
// version 0.8
// 16/0/2018 23:39:59


package rs.ac.bg.etf.pp1.ast;

public class ConstDecl implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private ConstDeclCommonPrefix constDeclCommonPrefix;
    private ConstDeclarations constDeclarations;

    public ConstDecl (ConstDeclCommonPrefix constDeclCommonPrefix, ConstDeclarations constDeclarations) {
        this.constDeclCommonPrefix=constDeclCommonPrefix;
        if(constDeclCommonPrefix!=null) constDeclCommonPrefix.setParent(this);
        this.constDeclarations=constDeclarations;
        if(constDeclarations!=null) constDeclarations.setParent(this);
    }

    public ConstDeclCommonPrefix getConstDeclCommonPrefix() {
        return constDeclCommonPrefix;
    }

    public void setConstDeclCommonPrefix(ConstDeclCommonPrefix constDeclCommonPrefix) {
        this.constDeclCommonPrefix=constDeclCommonPrefix;
    }

    public ConstDeclarations getConstDeclarations() {
        return constDeclarations;
    }

    public void setConstDeclarations(ConstDeclarations constDeclarations) {
        this.constDeclarations=constDeclarations;
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
        if(constDeclCommonPrefix!=null) constDeclCommonPrefix.accept(visitor);
        if(constDeclarations!=null) constDeclarations.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(constDeclCommonPrefix!=null) constDeclCommonPrefix.traverseTopDown(visitor);
        if(constDeclarations!=null) constDeclarations.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(constDeclCommonPrefix!=null) constDeclCommonPrefix.traverseBottomUp(visitor);
        if(constDeclarations!=null) constDeclarations.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ConstDecl(\n");

        if(constDeclCommonPrefix!=null)
            buffer.append(constDeclCommonPrefix.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(constDeclarations!=null)
            buffer.append(constDeclarations.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConstDecl]");
        return buffer.toString();
    }
}
