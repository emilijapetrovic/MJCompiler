// generated with ast extension for cup
// version 0.8
// 28/5/2024 0:53:29


package rs.ac.bg.etf.pp1.ast;

public class Namespace_exists extends NamespaceOptional {

    private NamespaceOptional NamespaceOptional;
    private Namespace Namespace;

    public Namespace_exists (NamespaceOptional NamespaceOptional, Namespace Namespace) {
        this.NamespaceOptional=NamespaceOptional;
        if(NamespaceOptional!=null) NamespaceOptional.setParent(this);
        this.Namespace=Namespace;
        if(Namespace!=null) Namespace.setParent(this);
    }

    public NamespaceOptional getNamespaceOptional() {
        return NamespaceOptional;
    }

    public void setNamespaceOptional(NamespaceOptional NamespaceOptional) {
        this.NamespaceOptional=NamespaceOptional;
    }

    public Namespace getNamespace() {
        return Namespace;
    }

    public void setNamespace(Namespace Namespace) {
        this.Namespace=Namespace;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(NamespaceOptional!=null) NamespaceOptional.accept(visitor);
        if(Namespace!=null) Namespace.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(NamespaceOptional!=null) NamespaceOptional.traverseTopDown(visitor);
        if(Namespace!=null) Namespace.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(NamespaceOptional!=null) NamespaceOptional.traverseBottomUp(visitor);
        if(Namespace!=null) Namespace.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Namespace_exists(\n");

        if(NamespaceOptional!=null)
            buffer.append(NamespaceOptional.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Namespace!=null)
            buffer.append(Namespace.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Namespace_exists]");
        return buffer.toString();
    }
}
