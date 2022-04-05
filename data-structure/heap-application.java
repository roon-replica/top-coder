import java.util.*;

// 시간 구간이 주어질 때 특정 시간에 최대 몇개의 구간이 겹칠지 구하기
// sort by start time ascending
// use min heap (end time, ascending)
// iterate times
// if now_start_time > heap_first_end_time, remove from heap, count-- (iterate)
// because heap_first is out of range
// pq.add(now_end_time), count++

public class PriorityQueueUtil{   
     public static void main(String []args){
        int[][] times = new int[][]{{1,5},{2,3},{3,6},{3,7},{4,5}, {4,4}, {4,4}, {4,4}};
        // xoooooxxxx
        // xxooxxxxxx
        // xxooooxxxx
        // xxoooooxxx
        // xxxooxxxxx
        // xxxxoxxxxx
        // xxxxoxxxxx
        // xxxxoxxxxx
        
        Arrays.sort(times, (a,b) -> Integer.compare(a[0],b[0]));
        
        int maxCount = 0;
        int count = 0;
        
        // 'var' requires jdk >= 10
        var pq = new PriorityQueue<Integer>();  //min heap
        
        for(int[] time : times){
            int s = time[0];
            int e = time[1];
            maxCount = Math.max(maxCount,count);
            
            for(; !pq.isEmpty() && pq.peek() < s;){
                pq.poll();
                count--;
            }
            pq.add(e);
            count++;
            
        }
        maxCount = Math.max(maxCount,count);
        
        System.out.println(maxCount);
     }
}
