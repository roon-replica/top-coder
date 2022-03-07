import java.util.ArrayList;
import java.util.List;

public class InterestingDigits {
    public static void main(String[] args) {
        Integer[] result = getSpecialDigits(30);
        for (int res : result) {
            System.out.print(res + " ");
        }
    }

    // 약간 당황했는데
    // 대충 코드 스켈레톤을 짜다보니 어떻게 해야할 지 명확히 보였다
    private static Integer[] getSpecialDigits(int base) {
        List<Integer> ret = new ArrayList<>();

        for (int b = 2; b <= base; b++) {
            boolean special = true;

            for (int f = 1; f <= 100; f++) {
                int tmp = b * f;
                int digitSum = 0;

                for (; tmp != 0; ) {
                    digitSum += (tmp % base);
                    tmp /= base;
                }

                if ((digitSum % b) != 0) {
                    special = false;
                }
            }

            if (special) {
                ret.add(b);
            }
        }

        return ret.toArray(new Integer[0]);
    }
}
