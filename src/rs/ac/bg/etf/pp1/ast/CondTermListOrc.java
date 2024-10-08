// generated with ast extension for cup
// version 0.8
// 28/5/2024 0:53:29


package rs.ac.bg.etf.pp1.ast;

public class CondTermListOrc extends CondTermListOr {

    private CondTermListOr CondTermListOr;
    private CondTerm CondTerm;

    public CondTermListOrc (CondTermListOr CondTermListOr, CondTerm CondTerm) {
        this.CondTermListOr=CondTermListOr;
        if(CondTermListOr!=null) CondTermListOr.setParent(this);
        this.CondTerm=CondTerm;
        if(CondTerm!=null) CondTerm.setParent(this);
    }

    public CondTermListOr getCondTermListOr() {
        return CondTermListOr;
    }

    public void setCondTermListOr(CondTermListOr CondTermListOr) {
        this.CondTermListOr=CondTermListOr;
    }

    public CondTerm getCondTerm() {
        return CondTerm;
    }

    public void setCondTerm(CondTerm CondTerm) {
        this.CondTerm=CondTerm;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(CondTermListOr!=null) CondTermListOr.accept(visitor);
        if(CondTerm!=null) CondTerm.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(CondTermListOr!=null) CondTermListOr.traverseTopDown(visitor);
        if(CondTerm!=null) CondTerm.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(CondTermListOr!=null) CondTermListOr.traverseBottomUp(visitor);
        if(CondTerm!=null) CondTerm.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("CondTermListOrc(\n");

        if(CondTermListOr!=null)
            buffer.append(CondTermListOr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(CondTerm!=null)
            buffer.append(CondTerm.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [CondTermListOrc]");
        return buffer.toString();
    }
}
