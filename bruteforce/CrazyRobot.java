public class CrazyRobot {
    public static void main(String[] args) {
        System.out.println(getProbability(5, 25, 25, 25, 25));
    }

    public static double getProbability(int n, int east, int west, int south, int north) {
        probs = new int[]{west, north, east, south};
        prob = 1;
        dfs(50, 50, n);
        return prob;
    }

    private static boolean[][] visited = new boolean[100][100];
    private static int[][] dirs = new int[][]{{0, -1}, {-1, 0}, {0, 1}, {1, 0}};
    private static int[] probs;
    private static double prob;

    private static void dfs(int r, int c, int moveLimit) {
        if (visited[r][c] || moveLimit == 0) return;
        System.out.println(r + " " + c + " " + prob);
        visited[r][c] = true;

        for (int i = 0; i < dirs.length; i++) {
            int nr = r + dirs[i][0];
            int nc = c + dirs[i][1];

            prob *= probs[i];
            prob /= 100;

            dfs(nr, nc, moveLimit - 1);
        }
    }
}
