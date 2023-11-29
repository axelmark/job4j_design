package ru.job4j.tree;

import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;

public class SimpleTree<E> implements Tree<E> {

    private final Node<E> root;

    public SimpleTree(final E root) {
        this.root = new Node<>(root);
    }

    /**
     * Найти узел по значению parent и добавлять в него дочерний узел со значением child.
     * Проверить, что значения child еще нет в дереве, а parent есть.
     * Если child есть, то метод должен вернуть false.
     */
    @Override
    public boolean add(E parent, E child) {
        boolean rsl = false;
        if (findBy(parent).isPresent()) {
            if (findBy(child).isEmpty()) {
                root.children.add(new Node<>(child));
                rsl = false;
            }
        }
        return rsl;
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);

        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.value.equals(value)) {
                rsl = Optional.of(el);
                break;
            }
            data.addAll(el.children);
        }
        return rsl;
    }
}