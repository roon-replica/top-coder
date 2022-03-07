import java.util.ArrayList;
import java.util.List;

public class InterestingDigitsRefactor {
    public static void main(String[] args) {
        // 수학적 접근
        // 예를 들어 n진수 abc가 있을 때 10진법으로 나타내면
        // a*n*n + b*n + c
        // = (a*(n-1) + 2a + b) *(n-1) + (a+b+c)
        // (a+b+c)가 n-1의 배수라면 abc는 n-1의 배수이다.
        // 따라서 n-1과 그 약수들이 답이다.

        Integer[] result = getSpecialDigits(10);
        for (int res : result) {
            System.out.print(res + " ");
        }
    }

    private static Integer[] getSpecialDigits(int base) {
        List<Integer> res = new ArrayList<>();
        if (base <= 1) {
            return res.toArray(new Integer[0]);
        }

        base -= 1;

        for (int div = 2; div <= base; div++) {
            if (base % div == 0) {
                res.add(div);
            }
        }

        return res.toArray(new Integer[0]);
    }
}
