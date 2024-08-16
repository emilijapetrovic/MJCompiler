// generated with ast extension for cup
// version 0.8
// 28/5/2024 0:53:29


package rs.ac.bg.etf.pp1.ast;

public class CondFactListAndc extends CondFactListAnd {

    private CondFactListAnd CondFactListAnd;
    private CondFact CondFact;

    public CondFactListAndc (CondFactListAnd CondFactListAnd, CondFact CondFact) {
        this.CondFactListAnd=CondFactListAnd;
        if(CondFactListAnd!=null) CondFactListAnd.setParent(this);
        this.CondFact=CondFact;
        if(CondFact!=null) CondFact.setParent(this);
    }

    public CondFactListAnd getCondFactListAnd() {
        return CondFactListAnd;
    }

    public void setCondFactListAnd(CondFactListAnd CondFactListAnd) {
        this.CondFactListAnd=CondFactListAnd;
    }

    public CondFact getCondFact() {
        return CondFact;
    }

    public void setCondFact(CondFact CondFact) {
        this.CondFact=CondFact;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(CondFactListAnd!=null) CondFactListAnd.accept(visitor);
        if(CondFact!=null) CondFact.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(CondFactListAnd!=null) CondFactListAnd.traverseTopDown(visitor);
        if(CondFact!=null) CondFact.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(CondFactListAnd!=null) CondFactListAnd.traverseBottomUp(visitor);
        if(CondFact!=null) CondFact.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("CondFactListAndc(\n");

        if(CondFactListAnd!=null)
            buffer.append(CondFactListAnd.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(CondFact!=null)
            buffer.append(CondFact.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [CondFactListAndc]");
        return buffer.toString();
    }
}
