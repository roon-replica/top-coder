// 입력이 작아서 완탐해보면 되지
// 순열
// 순열 완성될 때 마다 제약 체크
// 파싱 - 인덱스 위치로

// LV2 / 단체사진 찍기
// 알고리즘은 기본적인 건데
// 순열 구현 제대로 못 함 -> 순열: 처음부터 다시 뽑는건데, 중복은 피하는 것
// 미세한 생각 제대로 못 함
// 그래서 좀 오래걸림

import java.util.*;

class Solution {
    public int solution(int n, String[] data0) {
        N = 8;    
        // N = 2;
        data = data0;
            
        charToInt.put('A',0);
        charToInt.put('C',1);
        charToInt.put('F',2);
        charToInt.put('J',3);
        charToInt.put('M',4);
        charToInt.put('N',5);
        charToInt.put('R',6);
        charToInt.put('T',7);
        
        permutation();
        
        int answer = cnt;
        return answer;
    }
    
    int cnt;
    int N;
    String[] data;
    List<Integer> picked = new ArrayList<>();
    Map<Character,Integer> charToInt = new HashMap<>();
    
    int printCnt = 0;
    
    void permutation(){
        if(picked.size() == N){
            // if(printCnt < 10){
            //     System.out.println(picked);
            //     printCnt++;
            // }       
            
            if(check()) cnt++;
            return;
        }
         
        for(int i=0;i<N;i++){
            if(picked.contains(i)) continue;
            
            picked.add(i);
            
            permutation();
            
            int removeIdx = picked.indexOf(i);
            picked.remove(removeIdx);
        }
    }
    
    boolean check(){
        for(String d : data){
                int num1 = charToInt.get(d.charAt(0));
                int num2 = charToInt.get(d.charAt(2));
                int eq = d.charAt(3);
                int distLimit = d.charAt(4) - '0';
                
                int idx1 = picked.indexOf(num1);
                int idx2 = picked.indexOf(num2);
                int dist = Math.abs(idx1-idx2) -1;
             
                if(eq == '='){
                    if(distLimit != dist) return false;
                }else if(eq == '>'){
                    if(distLimit >= dist) return false;
                }else if(eq == '<') {
                    if(distLimit <= dist) return false;
                }
            }
        
        return true;
    }
}
