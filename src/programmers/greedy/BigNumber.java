package programmers.greedy;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class BigNumber {

    public static void main(String[] args) {
        System.out.println(new BigNumber().solution("1924",2));
//        System.out.println(new BigNumber().solution("777252841",4));

//        System.out.println(new BigNumber().solution("1231234",3));
//        System.out.println(new BigNumber().solution("10234",3));
    }

    public String solution(String number, int k) {

        int[] num = Stream.of(number.split("")).mapToInt(Integer::valueOf).toArray();

        StringBuilder builder = new StringBuilder();

        int index = 0;
        for(int i =0 ; i<num.length - k ;i++){
            int max = 0;
            for(int j = index; j <= k+i ; j++){
                if(num[j] > max){
                    max = num[j];
                    index = j+1;
                }
            }
            builder.append(max);
        }

        return builder.toString();
    }

}
