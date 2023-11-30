package linkedlist;

public class TLinkedList {
    Node head1;
    Node head2;


    static class Node {
        int data;
        Node next;

        public Node(int num) {
            this.data = num;
        }
    }

    int getCount(Node node) {
        if (node == null) return 0;
        Node tmp = node;
        int count = 0;
        while (tmp != null) {
            count ++;
            tmp = tmp.next;
        }
        return count;
    }

    Node getNode() {
        if (head1 == null || head2 == null) {
            return null;
        }

        int count1 = getCount(head1);
        int count2 = getCount(head2);
        int d = 0;
        if (count1 > count2) {
            d = count1 - count2;
            return getIntersectionNode(d, head1, head2);
        } else {
            d = count2 - count1;
            return getIntersectionNode(d, head2, head1);
        }
    }
    private Node getIntersectionNode(int d, Node n1, Node n2) {
        if (n1 == null || n2 == null) {
            return null;
        }
        Node cur1 = n1;
        Node cur2 = n2;
        int count = 0;
        while (count < d) {
            count++;
            cur1 = cur1.next;
        }

        while(cur2 != null & cur1 != null) {
            if (cur1.data == cur2.data) {
                return cur1;
            }
            cur1 = cur1.next;
            cur2 = cur2.next;
        }

        return null;
    }
}
