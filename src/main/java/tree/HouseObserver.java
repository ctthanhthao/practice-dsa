package tree;

public class HouseObserver implements Observer {

    public HouseObserver(Observable observable) {
        observable.addObserver(this);
    }

    @Override
    public void update() {
        System.out.println("House update ....");
    }
}
