package leetcode.dynamicProgramming;

import java.util.stream.IntStream;

public class MaximumSubarray {

    public static void main(String[] args) {

        System.out.println(new MaximumSubarray().maxSubArray(new int[]
                {-2,1,-3,4,-1,2,1,-5,4}));
    }

    public int maxSubArray(int[] nums) {

        int[] total = new int[nums.length];
        total[nums.length - 1] = nums[nums.length-1];
        for(int i = nums.length-2 ; i>=0 ;i--){
            total[i] = Math.max(nums[i], nums[i]+total[i+1]);
        }

        return IntStream.of(total).max().getAsInt();
    }
}
