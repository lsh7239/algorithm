package programmers.kakao;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Convert {

    public static void main(String[] args) {

        Convert convert = new Convert();
        System.out.println(convert.solution(")("));
    }

    public String solution(String p) {
        if(p.isEmpty()){
            return "";
        }

        String[] wStrings = p.split("");
        String u = "";
        String v = "";
        String answer = "";

        int leftCnt = 0, rightCnt = 0;
        for(int i = 0; i<wStrings.length ; i++){
            if(wStrings[i].equals("(")) leftCnt ++;
            else rightCnt ++;

            if(leftCnt == rightCnt){
                u = p.substring(0,i+1);
                v = p.substring(i+1);
                System.out.println(String.format("u : %s , v : %s",u,v));
                break;
            }
        }

        if(isRightString(u)){
            answer = u + solution(v);
        }else{

            String tmp = solution(v);
            String no = notRightString(u);

            answer = "(" + tmp + ")" + no;
            System.out.println(String.format("[ tmp : %s , no : %s ]",tmp,no));
        }

        return answer;
    }

    public String notRightString(String u){
        u = u.substring(1,u.length()-1);
        String result = Stream.of(u.split("")).map(i->{
            if(i.equals("(")){
                return ")";
            }else if(i.equals(")")){
                return "(";
            }else return "";
        }
        ).collect(Collectors.joining());
        return result;
    }

    public boolean isRightString(String u){

        if(u.startsWith(")")){
            return false;
        }
        String[] uString = u.split("");
        List<String> firstString = new ArrayList<>(); // (
        List<String> secondString = new ArrayList<>(); // )

        boolean result = true;

        for(int i = 0 ; i< u.length() ; i++){
            if(uString[i].equals("(")){
                firstString.add(uString[i]);
            }else{
                secondString.add(uString[i]);
            }

            if(secondString.size() > firstString.size()){
                result = false;
                break;
            }
        }

        return result;
    }
}
