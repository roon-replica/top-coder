// LV3/2018 카카오 추석 트래픽
// 왜 어려웠지... 직선 코스가 있는데 이리저리 돌아가면서 풀려다보니 풀이가 복잡해졌다
// 문제의 조건도 놓쳤었다. 입력이 응답완료시간을 기준으로 정렬되어 주어지는 것..
// 무조건 단순한 방법을 떠올려야 한다.. 그래야 확실히 동작한다.... 

import java.util.*;

class Solution {
    public int solution(String[] lines) {
        int answer = 0;
        int N = lines.length;
        
        List<int[]> infos = new ArrayList<>();
        for(String line : lines){
            StringTokenizer st = new StringTokenizer(line);
            st.nextToken();
            String etime = st.nextToken();
            String duration = st.nextToken();
            duration = duration.substring(0,duration.length()-1);
            
            int convertedEndTime = convert(etime);
            infos.add(new int[] {convertedEndTime-(int)(Double.parseDouble(duration) * 1000)+1, convertedEndTime});
        }
        
        for(int i=0;i<N;i++){
            int start = infos.get(i)[0];
            int end = infos.get(i)[1];
            
            int cnt=0;
            for(int j=i;j<N;j++){
                int start2 = infos.get(j)[0];
                
                if(start2 <= end + 999){
                    cnt++;
                }
            }
            
            answer = Math.max(answer,cnt);
        }
        
        return answer;
    }
    
    private int convert(String stime){
        int time=0;
        
        StringTokenizer st = new StringTokenizer(stime,".");
        stime = st.nextToken();
        
        int millsec = Integer.parseInt(st.nextToken());
        
        st = new StringTokenizer(stime,":");
        for(;st.hasMoreTokens();){
            time*=60;
            time += Integer.parseInt(st.nextToken());
        }
        time*=1000;
        
        time += millsec;
        
        return time;
    }
}
