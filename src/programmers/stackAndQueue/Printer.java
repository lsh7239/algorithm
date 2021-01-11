package programmers.stackAndQueue;

import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;
import java.util.stream.Stream;

public class Printer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        Printer p = new Printer();
        System.out.println(p.solution(new int[]{1,1,9,1,1,1}, 0));

	}
	
    public int solution(int[] priorities, int location) {
        int answer = 0;

        Queue<Print> queue = new LinkedList<>();
        for(int i = 0 ; i<priorities.length ; i++){
            queue.add(new Print(i, priorities[i]));
        }

        while(!queue.isEmpty()){
            Print p = queue.poll();
            Optional<Print> optional = queue.stream().filter(print -> p.priority < print.priority).findAny();
            if(optional.isPresent()){
                queue.add(p);
            }else{
                answer ++;
                if(p.idx == location){
                    break;
                }
            }
        }

        return answer;
    }

    public class Print{
	    int idx;
	    int priority;

	    public Print(int idx, int priority){
	        this.idx = idx;
	        this.priority = priority;
        }
    }

}
