public class Gamepiece implements IObserver {
    private static int seed = 0;
    private final int id;
    public Gamepiece(){
        id = seed++;
    }

    @Override
    public void update() {
        System.out.println("Character " + id + " jumped!");
    }
}
