package tree;

import java.util.Stack;

public class PracticeCandyCrush {

    private static String solution(String s) {
        Stack<Integer> occurance = new Stack<>();
        Stack<Character> stack = new Stack<>();
        char c;
        for (int i = 0; i< s.length();) {
            c = s.charAt(i);
            if (stack.isEmpty() || c != stack.peek()) {
                if (!stack.isEmpty() && occurance.peek() >= 3) {
                    stack.pop();
                    occurance.pop();
                } else {
                    stack.push(c);
                    occurance.push(1);
                    i++;
                }
            } else {
                int count = occurance.pop();
                occurance.push(count + 1);
                i++;
            }
        }
        if (occurance.peek() >= 3) {
            stack.pop();
            occurance.pop();
        }

        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty()) {
            int count = occurance.pop();
            char ch = stack.pop();
            while (count > 0) {
                sb.append(ch);
                count--;
            }
        }
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        PracticeCandyCrush solution = new PracticeCandyCrush();
        System.out.println(solution.solution("aaabbbc"));
        System.out.println(solution.solution("aabbbacd"));
        System.out.println(solution.solution("aabbccddeeedcba"));
        System.out.println(solution.solution("aaabbbacd"));
    }
}
