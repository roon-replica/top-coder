// 어려운 문제는 아닌데 그냥 코드가 꽤 예뻐서
// LV2 / 오픈채팅방
import java.util.*;
import java.util.stream.*;

class HashMapExample {
    public String[] solution(String[] record) {
        Map<String,String> idToName = new HashMap<>();
        final String ENTER = "님이 들어왔습니다.";
        final String LEAVE = "님이 나갔습니다.";
        
        Map<String,String> msgConvertor = new HashMap<>();
        msgConvertor.put("Enter",ENTER);
        msgConvertor.put("Leave",LEAVE);
        
        List<Info> infos = new ArrayList<>();
        
        for(String r : record){
            String[] info = r.split(" ");
            String msg = info[0];
            String id = info[1];
            
            if(msg.equals("Leave")){
               infos.add(new Info(id,msg));
            }else if(msg.equals("Enter")){
                String name = info[2];
                idToName.put(id,name);
                infos.add(new Info(id,msg));
            }else if(msg.equals("Change")){
                String name = info[2];
                idToName.put(id,name);
            }
        }
        
        List<String> res = infos.stream().
            map(info -> idToName.get(info.id)+msgConvertor.get(info.msg))
            .collect(Collectors.toList());
        
        String[] answer = new String[res.size()];
        for(int i=0;i<res.size();i++){
            answer[i] = res.get(i);
        }
        
        return answer;
    }
    
    class Info{
        String id;
        String msg;
        
        Info(String id, String msg){
            this.id = id;
            this.msg = msg;
        }
    }
}
