// generated with ast extension for cup
// version 0.8
// 28/5/2024 0:53:29


package rs.ac.bg.etf.pp1.ast;

public class ConDeclList implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private Type Type;
    private ConDecl ConDecl;
    private ConDeclAdditional ConDeclAdditional;

    public ConDeclList (Type Type, ConDecl ConDecl, ConDeclAdditional ConDeclAdditional) {
        this.Type=Type;
        if(Type!=null) Type.setParent(this);
        this.ConDecl=ConDecl;
        if(ConDecl!=null) ConDecl.setParent(this);
        this.ConDeclAdditional=ConDeclAdditional;
        if(ConDeclAdditional!=null) ConDeclAdditional.setParent(this);
    }

    public Type getType() {
        return Type;
    }

    public void setType(Type Type) {
        this.Type=Type;
    }

    public ConDecl getConDecl() {
        return ConDecl;
    }

    public void setConDecl(ConDecl ConDecl) {
        this.ConDecl=ConDecl;
    }

    public ConDeclAdditional getConDeclAdditional() {
        return ConDeclAdditional;
    }

    public void setConDeclAdditional(ConDeclAdditional ConDeclAdditional) {
        this.ConDeclAdditional=ConDeclAdditional;
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
        if(Type!=null) Type.accept(visitor);
        if(ConDecl!=null) ConDecl.accept(visitor);
        if(ConDeclAdditional!=null) ConDeclAdditional.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Type!=null) Type.traverseTopDown(visitor);
        if(ConDecl!=null) ConDecl.traverseTopDown(visitor);
        if(ConDeclAdditional!=null) ConDeclAdditional.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Type!=null) Type.traverseBottomUp(visitor);
        if(ConDecl!=null) ConDecl.traverseBottomUp(visitor);
        if(ConDeclAdditional!=null) ConDeclAdditional.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ConDeclList(\n");

        if(Type!=null)
            buffer.append(Type.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ConDecl!=null)
            buffer.append(ConDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ConDeclAdditional!=null)
            buffer.append(ConDeclAdditional.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConDeclList]");
        return buffer.toString();
    }
}
