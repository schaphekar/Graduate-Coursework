package visitor;

public class VisitorFactory {
    public static IVisitor GetVisitor() {
        return new CountVisitor();
    }
}
