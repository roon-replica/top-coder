// 최대값 구하기
// 배열 길이 <= 201 
// => dp에 알맞은 조건이긴 함

// 엄청 어려운데..

// 괄호를 넣냐/안 넣냐? xxxxxxxx

// 가능한 연산들에 초점을 맞추지 말고 연산의 결과에 초점을 맞추면 해결됨
// 관점을 달리하면 해결할 수 있다...
// dp[f][f] = num[f]
// dp[f][r] = max(dp[f][r], dp[f][f+1]+dp[f+2][r], ..., dp[f][r-3] + dp[r-2][r], dp[f][r-2] + dp[r-1][r])

// (+) => max + max
// (-) => max - min

class Solution {
    public int solution(String arr[]) {
        int answer = 0;
        
        N = arr.length/2 +1;
        
        maxsum = new int[N][N];
        minsum = new int[N][N];
        numbers = new int[N];
        operators = new char[N-1];
        
        // 초기화를 덜 해서 dp값도 제대로 계산이 안 됐음..
        for(int i=0,idx=0;idx<N;i+=2,idx++){
            numbers[idx] = Integer.parseInt(arr[i]);
            if(idx != N-1){                             
                operators[idx] = arr[i+1].charAt(0);           
            }
            
        }
        
        for(int f=0;f<N;f++){
            for(int r=0;r<N;r++){
                if(f==r){
                    maxsum[f][r] = numbers[f];
                    minsum[f][r] = numbers[f];
                    
                }else{
                    maxsum[f][r] = -MAX;
                    minsum[f][r] = MAX;
                }
                
            }
        }
        
        
        getMax(0,N-1);
        getMin(0,N-1);
        
        answer = maxsum[0][N-1];
        
//         for(int f=0;f<N;f++){
//             System.out.print(maxsum[0][f]+" ");
//         }
//         System.out.println();
        
//         for(int f=0;f<N;f++){
//             System.out.print(minsum[0][f]+" ");
//         }
//         System.out.println();
        
        
        return answer;
    }
    
    int N;
    int[] numbers;
    char[] operators;
    int[][] maxsum;
    int[][] minsum;
    int MAX = (int)1e9;
    
    private int getMax(int from,int to){
        if(from>to) return -MAX;
        
        if(maxsum[from][to] != -MAX) return maxsum[from][to];
        // maxsum[from][to] = 0;
        
        for(int mid = from; mid<to;mid++){
            if(operators[mid] == '+'){
                maxsum[from][to] = Math.max(maxsum[from][to],getMax(from,mid) + getMax(mid+1,to));
                
            }else if(operators[mid] == '-'){
                maxsum[from][to] = Math.max(maxsum[from][to],getMax(from,mid) - getMin(mid+1,to));    
            }
        }
        
        return maxsum[from][to];
    }
    
    private int getMin(int from, int to){
        if(from>to) return MAX;
        
        if(minsum[from][to] != MAX) return minsum[from][to];
        // minsum[from][to] = 0;
        
        for(int mid = from; mid<to; mid++){
            if(operators[mid] == '+'){
                minsum[from][to] = Math.min(minsum[from][to],getMin(from,mid)+getMin(mid+1,to));    
            }else if(operators[mid] == '-'){
                minsum[from][to] = Math.min(minsum[from][to],getMin(from,mid)-getMax(mid+1,to));    
            }
        }
        
        return minsum[from][to];
    }
}
