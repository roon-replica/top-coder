import java.util.NoSuchElementException;

public class DoublyLinkedList<T> {
    int size;
    Node<T> first, last;

    public DoublyLinkedList() {
    }

    public void addFirst(T content) {
        final Node<T> originalFirst = first;
        final Node<T> newNode = new Node(content, null, originalFirst);
        first = newNode;

        if (originalFirst == null) {
            last = newNode;
        } else {
            originalFirst.prev = newNode;
        }
        size++;
    }

    public void add(int idx, T content) {
        if (idx == 0) {
            final Node<T> f = first;
            final Node<T> newNode = new Node(content, null, f);
            f.prev = newNode;
            first = newNode;

            size++;
            return;
        }

        Node<T> prev = first;

        for (int i = 0; i < idx - 1; i++) {
            prev = prev.next;
        }

        final Node<T> next = prev.next;
        final Node<T> newNode = new Node(content, prev, next);
        prev.next = newNode;

        if (next != null) {
            next.prev = newNode;
        }

        size++;
    }

    // REVIEW: ***** 이 메서드도 실수 안 하고 짜기 어려움 *****
    public T removeFirst() {
        if (first == null) throw new NoSuchElementException();

        final Node<T> f = first;
        final Node<T> next = first.next;
        final T ret = f.content;

        // REVIEW: 처음에 size==1 로 분류하려고 했는데 라이브러리의 분류 방법이 더 좋았음
        f.next = null;
        f.content = null;
        first = next;

        if (next == null) {
            last = null;
        } else {
            next.prev = null;

        }

        size--;

        return ret;
    }

    // REVIEW: first,last 처리 놓쳤었음
    // REVIEW: 헷갈린다..
    public T remove(int idx) {
        if (idx < 0 || idx >= size) throw new IndexOutOfBoundsException();

        Node<T> target = first;
        for (int i = 0; i < idx; i++) {
            target = target.next;
        }

        T ret = target.content;

        final Node<T> prev = target.prev;
        final Node<T> next = target.next;

        // REVIEW : 라이브러리에 구현된 코드가 더 나은듯? => 더 분류를 잘 해서 논리적이고 if문 depth가 1임
        if (target == first) {
            first = next;
        } else {
            prev.next = next;

            if (next != null) {
                next.prev = prev;
            }
        }

        target.prev = null;
        target.next = null;
        target.content = null;
        size--;

        return ret;
    }

    public int size() {
        return size;
    }

    public T getFirst() {
        if (first == null) throw new NoSuchElementException();
        return first.content;
    }

    public T getLast() {
        if (last == null) throw new NoSuchElementException();
        return last.content;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node<T> iter = first;

        for (; iter != null; iter = iter.next) {
            sb.append(iter.content + " ");
        }

        return sb.toString();
    }

    static class Node<T> {
        T content;
        Node<T> prev, next;

        Node(T content, Node<T> prev, Node<T> next) {
            this.content = content;
            this.prev = prev;
            this.next = next;
        }
    }
}
