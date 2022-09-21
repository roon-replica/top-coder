package kakao;// 카카오 코테 -> 구현이 메인
// *** 뭘 하든 큰 그림은 그려놓고 시작하라. 되도록 확실한 방법을 머리속이나 종이에 시뮬레이션하고 시작하라.
// *** 큰 그림을 그려놓지 않고 눈 앞의 수만 두다 보면 절대 좋은 수를 둘 수 없다.

// timetable 크기가 작음
// 완탐? 
// 정렬 / 숫자로 변환?
// timetable 1개씩 체크하며 시뮬?

// 수학적?
// min(최대한 늦은 셔틀 도착 시간, 대기열에서 최대한 늦은 시간)

// 모든 셔틀 도착시간에 대해 시뮬레이션

// 하... 좀 어려웠다... 1.5시간 정도 쓴듯..
// 안 익숙해서.. 예외 처리가 까다로움.
// 어렵게 푼 감도 있긴한데.. 뭔가 까다롭다... 세밀한 처리가 필요했어서.. 그에 맞는 적절한 처리방법을 떠올리다 보니 좀 어렵게 푼 듯?
// 처음에 입출력도 잘 이해 못했음
// 문제를 명확히 이해하는 독해 능력이 떨어져있으면 풀이 능력도 떨어지게 되는거임


// 더 쉬운방법은?
// =>  pq를 쓸수도 있었네...

//LV3/셔틀버스
import java.util.*;

class ShuttleBus {
    public String solution(int n, int t, int m, String[] timetable) {
        String answer = "";
        
        int initTime = convert("09:00");
        // List<String> times = Arrays.asList(timetable);
        List<String> times = new ArrayList<>();
        for(String time : timetable){
            times.add(time);
        }
        
        times.sort(String::compareTo);
        
        int[] cnt = new int[n+1];
        List<String>[] stepTimes = new ArrayList[n+1];
        for(int i=0;i<n;i++) stepTimes[i] = new ArrayList<>();
        
        for(int i=0;i<n;i++){
            int idx = 0;
            int start = initTime + t*i;
            
            for(;idx<Math.min(times.size(),m);idx++){
                int here = convert(times.get(idx));
                if(here > start) break;
                stepTimes[i].add(times.get(idx));
            }
            
            cnt[i] = idx;
            
            List<String> tmp = new ArrayList<>();
            for(idx = Math.min(idx, times.size());idx<times.size();idx++){
                tmp.add(times.get(idx));
            }
            
            times.clear();
            times.addAll(tmp);
            
            //System.out.println(times);
        }
        
        // for(int i=0;i<n;i++){
        //     System.out.print(cnt[i]+" ");
        //     for(String tt: stepTimes[i]){
        //         System.out.print(tt+" ");
        //     }
        //     System.out.println();
        // }
        // System.out.println();
        
        int leftSeats = n*m;
        
        for(int i=0;i<n;i++){
            leftSeats -= m;
            String start = reverseConvert(initTime + t*i);
            
            if(leftSeats <= 0){
                if(cnt[i] < m){
                    answer = start;
                }else{
                    answer = reverseConvert(convert(stepTimes[i].get(stepTimes[i].size()-1)) -1);    
                }
                break;
            }else{
                answer = start;
            }
        }
        
        return answer;
    }
    
    
    int convert(String t){
        int ret =0;
        String[] time = t.split(":");
        ret += Integer.parseInt(time[0]);
        ret*=60;
        ret += Integer.parseInt(time[1]);
        
        return ret;
    }
    
    String reverseConvert(int t){
        String ret = "";
        int h = t/60;
        int m = t%60;
        
        if(h<10) ret +='0';
        ret += String.valueOf(h);
        
        ret += ':';
        
        if(m < 10) ret += '0';
        ret += String.valueOf(m);
        
        return ret;
        
    }
}
