// generated with ast extension for cup
// version 0.8
// 28/5/2024 0:53:30


package rs.ac.bg.etf.pp1.ast;

public class InitList implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private DesignatorOptional DesignatorOptional;
    private InitListAdditional InitListAdditional;

    public InitList (DesignatorOptional DesignatorOptional, InitListAdditional InitListAdditional) {
        this.DesignatorOptional=DesignatorOptional;
        if(DesignatorOptional!=null) DesignatorOptional.setParent(this);
        this.InitListAdditional=InitListAdditional;
        if(InitListAdditional!=null) InitListAdditional.setParent(this);
    }

    public DesignatorOptional getDesignatorOptional() {
        return DesignatorOptional;
    }

    public void setDesignatorOptional(DesignatorOptional DesignatorOptional) {
        this.DesignatorOptional=DesignatorOptional;
    }

    public InitListAdditional getInitListAdditional() {
        return InitListAdditional;
    }

    public void setInitListAdditional(InitListAdditional InitListAdditional) {
        this.InitListAdditional=InitListAdditional;
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
        if(DesignatorOptional!=null) DesignatorOptional.accept(visitor);
        if(InitListAdditional!=null) InitListAdditional.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DesignatorOptional!=null) DesignatorOptional.traverseTopDown(visitor);
        if(InitListAdditional!=null) InitListAdditional.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DesignatorOptional!=null) DesignatorOptional.traverseBottomUp(visitor);
        if(InitListAdditional!=null) InitListAdditional.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("InitList(\n");

        if(DesignatorOptional!=null)
            buffer.append(DesignatorOptional.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(InitListAdditional!=null)
            buffer.append(InitListAdditional.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [InitList]");
        return buffer.toString();
    }
}
