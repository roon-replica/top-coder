public class CrazyRobot {
    public static void main(String[] args) {
//        System.out.println(getProbability(2, 25, 25, 25, 25));
        System.out.println(getProbability(2, 50, 50, 0, 0));
    }

    public static double getProbability(int n, int east, int west, int south, int north) {
        probs = new int[]{west, north, east, south};
        prob = 0;
        dfs(50, 50, n, 1);
        return prob;
    }

    private static boolean[][] visited = new boolean[100][100];
    private static int[][] dirs = new int[][]{{0, -1}, {-1, 0}, {0, 1}, {1, 0}};
    private static int[] probs;
    private static double prob;

    private static void dfs(int r, int c, int moveLimit, double hereProb) {
        if (visited[r][c]) return;

        if (moveLimit == 0) {
            prob += hereProb;
            return;
        }

        visited[r][c] = true;

        for (int i = 0; i < dirs.length; i++) {
            int nr = r + dirs[i][0];
            int nc = c + dirs[i][1];

            dfs(nr, nc, moveLimit - 1, hereProb * probs[i] / 100);
        }

        visited[r][c] = false;
    }
}
