package programmers.skillTest;

import java.util.ArrayList;
import java.util.List;

public class Press {

    public static void main(String[] args) {
        System.out.println(new Press().solution("TOBEORNOTTOBEORTOBEORNOT"));

    }
    public int[] solution(String msg) {
        List<Integer> answer = new ArrayList<>();
        // 1. 길이가 1인 모든 단어를 포함하도록 사전을 초기화한다.
        List<String> init = init();

        while(msg.length() > 0){
            // 2. 사전에서 현재 입력과 일치하는 가장 긴 문자열 w를 찾는다.
            String w = contain(init, msg);

            // 3. w에 해당하는 사전의 색인 번호를 출력하고, 입력에서 w를 제거한다.
            System.out.println(w);
            System.out.println(init.indexOf(w)+1);
            msg = msg.substring(w.length());
            answer.add(init.indexOf(w)+1);

            // 4. 입력에서 처리되지 않은 다음 글자가 남아있다면(c), w+c에 해당하는 단어를 사전에 등록한다.
            if(msg.length() > 0){
                String nextWord = msg.substring(0,1);
                init.add(w+nextWord);
            }
        }

        return answer.stream().mapToInt(Integer::intValue).toArray();
    }

    private String contain(List<String> init, String msg) {

        int max = Integer.MIN_VALUE;
        String result = null;

        for(int i = 1 ; i<=msg.length() ; i++){
            String w = msg.substring(0,i);
            if(init.contains(w)){
                int temp = max;
                max = Math.max(w.length(), max);
                if(max > temp){
                    result = w;
                }
            }else{
                break;
            }
        }

        return result;
    }

    private List<String> init() {
        List<String> string = new ArrayList<>();
        for(int i = 0; i<= 'Z'-'A'; i++){
            string.add(String.valueOf((char)('A'+i)));
        }
        return string;
    }
}
