// generated with ast extension for cup
// version 0.8
// 28/5/2024 0:53:30


package rs.ac.bg.etf.pp1.ast;

public class InitListAdditional_e extends InitListAdditional {

    public InitListAdditional_e () {
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("InitListAdditional_e(\n");

        buffer.append(tab);
        buffer.append(") [InitListAdditional_e]");
        return buffer.toString();
    }
}
