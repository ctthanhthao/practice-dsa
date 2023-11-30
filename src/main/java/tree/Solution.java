package tree;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Solution {

    public static int MaxLength(TreeNode root) {
        if (root == null)
            return 0;
        if (root.left == null && root.right == null) {
            return 1;
        }
        else {
            Set<Integer> results = new HashSet<>();
            traverseAndGetLength(root, 1, results);
            return Collections.max(results);
        }
    }

    private static void traverseAndGetLength(TreeNode node, int i, Set<Integer> results) {
        if (node.left == null && node.right == null) {
            results.add(i);
        }
        else {
            if (node.left != null) {
                traverseAndGetLength(node.left, i + 1, results);
            }
            if (node.right != null) {
                traverseAndGetLength(node.right, i + 1, results);
            }
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode();
        root.left = new TreeNode();
        root.right = new TreeNode();
        root.right.left = new TreeNode();
        root.right.right = new TreeNode();
        root.right.right.right = new TreeNode();
        root.right.right.right.right = new TreeNode();
        root.right.right.right.right.left = new TreeNode();
        System.out.println("max height is " + MaxLength(root));
    }

}
