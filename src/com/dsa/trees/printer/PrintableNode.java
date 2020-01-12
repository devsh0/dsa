package com.dsa.trees.printer;

import com.dsa.trees.Node;

import java.util.HashMap;

public class PrintableNode <T extends Comparable<T>> {
    private static final int GAP = 1;
    private int reqSpace;
    private Box<T> boundingBox;
    private final Node<T> node;

    public PrintableNode(Node<T> node, PrintableNode<T> parent, int levelWidth) {
        this.node = node;
        this.boundingBox = new Box<>(this.node.getKey(), levelWidth + 4);
        if (parent == null) {
            reqSpace = 0;
            return;
        }
        int boxWidth = boundingBox.getWidth();
        reqSpace = boxWidth - ((parent.boundingBox.getWidth() / 2) + GAP);
    }
}
