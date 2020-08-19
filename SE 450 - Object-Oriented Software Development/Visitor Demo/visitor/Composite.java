package visitor;

import java.util.ArrayList;

public class Composite implements IComposite {
    public int _payload;
    public ArrayList<IComposite> _children = new ArrayList<IComposite>();
    public Composite(int payload) {
        _payload = payload;
    }

    public void addChild(IComposite child) {
        _children.add(child);
    }

    public int accept(IVisitor visitor) {
        return visitor.visit(this);
    }
}
