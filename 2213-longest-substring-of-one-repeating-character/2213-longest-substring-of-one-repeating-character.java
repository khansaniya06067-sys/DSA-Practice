class Solution {

    static class Node {
        char leftChar, rightChar;
        int prefix, suffix, best, len;

        Node() {}

        Node(char c) {
            leftChar = rightChar = c;
            prefix = suffix = best = len = 1;
        }
    }

    Node[] tree;

    public int[] longestRepeating(String s, String queryCharacters, int[] queryIndices) {
        int n = s.length();

        tree = new Node[4 * n];
        build(1, 0, n - 1, s);

        int q = queryIndices.length;
        int[] ans = new int[q];

        for (int i = 0; i < q; i++) {
            int index = queryIndices[i];
            char c = queryCharacters.charAt(i);

            update(1, 0, n - 1, index, c);

            ans[i] = tree[1].best;
        }

        return ans;
    }

    private void build(int node, int l, int r, String s) {
        if (l == r) {
            tree[node] = new Node(s.charAt(l));
            return;
        }

        int mid = l + (r - l) / 2;

        build(node * 2, l, mid, s);
        build(node * 2 + 1, mid + 1, r, s);

        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    private void update(int node, int l, int r, int index, char c) {
        if (l == r) {
            tree[node] = new Node(c);
            return;
        }

        int mid = l + (r - l) / 2;

        if (index <= mid) {
            update(node * 2, l, mid, index, c);
        } else {
            update(node * 2 + 1, mid + 1, r, index, c);
        }

        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    private Node merge(Node left, Node right) {
        Node res = new Node();

        res.len = left.len + right.len;
        res.leftChar = left.leftChar;
        res.rightChar = right.rightChar;

        // Prefix
        res.prefix = left.prefix;

        if (left.prefix == left.len &&
            left.rightChar == right.leftChar) {
            res.prefix = left.len + right.prefix;
        }

        // Suffix
        res.suffix = right.suffix;

        if (right.suffix == right.len &&
            left.rightChar == right.leftChar) {
            res.suffix = right.len + left.suffix;
        }

        // Best
        res.best = Math.max(left.best, right.best);

        if (left.rightChar == right.leftChar) {
            res.best = Math.max(
                res.best,
                left.suffix + right.prefix
            );
        }

        return res;
    }
}