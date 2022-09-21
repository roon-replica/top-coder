// pick/not pick 조건이 바로 보이는 dp 문제 같은데..
// 관계식 잘 모르겠음

// 조건
//   - 최댓값 구하기
//   - 인접 2집 x
//   - 원형 배치
//   - money <= 1000 ..?

// dp[idx][is picked] = 최대값? 
// pick or not pick하면 될텐데 관계식을 못 세우겠다..

// dp[idx] = idx번째 집까지 털었을 때 최대값으로 하면 되구나..
// 1. idx번째 털면 
//    dp[idx-2] + money[idx]
// 2. idx번째 안 털면
//    dp[idx-1]

// 문제는 원형으로 배치되어 있어서 dp[0], dp[n-1] 사이 관계가 조건에 안 맞을 수도 있다?
// 아.. recursive 대신 iterative하게...

// LV4/도둑질
class IterativeDp {
    public int solution(int[] money) {
        int answer = 0;
        
        int LEN = money.length;
        int[] dp = new int[LEN+1]; // 1번째 집 pick
        int[] dp2 = new int[LEN+1]; // 1번째 집 not pick
        
        dp[0] = money[0];
        dp[1] = money[0];
        
        dp2[0] = 0;
        dp2[1] = money[1];
        answer = Math.max(money[0], money[1]);
        
        // 놓친 부분 : 첫번째 집을 훔친 경우, 마지막 집은 안 훔쳐야함... 
        for(int idx=2; idx<LEN-1; idx++){   
            dp[idx] = Math.max(dp[idx-1], dp[idx-2] + money[idx]);
            answer = Math.max(answer, dp[idx]);
        }
        
        for(int idx = 2; idx< LEN; idx++){
            dp2[idx] = Math.max(dp2[idx-1], dp2[idx-2] + money[idx]);
            answer = Math.max(answer, dp2[idx]);
        }
        
        return answer;
    }
    
}
