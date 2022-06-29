package tree.boj.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// https://www.acmicpc.net/problem/13325
// 골드 3 , 꽤 까다로움..
public class P13325 {
    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int H = Integer.parseInt(br.readLine());
        int[] inputs = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int EDGE_COUNT = (1 << (H + 1)) - 2;
        long sum = 0;

        for (int idx = EDGE_COUNT - 1; idx > 0; idx -= 2) {
            int maxVal = Math.max(inputs[idx], inputs[idx - 1]);
            sum += maxVal;
            int parentIdx = idx / 2 - 1;

            if (parentIdx < 0) {
                sum += maxVal;
                break;
            } else {
                inputs[parentIdx] += maxVal;
            }
        }

        System.out.println(sum);

    }
}
