package visitor;

public class CompositeFactory {
    public static IComposite BuildComposite() {
    	Composite composite = new Composite(34);
        composite.addChild(new Leaf(22));
        composite.addChild(new Leaf(465));
        composite.addChild(new Leaf(8363));
        Composite composite1 = new Composite(782);
        composite.addChild(composite1);
        composite1.addChild(new Leaf(384));
        composite1.addChild(new Leaf(12));
        composite1.addChild(new Leaf(78));
        composite1.addChild(new Leaf(75));
        Composite composite2 = new Composite(84);
        composite.addChild(composite2);
        composite2.addChild(new Leaf(58));
        composite2.addChild(new Leaf(49));
        return composite;
        //10406
    }
}
