package tree;

import java.util.Date;

public class TestObserver {

    public static void main(String[] args) {
        Observable obl = new Observable();
        new HouseObserver(obl);
        new BuildingObserver(obl);
        obl.publishEvent("Test Observer Pattern", new Date());
    }
}
