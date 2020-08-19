public interface IKeyboardSubject {
    void registerObserver(IKeyboardObserver observer);
    void removeObserver(IKeyboardObserver observer);
}
