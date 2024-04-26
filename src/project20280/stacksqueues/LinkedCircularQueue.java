package project20280.stacksqueues;

import project20280.interfaces.Queue;

/**
 * Realization of a circular FIFO queue as an adaptation of a
 * CircularlyLinkedList. This provides one additional method not part of the
 * general Queue interface. A call to rotate() is a more efficient simulation of
 * the combination enqueue(dequeue()). All operations are performed in constant
 * time.
 */

public class LinkedCircularQueue<E> implements Queue<E> {
    private Node<E> tail = null;
    private int size = 0;

    private static class Node<E> {
        private E element;
        private Node<E> next;

        public Node(E e, Node<E> n) {
            element = e;
            next = n;
        }

        public E getElement() {
            return element;
        }

        public Node<E> getNext() {
            return next;
        }

        public void setNext(Node<E> n) {
            next = n;
        }
    }

    public static void main(String[] args) {
        LinkedCircularQueue<Integer> queue = new LinkedCircularQueue<>();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        System.out.println(queue.first());  // prints: 1
        System.out.println(queue.dequeue());  // prints: 1
        System.out.println(queue.first());  // prints: 2
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void enqueue(E e) {
        if (size == 0) {
            tail = new Node<>(e, null);
            tail.setNext(tail);  // circularly link to itself
        } else {
            Node<E> newest = new Node<>(e, tail.getNext());
            tail.setNext(newest);
            tail = newest;
        }
        size++;
    }

    @Override
    public E first() {
        if (isEmpty()) {
            return null;
        }
        return tail.getNext().getElement();
    }

    @Override
    public E dequeue() {
        if (isEmpty()) {
            return null;
        }
        Node<E> head = tail.getNext();
        if (head == tail) {
            tail = null;  // must be the only node left
        } else {
            tail.setNext(head.getNext());
        }
        size--;
        return head.getElement();
    }
}

