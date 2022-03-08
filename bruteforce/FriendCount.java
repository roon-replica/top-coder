import java.util.HashSet;
import java.util.Set;

public class FriendCount {
    public static void main(String[] args) {
        String[] friends = new String[]{
                "NYY",
                "YNY",
                "YYN"
        };

//        String[] friends = new String[]{
//                "NYNNN",
//                "YNYNN",
//                "NYNYN",
//                "NNYNY",
//                "NNNYN"
//        };

//        String[] friends = new String[]{
//                "NNNNYNNNNN",
//                "NNNNYNYYNN",
//                "NNNYYYNNNN",
//                "NNYNNNNNNN",
//                "YYYNNNNNNY",
//                "NNYNNNNNYN",
//                "NYNNNNNYNN",
//                "NYNNNNYNNN",
//                "NNNNNYNNNN",
//                "NNNNYNNNNN"
//        };

        System.out.println(highestCount(friends));
    }

    //왜 어렵지...
    // 문제 잘 못 이해함 (친구의 친구 기준이 1다리 거치는거 까지만임..)
    // 그래서 union find나 floyd로 판별하는 게 아니라 단순한 2중 반복문으로 처리해야 했음
    // 직접 친구, 간접 친구 개념을 인지하고 단순하게 구현했으면 쉬운건데 떠올리지 못함
    public static int highestCount(String[] friends) {
        int N = friends.length;
        int maxCount = 0;

        boolean[][] state = new boolean[N][N];
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                state[r][c] = (friends[r].charAt(c) == 'Y');
            }
        }

        for (int r = 0; r < N; r++) {
            Set<Integer> friendset = new HashSet<>();
            for (int c = 0; c < N; c++) {
                if (state[r][c]==false) continue;

                friendset.add(c);
                for(int c2=0;c2<N;c2++) {
                    if (state[c][c2] == false) continue;
                    friendset.add(c2);
                }
            }

            maxCount = Math.max(maxCount, friendset.size()-1);
        }

        return maxCount;
    }

}
