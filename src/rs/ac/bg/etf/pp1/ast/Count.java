// generated with ast extension for cup
// version 0.8
// 28/5/2024 0:53:29


package rs.ac.bg.etf.pp1.ast;

public class Count extends SingleStatement {

    private DesignatorNameArr DesignatorNameArr;
    private Expr Expr;

    public Count (DesignatorNameArr DesignatorNameArr, Expr Expr) {
        this.DesignatorNameArr=DesignatorNameArr;
        if(DesignatorNameArr!=null) DesignatorNameArr.setParent(this);
        this.Expr=Expr;
        if(Expr!=null) Expr.setParent(this);
    }

    public DesignatorNameArr getDesignatorNameArr() {
        return DesignatorNameArr;
    }

    public void setDesignatorNameArr(DesignatorNameArr DesignatorNameArr) {
        this.DesignatorNameArr=DesignatorNameArr;
    }

    public Expr getExpr() {
        return Expr;
    }

    public void setExpr(Expr Expr) {
        this.Expr=Expr;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(DesignatorNameArr!=null) DesignatorNameArr.accept(visitor);
        if(Expr!=null) Expr.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DesignatorNameArr!=null) DesignatorNameArr.traverseTopDown(visitor);
        if(Expr!=null) Expr.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DesignatorNameArr!=null) DesignatorNameArr.traverseBottomUp(visitor);
        if(Expr!=null) Expr.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Count(\n");

        if(DesignatorNameArr!=null)
            buffer.append(DesignatorNameArr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Expr!=null)
            buffer.append(Expr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Count]");
        return buffer.toString();
    }
}
