// generated with ast extension for cup
// version 0.8
// 28/5/2024 0:53:30


package rs.ac.bg.etf.pp1.ast;

public class Init extends FactorX {

    private InitList InitList;

    public Init (InitList InitList) {
        this.InitList=InitList;
        if(InitList!=null) InitList.setParent(this);
    }

    public InitList getInitList() {
        return InitList;
    }

    public void setInitList(InitList InitList) {
        this.InitList=InitList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(InitList!=null) InitList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(InitList!=null) InitList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(InitList!=null) InitList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Init(\n");

        if(InitList!=null)
            buffer.append(InitList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Init]");
        return buffer.toString();
    }
}
