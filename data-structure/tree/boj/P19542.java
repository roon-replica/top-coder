package tree.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 생각해낸 전략
// 리프노드들에서 거리 d 떨어진 것들만 방문하면 될 것임
// 그래서 리프노드에서 거리 d 이하인 노드들 제거하기
// (남은 노드 -1) *2 가 답?

// https://www.acmicpc.net/problem/19542
// 골드 4, 좀 까다로웠다.
// 간단하지만 관찰이 필요했고
// 구현도 까다로운듯

public class P19542 {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st = new StringTokenizer("");

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        List<Integer>[] graph = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) graph[i] = new ArrayList<>();

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph[u].add(v);
            graph[v].add(u);
        }

        parent = new int[N + 1];
        visited = new boolean[N + 1];
        List<Integer> leafs = new ArrayList<>();

        Queue<Integer> q = new LinkedList<>();
        q.add(S);
        visited[S] = true;

        for (; q.isEmpty() == false; ) {
            int here = q.poll();
            boolean isLeaf = true;

            for (int nxt : graph[here]) {
                if (visited[nxt]) continue;

                visited[nxt] = true;
                parent[nxt] = here;
                isLeaf = false;

                q.add(nxt);
            }

            if (isLeaf) {
                leafs.add(here);
            }
        }

        int sum = 0;

        visited = new boolean[N + 1];

        for (int leaf : leafs) {
            dist = 0;
            sum += Math.max(0, (find(leaf) - D)) * 2;

            // System.out.println(dist);
        }

        System.out.println(sum);
    }

    private static int N, D, S;
    private static int dist;
    private static boolean[] visited;
    private static int[] parent;

    private static int find(int idx) {
        if (idx == S || visited[idx]) {
            return dist;
        } else {
            dist++;
            if (D < dist) visited[idx] = true; // 이 코드가 왜 필요하지????????????????? -> 방문한 노드 표시해야 되서
            return find(parent[idx]);
        }
    }
}
