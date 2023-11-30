package sort;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class TestSort {

    public static int maxCaughtTheifs(List<Character> arr, int k) {
        // Write your code here
        List<Integer> policeMen = new ArrayList<>(); // [2,3,5]
        List<Integer> theives = new ArrayList<>(); // [0,1,4]
        for (int i=0; i< arr.size();i++) {
            if (arr.get(i) == 'T') {
                theives.add(i);
            } else {
                policeMen.add(i);
            }
        }
        if (policeMen.size() == 0 || theives.size() == 0) {
            return 0;
        }
        int indexTheif = 0;
        int numOfCaughtTheives = 0;
        while (policeMen.size() > 0 && theives.size() > indexTheif) {
            if (Math.abs(theives.get(indexTheif) - policeMen.get(0)) <= k) {
                numOfCaughtTheives++;
                policeMen.remove(0);
                theives.remove(indexTheif);
            } else {
                indexTheif++;
            }
        }
        return numOfCaughtTheives;
    }

    private static int recurCountConnected(int i, int j, List<List<Integer>> matrix) {
        if (i < 0 || j < 0 || i >= matrix.size() || j >= matrix.get(i).size() || matrix.get(i).get(j)==0) {
            return 0;
        }
        int count = 0;
        if (matrix.get(i).get(j)==1) {
            count = 1;
            matrix.get(i).set(j, 0);
            System.out.println("Set matrix[" + i + "][" + j+"] " + matrix.get(i).get(j));
            System.out.println("Count = " + count);
            count += recurCountConnected(i-1, j-1, matrix);
            count += recurCountConnected(i-1, j, matrix);
            count += recurCountConnected(i-1, j+1, matrix);
            count += recurCountConnected(i, j-1, matrix);
            count += recurCountConnected(i, j, matrix);
            count += recurCountConnected(i, j+1, matrix);
            count += recurCountConnected(i+1, j-1, matrix);
            count += recurCountConnected(i+1, j, matrix);
            count += recurCountConnected(i+1, j+1, matrix);
        }
        return count;

    }
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int k = Integer.parseInt(bufferedReader.readLine().trim());

        List<Character> arr = new ArrayList<>();
        arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" ")).
                map(c -> c.charAt(0)).collect(Collectors.toList());


        int result = maxCaughtTheifs(arr, k);

        System.out.println(String.valueOf(result));

        bufferedReader.close();
    }
}

class Node{
    Node left,right;
    int data;
    Node(int data){
        this.data=data;
        left=right=null;
    }
}