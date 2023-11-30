package tree;

import java.io.*;
import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class Test1 {

    private static int getScore(List<Integer> arr) {
        if (arr.size() == 1) {
            return arr.get(0);
        }
        List<Integer> newArr = new ArrayList<>(arr);
        int totalSum, midSum, rightSum, leftSum;
        int score = 0;
        while(newArr.size() > 1) {
            totalSum = newArr.stream().reduce(0, Integer::sum);
            midSum = totalSum/2;
            leftSum = 0;
            for(int i=0; i < newArr.size(); i++) {
                leftSum+= newArr.get(i);
                if(leftSum > midSum) {
                    if (i > 0) {
                        leftSum = leftSum - newArr.get(i);
                    }
                    System.out.println("leftSum = " + leftSum);
                    rightSum = totalSum - leftSum;
                    System.out.println("rightSum = " + rightSum);
                    score += Math.min(leftSum, rightSum);
                    if (leftSum < rightSum) {
                        newArr = newArr.subList(0, i);
                    } else if (leftSum == rightSum) {
                        System.out.println("========= equal case ============");
                        List<Integer> leftArray = newArr.subList(0, i); // [6,7,7,7] vs [5,5,10,7]
                        List<Integer> rightArray = newArr.subList(i, newArr.size());
                        int leftScore =  getScore(leftArray);
                        System.out.println("left score : " + leftScore);
                        int rightScore = getScore(rightArray);
                        System.out.println("right score : " + rightScore);
                        score = score - leftSum;
                        score += Math.max(leftScore, rightScore);
                        // leftArray [6,7,7] -> getScore(leftArray) = 6
                        // rightArray [10,5,5] -> getScore(rightArray)
                        // -> score = 10, leftArray = [10], -> getScore = 0
                        //                rightArray = [5,5] -> score = 5, leftArray [5] -> 0
                        //                                                  rightArray [5] -> 0 ->
                        newArr.clear();
                    }
                    else {
                       newArr = newArr.subList(i + 1, newArr.size());
                    }
                    System.out.println("New Array is " + newArr);
                    break;
                }
            }
        }
        System.out.println("Score i have is " + score);
        return score;
    }

    private static void generateWellFormedParenthesises(int n) {
        List<StringBuilder> posibilities = new ArrayList<>();
        StringBuilder result = new StringBuilder();
        int o = 0;
        int c = 0;
        recurGenerate(o, c, n, result,posibilities);
        System.out.println(posibilities);
    }

    private static void recurGenerate(int open, int close, int pairs, StringBuilder output, List<StringBuilder> posibilities) {
        if (open == pairs && close == pairs) {
            posibilities.add(output);
            return;
        }

        if (open < pairs) {
            StringBuilder sb = new StringBuilder(output);
            sb.append("(");
            recurGenerate(open + 1, close, pairs, sb, posibilities);
        }
        if (open > close) {
            StringBuilder sb = new StringBuilder(output);
            sb.append(")");
            recurGenerate(open, close + 1, pairs, sb,posibilities);
        }
    }
    private static boolean isomorphic(String s, String t) {
        char[] sA = s.toCharArray();
        char[] tA = t.toCharArray();
        Map<Character, Integer> sMap = new HashMap<>();
        Map<Character, Integer> tMap = new HashMap<>();
        int length = sA.length;
        for (int i=0; i<length; i++) {
            sMap.putIfAbsent(sA[i], i);
            tMap.putIfAbsent(tA[i], i);
            if (!sMap.get(sA[i]).equals(tMap.get(tA[i]))) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        String s = "xyzz";
        String t = "abcx";
        System.out.println(isomorphic(s,t));
        System.out.println("=================================");
        generateWellFormedParenthesises(3);
        List<Integer> list = List.of(6,7,7,7,5,5,10,7);
        System.out.println(getScore(list));
    }
}
