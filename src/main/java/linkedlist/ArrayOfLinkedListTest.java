package linkedlist;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.*;
import java.util.stream.Collectors;

public class ArrayOfLinkedListTest {

    public static void main(String[] args) {
        LinkedList<Integer> e1 = new LinkedList<>(Arrays.asList(11,23,40));
        LinkedList<Integer> e2 = new LinkedList<>();


        LinkedList<Integer> list= new LinkedList<Integer>();
        list.add(123);
        list.add(12);
        list.add(11);
        list.add(1134);
        System.out.println("LinkedList: "+ list);
        list.removeLastOccurrence(12);
        //Object[] a = list.toArray();
        System.out.print("After remove lst occurrence: " + list);
//        for(Object element : a)
//            System.out.print(element+" ");

        TLinkedList tlist = new TLinkedList();

        // Creating first linked list
        tlist.head1 = new TLinkedList.Node(3);
        tlist.head1.next = new TLinkedList.Node(6);
        tlist.head1.next.next = new TLinkedList.Node(9);
        tlist.head1.next.next.next = new TLinkedList.Node(15);
        tlist.head1.next.next.next.next = new TLinkedList.Node(30);

        // Creating second linked list
        //tlist.head2 = new TLinkedList.Node(10);
        tlist.head2 = new TLinkedList.Node(15);
        tlist.head2.next = new TLinkedList.Node(30);

        System.out.println("The node of intersection is " +
                tlist.getNode().data);

        //
        LinkedList<String>[] llist = new LinkedList[9];
        llist[0] = new LinkedList<String>(Arrays.asList("a", "b", "c"));
        llist[1] = new LinkedList<>(Arrays.asList("d", "b", "c"));
        llist[2] = new LinkedList<>(Arrays.asList("e", "f", "g", "c"));
        llist[3] = new LinkedList<>(Arrays.asList("h"));
        llist[4] = new LinkedList<>(Arrays.asList("i", "k"));
        llist[5] = new LinkedList<>(Arrays.asList("i", "k"));
        llist[6] = new LinkedList<>(Arrays.asList("l", "k"));
        llist[7] = null;
        llist[8] = null;
        Set<ArrayList<Integer>> results = collectGroups(llist);
        results.stream().forEach(System.out::println);

    }

    private static Set<ArrayList<Integer>> collectGroups(LinkedList<String>[] arrays) {
        HashMap<String, ArrayList<Integer>> groupMap = new HashMap<>();
        Set<ArrayList<Integer>> groupSet = new HashSet<>();
        for (int i = 0; i < arrays.length; i++) {
            if (arrays[i] == null || arrays[i].isEmpty()) {
                groupSet.add(new ArrayList<>());
            } else {
                traverseAndAssemble(i, groupMap, arrays[i]);
            }
        }
        if (!groupMap.isEmpty()) {
            groupMap.values().forEach(g -> {
                groupSet.add(g);
            });
        }
        return groupSet;
    }

    private static void traverseAndAssemble(int indexArr, HashMap<String, ArrayList<Integer>> groupMap, LinkedList<String> dataarr) {
        if (dataarr == null || dataarr.isEmpty()) return;
        ArrayList<Integer> indexes;
        String lastElement = dataarr.getLast();
        if (!groupMap.containsKey(lastElement)) {
            indexes = new ArrayList<>();
        } else {
            indexes = groupMap.get(lastElement);
        }
        indexes.add(indexArr);
        groupMap.put(lastElement, indexes);
    }


}
