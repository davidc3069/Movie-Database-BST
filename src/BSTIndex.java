public class BSTIndex {

    private Node root;

    private static class Node {
        MovieInfo val;
        Node left;
        Node right;

        Node (MovieInfo val) {
            this.val = val;
        }
        String key() {
            return val.shortName.toLowerCase();
        }
    }

    public BSTIndex() {
        root = null;
    }

    private Node findExactN(String key) {
        key = key.toLowerCase();
        Node x = root;

        while (x != null) {
            int cmp = key.compareTo(x.key());

            if (cmp == 0) {
                return x;
            }
            else if (cmp < 0) {
                x = x.left;
            }
            else {x = x.right;}
        }
        return null;
    }

    public MovieInfo findExact(String key) {
        Node x = findExactN(key);

        if (x == null) {
            return null;
        }
        return x.val;
    }

    private Node findPrefixN(String prefix) {
        prefix = prefix.toLowerCase();
        Node x = root;
        while (x != null) {
            if (x.key().startsWith(prefix)) {
                return x;
            }
            else if (prefix.compareTo(x.key()) < 0) {
                x = x.left;
            }
            else {x = x.right;}
        }
        return null;
    }

    public MovieInfo findPrefix(String prefix) {
        if (prefix.endsWith("*")) {
            prefix = prefix.substring(0, prefix.length() - 1);
        }
        Node x = findPrefixN(prefix);
        if (x == null) {
            return null;
        }
        return x.val;
    }

    private Node insertN(Node x, MovieInfo data) {
        if (x == null) {
            return new Node(data);
        }

        int cmp = data.shortName.toLowerCase().compareTo(x.key());
        if (cmp < 0) {
            x.left = insertN(x.left, data);
        }
        else if (cmp > 0) {
            x.right = insertN(x.right, data);
        }
        else {x.val = data;}
        return x;
    }

    public void insert(MovieInfo data) {
        root = insertN(root, data);
    }
}
