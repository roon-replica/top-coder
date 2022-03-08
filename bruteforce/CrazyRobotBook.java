public class CrazyRobotBook {
    public static void main(String[] args) {
//        System.out.println(getProbability(2, 25, 25, 25, 25));
        System.out.println(getProbability(2, 50, 50, 0, 0));
    }

    public static double getProbability(int n, int east, int west, int south, int north) {
        probs = new double[]{west / 100.0, north / 100.0, east / 100.0, south / 100.0};

        return dfs(50, 50, n);
    }

    private static boolean[][] visited = new boolean[100][100];
    private static int[][] dirs = new int[][]{{0, -1}, {-1, 0}, {0, 1}, {1, 0}};
    private static double[] probs;

    // 비슷한데 내 코드보다 더 낫다
    // dfs 함수 파라미터도 더 적고, 전역 변수도 안 쓰고 함수가 값을 리턴하는 식이라서 더 나음
    // probs 초기화도 바로 double로 하는 것도 더 좋은 듯
    private static double dfs(int r, int c, int moveLimit) {
        if (visited[r][c]) return 0;
        if (moveLimit == 0) return 1;

        visited[r][c] = true;

        double ret = 0;

        for (int i = 0; i < dirs.length; i++) {
            int nr = r + dirs[i][0];
            int nc = c + dirs[i][1];

            ret += dfs(nr, nc, moveLimit - 1) * probs[i];
        }

        visited[r][c] = false;

        return ret;
    }
}
