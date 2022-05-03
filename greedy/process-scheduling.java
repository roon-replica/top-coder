// 흠..... "요청부터 종료까지 걸린 시간의 평균을 가장 줄이는 방법"이 뭔지 못 알아내면 못 푸는거 아닌가?
// 그냥 느낌에는 끝나는 시간이 빠른거부터 처리하면 좋을거 같은데 -> 아니네... 

// 최적의 전략은 수행 시간이 짧은 순으로 처리하기임. 
// (
//      단, 현재 시간 > 요청받은 시간인 작업들 중에서.
//      만약 작업은 남았는데 그러한 작업이 없다면, 가장 빠른 다음 작업으로 넘어가야 함
// )
// 떠올리기 좀 힘든듯.. 확인하고 나니 납득은 됨.
// 예를 들어 (0,3), (5,1)인 작업들이 있을 때 (5,1)이 처리 시간이 짧다고해서 앞의 5초 동안을 놀기보단 (0,3) 작업을 처리하는게 나음
// 또 (0,3),(0,9) 작업이 있을 때 (0,9)부터 처리하면 9 + 9 + 3이 걸리지만
// (0,3)부터 처리하면 3 + 3+ 9가 걸려서 더 유리함
// 이 2가지 원리가 전부임. 그리디한 전략인거임

// 까다롭다 LV3 아닌듯 LV4 정도인듯.
// 아이디어 못 떠올림 + 구현 꼼꼼히 생각해야 함 (***pq는 비었는데 작업은 남아있을 경우***)
// 꼭 다시 풀어봐야할듯
// 실전이었으면 못풀었을듯. 

// LV3 / 디스크 컨트롤러
import java.util.*;

class Solution {
    public int solution(int[][] jobs) {
        int answer = 0;
        int N = jobs.length;
        
        Arrays.sort(jobs,(t1,t2)->{
            if(t1[0] != t2[0]) return Integer.compare(t1[0],t2[0]);
            else return Integer.compare(t1[1],t2[1]);
        });
        
        PriorityQueue<int[]> pq = new PriorityQueue<>(500,(t1,t2) -> {
             if(t1[1] != t2[1]) return Integer.compare(t1[1],t2[1]);
             else return Integer.compare(t1[0],t2[0]);
        });
        pq.add(jobs[0]);
        
        int idx=1;
        int nowTime = jobs[0][0];
        
        for(;pq.isEmpty() == false || idx<jobs.length;){
            if(pq.isEmpty()){                
                nowTime = jobs[idx][0];
                pq.add(jobs[idx]);
                idx++;
                continue;
            }
            
            int[] here = pq.poll();
            
            answer += (here[1] + nowTime - here[0]);
            nowTime += here[1];
            
            for(;idx<jobs.length;idx++){
                if(jobs[idx][0] > nowTime) break;
                pq.add(jobs[idx]);
            }    
        }
        
        answer /= N;
                
        return answer;
    }
}
