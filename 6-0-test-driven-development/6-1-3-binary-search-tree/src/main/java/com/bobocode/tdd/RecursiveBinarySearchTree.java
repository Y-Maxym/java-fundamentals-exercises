package com.bobocode.tdd;

import java.util.function.Consumer;
import java.util.stream.Stream;

import static java.lang.Math.max;
import static java.util.Objects.requireNonNull;

public class RecursiveBinarySearchTree<T extends Comparable<? super T>> implements BinarySearchTree<T> {
    private Node<T> root;
    private int size;

    @SafeVarargs
    public static <T extends Comparable<? super T>> RecursiveBinarySearchTree<T> of(T... elements) {
        RecursiveBinarySearchTree<T> tree = new RecursiveBinarySearchTree<>();
        Stream.of(elements).forEach(tree::insert);
        return tree;
    }

    @Override
    public boolean insert(T element) {
        requireNonNull(element);
        if (root == null) {
            return insertRoot(element);
        }
        return insert(element, root);
    }

    private boolean insertRoot(T element) {
        root = new Node<>(element);
        size++;
        return true;
    }

    private boolean insert(T element, Node<T> node) {
        if (node.value.compareTo(element) == 0) {
            return false;
        } else if (node.value.compareTo(element) < 0) {
            return insertRightLeaf(element, node);
        } else {
            return insertLeftLeaf(element, node);
        }
    }

    private boolean insertLeftLeaf(T element, Node<T> node) {
        if (node.left == null) {
            node.left = new Node<>(element);
            size++;
            return true;
        } else {
            return insert(element, node.left);
        }
    }

    private boolean insertRightLeaf(T element, Node<T> node) {
        if (node.right == null) {
            node.right = new Node<>(element);
            size++;
            return true;
        } else {
            return insert(element, node.right);
        }
    }

    @Override
    public boolean contains(T element) {
        requireNonNull(element);
        return contains(element, root);
    }

    private boolean contains(T element, Node<T> node) {
        if (node == null) {
            return false;
        } else if (node.value.compareTo(element) > 0) {
            return contains(element, node.left);
        } else if (node.value.compareTo(element) < 0) {
            return contains(element, node.right);
        } else {
            return true;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int depth() {
        return root == null ? 0 : depth(root) - 1;
    }

    private int depth(Node<T> root) {
        if (root == null) {
            return 0;
        }
        return 1 + max(depth(root.left), depth(root.right));
    }

    @Override
    public void inOrderTraversal(Consumer<T> consumer) {
        inOrderTraversal(consumer, root);
    }

    private void inOrderTraversal(Consumer<T> consumer, Node<T> node) {
        if (node != null) {
            inOrderTraversal(consumer, node.left);
            consumer.accept(node.value);
            inOrderTraversal(consumer, node.right);
        }
    }

    private static class Node<T extends Comparable<? super T>> {
        private final T value;
        private Node<T> left;
        private Node<T> right;

        private Node(T value) {
            this.value = value;
        }
    }
}
