package project20280.stacksqueues;

public class ArrayBoundStack<E> extends ArrayStack<E> {
    private static final int MAX_CAPACITY = 10;
    public ArrayBoundStack() {
        super(MAX_CAPACITY);
    }
    public ArrayBoundStack(int capacity) {
        super(capacity);
    }
    @Override
    public void push(E element) {
        if (size() >= MAX_CAPACITY) {
            throw new IllegalStateException("Stack is full. Max capacity is " + MAX_CAPACITY);
        }
        super.push(element);
    }
    public static void main(String[] args) {
        ArrayBoundStack<Integer> stack = new ArrayBoundStack<>(5);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        // stack.push(6); // Uncommenting this line will trigger the IllegalStateException
        System.out.println(stack.toString());
    }
}