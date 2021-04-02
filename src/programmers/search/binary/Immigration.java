package programmers.search.binary;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.LongStream;

public class Immigration {

    public static void main(String[] args) {
        System.out.println(new Immigration().solution(6, new int[]{7,1000000000}));
        System.out.println(new Immigration().solution(1, new int[]{2,5}));
        System.out.println(new Immigration().solution(6, new int[]{5,10}));
        System.out.println(new Immigration().solution(1000000000, new int[]{5,10}));
    }

    public long solution(int n, int[] times) {

        Arrays.sort(times);

        long start = 1;
        long end = times[times.length-1]*n; //array.length;

        long answer = end;

        while(start<=end){
            long mid = (start + end) / 2;

            long finCount = 0;
            for(int i = 0; i<times.length ;i++){
                finCount += ( mid / times[i]);
            }

            if(finCount < n){
                start = mid + 1;
            }else if (finCount >= n){
                answer = Math.min(mid, answer);
                end  = mid - 1;
            }
        }
        return answer;
    }
}
