package tree;

import java.util.StringJoiner;

public class RedBlackTreeSolution {
    public static void main(String[] args) {
        // let us try to insert some data into tree and try to visualize the tree as well as traverse.
        RedBlackTree t = new RedBlackTree();
        int[] arr = {7,3,18,10,22,8,11,26,2,6,13};
        System.out.println("Adding items to tree");
        for(int i=0;i<11;i++)
        {
            t.insert(arr[i]);
            t.printTree();
        }
        System.out.println("Now deleting starts.........");
        int[] itemsToBeDeleted = {18,11,2,10,22};
        for (int j=0; j<5;j++) {
            System.out.println("delete " + itemsToBeDeleted[j]);
            t.delete(itemsToBeDeleted[j]);
            t.printTree();
        }
        // you can check colour of any node by with its attribute node.colour
    }
}

class RedBlackTree{

    NodeRB root;

    public RedBlackTree() {
        root = null;
    }

    private NodeRB rotateLeft(NodeRB node) {
        if (node != null) {
            NodeRB tmp = node.right;
            node.right = tmp.left;
            node.right.parent = node;
            tmp.left = node;
            tmp.parent = node.parent;
            if (tmp.parent == null) {
                root = tmp;
            }
            node.parent = tmp;
            node.color = 'R';
            tmp.color = 'B';
            return tmp;
        }
        return null;
    }

    private NodeRB rotateRight(NodeRB node) {
        if (node != null) {
            NodeRB tmp = node.left;
            node.left = tmp.right;
            node.left.parent = node;
            tmp.right = node;
            tmp.parent = node.parent;
            if (tmp.parent == null) {
                root = tmp;
            }
            node.parent = tmp;
            node.color = 'R';
            tmp.color = 'B';
            return tmp;
        }
        return null;
    }

    public NodeRB insert(int val) {
        if (this.root == null) {
            root = new NodeRB(val);
            root.color = 'B';
        } else {
            root = recurInsert(root, val);
        }
        return root;
    }

    public NodeRB delete(int val) {
        if (root == null) return null;
        return recurDelete(root, val);
    }
    
    private NodeRB findReplacedChild(NodeRB node) {
        if (node.left == null && node.right == null) {
            return null;
        }
        return (node.left != null) ? node.left : node.right;
    }
    private NodeRB recurDelete(NodeRB node, int val) {
        if (node.data == val) {
            if (node.left != null && node.right != null) {
                NodeRB minNode = findMinValueNode(node.right);
                // swap node
                int tmp = node.data;
                node.data = minNode.data;
                minNode.data = tmp;
                node.right = recurDelete(node.right, val);
                return node;
            }
            NodeRB replacedChild = findReplacedChild(node);
            NodeRB parent = node.parent;
            if (node.color == 'R' ||
                    (node.color == 'B' && replacedChild!= null && replacedChild.color == 'R')) {
                // just replace node by replacedChild and this is done in below code
            } else {// node is 'B' and (replaceChild is null or replaceChild is 'B') --> double back
                fixDoubleBack(node);
            }
            if (node.isOnLeft()) {
                parent.left = replacedChild;
            } else {
                parent.right = replacedChild;
            }
            if (replacedChild != null) {
                replacedChild.color = 'B';
                replacedChild.parent = parent;
            }
            return replacedChild;
        } 
        if (node.data < val) {
            node.right = recurDelete(node.right, val);
        } else {
            node.left = recurDelete(node.left, val);
        }
        return node;
    }

    private void fixDoubleBack(NodeRB node) {
        NodeRB sibling = node.sibling();
        if (sibling == null && node != root) {
            fixDoubleBack(node.parent);
            return;
        }
        if (sibling.color == 'B') { //
            NodeRB child = (sibling.left != null) ? sibling.left : sibling.right;
            if (child == null) { // sibling has no child
                if (sibling.parent.color == 'R' || sibling.parent == root) {
                    sibling.parent.color = 'B';
                } else {
                    sibling.color = 'R';
                    fixDoubleBack(sibling.parent);
                }
            } else { // sibling B has one R child at least
                if (sibling.isOnLeft()) { // sibling is on the left, there are 2 sub-cases such as Left Right Case and Left Left Case
                    if (sibling.right != null && sibling.right.color == 'R' &&
                            (sibling.left == null || sibling.left.color == 'B')) {
                        sibling = rotateLeft(sibling);
                    }
                    if (sibling.left != null && sibling.left.color == 'R') {
                        sibling.parent = rotateRight(sibling.parent);
                        sibling.left.color = 'B';
                    }
                } else { // sibling is on the right, there are 2 sub-cases such as Right Left Case and Right Right Case
                    if (sibling.left != null && sibling.left.color == 'R' &&
                            (sibling.right == null || sibling.right.color == 'B')) {
                        sibling = rotateRight(sibling);
                    }
                    if (sibling.right != null && sibling.right.color == 'R') {
                        sibling.parent = rotateLeft(sibling.parent);
                        sibling.right.color = 'B';
                    }
                }
            }
        }else { // sibling is R
            NodeRB parent = sibling.parent;
            if (!sibling.isOnLeft()) { // Right case
                parent = rotateLeft(parent);
                parent.color = 'B';
                parent.left.color = 'B';
                if (parent.left.right != null) {
                    parent.left.right.color = 'R';
                }
            } else { // Left Case
                parent = rotateRight(parent);
                parent.color = 'B';
                parent.right.color = 'B';
                if (parent.right.left != null) {
                    parent.right.left.color = 'R';
                }
            }

        }
    }

    private NodeRB findMinValueNode(NodeRB node) {
        NodeRB min = node;
        while (min.left != null) {
            min = min.left;
        }
        return min;
    }

    private NodeRB recurInsert(NodeRB node, int val) {
        if (node == null) {
            node = new NodeRB(val);
            return node;
        }
        if (node.data > val) {
            node.left = recurInsert(node.left, val);
            node.left.parent = node;
        } else if (node.data < val) {
            node.right = recurInsert(node.right, val);
            node.right.parent = node;
        } else {
            return node;
        }
        return recolor(node);
    }

    private NodeRB recolor(NodeRB node) {
        if (node.color == 'R') { // node's color node is R
            if (node == root) {
                node.color = 'B';
                return node;
            }
            NodeRB gp = node.parent;
            NodeRB uncle = (gp.left == node) ? gp.right : gp.left;
            if (uncle != null && uncle.color == 'R') {
                // uncle is red, change color of parent and uncle to B,
                // change grandfather's color to R
                if ((node.left != null && node.left.color == 'R')
                        ||(node.right != null && node.right.color == 'R') ) {
                    uncle.color = 'B';
                    node.color = 'B';
                    gp.color = 'R';
                }
            }
            else {
                // uncle's color is 'B' or null
                // then do rotateLeft or rotateRight to make grandparent, parent and child be on a line
                if (gp.left == node && node.right != null && node.right.color == 'R') {
                    node = rotateLeft(node);
                    node.color = 'R';
                } else if (gp.right == node && node.left != null && node.left.color == 'R') {
                    node = rotateRight(node);
                    node.color = 'R';
                }
            }
        } else {
            // node's color is B, it can have 2 adjacent red nodes. If so, do rotation
            if (node.left != null && node.left.color == 'R'
                    && node.left.left != null && node.left.left.color == 'R') {
                node = rotateRight(node);
            } else if (node.right != null && node.right.color == 'R'
                    && node.right.right != null && node.right.right.color == 'R') {
                node = rotateLeft(node);
            }
        }
        return node;
    }

    public void printTree() {
        StringJoiner sj = new StringJoiner(" ");
        recurPrintInOrder(this.root, sj);
        System.out.println(sj);
    }

    private void recurPrintInOrder(NodeRB node, StringJoiner sj) {
        if (node == null) return;
        recurPrintInOrder(node.left, sj);
        int parent = -1;
        if(node.parent != null) {
            parent = node.parent.data;
        }
        sj.add(node.data + "[color = " + node.color + ", parent = " + parent + "]");
        recurPrintInOrder(node.right, sj);
    }

}

class NodeRB {
    int data;
    char color;
    NodeRB parent;
    NodeRB left;
    NodeRB right;

    public NodeRB(int d) {
        data = d;
        color = 'R';
        left = null;
        right = null;
        parent = null;
    }

    public NodeRB sibling() {
        if (parent.left == this) {
            return parent.right;
        }
        return parent.left;
    }

    public boolean isOnLeft() {
        return this == parent.left;
    }
}