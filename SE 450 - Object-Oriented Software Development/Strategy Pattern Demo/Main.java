
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException{
		System.out.println("Enter operations in the form of \"x + y\" (with spaces)");

		Calculator calc = new Calculator();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input;
        while(!(input = br.readLine()).toLowerCase().equals("exit")) {
        	String[] inputParts = input.split(" ");
        	int int1 = Integer.parseInt(inputParts[0]);
        	String operator = inputParts[1];
        	int int2 = Integer.parseInt(inputParts[2]);

			IOperator operatorStrategy;

			switch(operator) {
				case "+":
					operatorStrategy = new AdditionOperator();
					break;
				case "-":
					operatorStrategy = new SubtractionOperator();
					break;
				default:
					continue;
			}

			calc.setOperator(operatorStrategy);
        	int result = calc.calculate(int1, int2);
        	System.out.println(result);
        }
	}
}
