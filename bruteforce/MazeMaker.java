import java.util.LinkedList;
import java.util.Queue;

public class MazeMaker {
    public static void main(String[] args) {
//        System.out.println(longestPath(
//                new String[] {"...","...","..."},
//                0,
//                1,
//                new int[] {1,0,-1,0},
//                new int[] {0,1,0,-1}
//        ));

//        System.out.println(longestPath(
//                new String[] {"X.X","...","XXX","X.X"},
//                0,
//                1,
//                new int[] {1,0,-1,0},
//                new int[] {0,1,0,-1}
//        ));

        System.out.println(longestPath(
                new String[]{".......", "X.X.X..", "XXX...X", "....X..", "X....X.", "......."},
                5,
                0,
                new int[]{1, 0, -1, 0, -2, 1},
                new int[]{0, -1, 0, 1, 3, 0}
        ));
    }

    // 다 해보면 되지
    // 좀 더 구체적으로,
    // 각 칸을 최대값으로 초기화해두고
    // 탈출자가 갈 수 있는 모든 경로 다 가보고, 각 칸에 최소 몇번만에 도달하는지 기록해두기
    // 그 값의 최대값이 답임
    // bfs like

    // 주의
    // 갈 수 있는 곳, 없는 곳
    // 초기화 값이랑 동일하면 -1로 표시
    // *** 마지막에 최대 이동 횟수 세아릴 때 'X'인 칸은 빼고 세아려야 했다. 깜빡함 주의 ***
    public static int longestPath(String[] maze, int sr, int sc, int[] moveRow, int[] moveCol) {
        int R = maze.length;
        int C = maze[0].length();
        int[][] minDist = new int[R][C];

        final int MAX_DIST = 200;
        final int[][] dirs = new int[][]{{0, -1}, {-1, 0}, {0, 1}, {1, 0}};

        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                minDist[r][c] = MAX_DIST;
            }
        }

        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{sr, sc});  // (r,c)
        minDist[sr][sc] = 0;

        for (; q.isEmpty() == false; ) {
            int[] here = q.poll();
            int r = here[0];
            int c = here[1];

            for (int i = 0; i < moveRow.length; i++) {
                int nr = r + moveRow[i];
                int nc = c + moveCol[i];
                if (nr < 0 || nr >= R || nc < 0 || nc >= C) continue;
                if (maze[nr].charAt(nc) == 'X') continue;
                if (minDist[nr][nc] <= minDist[r][c] + 1) continue;

                minDist[nr][nc] = minDist[r][c] + 1;
                q.add(new int[]{nr, nc});
            }
        }

        int maxDist = 0;
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (maze[r].charAt(c) == 'X') continue;
                maxDist = Math.max(maxDist, minDist[r][c]);
            }
        }

        if (maxDist == MAX_DIST) maxDist = -1;

        return maxDist;
    }
}
