package tree;

public class BuildingObserver implements Observer{

    public BuildingObserver(Observable observable) {
        observable.addObserver(this);
    }

    @Override
    public void update() {
        System.out.println("Building update ...");
    }
}
