package sort;

import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.lang.annotation.Inherited;
import java.util.ArrayList;
import java.util.List;

public class SelectionSort {
    private List<Integer> numbers;

    public SelectionSort(List<Integer> arr) {
        this.numbers = arr;
    }

    List<Integer> doSort() {
        ArrayList<Integer> result = new ArrayList<>();
       for(int i = 0; i < numbers.size(); i++) {
           MutablePair<Integer, Integer> smallest = new MutablePair<>(i, numbers.get(i));
           for (int j = i + 1; j < numbers.size() - 1; j++) {
               if(smallest.getValue() > numbers.get(j)) {
                   smallest.setRight(numbers.get(j));
                   smallest.setLeft(j);
               }
           }
           if (smallest.getValue() != numbers.get(i)) {
                numbers.set(smallest.getLeft(), numbers.get(i));
           }
           result.add(smallest.getValue());
       }

        return result;
    }

}

interface Closable {
    void close();
}
class File implements Closable {

    public void close() {

    }
}
