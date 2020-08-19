import visitor.CompositeFactory;
import visitor.IComposite;
import visitor.IVisitor;
import visitor.VisitorFactory;

public class Main {

	public static void main(String[] args) {
        IComposite composite = CompositeFactory.BuildComposite();
        IVisitor visitor = VisitorFactory.GetVisitor();
        // What method "accept" is being called? It depends on two different concrete types
        int result = composite.accept(visitor);
        System.out.println(result);
	}

}
