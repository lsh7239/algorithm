package programmers.kakao.blind_2018;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

public class NewsClustering {

    public static void main(String[] args) {
        System.out.println(new NewsClustering().solution("FRANCE","french"));
        System.out.println(new NewsClustering().solution("handshake","shake hands"));
        System.out.println(new NewsClustering().solution("aa1+aa2","AAAA12"));
        System.out.println(new NewsClustering().solution("E=M*C^2","e=m*c^2"));
    }

    public int solution(String str1, String str2) {
        double answer = 0;

        // 다중집합 원소 사이를 비교할 때, 대문자와 소문자의 차이는 무시한다. "AB"와 "Ab", "ab"는 같은 원소로 취급한다.
        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();


        // 입력으로 들어온 문자열은 두 글자씩 끊어서 다중집합의 원소로 만든다.
        // 이때 영문자로 된 글자 쌍만 유효하고, 기타 공백이나 숫자, 특수 문자가 들어있는 경우는 그 글자 쌍을 버린다.

        String regex = "^[a-z]*$";
        Map<String,Integer> str1Map = new HashMap<>();
        for(int i = 0; i<str1.length()-1 ; i++){

            String str = str1.substring(i,i+2);
            if(Pattern.matches(regex,str)){
                //str1Arr.add(str);
                if(str1Map.containsKey(str)){
                    str1Map.put(str, str1Map.get(str)+1);
                }else{
                    str1Map.put(str,1);
                }
            }

        }

        Map<String,Integer> str2Map = new HashMap<>();
        for(int i =0 ;i<str2.length()-1; i++){

            String str = str2.substring(i,i+2);
            if(Pattern.matches(regex,str)){
//                str2Arr.add(str);
                if(str2Map.containsKey(str)){
                    str2Map.put(str, str2Map.get(str)+1);
                }else{
                    str2Map.put(str,1);
                }
            }
        }



        // 자카드 유사도 출력
        // 교집합 갯수
        long betweenCnt = 0;
        Set<String> str1Set = str1Map.keySet();
        Set<String> str2Set = str2Map.keySet();
        for(String str : str1Set){
            if(str2Set.contains(str)){
                betweenCnt += Math.min(str1Map.get(str), str2Map.get(str));
            }
        }

        // 합집합 갯수
        long totalCnt = 0;
        Map<String,Integer> totalMap = new HashMap<>();
        totalMap.putAll(str1Map);

        for(String str: str2Set ){
            if(totalMap.containsKey(str)){
                totalMap.put(str, Math.max(totalMap.get(str), str2Map.get(str)));
            }else{
                totalMap.put(str, str2Map.get(str));
            }
        }

        totalCnt = totalMap.values().stream().mapToInt(Integer::intValue).sum();

        if(betweenCnt == 0 && totalCnt == 0){
            answer = 1.0;
        }else{
            answer = (double)betweenCnt / (double)totalCnt;
        }
        return (int)(answer*65536);
    }
}
