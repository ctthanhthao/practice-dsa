package tree;

import java.util.Stack;

public class CandyCrush1D {
    public String solution(String s) {
        Stack<Character> stack = new Stack<>();
        Stack<Integer> occurance = new Stack<>();
        for (int i = 0; i < s.length(); ) {
            char c = s.charAt(i);
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
        while (!stack.isEmpty()) {
            char c = stack.pop();
            int count = occurance.pop();
            while (count > 0) {
                sb.append(c);
                count--;
            }
        }

        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        CandyCrush1D solution = new CandyCrush1D();
        System.out.println(solution.solution("aaabbbc"));
        System.out.println(solution.solution("aabbbacd"));
        System.out.println(solution.solution("aabbccddeeedcba"));
        System.out.println(solution.solution("aaabbbacd"));
    }
}

