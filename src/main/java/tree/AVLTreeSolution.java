package tree;

import java.io.*;
import java.util.*;
import static java.util.stream.Collectors.toList;
import java.util.stream.*;


class Node {
    int data;
    int height;
    Node left;
    Node right;

    public Node(int value, int h) {
        data = value;
        height = h;
        left = null;
        right = null;
    }
}

public class AVLTreeSolution {

    static int getHeight(Node node) {
        if (node == null) return -1;
        return node.height;
    }

    static int balanceFactor(Node node) {
        if (node == null) return 0;
        return getHeight(node.left) - getHeight(node.right);
    }

    static Node rotateLeft(Node root) {
        Node newNode = root.right;
        root.right = newNode.left;
        newNode.left = root;
        root.height = 1 + Math.max(getHeight(root.left), getHeight(root.right));
        newNode.height = 1 + Math.max(getHeight(newNode.left), getHeight(newNode.right));
        return newNode;
    }

    static Node rotateRight(Node root) {
        Node newNode = root.left;
        root.left = newNode.right;
        newNode.right = root;
        root.height = 1 + Math.max(getHeight(root.left), getHeight(root.right));
        newNode.height = 1 + Math.max(getHeight(newNode.left), getHeight(newNode.right));
        return newNode;
    }

    static Node balance(Node node) {
        if (Math.abs(balanceFactor(node)) <= 1) return node;
        if (balanceFactor(node) > 1){
            if (balanceFactor(node.left) < 0) {
                node.left = rotateLeft(node.left);
            }
            node = rotateRight(node);
        } else {
            if (balanceFactor(node.right) > 0) {
                node.right = rotateRight(node.right);
            }
            node = rotateLeft(node);
        }
        return node;
    }

    static Node insert(Node root, int val) {

        if (root == null) {
            root = new Node(val, 0);
            return root;
        }
        System.out.println("root.data = " + root.data);
        if (root.data > val) {
            root.left = insert(root.left, val);
        } else if (root.data < val) {
            root.right = insert(root.right, val);
        } else {
            return root;
        }
        root.height = 1 + Math.max(getHeight(root.left), getHeight(root.right));
        return balance(root);
    }

    static void printInOrder(Node root, StringJoiner sj) {
        if (root == null) return;
        printInOrder(root.left, sj);
        String s = new String(root.data +"(BF=" + balanceFactor(root)+")");
        sj.add(s);
        printInOrder(root.right, sj);
    }

    static void printPreOrder(Node root, StringJoiner sj) {
        if (root == null) return;
        String s = new String(root.data +"(BF=" + balanceFactor(root)+")");
        sj.add(s);
        printPreOrder(root.left, sj);
        printPreOrder(root.right, sj);
    }

    public static void main(String[] args) throws IOException {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int k = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" ")).map(Integer::parseInt).collect(toList());
        Node root = null;
        for (int i=0; i< arr.size(); i++) {
            System.out.println(arr.get(i));
            root = insert(root, arr.get(i));
        }
        int n = Integer.parseInt(bufferedReader.readLine().trim());
        insert(root, n);
        StringJoiner sj = new StringJoiner(" ");
        printInOrder(root, sj);
        System.out.println(sj.toString());
        sj = new StringJoiner(" ");
        printPreOrder(root,sj);
        System.out.println(sj.toString());
        bufferedReader.close();

    }
}
