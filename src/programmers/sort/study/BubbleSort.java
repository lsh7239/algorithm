package programmers.sort.study;

public class BubbleSort {

    public static void bubble(int[] arr){
        // i 와 i+1을 비교하여 swap
        boolean isFin = false;
        while (!isFin){
            isFin = true;
            for(int i = 0; i< arr.length-1 ; i++){
                if(arr[i] > arr[i+1]){
                    int temp = arr[i+1];
                    arr[i+1] = arr[i];
                    arr[i] = temp;
                    isFin = false;

                    Print.print(arr);
                    System.out.println();
                }
            }
        }
    }
}
