package queue;

import java.util.Arrays;
import java.util.Queue;

/**
 * @author hood  2020/6/23
 */
public class RingQueue {
    private int size;
    private int head = 0;
    private int tail = 0;
    private int[] arr;
    private int capacity = 0;

    public RingQueue(int size) {
        this.size = size;
        this.arr = new int[size];
    }

    private boolean isFull() {
        return capacity == size;
    }

    private boolean isEmpty() {
        return capacity == 0;
    }

    private void push(int i) {
        if (isFull())
            throw new RuntimeException("is full");

        this.arr[tail] = i;
        tail = ++tail % size;
        capacity++;

        System.out.println("push:" + i);
        print();
    }

    private int pop() {
        if (isEmpty())
            throw new RuntimeException("is empty");

        int result = this.arr[head];
        this.arr[head] = 0;
        capacity--;
        head = ++head % size;

        System.out.println("pop:" + result);
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
        RingQueue ringQueue = new RingQueue(3);
        ringQueue.push(1);
        ringQueue.push(2);
        ringQueue.push(3);
        ringQueue.pop();
        ringQueue.pop();
        ringQueue.pop();
        ringQueue.push(1);
        ringQueue.push(2);
        ringQueue.push(3);
        ringQueue.pop();
        ringQueue.pop();
        ringQueue.pop();
    }

}
