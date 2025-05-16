package com.bobocode.tdd;

import java.util.NoSuchElementException;

import static java.util.Objects.requireNonNull;

public class LinkedStack<T> implements Stack<T> {
    private Node<T> head;
    private int size;

    @Override
    public void push(T element) {
        requireNonNull(element);
        head = new Node<>(element, head);
        size++;
    }

    @Override
    public T pop() {
        if (size == 0) {
            throw new NoSuchElementException("Stack is empty");
        }

        T value = head.value;
        head = head.next;
        size--;
        return value;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private static class Node<T> {
        private T value;
        private Node<T> next;

        public Node(T value) {
            this.value = value;
        }

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }
}
