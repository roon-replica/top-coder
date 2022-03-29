// 그냥 시간의 흐름에 따라 시뮬레이션하면 동작은 하는데 시간복잡도가 안됨

// 순환한 바퀴수를 기준으로 이분탐색하면 
// 각 케이스 체크하는데 20만,
// 최대 log(10^13)번 < log(2^50) = 50
// => 20만 * 50 = 1000만 => 가능

// 무지의 먹방 라이브/LV4
class Solution {
    public int solution(int[] foodTimes, long k) {
        int answer = 0;        
        
        long sum = 0;
        for(int time : foodTimes){
            sum += time;
        }
        
        if(sum <= k){
            return -1;
        }
        
        int min = 0, max = 100_000_000;
        sum = 0;
        int rotateX = 0;
        
        for(;min <= max;){ // 등호가 들어가야 하나? 헷갈림 => 들어가도 문제될 건 없음. min or max가 반드시 증/감하므로 종료는 보장되어 있음. 오히려 등호를 안 붙이면 오류나지
            int mid = (min + max) / 2;
            
            long tmp = 0;
            for(int time : foodTimes){
                tmp += Math.min(mid,time);
            }
            
            if(tmp <= k){          
                sum = tmp;
                rotateX = mid;
                
                min = mid +1;
            }else{
                max = mid-1;
            }
        }
        
        int LEN = foodTimes.length;
        for(int i=0;i<LEN;i++){
            foodTimes[i] = Math.max(0, foodTimes[i] - rotateX);
        }
        
        for(int i=0;i<LEN;i++){
            if(foodTimes[i] == 0) continue; //놓친 조건
            
            if(sum == k){
                answer = i+1;
                break;
            }
            
            sum += Math.min(1, foodTimes[i]);
            foodTimes[i] = Math.max(0,foodTimes[i]-1);
            
        }
        
        
        return answer;
    }
}
