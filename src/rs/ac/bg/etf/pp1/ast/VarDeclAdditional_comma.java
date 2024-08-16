// generated with ast extension for cup
// version 0.8
// 28/5/2024 0:53:29


package rs.ac.bg.etf.pp1.ast;

public class VarDeclAdditional_comma extends VarDeclAdditional {

    private VarDecl VarDecl;
    private VarDeclAdditional VarDeclAdditional;

    public VarDeclAdditional_comma (VarDecl VarDecl, VarDeclAdditional VarDeclAdditional) {
        this.VarDecl=VarDecl;
        if(VarDecl!=null) VarDecl.setParent(this);
        this.VarDeclAdditional=VarDeclAdditional;
        if(VarDeclAdditional!=null) VarDeclAdditional.setParent(this);
    }

    public VarDecl getVarDecl() {
        return VarDecl;
    }

    public void setVarDecl(VarDecl VarDecl) {
        this.VarDecl=VarDecl;
    }

    public VarDeclAdditional getVarDeclAdditional() {
        return VarDeclAdditional;
    }

    public void setVarDeclAdditional(VarDeclAdditional VarDeclAdditional) {
        this.VarDeclAdditional=VarDeclAdditional;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(VarDecl!=null) VarDecl.accept(visitor);
        if(VarDeclAdditional!=null) VarDeclAdditional.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(VarDecl!=null) VarDecl.traverseTopDown(visitor);
        if(VarDeclAdditional!=null) VarDeclAdditional.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(VarDecl!=null) VarDecl.traverseBottomUp(visitor);
        if(VarDeclAdditional!=null) VarDeclAdditional.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("VarDeclAdditional_comma(\n");

        if(VarDecl!=null)
            buffer.append(VarDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(VarDeclAdditional!=null)
            buffer.append(VarDeclAdditional.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [VarDeclAdditional_comma]");
        return buffer.toString();
    }
}
