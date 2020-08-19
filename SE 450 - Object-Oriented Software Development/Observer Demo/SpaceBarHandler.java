import java.util.ArrayList;
import java.util.List;

public class SpaceBarHandler implements ISubject, IKeyboardObserver {

    private List<IObserver> observers = new ArrayList<>();

    @Override
    public void registerObserver(IObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(IObserver observer) {
        observers.remove(observer);
    }

    private void notifyObservers(){
        for(IObserver observer : observers)
            observer.update();
    }

    @Override
    public void update(String input) {
        if(input.equals(" "))
            notifyObservers();
    }
}
