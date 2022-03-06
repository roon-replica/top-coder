import java.util.*;

public class SampleBruteforce {

    public static void main(String[] args) {
        int maxResult = bestInvitation(
                new String[]{"tennis", "tennis", "crossfit", "dance", "code", "chess", "chess"},
                new String[]{"chess", "crossfit", "skate", "rest in home", "mobile game", "mobile game", "dance"}
        );

        System.out.println(maxResult);
    }

    public static int bestInvitation(String[] firsts, String[] seconds) {
        int maxCount = 0;

        Set<String> allset = new HashSet<>();
        Collections.addAll(allset, firsts);
        Collections.addAll(allset, seconds);

        List<String> all = new ArrayList<>(allset);

        for (int i = 0; i < all.size(); i++) {
            for (int j = 0; j < all.size(); j++) {
                int count = 0;

                String[] picked = new String[]{all.get(i), all.get(j)};
                for (int k = 0; k < firsts.length; k++) {
                    String[] likes = new String[]{firsts[k], seconds[k]};

                    boolean same = false;
                    for (String p : picked) {
                        for (String l : likes) {
                            if (p.equals(l)) {
                                same = true;
                                break;
                            }
                        }
                    }

                    if (same) {
                        count++;
                    }
                }

                maxCount = Math.max(maxCount, count);
            }
        }

        return maxCount;

    }
}