package programmers.sort;

import java.util.Arrays;
import java.util.stream.IntStream;

public class HIndex {

    public static void main(String[] args) {

        HIndex index = new HIndex();
        System.out.println(index.solution(new int[]{0,1,1,3})); // 5
    }

    public int solution(int[] citations) {
        int max = 0;

        Arrays.sort(citations); // 0, 1, 1, 5, 6

        for(int i = 0 ; i<citations[citations.length-1]; i++){
            if(isLessThan(citations,i) && isGreaterThan(citations,i)){
                max = Math.max(i,max);
            }
        }

        return max;
    }

    private boolean isGreaterThan(int[] citations, int hIndex) {
        int count = 0;
        boolean isTrue = false;
        for (int i = citations.length-1 ; i >= 0 ; i--){
            if(hIndex <= citations[i]){
                count ++;
            }
            else{
                break;
            }
        }

        if(count >= hIndex){
            isTrue = true;
        }

        return isTrue;
    }

    private boolean isLessThan(int[] citations, int hIndex) {
        int count = 0;
        boolean isTrue = false;
        for (int i = 0 ; i < citations.length ; i++){
            if(hIndex >= citations[i]){
                count ++;
            }else{
                break;
            }
        }
        if(count <= hIndex){
            isTrue = true;
        }

        return isTrue;
    }

}
