package programmers.sort.study;

public class SelectionSort {


    public static void select(int[] arr) {
        // min value를 선택해서 변경한다
        for(int i = 0 ; i<arr.length; i++){
            int min = arr[i];
            int idx = i;
            for(int j = i+1 ; j<arr.length ; j++){
                if(arr[j] < min){
                    min = arr[j];
                    idx = j;
                }
            }
            int temp = arr[i];
            arr[i] = min;
            arr[idx] = temp;

            Print.print(arr);
            System.out.println();
        }
    }
}
