package tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/1167
// 트리의 지름 : 트리에서 임의의 두 정점 사이의 거리 중 가장 먼 길이
public class Diameter {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        List<Info>[] graph = makeGraph();

        int start = 1;
        int mostFarNode = bfs(start, graph).to;
        int diameter = bfs(mostFarNode, graph).dist;

        System.out.println(diameter);
    }

    // return (mostFarNode, mostFarDist)
    static Info bfs(int start, List<Info>[] graph) {
        Info ret = new Info(0, 0);

        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        int[] dist = new int[graph.length + 2];
        Set<Integer> visited = new HashSet<>();
        visited.add(start);

        for (; q.isEmpty() == false; ) {
            int here = q.poll();
            // visited.add(here);        // 여기서 visited 추가하면서 체크하면 메모리 초과 발생!!!

            for (Info nxtInfo : graph[here]) {
                int nxtNode = nxtInfo.to;
                int nxtDist = nxtInfo.dist;

                if(visited.contains(nxtNode)) continue;

                dist[nxtNode] = dist[here] + nxtDist;
                visited.add(nxtNode);

                if (dist[nxtNode] > ret.dist) {
                    ret.to = nxtNode;
                    ret.dist = dist[nxtNode];
                }

                q.add(nxtNode);
            }
        }

        return ret;
    }

    // 입력 처리하고 인접 리스트 만들어서 반환
    static List<Info>[] makeGraph() throws IOException {
        int V = Integer.parseInt(br.readLine());

        List<Info>[] graph = new ArrayList[V + 2];
        for (int i = 0; i <= V + 1; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < V; i++) {
            String[] input = br.readLine().split(" ");
            int from = Integer.parseInt(input[0]);

            for (int j = 1; j < input.length - 1; j += 2) {
                int to = Integer.parseInt(input[j]);
                int dist = Integer.parseInt(input[j + 1]);

                graph[from].add(new Info(to, dist));
                graph[to].add(new Info(from, dist));
            }
        }

        return graph;
    }

    private static class Info {
        int to;
        int dist;

        Info(int to, int dist) {
            this.to = to;
            this.dist = dist;
        }
    }
}
