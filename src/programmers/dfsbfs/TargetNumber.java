package programmers.dfsbfs;

import java.util.Map;

public class TargetNumber {

    int count = 0;
    public static void main(String[] args) {
        TargetNumber targetNumber = new TargetNumber();
        System.out.println(targetNumber.solution(new int[]{1, 1, 1, 1, 1}, 3));
    }

    public int solution(int[] numbers, int target) {

        // + numbers
        solv(numbers,target,1, numbers[0]);

        // - numbers
       solv(numbers,target,1, -numbers[0]);

        return count;
    }

    public void solv(int[] numbers, int target, int index,int result){
        if(index < numbers.length){
            // +
            solv(numbers, target, index+1,result+numbers[index]);
            // -
            solv(numbers, target, index+1,result-numbers[index]);
        }else{
            if(target == result){
                count ++;
            }
//            System.out.println(result);
        }
    }
}
