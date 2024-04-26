package project20280.list;

import project20280.interfaces.List;

import java.util.Iterator;

public class CircularlyLinkedList<E> implements List<E> {

    private class Node<T> {
        private final T data;
        private Node<T> next;

        public Node(T e, Node<T> n) {
            data = e;
            next = n;
        }

        public T getData() {
            return data;
        }

        public void setNext(Node<T> n) {
            next = n;
        }

        public Node<T> getNext() {
            return next;
        }
    }

    private Node<E> tail = null;
    private int size = 0;

    public CircularlyLinkedList() {

    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public E get(int i) {
        // TODO
        if (i >= size || i< 0){
            throw new ArrayIndexOutOfBoundsException("Position out of bounds");
        }
        Node<E> curr = tail.getNext();
        for (int j=0; j<i;j++){
            curr = curr.getNext();
        }
        return curr.getData();
    }

    @Override
    public void add(int i, E e) {
        // TODO
        if(isEmpty()){
            addFirst(e);
        }else if (i >= size || i< 0){
            throw new ArrayIndexOutOfBoundsException("Position out of bounds");
        }
        Node<E> curr = tail.getNext();
        if (i == 0){
            addFirst(e);
        }
        else{
            for (int j=0; j<i-1;j++){
                curr = curr.getNext();
            }
            Node<E> newnode = new Node<E>(e, curr.getNext());
            curr.setNext(newnode);
            size++;
        }
    }

    @Override
    public E remove(int i) {
        // TODO
        if (i >= size || i < 0) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        if (i == 0) {
            return removeFirst();
        } else if (i == size - 1) {
            return removeLast();
        } else {
            Node<E> curr = tail.getNext();
            for (int j = 0; j < i - 1; j++) {
                curr = curr.getNext();
            }
            Node<E> nodeToRemove = curr.getNext();
            curr.setNext(nodeToRemove.getNext());
            size--;
            return nodeToRemove.getData();
        }
    }

    public void rotate() {
        // TODO
        if (tail != null)
            tail = tail.getNext( );
    }

    private class CircularlyLinkedListIterator<E> implements Iterator<E> {
        Node<E> curr = (Node<E>) tail;

        @Override
        public boolean hasNext() {
            return curr != tail;
        }

        @Override
        public E next() {
            E res = curr.data;
            curr = curr.next;
            return res;
        }

    }

    @Override
    public Iterator<E> iterator() {
        return new CircularlyLinkedListIterator<E>();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public E removeFirst() {
        // TODO
        if (isEmpty( )) return null;
        Node<E> head = tail.getNext( );
        if (head == tail) tail = null;
        else tail.setNext(head.getNext( ));
        size = size - 1;
        return head.getData( );
    }

    @Override
    public E removeLast() {
        // TODO
        if (isEmpty()) return null;
        Node<E> head = tail.getNext();
        E data;
        if (head == tail) {
            data = tail.getData();
            tail = null;
        } else {
            Node<E> prev = head;
            while (prev.getNext() != tail) {
                prev = prev.getNext();
            }
            data = tail.getData();
            prev.setNext(head);
            tail = prev;
        }
        size--;
        return data;

    }

    @Override
    public void addFirst(E e) {
        // TODO
        if (size == 0) {
            tail = new Node<>(e, null);
            tail.setNext(tail);
        } else {
            Node<E> newest = new Node<>(e, tail.getNext( ));
            tail.setNext(newest);
        }
        size++;
    }

    @Override
    public void addLast(E e) {
        // TODO
        addFirst(e);
        tail = tail.getNext( );
    }


    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<E> curr = tail;
        do {
            curr = curr.next;
            sb.append(curr.data);
            if (curr != tail) {
                sb.append(", ");
            }
        } while (curr != tail);
        sb.append("]");
        return sb.toString();
    }


    public static void main(String[] args) {
        CircularlyLinkedList<Integer> ll = new CircularlyLinkedList<Integer>();
        for (int i = 10; i < 20; ++i) {
            ll.addLast(i);
        }

        System.out.println(ll);

        ll.removeFirst();
        System.out.println(ll);

        ll.removeLast();
        System.out.println(ll);

        ll.rotate();
        System.out.println(ll);

        ll.removeFirst();
        ll.rotate();
        System.out.println(ll);

        ll.removeLast();
        ll.rotate();
        System.out.println(ll);

        for (Integer e : ll) {
            System.out.println("value: " + e);
        }

    }
}
