package programmers.sort.study;

public class MergeSort {

    // divide and conquer
    public void mergeSort(int[] arr){
        // 임시 배열 저장소
        int[] temp = new int[arr.length];
        mergeAndSort(arr,temp,0,arr.length-1);
    }

    public void mergeAndSort(int[] arr, int[] temp, int start, int end){
        if(start < end){
            // start 와 end의 중간값으로 나눈 후 다시 mergeAndSort함수 호출
            int mid = (start + end) / 2;
            mergeAndSort(arr,temp,start,mid);
            mergeAndSort(arr, temp, mid+1, end);
            merge(arr,temp,start,mid,end);
        }
    }

    private void merge(int[] arr, int[] temp, int start, int mid, int end) {
        // 임시 배열방에 복사
        for(int i = start; i<=end;i++){
            temp[i] = arr[i];
        }

        // 첫번째 배열방
        int part1 = start;
        // 두번째 배열방
        int part2 = mid+1;
        int index = start;

        // 첫번째 배열이 끝까지 가거나, 두번째 배열이 끝까지 가거나 할 때 까지 반복문 돌린다.
        while(part1<=mid && part2 <=end){
            // 임시 배열의 첫번째 배열과 두번째 배열 크기 비교
            if(temp[part1] <= temp[part2]){
                // 첫번째 배열이 작다면 첫번째 것을 arr로 옮긴다.
                arr[index] = temp[part1];
                part1++;
            }else{
                // 두번째 배열이 작다면 두번째 것을 arr로 옮긴다.
                arr[index] = temp[part2];
                part2++;
            }
            index++;
        }

        // 만약 앞쪽 배열에 비교하지 않은 데이터가 남아있다면 현재 비교하고있는 두 배열방 중에 가장 큰 값들이므로 arr에 추가함
        // 뒤쪽 배열에 값이 남아있다면 두 배열방 중 가장 큰 값이지만 현재도 arr의 뒤쪽에 남아있기 때문에 skip
        for(int i = 0; i<=mid-part1 ; i++){
            arr[index+i] = temp[part1+i];
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{5,3,1,7,9,0,2,8};
        new MergeSort().mergeSort(arr);
        Print.print(arr);
    }

}
