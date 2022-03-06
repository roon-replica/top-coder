public class Sample2{
 public static void main(String[] args) {
  int[] result =  pouring(
   new int[] {20,20}, 
   new int[] {5,8},
   new int[] {0},
   new int[] {1}
  );
        
  for(int res : result){
   System.out.print(res+" ");
  }

 }

 public static int[] pouring(int[] capas, int[] bottles, int[] fromIds, int[] toIds){
  for(int i=0; i<fromIds.length; i++){
   int fromId = fromIds[i];
   int toId = toIds[i];
   
   // 다른 아이디어 : 옮기든 말든 총 합은 일정하다는 것을 이용!!
   int sum = bottles[fromId] + bottles[toId];
   
   bottles[toId] = Math.min(sum, capas[toId]);
   bottles[fromId] = sum - bottles[toId];  
  }

  return bottles;

 }
}

