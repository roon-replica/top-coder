package tree.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 트리 구조인지 파악하는게 핵심인듯
public class P14267 {
    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int[] input1 = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int n = input1[0];
        int m = input1[1];
        int[] parentInput = Arrays.stream(br.readLine().split(" ")).mapToInt(str -> Integer.parseInt(str) - 1).toArray();

        List<Integer>[] children = new ArrayList[n + 2];
        for (int i = 0; i < n + 2; i++) {
            children[i] = new ArrayList<>();
        }

        for (int i = 0; i < n; i++) {
            int parent = parentInput[i];
            if(parent < 0) continue;

            children[parent].add(i);
        }

        int[] phraseValues = new int[n + 2];

        for (int i = 0; i < m; i++) {
            int[] info = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int target = info[0] - 1;
            int value = info[1];

            phraseValues[target] += value;
        }

        for(int num = 0; num < n; num++){
            int val = phraseValues[num];

            for(int child : children[num]){
                phraseValues[child] += val;
            }
        }

        for(int num = 0; num < n ; num++){
            System.out.print(phraseValues[num]+" ");
        }
    }
}
