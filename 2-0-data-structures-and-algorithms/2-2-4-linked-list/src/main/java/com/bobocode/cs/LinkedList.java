package com.bobocode.cs;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * {@link LinkedList} is a list implementation that is based on singly linked generic nodes. A node is implemented as
 * inner static class {@link Node<T>}.
 * <p><p>
 * <strong>TODO: to get the most out of your learning, <a href="https://www.bobocode.com">visit our website</a></strong>
 * <p>
 *
 * @param <T> generic type parameter
 * @author Taras Boychuk
 * @author Serhii Hryhus
 */
public class LinkedList<T> implements List<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size;

    /**
     * This method creates a list of provided elements
     *
     * @param elements elements to add
     * @param <T>      generic type
     * @return a new list of elements the were passed as method parameters
     */
    public static <T> LinkedList<T> of(T... elements) {
        LinkedList<T> list = new LinkedList<>();
        Stream.of(elements).forEach(list::add);
        return list;
    }

    /**
     * Adds an element to the end of the list.
     *
     * @param element element to add
     */
    @Override
    public void add(T element) {
        Node<T> newNode = new Node<>(element);

        if (size == 0) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    /**
     * Adds a new element to the specific position in the list. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index   an index of new element
     * @param element element to add
     */
    @Override
    public void add(int index, T element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }

        Node<T> newNode = new Node<>(element);

        if (index == 0) {
            addAsHead(newNode);
        } else if (index == size) {
            addAsTail(newNode);
        } else {
            add(index, newNode);
        }
        size++;
    }

    private void addAsHead(Node<T> node) {
        node.next = head;
        head = node;
        if (tail == null) {
            tail = head;
        }
    }

    private void addAsTail(Node<T> node) {
        tail.next = node;
        tail = node;
        if (head == null) {
            head = tail;
        }
    }

    private void add(int index, Node<T> newNode) {
        Node<T> node = getNodeByIndex(index - 1);
        newNode.next = node.next;
        node.next = newNode;
    }

    private Node<T> getNodeByIndex(int index) {
        Objects.checkIndex(index, size);
        if (index == size - 1) {
            return tail;
        } else {
            return nodeAt(index);
        }
    }

    private Node<T> nodeAt(int index) {
        Node<T> node = head;

        while (--index >= 0) {
            node = node.next;
        }
        return node;
    }

    /**
     * Changes the value of an list element at specific position. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index   an position of element to change
     * @param element a new element value
     */
    @Override
    public void set(int index, T element) {
        if (size == 0 || index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        Node<T> node = getNodeByIndex(index);
        node.value = element;
    }

    /**
     * Retrieves an elements by its position index. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index element index
     * @return an element value
     */
    @Override
    public T get(int index) {
        if (size == 0 || index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return getNodeByIndex(index).value;
    }

    /**
     * Returns the first element of the list. Operation is performed in constant time O(1)
     *
     * @return the first element of the list
     * @throws java.util.NoSuchElementException if list is empty
     */
    @Override
    public T getFirst() {
        checkElementsExist();
        return head.value;
    }

    /**
     * Returns the last element of the list. Operation is performed in constant time O(1)
     *
     * @return the last element of the list
     * @throws java.util.NoSuchElementException if list is empty
     */
    @Override
    public T getLast() {
        checkElementsExist();
        return tail.value;
    }

    private void checkElementsExist() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
    }

    /**
     * Removes an elements by its position index. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index element index
     * @return deleted element
     */
    @Override
    public T remove(int index) {
        if (size == 0 || index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        T value;

        if (index == 0) {
            value = head.value;
            removeHead();
        } else {
            Node<T> node = getNodeByIndex(index - 1);
            value = node.next.value;
            node.next = node.next.next;

            if (index == size - 1) {
                tail = node;
            }
        }
        size--;
        return value;
    }

    private void removeHead() {
        head = head.next;
        if (head == null) {
            tail = null;
        }
    }


    /**
     * Checks if a specific exists in he list
     *
     * @return {@code true} if element exist, {@code false} otherwise
     */
    @Override
    public boolean contains(T element) {
        Node<T> node = head;
        while (node != null) {

            if (node.value.equals(element)) {
                return true;
            }
            node = node.next;
        }
        return false;
    }

    /**
     * Checks if a list is empty
     *
     * @return {@code true} if list is empty, {@code false} otherwise
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the number of elements in the list
     *
     * @return number of elements
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Removes all list elements
     */
    @Override
    public void clear() {
        head = tail = null;
        size = 0;
    }

    private static class Node<T> {
        private T value;
        private Node<T> next;

        private Node(T value) {
            this.value = value;
        }
    }
}
