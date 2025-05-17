package com.bobocode.tdd;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Slf4j
public class LinkedListTest {

    private List<Integer> list;

    @BeforeEach
    void init() {
        list = new LinkedList<>();
    }

    @Test
    void givenEmptyList_whenCallSize_thenZeroIsReturned() {
        // when
        int size = list.size();

        // then
        assertThat(size).isEqualTo(0);
    }

    @Test
    void givenEmptyList_whenCallIsEmpty_thenReturnedTrue() {
        // when
        boolean isEmpty = list.isEmpty();

        // then
        assertThat(isEmpty).isTrue();
    }

    @Test
    void givenElement_whenAdd_thenSizeIsIncreased() {
        // when
        list.add(42);
        int size = list.size();


        // then
        assertThat(size).isEqualTo(1);
    }

    @Test
    void givenEmptyListOfElements_whenCallOf_thenEmptyListIsReturned() {
        // when
        list = LinkedList.of();

        // then
        assertThat(list.isEmpty()).isTrue();
        assertThat(list.size()).isEqualTo(0);
    }

    @Test
    void givenOneElement_whenCallOf_thenListHasSizeOne() {
        // when
        list = LinkedList.of(42);

        // then
        assertThat(list.isEmpty()).isFalse();
        assertThat(list.size()).isEqualTo(1);
    }

    @Test
    void givenTwoElements_whenCallGetFirst_thenListReturnsFirstElement() {
        // given
        list = LinkedList.of(42, 101);

        // when
        Integer first = list.getFirst();

        // then
        assertThat(first).isEqualTo(42);
    }

    @Test
    void givenTwoElements_whenCallGetLast_thenListReturnsLastElement() {
        // given
        list = LinkedList.of(42, 101);

        // when
        Integer last = list.getLast();

        // then
        assertThat(last).isEqualTo(101);
    }

    @Test
    void givenNonEmptyList_whenCallIsEmpty_thenReturnsFalse() {
        // given
        list = LinkedList.of(42, 111);

        // when
        boolean isEmpty = list.isEmpty();

        // then
        assertThat(isEmpty).isFalse();
    }

    @Test
    void givenNonEmptyList_whenCallClear_thenListIsEmpty() {
        // given
        list = LinkedList.of(42, 111);

        // when
        list.clear();

        // then
        assertThat(list.isEmpty()).isTrue();
    }

    @Test
    void givenNonEmptyList_whenClear_thenGetFirstThrowsException() {
        // given
        list = LinkedList.of(1, 2, 3);

        // when
        list.clear();

        // then
        assertThatThrownBy(() -> list.getFirst())
                .isInstanceOf(NoSuchElementException.class)
                .hasMessage("List is empty");
    }

    @Test
    void givenNonEmptyList_whenClear_thenGetLastThrowsException() {
        // given
        list = LinkedList.of(1, 2, 3);

        // when
        list.clear();

        // then
        assertThatThrownBy(() -> list.getLast())
                .isInstanceOf(NoSuchElementException.class)
                .hasMessage("List is empty");
    }

    @Test
    void givenNonEmptyList_whenGetByIndex_thenElementIsReturned() {
        // given
        list = LinkedList.of(1, 2, 3);

        // when
        Integer element = list.get(1);

        // then
        assertThat(element).isEqualTo(2);
    }

    @Test
    void givenEmptyList_whenGetByIndex_thenExceptionIsThrown() {
        // given
        list = LinkedList.of();

        // when && then
        assertThatThrownBy(() -> list.get(1))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void givenNonEmptyList_whenGetByNegativeIndex_thenExceptionIsThrown() {
        // given
        list = LinkedList.of(1, 2, 3);

        // when && then
        assertThatThrownBy(() -> list.get(-1))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void givenNonEmptyList_whenGetByIndexGreaterTHenPossible_thenExceptionIsThrown() {
        // given
        list = LinkedList.of(1, 2, 3);

        // when && then
        assertThatThrownBy(() -> list.get(3))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void givenNonEmptyList_whenGetFirstElement_thenElementIsReturned() {
        // given
        list = LinkedList.of(1, 2, 3, 4, 5);

        // when
        Integer element = list.get(0);

        // then
        assertThat(element).isEqualTo(1);
    }

    @Test
    void givenNonEmptyList_whenGetLastElement_thenElementIsReturned() {
        // given
        list = LinkedList.of(1, 2, 3, 4, 5);

        // when
        Integer element = list.get(4);

        // then
        assertThat(element).isEqualTo(5);
    }

    @Test
    void givenEmptyList_whenAddByNegativeIndex_thenExceptionIsThrown() {
        // given
        list = LinkedList.of();

        // when && then
        assertThatThrownBy(() -> list.add(-1, 42))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void givenNonEmptyList_whenAddByIndexThatIsGreaterThanSize_thenExceptionIsThrown() {
        // given
        list = LinkedList.of(1, 2, 3, 4, 5);

        // when && then
        assertThatThrownBy(() -> list.add(6, 42))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void givenNonEmptyList_whenAddByLastIndex_thenElementIsAdded() {
        // given
        list = LinkedList.of(1, 2, 3, 4, 5);

        // when
        list.add(list.size(), 42);

        // then
        assertThat(list.getLast()).isEqualTo(42);
        assertThat(list.size()).isEqualTo(6);
    }

    @Test
    void givenNonEmptyList_whenAddByFirstIndex_thenElementIsAdded() {
        // given
        list = LinkedList.of(1, 2, 3, 4, 5);

        // when
        list.add(0, 42);

        // then
        assertThat(list.getFirst()).isEqualTo(42);
        assertThat(list.getLast()).isEqualTo(5);
        assertThat(list.get(3)).isEqualTo(3);
        assertThat(list.get(1)).isEqualTo(1);
        assertThat(list.size()).isEqualTo(6);
    }

    @Test
    void givenNonEmptyList_whenAddByIndex_thenElementIsAdded() {
        // given
        list = LinkedList.of(1, 2, 3, 4, 5);

        // when
        list.add(2, 42);

        // then
        assertThat(list.get(2)).isEqualTo(42);
        assertThat(list.size()).isEqualTo(6);
    }

    @Test
    void givenEmptyList_whenAddByZeroIndex_thenElementIsAdded() {
        // given
        list = LinkedList.of();

        // when
        list.add(0, 42);

        // then
        assertThat(list.getFirst()).isEqualTo(42);
        assertThat(list.getLast()).isEqualTo(42);
        assertThat(list.get(0)).isEqualTo(42);
        assertThat(list.size()).isEqualTo(1);
    }

    @Test
    void givenEmptyList_whenSetByNegativeIndex_thenExceptionIsThrown() {
        // given
        list = LinkedList.of();

        // when && then
        assertThatThrownBy(() -> list.set(-1, 42))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void givenNonEmptyList_whenSetByIndexThatIsGreaterThanSize_thenExceptionIsThrown() {
        // given
        list = LinkedList.of(1, 2, 3, 4, 5);

        // when && then
        assertThatThrownBy(() -> list.set(6, 42))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void givenNonEmptyList_whenSetByIndexThatEqualsSize_thenExceptionIsThrown() {
        // given
        list = LinkedList.of(1, 2, 3, 4, 5);

        // when && then
        assertThatThrownBy(() -> list.set(5, 42))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void givenNonEmptyList_whenSetHeadValue_thenValueIsChanged() {
        // given
        list = LinkedList.of(1, 2, 3, 4, 5);

        // when
        list.set(0, 42);

        // then
        assertThat(list.getFirst()).isEqualTo(42);
        assertThat(list.get(0)).isEqualTo(42);
        assertThat(list.size()).isEqualTo(5);
    }

    @Test
    void givenNonEmptyList_whenSetTailValue_thenValueIsChanged() {
        // given
        list = LinkedList.of(1, 2, 3, 4, 5);

        // when
        list.set(4, 42);

        // then
        assertThat(list.getLast()).isEqualTo(42);
        assertThat(list.get(4)).isEqualTo(42);
        assertThat(list.size()).isEqualTo(5);
    }

    @Test
    void givenNonEmptyList_whenSetValue_thenValueIsChanged() {
        // given
        list = LinkedList.of(1, 2, 3, 4, 5);

        // when
        list.set(2, 42);
        list.set(3, 101);

        // then
        assertThat(list.get(2)).isEqualTo(42);
        assertThat(list.get(3)).isEqualTo(101);
        assertThat(list.size()).isEqualTo(5);
    }

    @Test
    void givenEmptyList_whenRemove_thenExceptionIsThrown() {
        // given
        list = LinkedList.of();

        // when && then
        assertThatThrownBy(() -> list.remove(1))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void givenNonEmptyList_whenRemoveByNegativeIndex_thenExceptionIsThrown() {
        // given
        list = LinkedList.of(1, 2, 3, 4, 5);

        // when && then
        assertThatThrownBy(() -> list.remove(-1))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void givenEmptyList_whenRemoveByIndexThatGreaterThanSize_thenExceptionIsThrown() {
        // given
        list = LinkedList.of(1, 2, 3, 4, 5);

        // when && then
        assertThatThrownBy(() -> list.remove(6))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void givenEmptyList_whenRemoveByIndexThatEqualsSize_thenExceptionIsThrown() {
        // given
        list = LinkedList.of(1, 2, 3, 4, 5);

        // when && then
        assertThatThrownBy(() -> list.remove(5))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void givenNonEmptyList_whenRemoveFirstElement_thenRemovedElementIsReturned() {
        // given
        list = LinkedList.of(1, 2, 3, 4, 5);

        // when
        Integer removedElement = list.remove(0);

        // then
        assertThat(removedElement).isEqualTo(1);
        assertThat(list.getFirst()).isEqualTo(2);
        assertThat(list.size()).isEqualTo(4);
    }

    @Test
    void givenNonEmptyList_whenRemoveLastElement_thenRemovedElementIsReturned() {
        // given
        list = LinkedList.of(1, 2, 3, 4, 5);

        // when
        Integer removedElement = list.remove(4);

        // then
        assertThat(removedElement).isEqualTo(5);
        assertThat(list.getLast()).isEqualTo(4);
        assertThat(list.get(3)).isEqualTo(4);
        assertThat(list.size()).isEqualTo(4);
    }

    @Test
    void givenNonEmptyList_whenRemove_thenRemovedElementIsReturned() {
        // given
        list = LinkedList.of(1, 2, 3, 4, 5);

        // when
        Integer removedElement = list.remove(2);

        // then
        assertThat(removedElement).isEqualTo(3);
        assertThat(list.get(2)).isEqualTo(4);
        assertThat(list.size()).isEqualTo(4);
    }

    @Test
    void givenEmptyList_whenContains_thenReturnsFalse() {
        // given
        list = LinkedList.of();

        // when
        boolean isPresent = list.contains(3);

        // then
        assertThat(isPresent).isFalse();
    }

    @Test
    void givenNonEmptyList_whenContains_thenReturnsFalse() {
        // given
        list = LinkedList.of(1, 2, 3, 4, 5);

        // when
        boolean isPresent = list.contains(42);

        // then
        assertThat(isPresent).isFalse();
    }

    @Test
    void givenNonEmptyList_whenContains_thenReturnsTrue() {
        // given
        list = LinkedList.of(1, 2, 3, 4, 5);

        // when
        boolean isPresent = list.contains(5);

        // then
        assertThat(isPresent).isTrue();
    }

    @Test
    void givenNonEmptyList_whenContainsNull_thenExceptionIsThrown() {
        // given
        list = LinkedList.of(1, 2, 3, 4, 5);

        // when && then
        assertThatThrownBy(() -> list.contains(null))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    void givenNonEmptyList_whenAddNullElement_thenExceptionIsThrown() {
        // given
        list = LinkedList.of();

        // when && then
        assertThatThrownBy(() -> list.add(null))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    void givenNonEmptyList_whenAddNullElementByIndex_thenExceptionIsThrown() {
        // given
        list = LinkedList.of(1, 2, 3);

        // when && then
        assertThatThrownBy(() -> list.add(1, null))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    void givenNonEmptyList_whenSetNullElementByIndex_thenExceptionIsThrown() {
        // given
        list = LinkedList.of(1, 2, 3);

        // when && then
        assertThatThrownBy(() -> list.set(1, null))
                .isInstanceOf(NullPointerException.class);
    }
}
