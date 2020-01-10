package com.dsa.trees;

import com.dsa.utils.DsaUtils;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import static junit.framework.TestCase.*;

public class BinarySearchTreeTest {
    private BinarySearchTree<Integer> tree;

    @SuppressWarnings({"FieldCanBeLocal"})
    private List<Integer> list;

    @Before
    public void prepareBst() {
        tree = new BinarySearchTree<>();
        list = new ArrayList<>(List.of(
                100, 50, 75, 62, 68, 87, 81, 25, 37, 31, 12, 18,
                200, 150, 125, 175, 163, 300, 250, 275, 400, 350
        ));
    }

    private Stack<Node<Integer>> getPath(int key) {
        var stack = new Stack<Node<Integer>>();
        var current = tree.root;
        while (current != null && current.keyNotEquals(key)) {
            stack.push(current);
            current = current.keyGT(key) ? current.left : current.right;
        }
        return stack;
    }

    @Test
    public void insertionTest() {
        assertTrue(tree.isEmpty());
        tree.insert(10);
        assertFalse(tree.isEmpty());
        tree.insert(5);
        assertEquals(5, tree.root.left.key.intValue());
        tree.insert(15);
        assertEquals(15, tree.root.right.key.intValue());
        assertEquals(3, tree.size());
    }

    @Test
    public void duplicateInsertionTest() {
        tree.insertAll(2, 4);
        assertEquals(4, tree.root.right.key.intValue());
        tree.insert(2);
        assertEquals(2, tree.root.right.key.intValue());
        assertEquals(3, tree.size());
    }

    @Test
    public void deletionTest() {
        for (int i = 0; i < 1000; i++) {
            tree = new BinarySearchTree<>();
            list.clear();
            list = DsaUtils.getUniqueRandomInts(100, 100);
            list.forEach(element -> tree.insert(element));

            while (tree.size() > 1) {
                int index = DsaUtils.getRandomInt(1, list.size());
                int key = list.get(index);
                var node = tree.search(key);
                var path = getPath(key);

                var parent = path.pop();
                assertTrue(parent.left == node || parent.right == node);
                var inLeft = parent.left == node;

                if (node.left == null && node.right == null) {
                    // case 1: node is a leaf
                    tree.delete(key);
                    assertNull(inLeft ? parent.left : parent.right);
                } else if (node.left == null || node.right == null) {
                    // case 2: node has only one child
                    var child = node.left == null ? node.right : node.left;
                    tree.delete(key);
                    assertTrue(inLeft ? parent.left.keyEquals(child.key) : parent.right.keyEquals(child.key));
                } else {
                    // case 3: node has both, left and right child
                    var successor = tree.getInorderSuccessor(key);
                    var sParent = getPath(successor.key).pop();
                    var sInLeft = sParent.left == successor;
                    var sChild = successor.right;
                    var directChild = sParent == node;
                    tree.delete(key);
                    if (directChild)
                        // if successor is direct child of node, then after deletion of node
                        // sParent should be set to successor itself
                        sParent = successor;
                    // test key equality because values have been swapped b/w successor and keyNode
                    assertTrue(inLeft ? parent.left.keyEquals(successor.key) : parent.right.keyEquals(successor.key));
                    assertTrue(sChild == null || (sInLeft ? sParent.left.keyEquals(sChild.key) :
                            sParent.right.keyEquals(sChild.key)));
                }

                list.remove(index);
            }
        }
    }
}
