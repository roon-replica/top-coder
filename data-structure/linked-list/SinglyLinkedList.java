import java.util.NoSuchElementException;

public class SinglyLinkedList<T> {
    int size;

    Node<T> first, last;    // head, tail

    public SinglyLinkedList() {
    }

//    public void addFirst(T content) {
//        Node<T> newNode = new Node<>(content, first);
//
//        if (first == null) {
//            first = newNode;
//            last = newNode;
//        } else {
//            newNode.next = first;
//            first = newNode;
//        }
//        size++;
//    }

    // REVIEW : 조금 다른 구현 논리 (라이브러리가 구현한 방식)
    public void addFirst(T content) {
        final Node<T> f = first;
        final Node<T> newNode = new Node<>(content, f);
        first = newNode;
        newNode.next = f;

        if(f == null) last = newNode;

        size++;
    }

    //REVIEW : 많이 헷갈린다..
    public void add(int idx, T content) {
        if (size < idx) throw new IndexOutOfBoundsException();

        Node<T> before = first;

        for (int i = 0; i < idx - 1; i++) {
            before = before.next;
        }

        if (idx == 0) {
            Node<T> newNode = new Node(content, before);
            first = newNode;
        } else {
            Node<T> newNode = new Node(content, before.next);
            before.next = newNode;
        }

        size++;
    }

    public T removeFirst() {
        final Node<T> f = first;
        if (f == null) throw new NoSuchElementException();
        first = f.next;

        size--;
        return f.content;
    }

    public T getFirst() {
        if (first == null) throw new NoSuchElementException();
        return first.content;
    }

    public T getLast() {
        if (last == null) throw new NoSuchElementException();
        return last.content;
    }

    public int size() {
        return size;
    }

    private static class Node<T> {
        T content;
        Node<T> next;

        Node(T content, Node<T> next) {
            this.content = content;
            this.next = next;
        }
    }

    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder();
        for (Node<T> iterator = first; iterator != null; iterator = iterator.next) {
            ret.append(iterator.content + " ");
        }
        return ret.toString();
    }
}
