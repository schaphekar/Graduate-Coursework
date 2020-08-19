package visitor;

import java.util.ArrayList;

public class Leaf implements IComposite {
	public int _payload;
    public Leaf(int payload) {
        _payload = payload;
    }

    public int accept(IVisitor visitor) {
        return visitor.visit(this);
    }

}
