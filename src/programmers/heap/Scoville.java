package programmers.heap;

import java.util.Arrays;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.PriorityQueue;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Scoville {

    public static void main(String[] args) {
        Scoville scoville = new Scoville();
        System.out.println(scoville.solution(new int[]{1,2,3,4,5,6,7}, 5));
        System.out.println(scoville.solution2(new int[]{1, 2, 3, 9, 10, 12}, 7));
    }

    public int solution(int[] scoville, int K) {
        int answer = 0;

        while(isOver(scoville,K)){
            if(scoville.length < 2){
                answer = -1;
                break;
            }
            answer ++;
            // 시간 초과의 원인 중 하나
            Arrays.sort(scoville);

            int[] newScoville = new int[scoville.length - 1];
            int newScv = scoville[0] + scoville[1] * 2;
            newScoville[0] = newScv;
            for(int i = 1 ; i<newScoville.length ; i++){
                newScoville[i] = scoville[i+1];
            }
            scoville = newScoville;
        }

        return answer;
    }

    private boolean isOver(int[] scoville, int k) {
        Optional<Integer> isOver = IntStream.of(scoville).boxed().filter(i -> i < k).findAny();
        return isOver.isPresent();
    }


    public int solution2(int[] scoville, int K){
        int answer = 0;
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        queue.addAll(IntStream.of(scoville).boxed().collect(Collectors.toList()));

        while(queue.stream().filter(i -> i<K).findAny().isPresent()){
            if(queue.size() < 2){
                answer = -1;
                break;
            }
            answer ++;
            int scv = queue.poll() + queue.poll()*2;
            queue.add(scv);
        }

        return answer;
    }

}
