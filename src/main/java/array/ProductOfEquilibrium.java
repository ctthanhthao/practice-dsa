package array;

import java.util.ArrayList;
import java.util.List;

public class ProductOfEquilibrium {

    private static int prodEquilibrium(int [] arr) {
        if (arr.length == 0)
            return -1;
        int leftProduct = 1;
        int rightProduct = 1;
        for (int i =0; i< arr.length;i++) {
            leftProduct*= arr[i];
            for (int j = i+1; j< arr.length; j++) {
                rightProduct*= arr[j];
            }
            if (leftProduct == rightProduct) {
                return i + 1;
            }
            rightProduct = 1;
        }
        return -1;
    }
    private static int prodEquilibrium1(int[] arr) {
        int leftProduct = 1; //arr[0]; //
        int rightProduct = 1; //arr[arr.length - 1]; //
        for (int i = 0; i < arr.length; i++) {
            rightProduct *= arr[i]; // 9 * 1 * 2 * 1 * 2 * 3 * 3 * 5= 81 * 20 = 1620
        }
        for (int l = 0; l < arr.length; l++) { //0 1 2 3 4 5
            leftProduct *= arr[l]; // 9 * 1 =9 * 2=18 * 1=18 * 2 = 36 * 3 = 108
            rightProduct /= arr[l]; // 1620/9 = 180/1 = 180/2=90/1=90/2=45/3=15
            if (leftProduct == rightProduct) {
                return l+1; // 3
            } else if (leftProduct > rightProduct) {
                return -1;
            }
        }
        return -1;
    }
    public static void main(String[] args) {
        int[] nums = new int[] {9,1,2,1,2,3,3,5};
        List<Integer> list = new ArrayList<>();
        System.out.println(" The smallest index which divides array into 2 parts with equal product is " + prodEquilibrium1(nums));
    }
}
