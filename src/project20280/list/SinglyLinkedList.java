package project20280.list;

import project20280.interfaces.List;

import java.util.Iterator;

public class SinglyLinkedList<E> implements List<E> {

    private static class Node<E> {
        private final E element;            // reference to the element stored at this node
        private Node<E> next;         // reference to the subsequent node in the list

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
    } //----------- end of nested Node class -----------

    private Node<E> head = null;

    private int size = 0;

    public SinglyLinkedList() {
    }              // constructs an initially
    //@Override
    public int size() {
        return size;
    }
    //@Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public E get(int position) {
        if (position >= size || position< 0){
            throw new ArrayIndexOutOfBoundsException("Position out of bounds");
        }
        Node<E> curr = head;
        for (int i=0; i<position;i++){
            curr = curr.getNext();
        }
        return curr.getElement();
    }

    @Override
    public void add(int position, E e) {
        if(isEmpty()){
            addFirst(e);
        }else if (position >= size || position< 0){
            throw new ArrayIndexOutOfBoundsException("Position out of bounds");
        }
        Node<E> curr = head;
        if (position == 0){
            addFirst(e);
        }
        else{
            for (int i=0; i<position-1;i++){
                curr = curr.getNext();
            }
            Node<E> newnode = new Node<E>(e, curr.getNext());
            curr.setNext(newnode);
            size++;
        }
    }


    @Override
    public void addFirst(E e) {
        head = new Node<>(e, head);
        size = size + 1;
    }

    @Override
    public void addLast(E e) {
        Node<E> newest = new Node<>(e, null);
        if (isEmpty()) {
            head = newest;
        } else {
            Node<E> tail = head;
            while (tail.getNext() != null) {
                tail = tail.getNext();
            }
            tail.setNext(newest);
        }
        size++;
    }


    @Override
    public E remove(int position) {
        Node<E> curr = head.getNext();
        if (position<0 || position>=size){
            throw new ArrayIndexOutOfBoundsException("out of bounds");
        }
        if (position==0){
            removeFirst();
            return curr.getElement();
        }else{
            Node<E> pre = head;
            for (int j=0; j<position-1;j++){
                pre = pre.getNext();
            }
            Node<E> del = pre.getNext();
            Node<E> aft = del.getNext();
            E elementRemoved = del.getElement();
            del.setNext(null);
            pre.setNext(aft);
            size--;
            return elementRemoved;
        }
    }

    @Override
    public E removeFirst() {
        if(isEmpty()){
            return null;
        }
        E answer = head.getElement();
        head = head.getNext();
        size--;
        if (size == 0) {
            head = null;
        }
        return answer;
    }

    @Override
    public E removeLast() {
        if (head == null) {
            return null;
        }
        if (head.getNext() == null) {
            E removedElement = head.getElement();
            head = null;
            size--;
            return removedElement;
        }

        Node<E> last = head.getNext();
        Node<E> prev = head;

        while (last.getNext() != null) {
            last = last.getNext();
            prev = prev.getNext();
        }
        E elementremoved = prev.getNext().getElement();
        prev.setNext(null);
        size--;
        return elementremoved;
    }

    //@Override
    public Iterator<E> iterator() {
        return new SinglyLinkedListIterator<E>();
    }

    private class SinglyLinkedListIterator<E> implements Iterator<E> {
        Node<E> curr = (Node<E>) head;

        @Override
        public boolean hasNext() {
            return curr != null;
        }

        @Override
        public E next() {
            E res = curr.getElement();
            curr = curr.next;
            return res;
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<E> curr = head;
        while (curr != null) {
            sb.append(curr.getElement());
            if (curr.getNext() != null)
                sb.append(", ");
            curr = curr.getNext();
        }
        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) {
        SinglyLinkedList<Integer> ll = new SinglyLinkedList<Integer>();
        System.out.println("ll " + ll + " isEmpty: " + ll.isEmpty());
        //LinkedList<Integer> ll = new LinkedList<Integer>();

        ll.addFirst(0);
        ll.addFirst(1);
        ll.addFirst(2);
        ll.addFirst(3);
        ll.addFirst(4);
        ll.addLast(-1);
        //ll.removeLast();
        //ll.removeFirst();
        //System.out.println("I accept your apology");
        //ll.add(3, 2);
        System.out.println(ll);
        ll.remove(5);
        System.out.println(ll);

//        for(Integer i : ll) {
//            System.out.println(i);
//        }
        /*
        ll.addFirst(-100);
        ll.addLast(+100);
        System.out.println(ll);

        ll.removeFirst();
        ll.removeLast();
        System.out.println(ll);

        // Removes the item in the specified index
        ll.remove(2);
        System.out.println(ll);
        
         */
    }
}
