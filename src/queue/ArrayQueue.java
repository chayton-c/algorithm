package queue;

/**
 * 队列,先进先出
 *
 * @author hood  2020/4/20
 */
public class ArrayQueue {
    private int maxSize;
    private int front; // 队列头的第一个元素
    private int rear; // 队列的最后一个元素的后一个位置，因为希望空出一个空间作为约定
    private int[] arr;

    // 实例化
    public ArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        this.arr = new int[maxSize];
        front = -1;
        rear = -1;
    }

    public boolean isFull() {
        return rear == maxSize - 1;
    }

    public boolean isEmpty() {
        return rear == front;
    }

    public void addQueue(int n) {
        if (isFull()) {
            System.out.println("isFull");
            return;
        }

        arr[++rear] = n;
    }

    public int getQueue() {
        if (isEmpty())
            throw new RuntimeException("队列空，不能取数据");

        return arr[front++];
    }

    public void showDown() {
        if (isEmpty()) {
            System.out.println("队列空，无数据");
        }

        for (int i = 0; i < arr.length; i++) {
            System.out.println(String.format("arr[%d]=%d\n", i, arr[i]));
        }
    }

    public int peek() {
        if (isEmpty())
            throw new RuntimeException("队列空，不能取数据");

        return arr[front + 1];
    }
}
