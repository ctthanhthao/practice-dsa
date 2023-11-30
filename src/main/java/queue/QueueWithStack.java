package queue;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class QueueWithStack {
    static class MyQueue {
        Stack<Integer> mainSt;
        Stack<Integer> helperSt;
        int firstElement;

        public MyQueue() {
            mainSt = new Stack<>();
            helperSt = new Stack<>();
        }

        public void enqueue(int value) {
            if (mainSt.empty()) {
                firstElement = value;
            }
            mainSt.push(value);
        }

        public void dequeue() throws Exception{
            System.out.println("========= dequeue ==========");
            if (mainSt.empty() && helperSt.empty()) {
                throw new Exception("No element found.");
            } else {
                if (helperSt.empty()) {
                    while (!mainSt.empty()) {
                        helperSt.push(mainSt.pop());
                    }
                }
                System.out.println("dequeue item " + helperSt.pop());
            }
        }

        public void printTop() {
            if (!helperSt.empty()) {
                firstElement = helperSt.peek();
                System.out.println(helperSt.peek());
            } else {
                System.out.println(firstElement);
            }

        }
    }

    public static void handle(List<String> operations) throws Exception {
        MyQueue queue = new MyQueue();
        for (int i = 0; i< operations.size(); i++) {
            String line = operations.get(i);
            String[] ops  = line.split(" ");
            int type = Integer.parseInt(ops[0]);
            switch(type) {
                case 1:
                    queue.enqueue(Integer.parseInt(ops[1]));
                    break;
                case 2:
                    queue.dequeue();
                    break;
                case 3:
                    queue.printTop();
                    break;
                default:
                    break;
            }
        }
    }
    public static void main(String[] args) throws Exception{
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner scanner = new Scanner(System.in);
        int q = Integer.parseInt(scanner.next());
        String line;
        List<String> ops = new ArrayList<>();
        scanner.nextLine();
        for (int i=0; i<q; i++) {
            line = scanner.nextLine();
            ops.add(line);
        }
        handle(ops);
        scanner.close();

    }
}
