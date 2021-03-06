package programmers.sort.study;

public class BubbleSort {

    public static void bubble(int[] arr){

        for(int i = 0 ; i< arr.length ; i++){
            for(int j = 0; j< arr.length-1 ; j++){
                if(arr[j] > arr[j+1]){
                    int temp = arr[j+1];
                    arr[j+1] = arr[j];
                    arr[j] = temp;

                    Print.print(arr);
                    System.out.println();
                }
            }
        }
    }
}
