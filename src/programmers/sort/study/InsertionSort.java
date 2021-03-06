package programmers.sort.study;

public class InsertionSort {

    // 삽입정렬
    public static void insert(int[] arr) {

        // 현재 인덱스와 이전 인덱스를 비교한다.
        // 이전 인덱스보다 값이 작으면, 그 이전 인덱스 값을 또 비교한다.
        // 만약, 현재 인덱스가 i 라면 비교 순서는 i-1, i-2, i-3 ... 0 이다.
        // O(n^2)

        for(int i = 1 ; i<arr.length ; i++){
            for(int j = i ; j>0 ;j--){
                if(arr[j-1] > arr[j]){
                    int temp = arr[j-1];
                    arr[j-1] = arr[j];
                    arr[j] = temp;
                }else{
                    break;
                }
                Print.print(arr);
                System.out.println();
            }
        }
    }


}
