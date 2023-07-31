package lab9;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Stack;

/**
 * Implementation of interface Map61B with BST as core data structure.
 *
 * @author Your name here
 */
public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    private class Node {
        /* (K, V) pair stored in this Node. */
        private K key;
        private V value;

        /* Children of this Node. */
        private Node left;
        private Node right;

        private Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    private Node root;  /* Root node of the tree. */
    private int size; /* The number of key-value pairs in the tree */

    /* Creates an empty BSTMap. */
    public BSTMap() {
        this.clear();
    }

    /* Removes all of the mappings from this map. */
    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    /** Returns the value mapped to by KEY in the subtree rooted in P.
     *  or null if this map contains no mapping for the key.
     */
    private V getHelper(K key, Node p) {
        if (p == null) {
            return null;
        }
        if (p.key.compareTo(key) == 0) {
            return p.value;
        } else if (p.key.compareTo(key) > 0) {
            return getHelper(key, p.left);
        } else {
            return getHelper(key, p.right);
        }
    }

    /** Returns the value to which the specified key is mapped, or null if this
     *  map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException("calls get() with a null key");
        }
        return getHelper(key, this.root);
    }

    /** Returns a BSTMap rooted in p with (KEY, VALUE) added as a key-value mapping.
      * Or if p is null, it returns a one node BSTMap containing (KEY, VALUE).
     */
    private Node putHelper(K key, V value, Node p) {
        if (p == null) {
            this.size++;
            return new Node(key, value);
        }
        if (p.key.compareTo(key) == 0) {
            p.value = value;
        } else if (p.key.compareTo(key) > 0) {
            p.left = putHelper(key, value, p.left);
        } else {
            p.right = putHelper(key, value, p.right);
        }
        return p;
    }

    /** Inserts the key KEY
     *  If it is already present, updates value to be VALUE.
     */
    @Override
    public void put(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("calls put() with a null key");
        }
        if (value == null) {
            remove(key);
            return;
        }
        this.root = putHelper(key, value, this.root);
    }

    /* Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return size;
    }

    //////////////// EVERYTHING BELOW THIS LINE IS OPTIONAL ////////////////


    /* Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {
        Set<K> keyset = new HashSet<>();
        addToKeySet(this.root, keyset);
        return keyset;
    }

    private void addToKeySet(Node p, Set<K> keyset) {
        if (p == null) {
            return;
        }
        keyset.add(p.key);
        addToKeySet(p.left, keyset);
        addToKeySet(p.right, keyset);
    }


    /** Removes KEY from the tree if present
     *  returns VALUE removed,
     *  null on failed removal.
     */
    @Override
    public V remove(K key) {
        if (key == null) {
            throw new IllegalArgumentException("calls remove() with a null key");
        }
        if (this.root == null) {
            return null;
        }
        int cmp = this.root.key.compareTo(key);
        if (cmp == 0) {
            Node returnNode = this.root;
            if (this.root.left == null && this.root.right == null) {
                this.root = null;
            } else if (this.root.left == null) {
                this.root = this.root.right;
            } else if (this.root.right == null) {
                this.root = this.root.left;
            } else {
                Node leftNeighbor = findLeftNeighnor(root);
                leftNeighbor.left = root.left;
                leftNeighbor.right = root.right;
                this.root = leftNeighbor;
            }
            return returnNode.value;
        }
        return checkChildren(key, this.root);
    }
    private Node findLeftNeighnor(Node p) {
        Node parent = p;
        Node leftChild = p.left;
        if (leftChild.right == null) {
            parent.left = leftChild.left;
            return leftChild;
        }
        while (leftChild.right != null) {
            parent = leftChild;
            leftChild = leftChild.right;
        }
        parent.right = leftChild.left;
        return leftChild;
    }

    private Node removeLeftChild(Node p) {
        Node returnNode = p.left;
        if (returnNode.left == null) {   //left child has one or no child
            p.left = p.left.right;
        } else if (returnNode.right == null) {
            p.left = p.left.left;
        } else {   //left child has two child
            Node leftNeighbor = findLeftNeighnor(p.left);
            leftNeighbor.left = p.left.left;
            leftNeighbor.right = p.left.right;
            p.left = leftNeighbor;
        }
        return returnNode;
    }

    private Node removeRightChild(Node p) {
        Node returnNode = p.right;
        if (returnNode.left == null) {   //right child has one or no child
            p.right = p.right.right;
        } else if (returnNode.right == null) {
            p.right = p.right.left;
        } else {   //right child has two child
            Node leftNeighbor = findLeftNeighnor(p.right);
            leftNeighbor.left = p.right.left;
            leftNeighbor.right = p.right.right;
            p.right = leftNeighbor;
        }
        return returnNode;
    }

    private V checkChildren(K key, Node p) {
        if (p.left == null && p.right == null) {
            return null;
        }
        if (p.left != null) {   //check left child
            int leftcmp = p.left.key.compareTo(key);
            if (leftcmp == 0) {
                return removeLeftChild(p).value;
            }
        }
        if (p.right != null) {   //check right child
            int rightcmp = p.right.key.compareTo(key);
            if (rightcmp == 0) {
                return removeRightChild(p).value;
            }
        }
        int cmp = p.key.compareTo(key);
        if (cmp > 0 && p.left != null) {
            return checkChildren(key, p.left);
        } else if (cmp < 0 && p.right != null) {
            return checkChildren(key, p.right);
        } else {
            return null;
        }
    }

    @Override
    public V remove(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("calls remove() with a null key");
        }
        if (value == null) {
            throw new IllegalArgumentException("calls remove() with a null value");
        }
        if (this.root == null) {
            return null;
        }
        int cmp = this.root.key.compareTo(key);
        if (cmp == 0) {
            if (this.root.value == value) {
                Node returnNode = this.root;
                if (this.root.left == null && this.root.right == null) {
                    this.root = null;
                } else if (this.root.left == null) {
                    this.root = this.root.right;
                } else if (this.root.right == null) {
                    this.root = this.root.left;
                } else {
                    Node leftNeighbor = findLeftNeighnor(root);
                    leftNeighbor.left = root.left;
                    leftNeighbor.right = root.right;
                    this.root = leftNeighbor;
                }
                return returnNode.value;
            } else {
                return null;
            }
        }
        return checkChildrenWithValue(key, value, this.root);
    }

    private V checkChildrenWithValue(K key, V value, Node p) {
        if (p.left == null && p.right == null) {
            return null;
        }
        if (p.left != null) {
            int leftcmp = p.left.key.compareTo(key);
            if (leftcmp == 0) {
                if (p.left.value == value) {
                    return removeLeftChild(p).value;
                } else {
                    return null;
                }
            }
        }
        if (p.right != null) {
            int rightcmp = p.right.key.compareTo(key);
            if (rightcmp == 0) {
                if (p.right.key == value) {
                    return removeRightChild(p).value;
                } else {
                    return null;
                }
            }
        }
        int cmp = p.key.compareTo(key);
        if (cmp > 0 && p.left != null) {
            return checkChildrenWithValue(key, value, p.left);
        } else if (cmp < 0 && p.right != null) {
            return checkChildrenWithValue(key, value, p.right);
        } else {
            return null;
        }
    }


    @Override
    public Iterator<K> iterator() {
        return new KeyIterator();
    }

    private class KeyIterator implements Iterator<K> {
        private int leftKeys;
        private Stack<Node> parents;

        public KeyIterator() {
            this.leftKeys = size;
            this.parents = new Stack<>();
            goThroughleft(root, this.parents);
        }

        private void goThroughleft(Node ptr, Stack<Node> stack) {
            while (ptr != null) {
                stack.push(ptr);
                ptr = ptr.left;
            }
            Node topNode = stack.peek();
            if (topNode.right != null) {
                goThroughleft(topNode.right, stack);
            }
        }

        public boolean hasNext() {
            return leftKeys != 0;
        }

        public K next() {
            if (leftKeys == 0) {
                throw new IllegalCallerException("no next elements");
            }
            this.leftKeys--;
            Node topNode = this.parents.pop();
            if (!parents.isEmpty()) {
                Node secondNode = this.parents.peek();
                if (secondNode.right != null) {
                    goThroughleft(secondNode.right, this.parents);
                }
            }
            return topNode.key;
        }

    }


}
