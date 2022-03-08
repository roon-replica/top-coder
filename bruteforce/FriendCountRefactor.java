import java.util.HashSet;
import java.util.Set;

public class FriendCountRefactor {
    public static void main(String[] args) {
//        String[] friends = new String[]{
//                "NYY",
//                "YNY",
//                "YYN"
//        };

//        String[] friends = new String[]{
//                "NYNNN",
//                "YNYNN",
//                "NYNYN",
//                "NNYNY",
//                "NNNYN"
//        };

        String[] friends = new String[]{
                "NNNNYNNNNN",
                "NNNNYNYYNN",
                "NNNYYYNNNN",
                "NNYNNNNNNN",
                "YYYNNNNNNY",
                "NNYNNNNNYN",
                "NYNNNNNYNN",
                "NYNNNNYNNN",
                "NNNNNYNNNN",
                "NNNNYNNNNN"
        };

        System.out.println(highestCount(friends));
    }

    // 더 깔끔하다
    // floyd 처럼 해도 되는거였네..
    // 생각을 코드로 옮기는 걸 잘 못했다
    public static int highestCount(String[] friends) {
        int N = friends.length;
        int maxCount = 0;

        for (int r = 0; r < N; r++) {
            int count = 0;
            for (int c = 0; c < N; c++) {
                if (friends[r].charAt(c) == 'Y') {
                    count++;
                } else {
                    for (int k = 0; k < N; k++) {
                        if (friends[r].charAt(k) == 'Y' && friends[c].charAt(k) == 'Y') {
                            count++;
                            break;
                        }
                    }
                }
            }
            maxCount = Math.max(maxCount, count - 1);
        }

        return maxCount;
    }
}
