package programmers.stackAndQueue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Development {

    public static void main(String[] args) {

        Development development = new Development();
        System.out.println(development.solution(new int[]{93,30,55}, new int[]{1,30,5}));
        System.out.println(development.solution(new int[]{95, 90, 99, 99, 80, 99}, new int[]{1,1,1,1,1,1}));

    }

    public int[] solution(int[] progresses, int[] speeds) {
        int[] answer = {};

        Queue<Developer> queue = new LinkedList<>();
        for(int i = 0 ; i<progresses.length ; i++){
            queue.add(new Developer(speeds[i], progresses[i]));
        }

        List<Integer> list = new ArrayList<>();
        while(!queue.isEmpty()){
            Developer developer = queue.poll();
            int cnt = fin(developer);
            queue.forEach(d -> {
                d.progress += d.speed*cnt;
            });

            int rst = 1;

            while(!queue.isEmpty()){
                Developer nextDeveloper = queue.peek();
                if(nextDeveloper.progress >= 100){
                    queue.poll();
                    rst++;
                }else{
                    break;
                }
            }
            list.add(Long.valueOf(rst).intValue());
        }

        return list.stream().mapToInt(Integer::intValue).toArray();
    }

    public int fin(Developer developer){
        int cnt = 0;
        while(developer.progress < 100){
            developer.progress += developer.speed;
            cnt ++;
        }
        return cnt;
    }

    public class Developer{
        int speed;
        int progress;

        public Developer(int speed, int progress){
            this.speed = speed;
            this.progress = progress;
        }
    }
}
