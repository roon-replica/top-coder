// graph
// tp sort? 근데 양방향 그래프면 아닌데
// 먼저 방문해야 할 방과 나중에 방문할 방을 반드시 연속해서 방문해야 할 필요는 없어

// 모르겠다...

// bfs하면 중복 방문이 많이 생길거 같아서 문젠데..
// 시작은 중립 노드에서 하면 될거 같고
// 선행 방문이 필요한 노드는 일단 방문하지 않도록 하자
// 분류: 방문한 노드, 방문 안 한 노드,  
// pq?

// 그러고보니 구하는 값이 경로가 아니라 전체 탐색 가능 여부임
// 트리 만들고
// 위상정렬 + 사전방문 여부?

//////////////////////////////////////////////////
//////////////////////////////////////////////////
//////////////////////////////////////////////////
// 와 미쳤다.. 이게 위상정렬 가능 여부로 귀결되네....
// 근데 예제 3번 보고 사이클을 관찰했다면 위상정렬 떠올릴 수 있었을듯
// LV4+/동굴 탐험

import java.util.*;

class Solution {
    public boolean solution(int n, int[][] path, int[][] order) {
        boolean answer = true;
        List<Integer>[] adjs = new ArrayList[n];
        for(int i=0;i<adjs.length;i++) adjs[i] = new ArrayList<>();
        
        for(int[] p : path){
            int u = p[0];
            int v = p[1];
            
            adjs[u].add(v);
            adjs[v].add(u);
        }
        
        List<Integer>[] tree = new ArrayList[n];
        for(int i=0;i<tree.length;i++) tree[i] = new ArrayList<>();
        int ROOT = 0;
        boolean[] visited = new boolean[tree.length];
        Queue<Integer> q = new LinkedList<>();
        q.add(ROOT);
        
        for(;q.isEmpty() == false;){
            int here = q.poll();
            visited[here] = true;
            
            for(int nxt : adjs[here]){
                if(visited[nxt]) continue;
                tree[here].add(nxt);
                q.add(nxt);
            }
        }
        
        for(int[] o : order){
            tree[o[0]].add(o[1]);
        }
        
        answer = tpSort(tree);
        
        return answer;
    }
    
    // 위상정렬
    // - directed graph
    // - put all nodes that inDegree is 0
    // - poll 'here' node
    // - for 'nxt' node in graph[here] inDegree[nxt]--;
    // - if (inDegree[nxt] == 0), Queue.add(nxt) ... 
    // - if (count(visited node) == total) possible, else impossible 
    // == 사이클 존재 여부
    
    boolean tpSort(List<Integer>[] tree){
        int N = tree.length;
        int[] inDegree = new int[N];
        
        for(int i=0;i<N;i++){
            for(int nxt : tree[i]){
                inDegree[nxt]++;
            }
        }
        
        Queue<Integer> q = new LinkedList<>();
        
        for(int i=0;i<N;i++){
            if(inDegree[i] == 0) q.add(i);
        }
        
        int visitCount = 0;
        
        for(;q.isEmpty() == false;){
            int here = q.poll();
            visitCount++;
            
            for(int nxt : tree[here]){
                inDegree[nxt]--;
                if(inDegree[nxt] == 0) q.add(nxt);
            }
        }
        
        return visitCount == N;
    }
}
