package programmers.sort.study;

public class QuickSort {

    public void quick(int[] arr){
        // start, end point 존재함
        int start = 0;
        int end = arr.length-1;

        quick(arr,start,end);
    }

    private void quick(int[] arr, int start, int end){
        int pivot = partition(arr,start,end);

        if(start < pivot - 1){
            quick(arr,start,pivot-1);
        }
        if(pivot < end){
            quick(arr,pivot,end);
        }
    }

    private int partition(int[] arr, int start, int end){
        int pivot = arr[(start+end)/2];
        while(start <= end){
            while(arr[start] < pivot){
                start++;
            }
            while(arr[end] > pivot){
                end --;
            }
            if(start <= end){
                int temp = arr[start];
                arr[start] = arr[end];
                arr[end] = temp;

                start++;
                end--;
            }
        }
        Print.print(arr);
        System.out.println();
        return start;
    }

    public static void main(String[] args) {
        new QuickSort().quick(new int[]{3,5,1,7,9,0});
    }
}
