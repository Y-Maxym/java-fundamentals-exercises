package com.bobocode.tdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BinarySearchTreeTest {

    private BinarySearchTree<Integer> tree;

    @BeforeEach
    void init() {
        tree = new RecursiveBinarySearchTree<>();
    }

    @Test
    void givenEmptyTree_whenCallSize_thenSizeIsZero() {
        // when
        int size = tree.size();

        // then
        assertThat(size).isEqualTo(0);
    }

    @Test
    void givenEmptyTree_whenInsert_thenSizeIsIncreased() {
        // when
        tree.insert(42);

        // then
        assertThat(tree.size()).isEqualTo(1);
    }

    @Test
    void givenEmptyTree_whenInsert_thenReturnsTrue() {
        // when
        boolean isInserted = tree.insert(42);

        // then
        assertThat(isInserted).isTrue();
    }

    @Test
    void givenEmptyTree_whenInsertNullElement_thenExceptionIsThrown() {
        // when && then
        assertThatThrownBy(() -> tree.insert(null))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    void givenElements_whenCallOf_thenFilledTreeReturns() {
        // given
        Integer[] elements = {1, 2, 3, 4, 5};

        // when
        RecursiveBinarySearchTree<Integer> newTree = RecursiveBinarySearchTree.of(elements);

        // then
        assertThat(newTree.size()).isEqualTo(5);
    }

    @Test
    void givenNonEmptyTree_whenInsert_thenReturnsFalse() {
        // given
        tree = RecursiveBinarySearchTree.of(1, 2, 3, 4, 5, 42);

        // when
        boolean isInserted = tree.insert(42);

        // then
        assertThat(isInserted).isFalse();
        assertThat(tree.size()).isEqualTo(6);
    }

    @Test
    void givenNonEmptyTree_whenInsert_thenReturnsTrue() {
        // given
        tree = RecursiveBinarySearchTree.of(1, 2, 3, 4, 5);

        // when
        boolean isInserted = tree.insert(42);

        // then
        assertThat(isInserted).isTrue();
        assertThat(tree.size()).isEqualTo(6);
    }

    @Test
    void givenEmptyTree_whenContainsNullElement_thenExceptionIsThrown() {
        // when && then
        assertThatThrownBy(() -> tree.contains(null))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    void givenNonEmptyTree_whenContains_thenReturnTrue() {
        // given
        tree = RecursiveBinarySearchTree.of(1, 2, 3, 4, 5);

        // when
        boolean isPresent = tree.contains(3);

        // then
        assertThat(isPresent).isTrue();
    }

    @Test
    void givenNonEmptyTree_whenContains_thenReturnFalse() {
        // given
        tree = RecursiveBinarySearchTree.of(1, 2, 3, 4, 5);

        // when
        boolean isPresent = tree.contains(42);

        // then
        assertThat(isPresent).isFalse();
    }

    @Test
    void givenEmptyTree_whenContains_thenReturnFalse() {
        // when
        boolean isPresent = tree.contains(42);

        // then
        assertThat(isPresent).isFalse();
    }

    @Test
    void givenEmptyTree_whenDepth_thenReturnsZero() {
        // when
        int depth = tree.depth();

        // then
        assertThat(depth).isEqualTo(0);
    }

    @Test
    void givenOneElementTree_whenDepth_thenReturnsZero() {
        // given
        tree = RecursiveBinarySearchTree.of(1);

        // when
        int depth = tree.depth();

        // then
        assertThat(depth).isEqualTo(0);
    }

    @ParameterizedTest
    @MethodSource("provideDataForDepth")
    void givenNonEmptyTree_whenDepth_thenReturnsCorrectDepth(Integer[] elements, int expectedDepth) {
        // given
        tree = RecursiveBinarySearchTree.of(elements);

        // when
        int depth = tree.depth();

        // then
        assertThat(depth).isEqualTo(expectedDepth);
    }

    @ParameterizedTest
    @MethodSource("provideDataForInOrderTraversal")
    void givenNonEmptyTree_whenInOrderTraversal_thenBehaviorIsExpected(Integer[] elements, String expectedResult) {
        // given
        tree = RecursiveBinarySearchTree.of(elements);
        StringBuilder sb = new StringBuilder();

        // when
        tree.inOrderTraversal(sb::append);

        // then
        assertThat(sb.toString()).isEqualTo(expectedResult);
    }

    private static Stream<Arguments> provideDataForDepth() {
        return Stream.of(
                Arguments.of(new Integer[]{1, 2, 3, 4, 5}, 4),
                Arguments.of(new Integer[]{3, 1, 4, 5}, 2),
                Arguments.of(new Integer[]{3, 2, 1, 4}, 2)
        );
    }

    private static Stream<Arguments> provideDataForInOrderTraversal() {
        return Stream.of(
                Arguments.of(new Integer[]{1, 2, 3, 4, 5}, "12345"),
                Arguments.of(new Integer[]{3, 1, 4, 5}, "1345"),
                Arguments.of(new Integer[]{3, 2, 1, 4}, "1234")
        );
    }
}
