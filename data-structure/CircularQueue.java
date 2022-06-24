// size+1번째는 비워두는 전략? => xxxxxxxxxxx(이해 못했을 때 하는 말)

// empty랑 full을 구별하는게 핵심인데........
// empty도 head == tail일 떄고
// full도  tail = head + size 또는 tail = head일 때 같아서 문제...
// 아.......... empty랑 full 어떻게 구별하지...
// 모르겠다..


// size +1 번째를 비워두는게 아니라
// 그냥 head랑 tail이랑 한 칸 띄워 놓는 느낌...
// full / empty 검사를 먼저 하네

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Collectors;

public class CircularQueue<T> {
    private T[] elements;
    int size;
    int head;
    int tail;

    public CircularQueue(int size) {
        elements = (T[]) new Object[size + 1];
        this.size = size + 1;
    }

    public void push(T element) {
        if ((tail + 1) % size == head) {
            System.out.println("full");
            return;
        }

        tail = ((tail + 1) % size);
        elements[tail] = element;
    }

    public T poll() {
        if (head == tail) {
            System.out.println("empty");
            return null;
        }

        head = ((head + 1) % size);
        T ret = elements[head];
        elements[head] = null;

        return ret;
    }

    public void print() {
        String elementStr = Arrays.stream(elements)
                .map(String::valueOf)
                .map(str -> str == "null" ? "-" : str)
                .collect(Collectors.joining(" "));

        int width = "____________ circular queue state __________________".length();


        System.out.println("____________ circular queue state __________________");
        String headTail = String.format("head: %d, tail: %d", head, tail);

        String wall1 = " ".repeat(width - 1 - headTail.length()) + "|";
        System.out.println(headTail + wall1);

        System.out.print("elements: " + elementStr);
        String wall2 = " ".repeat(width - 1 - ("elements: " + elementStr).length()) + "|";
        System.out.println(wall2);
        System.out.println("_____________________________________________________");
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        CircularQueue<Integer> circularQueue = new CircularQueue<>(5);
        while (true) {
            System.out.println("> command : (1) push, (2) poll");
            String command = br.readLine();

            if (command.equals("1")) {
                System.out.println("input item");
                int item = Integer.parseInt(br.readLine());
                circularQueue.push(item);
            } else if (command.equals("2")) {
                System.out.println("polled item = " + circularQueue.poll());
            } else {
                System.out.println("command not exist.");
            }

            circularQueue.print();
        }
    }
}
