// generated with ast extension for cup
// version 0.8
// 28/5/2024 0:53:29


package rs.ac.bg.etf.pp1.ast;

public class VarDecl_v extends VarDecl {

    private String nameVar;

    public VarDecl_v (String nameVar) {
        this.nameVar=nameVar;
    }

    public String getNameVar() {
        return nameVar;
    }

    public void setNameVar(String nameVar) {
        this.nameVar=nameVar;
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
        buffer.append("VarDecl_v(\n");

        buffer.append(" "+tab+nameVar);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [VarDecl_v]");
        return buffer.toString();
    }
}
