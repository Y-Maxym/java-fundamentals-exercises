package com.bobocode.cs;

import java.util.function.Consumer;
import java.util.stream.Stream;

import static java.lang.Math.max;
import static java.util.Objects.requireNonNull;

/**
 * {@link RecursiveBinarySearchTree} is an implementation of a {@link BinarySearchTree} that is based on a linked nodes
 * and recursion. A tree node is represented as a nested class {@link Node}. It holds an element (a value) and
 * two references to the left and right child nodes.
 * <p><p>
 * <strong>TODO: to get the most out of your learning, <a href="https://www.bobocode.com">visit our website</a></strong>
 * <p>
 *
 * @param <T> a type of elements that are stored in the tree
 * @author Taras Boychuk
 * @author Maksym Stasiuk
 */
public class RecursiveBinarySearchTree<T extends Comparable<T>> implements BinarySearchTree<T> {
    private Node<T> root;
    private int size;

    public static <T extends Comparable<T>> RecursiveBinarySearchTree<T> of(T... elements) {
        RecursiveBinarySearchTree<T> tree = new RecursiveBinarySearchTree<>();
        Stream.of(elements).forEach(tree::insert);
        return tree;
    }

    @Override
    public boolean insert(T element) {
        requireNonNull(element);
        boolean isInserted = insertElement(element);
        if (isInserted) {
            size++;
        }
        return isInserted;
    }

    private boolean insertElement(T element) {
        if (root == null) {
            root = new Node<>(element);
            return true;
        }
        return insertIntoSubTree(element, root);
    }

    private boolean insertIntoSubTree(T element, Node<T> node) {
        if (node.value.compareTo(element) > 0) {
            return insertIntoSubTreeLeft(element, node);
        } else if (node.value.compareTo(element) < 0) {
            return insertIntoSubTreeRight(element, node);
        } else {
            return false;
        }
    }

    private boolean insertIntoSubTreeLeft(T element, Node<T> node) {
        if (node.left != null) {
            return insertIntoSubTree(element, node.left);
        }
        node.left = new Node<>(element);
        return true;
    }

    private boolean insertIntoSubTreeRight(T element, Node<T> node) {
        if (node.right != null) {
            return insertIntoSubTree(element, node.right);
        }
        node.right = new Node<>(element);
        return true;
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
        return root != null ? depth(root) - 1 : 0;
    }

    private int depth(Node<T> node) {
        if (node == null) {
            return 0;
        }
        return max(depth(node.left), depth(node.right)) + 1;
    }

    @Override
    public void inOrderTraversal(Consumer<T> consumer) {
        inOrderTraversal(root, consumer);
    }

    private void inOrderTraversal(Node<T> node, Consumer<T> consumer) {
        if (node != null) {
            inOrderTraversal(node.left, consumer);
            consumer.accept(node.value);
            inOrderTraversal(node.right, consumer);
        }
    }

    private static class Node<T> {
        private T value;
        private Node<T> left;
        private Node<T> right;

        public Node(T value) {
            this.value = value;
        }
    }
}
