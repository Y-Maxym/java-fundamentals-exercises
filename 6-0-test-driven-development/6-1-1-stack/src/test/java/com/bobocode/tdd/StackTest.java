package com.bobocode.tdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class StackTest {

    private Stack<Integer> stack;

    @BeforeEach
    void init() {
        stack = new LinkedStack<>();
    }

    @Test
    void givenEmptyStack_whenCallSize_thenSizeIsZero() {
        // when
        int size = stack.size();

        // then
        assertThat(size).isEqualTo(0);
    }

    @Test
    void givenEmptyStack_whenCallIsEmpty_thenReturnTrue() {
        // when
        boolean isEmpty = stack.isEmpty();

        // then
        assertThat(isEmpty).isTrue();
    }

    @Test
    void givenElement_whenPush_thenSizeIsNotZero() {
        // given
        int element = 42;

        // when
        stack.push(element);

        // then
        assertThat(stack.size()).isEqualTo(1);
    }

    @Test
    void givenNull_whenPush_thenExceptionIsThrown() {
        // when && then
        assertThatThrownBy(() -> stack.push(null))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    void givenElement_whenCallPushAndSize_thenSizeIsGreaterZero() {
        // given
        int element = 0;

        // when
        stack.push(element);
        int size = stack.size();

        // then
        assertThat(size).isEqualTo(1);
    }

    @Test
    void givenElement_whenCallPushAndIsEmpty_thenReturnFalse() {
        // given
        int element = 42;

        // when
        stack.push(element);
        boolean isEmpty = stack.isEmpty();

        // then
        assertThat(isEmpty).isFalse();
    }

    @Test
    void givenElement_whenPushAndPop_thenSameElementIsReturned() {
        // given
        int element = 42;

        // when
        stack.push(element);
        int popped = stack.pop();

        // then
        assertThat(popped).isEqualTo(element);
    }

    @Test
    void givenEmptyStack_whenPop_thenThrowException() {
        // when && then
        assertThatThrownBy(() -> stack.pop())
                .isInstanceOf(NoSuchElementException.class)
                .hasMessage("Stack is empty");
    }

    @Test
    void givenMultiplePushAndPop_whenCheckSize_thenSizeIsCorrect() {
        // given
        assertThat(stack.size()).isZero();

        // when
        stack.push(1);
        int sizeAfterFirstPush = stack.size();

        stack.push(2);
        int sizeAfterSecondPush = stack.size();

        stack.pop();
        int sizeAfterFirstPop = stack.size();

        stack.pop();
        int sizeAfterSecondPop = stack.size();

        // then
        assertThat(sizeAfterFirstPush).isEqualTo(1);
        assertThat(sizeAfterSecondPush).isEqualTo(2);
        assertThat(sizeAfterFirstPop).isEqualTo(1);
        assertThat(sizeAfterSecondPop).isZero();
    }}
