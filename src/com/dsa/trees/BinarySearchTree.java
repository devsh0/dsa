package com.dsa.trees;

import java.util.List;
import java.util.Stack;

@SuppressWarnings({"WeakerAccess", "unused"})
public class BinarySearchTree<T extends Comparable<T>> {
    Node<T> root;
    private int size;

    public BinarySearchTree() {
        root = null;
    }

    /**
     * Inserts a new node in the tree.
     * <p>
     * The insertion takes cares of duplicates and links them as the
     * right child of the original node. This way they form a linked
     * list down the chain with head pointing to the original node.
     */
    public void insert(T key) {
        size++;

        if (root == null) {
            root = new Node<>(key);
            return;
        }

        Node<T> newNode = new Node<>(key);
        Node<T> current = root;
        Node<T> parent = null;

        while (current != null) {
            parent = current;
            if (current.keyGT(key))
                current = current.left;
            else if (current.keyST(key))
                current = current.right;
            else {
                // make sure all the duplicates form a linked list
                // where the head points to the original node.
                newNode.right = current.right;
                current.right = newNode;
                return;
            }
        }

        // at this point, parent will be pointing to a leaf node
        if (parent.keyGT(key))
            parent.left = newNode;
        else parent.right = newNode;
    }

    @SafeVarargs
    public final void insertAll(T... keys) {
        for (T key:keys)
            insert(key);
    }

    @SafeVarargs
    private void throwExceptionIfTreeIsEmpty(Node<T>... node) {
        Node<T> examine = node.length == 0 ? root : node[0];
        if (examine == null)
            throw new RuntimeException("Tree is empty!");
    }

    void inorderWalkUsingStack() {
        if (root == null)
            return;

        Node<T> current = root;
        Stack<Node<T>> nodes = new Stack<>();

        while (current != null || nodes.size() > 0) {
            while (current != null) {
                nodes.push(current);
                current = current.left;
            }

            current = nodes.pop();
            System.out.print(current.key + ", ");
            current = current.right;
        }

        System.out.println();
    }

    public void inorderCollect(Node<T> root, List<T> nodes) {
        if (root != null) {
            inorderCollect(root.left, nodes);
            nodes.add(root.key);
            inorderCollect(root.right, nodes);
        }
    }

    /**
     * Returns the first node N found in the tree for which
     * N.key == key.
     */
    public Node<T> search(T key) {
        throwExceptionIfTreeIsEmpty();
        Node<T> keyNode = root;
        while (keyNode != null && keyNode.keyNotEquals(key))
            keyNode = keyNode.keyGT(key) ? keyNode.left : keyNode.right;
        return keyNode;
    }

    public Node<T> searchRecursive(Node<T> root, T key) {
        if (root == null || root.keyEquals(key))
            return root;
        if (root.keyGT(key))
            return searchRecursive(root.left, key);
        return searchRecursive(root.right, key);
    }

    /**
     * There are two cases that we should take care of.
     * <p>
     * 1. When the keyNode (N such that N.key = key) has non-
     * empty right subtree, then the minimum of N.right subtree
     * is the successor.
     * </p>
     * <p>
     * 2. When the key node (N such that N.key = key) has empty
     * right subtree, then the successor either does not exist
     * or it's the lowest ancestor X whose left subtree contains the
     * parent of N or N itself.
     * </p>
     */
    public Node<T> getInorderSuccessor(T key) {
        throwExceptionIfTreeIsEmpty();

        Node<T> current = root;
        Node<T> lastLeft = null;

        while (current.keyNotEquals(key)) {
            // We assume that the key exists in the tree
            if (current.keyGT(key)) {
                lastLeft = current;
                // Save the node from where we turned left the last time
                current = current.left;
            } else
                current = current.right;
        }

        if (current.right != null)
            return minimum(current.right);

        return lastLeft;
    }

    /**
     * There are two cases that we should take care of.
     * <p>
     * 1. When the key node (N such that N.key = key) has non-
     * empty left subtree, then the maximum of N.left subtree
     * is the predecessor.
     * </p>
     * <p>
     * 2. When the key node (N such that N.key = key) has
     * empty left subtree, then the predecessor either does not exist
     * or it's the lowest node X whose right subtree contains the parent
     * of N or N itself.
     * </p>
     */
    public Node<T> getInorderPredecessor(T key) {
        throwExceptionIfTreeIsEmpty();
        Node<T> current = root;
        Node<T> lastRight = null;

        while (current.keyNotEquals(key)) {
            // We assume that the key exists in the tree
            if (current.keyGT(key))
                current = current.left;
            else {
                // Save the node from where we turned right the last time
                lastRight = current;
                current = current.right;
            }
        }

        if (current.left != null)
            return maximum(current.left);

        return lastRight;
    }

    /**
     * Maximum is the node with highest key in the tree.
     */
    @SafeVarargs
    final public Node<T> maximum(Node<T>... aSubtree) {
        Node<T> subtree = aSubtree.length == 0 ? root : aSubtree[0];
        throwExceptionIfTreeIsEmpty(subtree);
        while (subtree.right != null)
            subtree = subtree.right;
        return subtree;
    }

    /**
     * Minimum is the node with lowest key in the tree.
     */
    @SafeVarargs
    final public Node<T> minimum(Node<T>... aSubtree) {
        Node<T> subtree = aSubtree.length == 0 ? root : aSubtree[0];
        throwExceptionIfTreeIsEmpty(subtree);
        while (subtree.left != null)
            subtree = subtree.left;
        return subtree;
    }

    private Node<T> minimumRecursive(Node<T> root) {
        if (root.left == null)
            return root;
        return minimumRecursive(root.left);
    }

    private Node<T> maximumRecursive(Node<T> root) {
        if (root.right == null)
            return root;
        return maximumRecursive(root.right);
    }

    /**
     * Aids {@code delete} by searching the parent of the first node
     * N for which N.key == key.
     *
     * <p>Returns null if the required node is the root of this tree.
     * The method is added to save some lines in the already lengthy
     * {@code delete} procedure.</p>
     */
    private Node<T> parentSearchHelper(T key) {
        Node<T> current = root;
        Node<T> parent = null;

        while (current != null && current.keyNotEquals(key)) {
            parent = current;
            current = current.keyGT(key) ? current.left : current.right;
        }

        if (current == null)
            throw new RuntimeException("The key does not exist!");

        return parent;
    }

    /**
     * Aids {@code delete} by cleaning up duplicates, if there are any.
     *
     * <p>Because of the way insertion of duplicates is done, if a
     * node N contains duplicates, they are all present as successive
     * right children of N and they don't have any left pointers. This
     * guarantees if we keep going down the right subtree of N, we will
     * discover all k duplicates of N present in the tree in k steps.</p>
     */
    private void duplicateDeleteHelper(Node<T> keyNode) {
        T key = keyNode.key;
        while (keyNode.right != null && keyNode.right.keyEquals(key)) {
            Node<T> duplicate = keyNode.right;
            keyNode.right = duplicate.right;
            size--;
        }
    }

    /**
     * Given a key and its parent (which may be null, e.g.: in the case
     * when keyNode is root), finds and returns the node containing the
     * key in this tree. The method is added to save some lines in the
     * already lengthy {@code delete} procedure.
     */
    private Node<T> keyNodeSearchHelper(Node<T> parent, T key) {
        if (parent == null)
            return root;
        if (parent.left != null && parent.left.keyEquals(key))
            return parent.left;
        return parent.right;
    }

    /**
     * Deletes nodes with the given key along with its duplicates.
     *
     * <p>We need to handle three cases.
     *  <ol>
     *   <li>
     *    <p>When the node to be deleted is a leaf node:</p>
     *    <p>In this case, all we do is unlink the leaf node from
     *       its parent.</p>
     *   </li>
     *   <li>
     *    <p>When the node to be deleted has only one child:</p>
     *    <p>In this case, we need to replace the node with it's left
     *       or right child.</p>
     *   </li>
     *   <li>
     *    <p>When the node to be deleted has both, left and right children:</p>
     *    <p>In this case, we replace the node with its successor. In the
     *       process of doing so, we will have to take care of two cases.</p>
     *    <ol>
     *     <li>
     *      <p>Successor is the direct right child of node:</p>
     *      <p>In this case, all we do is replace the node with successor,
     *         leaving the successor's right child alone. Note that the
     *         successor won't have any left child because it is the smallest
     *         element in node's right subtree.</p>
     *     </li>
     *     <li>
     *       <p>Successor is not the direct right child of node:</p>
     *       <p>In this case, we first have to replace the successor with its
     *          right child, meaning, we take the right child of successor and
     *          attach it to the successor's parent as its left child. Then we,
     *          replace the node with successor.</p>
     *     </li>
     *    </ol>
     *   </li>
     *     </ol>
     * </p>
     */
    public void delete(T key) {
        if (root == null)
            return;

        // if root is to be deleted, parent will be null
        Node<T> parent = parentSearchHelper(key);
        Node<T> keyNode = keyNodeSearchHelper(parent, key);
        boolean isKeyNodeRoot = parent == null;
        boolean isKeyNodeInLeft = !isKeyNodeRoot && parent.left == keyNode;

        // start by getting rid of the duplicates, if any
        duplicateDeleteHelper(keyNode);
        size -= 1;

        // CASE 1: keyNode is a leaf node
        if (keyNode.left == null && keyNode.right == null) {
            if (isKeyNodeRoot)
                root = null;
            else if (isKeyNodeInLeft)
                parent.left = null;
            else
                parent.right = null;
        }

        // CASE 2a: keyNode has no left child
        else if (keyNode.left == null) {
            if (isKeyNodeRoot)
                root = keyNode.right;
            else if (isKeyNodeInLeft)
                parent.left = keyNode.right;
            else
                parent.right = keyNode.right;
        }

        // CASE 2b: keyNode has no right child
        else if (keyNode.right == null) {
            if (isKeyNodeRoot)
                root = keyNode.left;
            else if (isKeyNodeInLeft)
                parent.left = keyNode.left;
            else
                parent.right = keyNode.left;
        }

        // CASE 3: KeyNode has both, left and right children
        else {
            // find successor of keyNode
            // NOTE: duplicates are already deleted
            Node<T> successor = keyNode.right;

            // find parent of successor
            Node<T> parentSucc = null;
            while (successor.left != null) {
                parentSucc = successor;
                successor = successor.left;
            }

            // CASE 3a: successor is direct right child of keyNode
            if (parentSucc == null) {
                if (isKeyNodeRoot)
                    root = successor;
                else if (isKeyNodeInLeft)
                    parent.left = successor;
                else
                    parent.right = successor;
                successor.left = keyNode.left;
            }

            // CASE 3b: successor is somewhere in the right subtree of keyNode
            else {
                boolean successorInLeft = parentSucc.left == successor;
                if (successor.right != null) {
                    // replace successor with its right children
                    if (successorInLeft)
                        parentSucc.left = successor.right;
                    else
                        parentSucc.right = successor.right;
                } else {
                    // successor has no right children
                    if (successorInLeft)
                        parentSucc.left = null;
                    else
                        parentSucc.right = null;
                }

                // lastly, replace keyNode with its successor
                keyNode.key = successor.key;
            }
        }
    }

    /**
     * Returns the preorder successor of the given key, if exists.
     * <br>
     * <p>In preorder walk, root is printed first, followed by
     * its left child and then the right child.
     * <br><br>
     * Cases:<br>
     * 1. KeyNode has children:<br>
     * keyNode is checked for having left or a right child
     * in that order and whichever is found, returned as the
     * successor.
     * <br>
     * 2. KeyNode is a leaf:<br>
     * Walk up the tree until a node N is found which has
     * both left and right child and whose LEFT child is
     * a parent of keyNode. If such N exists, return its
     * right child.
     * </p>
     */
    public Node<T> getPreorderSuccessor(T key) {
        throwExceptionIfTreeIsEmpty();

        Node<T> keyNode = root;
        Node<T> possibleSucc = null;

        while (keyNode != null && keyNode.keyNotEquals(key)) {
            if (keyNode.keyGT(key)) {
                possibleSucc = keyNode.right != null ? keyNode.right : possibleSucc;
                keyNode = keyNode.left;
            } else {
                keyNode = keyNode.right;
            }
        }

        if (keyNode == null)
            return null;
        if (keyNode.left != null)
            return keyNode.left;
        if (keyNode.right != null)
            return keyNode.right;

        return possibleSucc;
    }

    /**
     * Returns the preorder predecessor of the given key, if exists.
     * <br>
     * <p>In preorder walk, root is printed first, followed by
     * its left child and then the right child.
     * <br><br>
     * Cases:<br>
     * 1. KeyNode is left child of its parent:<br>
     * In this case, the parent of keyNode is returned as the
     * predecessor.
     * <br>
     * 2. KeyNode is right child of its parent. Two cases arise:<br>
     * 2a. Parent has no left child:<br>
     * In this case, parent is returned as the predecessor.<br>
     * 2b. Parent has a left child:<br>
     * Walk down parent.left and find a node N which is the
     * deepest right child in the subtree. If N does not exist,
     * i.e.: there are no right child in the subtree, return
     * minimum(parent.left). If N exists, return minimum(N)
     * </p>
     */
    public Node<T> getPreorderPredecessor(T key) {
        throwExceptionIfTreeIsEmpty();

        Node<T> keyNode = root;
        Node<T> parent = null;

        while (keyNode != null && keyNode.keyNotEquals(key)) {
            parent = keyNode;
            keyNode = keyNode.keyGT(key) ? keyNode.left : keyNode.right;
        }

        if (keyNode == null || parent == null)
            // either keyNode is not found or it was the root
            return null;

        if (parent.left == keyNode)
            // keyNode is left child of its parent
            return parent;

        if (parent.left != null) {
            /* keyNode is right child of its parent and parent's left child
             * exists. In this case, we keep moving right in parent.left. If
             * the right child does not exist for some node N in the subtree,
             * we visit N.left. We repeat the procedure until we find a leaf.
             **/
            Node<T> next = parent.left;
            while (next != null) {
                parent = next;
                next = next.right != null ? next.right : next.left;
            }
        }
        return parent;
    }

    /**
     * Preorder walk. Root -> Left -> Right
     */
    public void preorderCollect(Node<T> root, List<T> nodes) {
        if (root != null) {
            nodes.add(root.key);
            preorderCollect(root.left, nodes);
            preorderCollect(root.right, nodes);
        }
    }

    /**
     * Returns the postorder successor of the given key, if exists.
     * <br>
     * <p>In postorder walk, root is printed last, after its left
     * and right child.
     * <br><br>
     * Cases:<br>
     * 1. KeyNode is right child of its parent:<br>
     * In this case, successor would be the parent.
     * <br>
     * 2. KeyNode is left child of its parent:<br>
     * If parent.right doesn't exist, successor would be the parent.
     * If parent.right exists, walk down parent.right and find node N
     * which is the deepest left child in subtree. If such N does not
     * exist, i.e.: there are no left child in the subtree, return
     * maximum(parent.right). If N exists, return maximum(N).
     * </p>
     */
    public Node<T> getPostorderSuccessor(T key) {
        throwExceptionIfTreeIsEmpty();
        Node<T> keyNode = root;
        Node<T> parent = null;

        while (keyNode != null && keyNode.keyNotEquals(key)) {
            parent = keyNode;
            keyNode = keyNode.keyGT(key) ? keyNode.left : keyNode.right;
        }

        if (keyNode == null || parent == null)
            return null;

        if (parent.right == keyNode)
            return parent;

        if (parent.right != null) {
            Node<T> current = parent.right;
            while (current != null) {
                parent = current;
                current = current.left != null ? current.left : current.right;
            }
        }

        return parent;
    }

    /**
     * Returns the postorder predecessor of the given key, if exists.
     * <br>
     * <p>In postorder walk, root is printed last, after its left
     * and right child.
     * <br><br>
     * Cases:<br>
     * 1. KeyNode has children:<br>
     * In this case, keyNode is checked to have left or right
     * child in that order, if either of it exists, its returned
     * as the predecessor.
     * <br>
     * 2. KeyNode is a leaf:<br>
     * Walk up the tree and find a node N which has both left and
     * right child and whose right child is also a parent of keyNode.
     * If N exists, return N.left.
     * </p>
     */
    public Node<T> getPostorderPredecessor(T key) {
        throwExceptionIfTreeIsEmpty();
        Node<T> keyNode = root;
        Node<T> parent = null;
        Node<T> possiblePred = null;

        while (keyNode != null && keyNode.keyNotEquals(key)) {
            parent = keyNode;
            if (keyNode.keyGT(key))
                keyNode = keyNode.left;
            else {
                possiblePred = keyNode.left != null ? keyNode.left : possiblePred;
                keyNode = keyNode.right;
            }
        }

        if (keyNode == null)
            return null;

        if (keyNode.right != null)
            return keyNode.right;

        if (keyNode.left != null)
            return keyNode.left;

        if (parent == null)
            // only one node in the tree
            return null;

        return possiblePred;
    }

    public void postorderCollect(Node<T> root, List<T> nodes) {
        if (root != null) {
            postorderCollect(root.left, nodes);
            postorderCollect(root.right, nodes);
            nodes.add(root.key);
        }
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void clear() {
        root = null;
        size = 0;
    }
}
