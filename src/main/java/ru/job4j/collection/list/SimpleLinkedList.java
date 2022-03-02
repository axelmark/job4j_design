package ru.job4j.collection.list;

import java.util.Iterator;
import java.util.Objects;

public class SimpleLinkedList<E> implements List<E> {

    private Node<E> first = null;
    private Node<E> last = null;

    private int modCount;
    private int size;

    @Override
    public void add(E value) {
        Node<E> newNode = new Node<E>(null, value, null);

        if (first == null) {
            first = newNode;
        } else {
            last = newNode.prev = first;
            first.next = last;
        }

        size++;
        modCount++;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        return null;
    }

    private static class Node<E> {

        E item;
        SimpleLinkedList.Node<E> next;
        SimpleLinkedList.Node<E> prev;

        Node(SimpleLinkedList.Node<E> prev, E element, SimpleLinkedList.Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    public static void main(String[] args) {
        SimpleLinkedList<Integer> ls = new SimpleLinkedList<>();
        ls.add(1);
        ls.add(2);
    }
}