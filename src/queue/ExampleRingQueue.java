package queue;

import java.util.Arrays;

/**
 * @author hood  2020/7/7
 */
public class ExampleRingQueue {
    private int size;
    // 指向队列的第一个元素
    private int front;
    // 指向队列的后一个元素的后一个位置
    private int rear;
    private int[] arr;

    public ExampleRingQueue(int size) {
        this.size = size;
        this.arr = new int[size];
    }

    private int size() {
        return (rear + size - front) % size;
    }

    private boolean isFull() {
        return (rear + 1) % size == front;
    }

    private boolean isEmpty() {
        return rear == front;
    }

    private void push(int i) {
        if (isFull())
            throw new RuntimeException("is full");

        this.arr[rear] = i;
        rear = ++rear % size;

        System.out.println("push:" + i);
        System.out.println("size:" + size());
        print();
    }

    private int pop() {
        if (isEmpty())
            throw new RuntimeException("is empty");

        int result = this.arr[front];
        this.arr[front] = 0;
        front = ++front % size;

        System.out.println("pop:" + result);
        System.out.println("size:" + size());
        print();

        return result;
    }

    private void print() {
        System.out.println(Arrays.toString(showDown()));
    }

    private int[] showDown() {
        return this.arr;
    }

    public static void main(String[] args) {
        ExampleRingQueue exampleRingQueue = new ExampleRingQueue(4);
        exampleRingQueue.push(1);
        exampleRingQueue.push(2);
        exampleRingQueue.push(3);
        exampleRingQueue.pop();
        exampleRingQueue.push(4);
        exampleRingQueue.pop();
        exampleRingQueue.push(4);

        exampleRingQueue.pop();
        exampleRingQueue.pop();
        exampleRingQueue.pop();
        exampleRingQueue.pop();
    }

}
