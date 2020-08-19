import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) throws Exception {
	    SpaceBarHandler handler = new SpaceBarHandler();

        List<Gamepiece> pieces = new ArrayList<>();

        for(int i = 0; i < 10000; i++) {
            Gamepiece piece = new Gamepiece();
            if(i < 10)
                handler.registerObserver(piece);
        }

        KeyboardCommandHandler keyboardHandler = new KeyboardCommandHandler();

        keyboardHandler.registerObserver(handler);

		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input;
        while(!(input = br.readLine()).toLowerCase().equals("exit")) {
            keyboardHandler.readInput(input);
        }
	}

}
