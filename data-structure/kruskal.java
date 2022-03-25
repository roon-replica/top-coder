// dfs하고 나서 어떻게 하는 거 같은데... 모르겠다

// bfs하면서 dp[r][c] = 최소 비용으로 해도 되려나? xxxx

// MST.. kruskal..
// union find
// sort by edge cost, pick n-1 edge? -> n nodes are picked

import java.util.*;

public class kruskal {
    public int solution(int[][] land, int height) {
        int answer = 0;
        int N = land.length;

        List<int[]> edges = new ArrayList<>(); // (from,to,cost)
        int[][] dirs = new int[][]{{0, -1}, {-1, 0}, {0, 1}, {1, 0}};

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                for (int[] dir : dirs) {
                    int nr = r + dir[0];
                    int nc = c + dir[1];

                    if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;

                    int node1 = r * N + c % N;
                    int node2 = nr * N + nc % N;

                    int diff = Math.abs(land[nr][nc] - land[r][c]);
                    if (diff <= height) diff = 0;
                    edges.add(new int[]{node1, node2, diff});
                    edges.add(new int[]{node2, node1, diff});
                }
            }
        }

        //sort by edge cost
        edges.sort((e1, e2) -> e1[2] - e2[2]);

        DisJointSet dset = new DisJointSet(N * N + 1);

        //pick edges
        for (int cnt = 0; cnt < edges.size(); cnt++) {
            int[] edge = edges.get(cnt);
            int node1 = edge[0];
            int node2 = edge[1];
            int cost = edge[2];

            boolean connected = dset.merge(node1, node2);

            if (connected) answer += cost;

        }

        return answer;
    }

    class DisJointSet {
        int[] parent;

        DisJointSet(int sz) {
            parent = new int[sz];
            for (int i = 0; i < sz; i++) parent[i] = i;
        }

        public int find(int idx) {
            if (parent[idx] == idx) return idx;
            else return parent[idx] = find(parent[idx]);
        }

        public boolean merge(int idx1, int idx2) {
            int p1 = find(idx1);
            int p2 = find(idx2);

            if (p1 == p2) return false;

            parent[p1] = p2;

            return true;
        }
    }
}