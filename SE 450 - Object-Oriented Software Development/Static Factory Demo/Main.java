import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.InvalidParameterException;
import FactoryDemo.*;
public class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input;
		while(!(input = br.readLine()).toLowerCase().equals("exit")) {
			String shapeType = input;

			IShape shape;

			if (shapeType.toLowerCase().equals("circle")) {
                shape = ShapeFactory.getCircle();
			} else if (shapeType.toLowerCase().equals("rectangle")) {
                shape = ShapeFactory.getRectangle();
            } else if (shapeType.toLowerCase().equals("triangle")) {
                shape = ShapeFactory.getTriangle(false);
            } else {
				throw new InvalidParameterException("Parameter must be the correct shape");
			}

            System.out.println(shape.getNumSides());
		}
	}

}
