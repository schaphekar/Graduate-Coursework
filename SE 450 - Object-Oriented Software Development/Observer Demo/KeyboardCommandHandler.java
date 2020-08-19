import java.util.ArrayList;
import java.util.List;

public class KeyboardCommandHandler implements IKeyboardSubject {
    List<IKeyboardObserver> observers = new ArrayList<>();

    @Override
    public void registerObserver(IKeyboardObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(IKeyboardObserver observer) {
        observers.remove(observer);
    }

    public void readInput(String input){
        notifyObservers(input);
    }

    private void notifyObservers(String input) {
        for(IKeyboardObserver observer : observers) {
            observer.update(input);
        }
    }
}
