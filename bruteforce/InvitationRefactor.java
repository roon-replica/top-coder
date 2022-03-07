import java.util.*;

public class InvitationRefactor {
    public static void main(String[] args) {
        int maxResult = bestInvitation(
                new String[]{"tennis", "tennis", "crossfit", "dance", "code", "chess", "chess"},
                new String[]{"chess", "crossfit", "skate", "rest in home", "mobile game", "mobile game", "dance"}
        );

        System.out.println(maxResult);
    }

    public static int bestInvitation(String[] firsts, String[] seconds) {
        //연관 배열 사용해서 2중 반복문 제거!!!
        Map<String, Integer> count = new HashMap<>();

        addAll(count, firsts);
        addAll(count, seconds);

        List<Integer> counts = new ArrayList<>(count.values());
        counts.sort(Comparator.reverseOrder());

        int maxCount = counts.get(0) + counts.get(1);
        return maxCount;

    }

    private static void addAll(Map<String, Integer> map, String[] arr) {
        for (String item : arr) {
            map.put(item, map.getOrDefault(item, 0) + 1);
        }
    }
}
