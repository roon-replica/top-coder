package tree.boj.gold;

// https://www.acmicpc.net/problem/11437
// 공통 조상을 알아내는 쿼리가 1개가 아니라 여러개인 문제
// 쿼리는 최대 10만개 -> 쿼리 1개당 1억 / 10만번의 연산 가능 = 1000번의 연산 가능
// 각 노드에서 따라 올라가면서 공통 조상 찾으면 되겠는데?

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class LCA {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        Node[] nodes = new Node[N + 1];
        for (int i = 0; i <= N; i++) {
            nodes[i] = new Node(i);
        }

        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            nodes[u].children.add(v);
            nodes[v].children.add(u);
        }

        Queue<Node> q = new LinkedList<>();
        q.add(nodes[1]);
        nodes[1].parent = -1;
        nodes[1].depth = 1;
        boolean[] visited = new boolean[N + 1];

        for (; q.isEmpty() == false; ) {
            Node here = q.poll();
            visited[here.id] = true;

            for (int nxt : here.children) {
                if (visited[nxt]) continue;
                q.add(nodes[nxt]);
                nodes[nxt].parent = here.id;
                nodes[nxt].depth = here.depth + 1;
            }
        }

        int M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            // fiind(u,v)
            System.out.println(find(u, v, nodes));
        }
    }

    static int find(int u, int v, Node[] nodes) {
        for (; u != v; ) {
            int du = nodes[u].depth;
            int dv = nodes[v].depth;

            if (du > dv) {
                u = nodes[u].parent;
            } else if (du < dv) {
                v = nodes[v].parent;
            } else {
                u = nodes[u].parent;
                v = nodes[v].parent;
            }
        }

        return u;
    }

    static class Node {
        int id;
        int parent;
        int depth;
        List<Integer> children = new ArrayList<>();

        Node(int id) {
            this.id = id;
        }
    }
}
