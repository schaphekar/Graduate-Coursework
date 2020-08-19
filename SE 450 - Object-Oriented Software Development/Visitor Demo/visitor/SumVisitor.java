package visitor;

public class SumVisitor implements IVisitor {
    public int visit(Composite composite) {
        int sum = composite._payload;
        for(IComposite child : composite._children) {
            sum += child.accept(this);
        }
        return sum;
    }

    public int visit(Leaf leaf) {
        return leaf._payload;
    }
}
