public class SampleSimul {
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

   int amount = bottles[fromId];
   amount = Math.min(amount, capas[toId] - bottles[toId]);

   bottles[fromId] -= amount;
   bottles[toId] += amount;

  }

  return bottles;

 }
}

