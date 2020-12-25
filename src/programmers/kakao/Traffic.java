package programmers.kakao;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Traffic {

    public static void main(String[] args) {

        Traffic traffic = new Traffic();
        System.out.println(traffic.solution(new String[]{
                "2016-09-15 01:00:02.000 2s", "2016-09-15 01:00:03.500 1.6s", "2016-09-15 01:00:05.000 2.002s"}));
    }

    public int solution(String[] lines) {

        int answer = Integer.MIN_VALUE;

        List<ProcessTime> list = new ArrayList<>();
        for (String line :
                lines) {
            //2016-09-15 hh:mm:ss.sss
            ProcessTime processTime = new ProcessTime(line.substring(0, 23), line.split(" ")[2]);
            list.add(processTime);
        }
        for(int i = 0 ; i<lines.length ; i ++){
            answer = Math.max(Integer.valueOf(String.valueOf(compare(list.get(i).getStartTime(), list))),answer);
            answer = Math.max(Integer.valueOf(String.valueOf(compare(list.get(i).getEndTime(), list))),answer);
        }

        return answer;
    }

    private long compare(long time, List<ProcessTime> list){

        long cnt = list.stream().filter(p -> (p.getStartTime() >= time && p.getStartTime() < time+1000)
                || (p.getEndTime() >= time && p.getEndTime() < time + 1000)
                || (p.getStartTime() <= time && p.getEndTime() > time+1000))
                .count();
        return cnt;
    }

    public class ProcessTime {

        long startTime;
        long endTime;

        public ProcessTime(String time, String processTime) {

            this.endTime = Timestamp.valueOf(time).getTime();
            this.startTime = (this.endTime + 1) - new BigDecimal(processTime.substring(0, processTime.length() - 1)).movePointRight(3).longValue();

        }

        public long getStartTime() {
            return startTime;
        }

        public long getEndTime() {
            return endTime;
        }
    }
}
