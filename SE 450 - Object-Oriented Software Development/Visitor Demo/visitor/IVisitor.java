package visitor;

public interface IVisitor {
    int visit(Composite composite);
    int visit(Leaf composite);
}
