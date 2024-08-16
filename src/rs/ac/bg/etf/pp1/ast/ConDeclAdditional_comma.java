// generated with ast extension for cup
// version 0.8
// 28/5/2024 0:53:29


package rs.ac.bg.etf.pp1.ast;

public class ConDeclAdditional_comma extends ConDeclAdditional {

    private ConDecl ConDecl;
    private ConDeclAdditional ConDeclAdditional;

    public ConDeclAdditional_comma (ConDecl ConDecl, ConDeclAdditional ConDeclAdditional) {
        this.ConDecl=ConDecl;
        if(ConDecl!=null) ConDecl.setParent(this);
        this.ConDeclAdditional=ConDeclAdditional;
        if(ConDeclAdditional!=null) ConDeclAdditional.setParent(this);
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

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ConDecl!=null) ConDecl.accept(visitor);
        if(ConDeclAdditional!=null) ConDeclAdditional.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ConDecl!=null) ConDecl.traverseTopDown(visitor);
        if(ConDeclAdditional!=null) ConDeclAdditional.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ConDecl!=null) ConDecl.traverseBottomUp(visitor);
        if(ConDeclAdditional!=null) ConDeclAdditional.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ConDeclAdditional_comma(\n");

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
        buffer.append(") [ConDeclAdditional_comma]");
        return buffer.toString();
    }
}
