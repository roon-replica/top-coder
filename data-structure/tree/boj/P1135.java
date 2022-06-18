package tree.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 얘도 트리 거꾸로 올라가면 되겠네
// 헷갈린다....름
public class P1135 {
    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        int[] parents = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int[] timeSum = new int[N];

        for (int i = N - 1; i > 0; i--) {
            int parent = parents[i];
            timeSum[parent] += timeSum[i] + 1;
        }

        System.out.println(timeSum[0]);
    }
}
