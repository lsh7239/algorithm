package programmers.search;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

public class PrimeNumber {

    public static void main(String[] args) {

        PrimeNumber number = new PrimeNumber();
        System.out.println(number.solution("123"));
    }

    public int solution(String numbers) {
        int answer = 0;

        String[] num = numbers.split("");
        Set<Integer> integers = new HashSet<>();

        for(int length  = 1 ; length <= num.length ; length++){
            // num 갯수만큼 숫자 만들기
            String result = "";
            for(int j = 0 ; j < num.length ; j++){
                boolean[] visit = new boolean[num.length];
                visit[j] = true;
                result += num[j];
                result = getNumber(num, length,result,visit,integers);
                result = "";
            }

        }

        Long count = integers.stream()
                .filter(i -> i > 1)
                .filter(i -> isPrimeNum(i)).count();
        answer = count.intValue();

        return answer;
    }

    // 소수 구하는 메소드
    public boolean isPrimeNum(int number){

        boolean isPrimeNumber = true;

        for(int i = 2; i < number ; i++){
            if(number % i == 0){
                isPrimeNumber = false;
                break;
            }
        }

        return isPrimeNumber;
    }

    public String getNumber(String[] num, int length, String result, boolean[] visit,Set<Integer> set){

        if(result.length() == length){
            //break;
            set.add(Integer.valueOf(result));
            return result;
        }

        for(int i = 0; i<num.length ; i++){
            if(!visit[i]){
                visit[i] = true;
                result = getNumber(num,length,result+num[i],visit,set);
                visit[i] = false;
                result = result.substring(0,result.length()-1);
            }
        }

        return result;
    }
}
