package tree;

public class TrieNode {
    Trie root;

    public TrieNode() {
        root = new Trie();
    }

    public TrieNode insert(int num) {
        int bit;
        Trie node = root;
        for (int i = 31; i >= 0; i--) {
            bit = (num >>> i) & 1;
            if(node.children[bit] == null) {
                node.children[bit] = new Trie();
            }
            node = node.children[bit];
        }
        return this;
    }

    public int findMaxXor(int num) {
        int bit = 0;
        int maxXor = 0;
        Trie node = root;
        for (int i=31; i>=0;i--) {
            bit = (num >>> i) & 1;
            if(node.children[bit^1] != null) {
                maxXor = (maxXor << 1) | 1;
                node = node.children[bit^1];
             }
            else {
                maxXor = maxXor << 1;
                node = node.children[bit];
            }
        }
        return maxXor;
    }
}

class Trie {
    Trie[] children;

    public Trie() {
        children = new Trie[2];
    }

}