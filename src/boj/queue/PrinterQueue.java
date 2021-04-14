package boj.queue;

import java.util.*;
import java.util.stream.Collectors;

// 1966
public class PrinterQueue {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int testCase = sc.nextInt();

        for(int i = 0; i<testCase; i++){

            int[] document = new int[2];
            for(int k =0; k<document.length ;k++){
                document[k] = sc.nextInt();
            }

            int[] printer = new int[document[0]];
            for(int k =0 ; k<printer.length ;k++){
                printer[k] = sc.nextInt();
            }

            System.out.println(solution(document,printer));
        }
    }

    public static int solution(int[] document, int[] printer){

        Queue<Print> pQueue = new LinkedList<>();

        for(int i = 0; i<printer.length; i++){
            pQueue.add(new Print(i, printer[i]));
        }

        int foundIndex = document[1];
        int count = 0;

        while (!pQueue.isEmpty()){
            Print nowPrinter = pQueue.poll();
            if(moreImportant(pQueue, nowPrinter.importance)){
                pQueue.add(nowPrinter);
            }else{
                // print 완료
                System.out.println(nowPrinter.toString());
                count++;
                if(nowPrinter.index == foundIndex){
                    break;
                }
            }
        }

        return count;
    }

    private static boolean moreImportant(Queue<Print> queue, int importance) {

        // binary search로 현재 queue 가운데 인덱스 값이 importance보다 큰지 작은지 비교
        int start = 0;
        int end = queue.size();

        List<Print> queueList = queue.stream()
                .sorted(Comparator.comparing(p -> p.importance))
                .collect(Collectors.toList());

        while(start < end){
            int pivot = (start + end) / 2;
            Print pivotQueue = queueList.get(pivot);
            if(pivotQueue.importance > importance){
                return true;
            }else {
                // pivotQueue.importance <= importance
                start = pivot + 1;
            }
        }

        return false;
    }

    public static class Print{
        int index;
        int importance;

        public Print(int index, int importance){
            this.index = index;
            this.importance = importance;
        }

        @Override
        public String toString() {
            return String.format("[%s,%s]",index,importance);
        }
    }
}
