package programmers.search.binary;

public class BinarySearch {

    public static void main(String[] args) {
        int[] arr = new int[]{1,1,3,4,5};

        int fin = 2;

        int start = 0;
        int end = arr.length;

        int answer =-1;

        while(start <= end){
            int pivot = (start+end)/2;
            if(arr[pivot] == fin){
                answer = pivot;
                break;
            }else if(arr[pivot] > fin){
                end = pivot -1 ;
            }else if(arr[pivot] < fin){
                start = pivot + 1;
            }
        }

        System.out.println(answer);
    }
}
