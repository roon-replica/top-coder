// 어떻게 풀면 좋을지 바로 보였다
// 아래에서부터 최댓값을 기록해가는 이미지가 그려져서 쉬웠음
// 약간의 트릭?은 위에서부터 아래가 아니라 아래에서부터 위로 올라가는 이미지를 떠올려보는 것인듯

// LV3 / 정수 삼각형
class Solution {
    public int solution(int[][] triangle) {
        int answer = 0;
        int R = triangle.length;
        int C = triangle[R-1].length;
        
        for(int r=R-1;r>0;r--){
            for(int c=r-1;c>=0;c--){
                triangle[r-1][c] = triangle[r-1][c] + Math.max(triangle[r][c], triangle[r][c+1]);
            }
        }
        
        answer = triangle[0][0];
        
        // for(int r=0;r<R;r++){
        //     for(int c=0;c<=r;c++){
        //         System.out.print(triangle[r][c]+" ");
        //     }
        //     System.out.println();
        // }
        
        return answer;
    }
}
