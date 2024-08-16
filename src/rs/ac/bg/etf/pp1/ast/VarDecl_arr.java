// generated with ast extension for cup
// version 0.8
// 28/5/2024 0:53:29


package rs.ac.bg.etf.pp1.ast;

public class VarDecl_arr extends VarDecl {

    private String nameVarArr;

    public VarDecl_arr (String nameVarArr) {
        this.nameVarArr=nameVarArr;
    }

    public String getNameVarArr() {
        return nameVarArr;
    }

    public void setNameVarArr(String nameVarArr) {
        this.nameVarArr=nameVarArr;
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
        buffer.append("VarDecl_arr(\n");

        buffer.append(" "+tab+nameVarArr);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [VarDecl_arr]");
        return buffer.toString();
    }
}
