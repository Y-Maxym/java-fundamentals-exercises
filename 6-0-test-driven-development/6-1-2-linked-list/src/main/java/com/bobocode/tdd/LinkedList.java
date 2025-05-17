package com.bobocode.tdd;


import java.util.NoSuchElementException;
import java.util.stream.Stream;

import static java.util.Objects.checkIndex;
import static java.util.Objects.requireNonNull;

/**
 * {@link LinkedList} is a list implementation that is based on singly linked generic nodes. A node is implemented as
 * inner static class {@link Node<T>}.
 *
 * @param <T> generic type parameter
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
    @SafeVarargs
    public static <T> List<T> of(T... elements) {
        List<T> list = new LinkedList<>();
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
        requireNonNull(element);
        Node<T> node = new Node<>(element);
        if (size == 0) {
            head = tail = node;
        } else {
            tail.next = node;
            tail = node;
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
        requireNonNull(element);
        checkIndex(index, size + 1);
        if (index == size) {
            add(element);
            return;
        } else if (index == 0) {
            head = new Node<>(element, head);
        } else {
            Node<T> node = getNode(index - 1);
            node.next = new Node<>(element, node.next);
        }
        size++;
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
        requireNonNull(element);
        checkIndex(index, size);
        getNode(index).value = element;
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
        checkIndex(index, size);
        return getNode(index).value;
    }

    private Node<T> getNode(int index) {
        if (index == 0) {
            return head;
        } else if (index == size - 1) {
            return tail;
        }
        Node<T> node = head;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }

    /**
     * Returns the first element of the list. Operation is performed in constant time O(1)
     *
     * @return the first element of the list
     * @throws java.util.NoSuchElementException if list is empty
     */
    @Override
    public T getFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("List is empty");
        }
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
        if (isEmpty()) {
            throw new NoSuchElementException("List is empty");
        }
        return tail.value;
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
        checkIndex(index, size);
        T value;
        if (index == 0) {
            value = removeFirst();
        } else if (index == size - 1) {
            value = removeLast();
        } else {
            value = removeByIndex(index);
        }
        size--;
        return value;
    }

    private T removeFirst() {
        T value = getFirst();
        head = head.next;
        return value;
    }

    private T removeLast() {
        T value = getLast();
        Node<T> prev = getNode(size - 2);
        prev.next = null;
        tail = prev;
        return value;
    }

    private T removeByIndex(int index) {
        Node<T> prev = getNode(index - 1);
        T value = prev.next.value;
        prev.next = prev.next.next;
        return value;
    }

    /**
     * Checks if a specific exists in he list
     *
     * @return {@code true} if element exist, {@code false} otherwise
     */
    @Override
    public boolean contains(T element) {
        requireNonNull(element);
        Node<T> node = head;
        for (int index = 0; index < size; index++) {
            if (element.equals(node.value)) {
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

        private Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }
}
