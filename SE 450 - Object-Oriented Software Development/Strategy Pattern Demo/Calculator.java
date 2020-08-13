public class Calculator {
    private IOperator operator;

    public void setOperator(IOperator operator){
        this.operator = operator;
    }

    public int calculate(int int1, int int2){
        return operator.calculate(int1, int2);
    }
}
