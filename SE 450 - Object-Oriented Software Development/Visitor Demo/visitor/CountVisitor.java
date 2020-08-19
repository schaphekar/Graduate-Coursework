package visitor;

public class CountVisitor implements IVisitor {
    @Override
    public int visit(Composite composite) {
        int count = 1;
        for(IComposite child : composite._children) {
            count += child.accept(this);
        }

        return count;
    }

    @Override
    public int visit(Leaf composite) {
        return 1;
    }
}
