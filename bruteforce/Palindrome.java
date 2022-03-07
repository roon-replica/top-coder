// 문자열 s 뒤에 문자를 몇개 붙여서 회문을 만들려고 한다.
// 생성할 수 있는 가장 짧은 회문의 길이는?

public class Palindrome {
    public static void main(String[] args) {
        System.out.println(find("nbaaqweqweqweqweqweqweqweqweqweqweqweqweadfafsafasf"));
    }

    //약간 까다롭네
    public static int find(String s) {
        int minlen = s.length() * 2;

        for (int idx = 0; idx <= s.length(); idx++) {
            String rev = new StringBuilder(s.substring(0, idx)).reverse().toString();
            String s2 = s + rev;

            boolean ispal = checkIfPalindrome(s2);
            if (ispal) {
                minlen = Math.min(minlen, s2.length());
            }
        }

        return minlen;
    }

    private static boolean checkIfPalindrome(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != s.charAt(s.length() - 1 - i)) {
                return false;
            }
        }
        return true;
    }
}
