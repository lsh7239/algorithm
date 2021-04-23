package programmers.heap;

import java.util.Comparator;
import java.util.PriorityQueue;

public class DoublePriorityQueue {

    public static void main(String[] args) {
        System.out.println(new DoublePriorityQueue().solution(new String[]{
                "I 7","I 5","I -5","D -1"
        }));
    }

    public int[] solution(String[] operations) {

        // idea 1 - 우선순위 큐 2개 생성
        PriorityQueue<Integer> maxQueue = new PriorityQueue<>(Comparator.reverseOrder());
        PriorityQueue<Integer> minQueue = new PriorityQueue<>(Comparator.naturalOrder());

        for(String operation : operations){
            if(operation.startsWith("I")){
                int value = Integer.valueOf(operation.substring(2));
                maxQueue.add(value);
                minQueue.add(value);
            }else if(operation.startsWith("D")){
                int check = Integer.valueOf(operation.substring(2));
                if(check > 0){
                    if(!maxQueue.isEmpty()){
                        int rmValue = maxQueue.poll();
                        minQueue.remove(rmValue);
                    }
                }else{
                    if(!minQueue.isEmpty()){
                        int rmValue = minQueue.poll();
                        maxQueue.remove(rmValue);
                    }
                }
            }
        }

        int[] answer;
        if(maxQueue.isEmpty()){
            answer = new int[]{0, 0};
        }else{
            answer = new int[]{maxQueue.poll(), minQueue.poll()};
        }

        return answer;
    }
}
