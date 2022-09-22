// LV4 / 올바른 괄호의 갯수
// https://school.programmers.co.kr/learn/courses/30/lessons/12929?language=java

// 괄호 쌍 개수 조건이 14쌍까지니까 완전 탐색하면 2^28 = 100만 * 256 = 2억.
// 그리고 검사까지 하면 20억 수준.
// 20억 수준이면 시뮬레이션하면 시간초과남

// 가지치기?
// 괄호쌍들 만들어가며 시뮬레이션 하는데 안되는 경우는 더 이상 진행하지 않는 방식으로
// 시간복잡도는? -> 글쎄... 정확히는 모르겠는데 꽤 많이 줄어들듯

// 올바른 괄호 검사 로직?
// stack 사용?

// 아 근데 가지치기 적용하기가...
// 중간문자열이 잘못된 문자열인지 어케 판단하지.. 모르겠다...

// 괄호문자열 만들어갈 때 ( 보다 )가 더 많으면 무조건 잘못된거?

// 그리고 여는 괄호, 닫는 괄호 개수도 각각 N개 여야만 하네..!

// 총평
// - 가지치기하면 시간 안에 동작할 거라 생각은 했음.
// - 근데 가지치기 조건을 완벽히 생각해내지 못해서 시간초과를 해결하지 못했었음.


import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Parentheses {
    public static void main(String[] args) {
        Parentheses p = new Parentheses();

        p.dfs(0);

        System.out.println(count);
    }

    private int N = 2;
    private static final Stack<Character> stack = new Stack<>();
    private static final List<Character> parentheses = new ArrayList<>();
    private static int count;
    int openCount;
    int closeCount;

    private void dfs(int idx) {
        if (idx == N * 2) {
            if(validate()){
                count++;
            }

            return;
        }

        if(openCount < closeCount || openCount > N || closeCount > N){
            return;
        }

        // 1. add '('
        parentheses.add('(');
        openCount++;

        dfs(idx + 1);

        parentheses.remove(parentheses.size() - 1);
        openCount--;

        // 2. add ')'
        parentheses.add(')');
        closeCount++;

        dfs(idx + 1);

        parentheses.remove(parentheses.size() - 1);
        closeCount--;
    }

    private boolean validate() {
        // stack.clear();

        for (int i = 0, len = parentheses.size(); i < len; i++) {
            char here = parentheses.get(i);

            if (stack.isEmpty()) {
                if (here == ')') {
                    return false;
                } else {
                    stack.add(here);
                }

                continue;
            }

            if (here == ')') {
                if (stack.isEmpty()) {
                    return false;
                } else {
                    stack.pop();
                }
            } else if (here == '(') {
                stack.add(here);
            } else {
                // never. wrong input.
            }
        }

        boolean ret = stack.isEmpty();
        stack.clear();

        return ret;
    }
}