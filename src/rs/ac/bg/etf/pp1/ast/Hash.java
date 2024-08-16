// generated with ast extension for cup
// version 0.8
// 28/5/2024 0:53:30


package rs.ac.bg.etf.pp1.ast;

public class Hash extends Designator {

    private DesignatorNameHash DesignatorNameHash;

    public Hash (DesignatorNameHash DesignatorNameHash) {
        this.DesignatorNameHash=DesignatorNameHash;
        if(DesignatorNameHash!=null) DesignatorNameHash.setParent(this);
    }

    public DesignatorNameHash getDesignatorNameHash() {
        return DesignatorNameHash;
    }

    public void setDesignatorNameHash(DesignatorNameHash DesignatorNameHash) {
        this.DesignatorNameHash=DesignatorNameHash;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(DesignatorNameHash!=null) DesignatorNameHash.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DesignatorNameHash!=null) DesignatorNameHash.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DesignatorNameHash!=null) DesignatorNameHash.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Hash(\n");

        if(DesignatorNameHash!=null)
            buffer.append(DesignatorNameHash.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Hash]");
        return buffer.toString();
    }
}
