package tree;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Observable {
    private List<Observer> observerList = new ArrayList<>();

    public void addObserver(Observer observer) {
        observerList.add(observer);
    }

    public void publishEvent(String nameEvent, Date startDate) {
        observerList.stream().forEach(Observer::update);
    }
}
