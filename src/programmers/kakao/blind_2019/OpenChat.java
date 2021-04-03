package programmers.kakao.blind_2019;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OpenChat {

    public static void main(String[] args) {
        System.out.println(new OpenChat().solution(new String[]{
                "Enter uid1234 Muzi", "Enter uid4567 Prodo", "Leave uid1234", "Enter uid1234 Prodo", "Change uid4567 Ryan"
        }));
    }

    public String[] solution(String[] record) {
        String[] answer = {};

        List<String> result = new ArrayList<>();

        Map<String,String> ids = new HashMap<>();
        for(String rd : record){
            String[] r = rd.split(" ");
            if(r[0].equals("Enter")){
                ids.put(r[1],r[2]);
                result.add(String.format("%s 들어왔습니다.",r[1]));
            }else if(r[0].equals("Leave")){
                result.add(String.format("%s 나갔습니다.",r[1]));
            }else{
                ids.put(r[1],r[2]);
            }
        }

       answer = result.stream().map(s -> {
            String id = s.split(" ")[0];
            s = s.replaceAll(id,String.format("%s님이", ids.get(id)));
            return s;
        }).toArray(String[]::new);

        return answer;
    }

}
