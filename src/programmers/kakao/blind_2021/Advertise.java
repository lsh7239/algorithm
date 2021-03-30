package programmers.kakao.blind_2021;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Advertise {

    public static void main(String[] args) {

        System.out.println(new Advertise().solution2(
                "02:03:55",
                "00:14:15",
                new String[]{"01:20:15-01:45:14", "00:25:50-00:48:29", "00:40:31-01:00:00", "01:37:44-02:02:30", "01:30:59-01:53:29"}));

        System.out.println(new Advertise().solution2(
                "99:59:59",
                "25:00:00",
                new String[]{"69:59:59-89:59:59", "01:00:00-21:00:00", "79:59:59-99:59:59", "11:00:00-31:00:00"}));

        System.out.println(new Advertise().solution2(
                "99:59:59",
                "99:59:59",
                new String[]{"15:36:51-38:21:49", "10:14:18-15:36:51", "38:21:49-42:51:45"}));

    }

    public String solution(String play_time, String adv_time, String[] logs) {
        String answer = "";

        if(play_time.equals(adv_time)){
            return "00:00:00";
        }

        List<LogTime> logTimes = new ArrayList<>();

        String[] t = adv_time.split(":");
        int advTime = Integer.valueOf(t[0]) * 3600 + Integer.valueOf(t[1]) * 60 + Integer.valueOf(t[2]);
        String[] play = play_time.split(":");
        int totalTime = Integer.valueOf(play[0]) * 3600 + Integer.valueOf(play[1]) * 60 + Integer.valueOf(play[2]);

        int[] advs = new int[logs.length*2];
        int[] advs_accumulate_count = new int[logs.length*2];
        int idx = 0;

        for(int i = 0; i<logs.length ; i++){
            LogTime logTime =new LogTime(logs[i].split("-"));
            logTimes.add(logTime);

            advs[idx++] = logTime.start;
            advs[idx++] = logTime.end;
        }
        Arrays.sort(advs);

        logTimes = logTimes.stream().sorted(Comparator.comparing(LogTime::getStart)).collect(Collectors.toList());

        for(int i = 0 ;i<advs.length ;i++){
            int adv_start_time = advs[i];
            int adv_end_time = adv_start_time+advTime;

            if(adv_end_time > totalTime){
                break;
            }

            for(LogTime logTime : logTimes){
                if(logTime.start > adv_end_time){
                    break;
                }if(logTime.end < adv_start_time){
                    continue;
                }
                if(logTime.start <= adv_start_time && logTime.end >= adv_end_time){

                    advs_accumulate_count[i] += advTime;

                }else if(logTime.end > adv_start_time && logTime.start < adv_start_time){

                    advs_accumulate_count[i] += (logTime.end - adv_start_time);
                }else if(logTime.start < adv_end_time && logTime.end > adv_end_time){

                    advs_accumulate_count[i] += (adv_end_time - logTime.start);
                }

                System.out.println(String.format("[adv_start_time:%s(%s), " +
                                "adv_end_time:%s, " +
                                "accumulate:%s, " +
                                "logs:%s]",
                        times(adv_start_time),adv_start_time,adv_end_time,
                        advs_accumulate_count[i],
                        logTime.toString()));
            }
        }

        int max = Integer.MIN_VALUE;
        int index = 0;
        for(int i = 0; i<advs_accumulate_count.length ; i++){
            if(max < advs_accumulate_count[i]){
                max = advs_accumulate_count[i];
                index = i;
            }
        }

        return times(advs[index]);
    }

    public String solution2(String play_time, String adv_time, String[] logs){
        int[] timeline = new int[getTime(play_time)];

        for(String log : logs){
            int start = getTime(log.split("-")[0]);
            int end = getTime(log.split("-")[1]);

            for(int i = start ; i<end ;i++){
                timeline[i]++;
            }
        }

        int adv = getTime(adv_time);
        int idx = 0;
        long sum = Arrays.stream(timeline,0,adv).sum();
        long max = sum;
        System.out.println("초기값:"+sum);

//        for(int i = adv ; i<timeline.length ; i++){
//            sum = sum - timeline[i-adv] + timeline[i];
//            if(max < sum){
//                max = sum;
//                idx = i - adv + 1;
//            }
//        }

        for(int i = 1 ; i<timeline.length-adv + 1 ; i++){
            sum = sum - timeline[i-1] + timeline[i+(adv-1)];
            if(max < sum){
                max = sum;
                idx = i;
            }
        }

        System.out.println(idx);

            return String.format("%02d:%02d:%02d", idx/3600,
                    (idx%3600)/60, (idx%3600)%60);
    }

    private String times(int time){
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("%02d",time/3600));
        builder.append(":");
        builder.append(String.format("%02d",(time%3600)/60));
        builder.append(":");
        builder.append(String.format("%02d",(time%3600)%60));
        return builder.toString();
    }

    private int getTime(String time){
        String[] t = time.split(":");
        return Integer.valueOf(t[0]) * 3600 + Integer.valueOf(t[1]) * 60 + Integer.valueOf(t[2]);
    }

    public class LogTime{
        private int start;
        private int end;

        public LogTime(String[] time){
            this.start = getTime(time[0]);
            this.end = getTime(time[1]);
        }

        public int getStart() {
            return start;
        }

        private int getTime(String time){
            // HH:MM:SS - "00:00:00"
            String[] t = time.split(":");
            return Integer.valueOf(t[0]) * 3600 + Integer.valueOf(t[1]) * 60 + Integer.valueOf(t[2]);
        }

        @Override
        public String toString() {
            return String.format("{start:%s,end:%s}",this.start, this.end);
        }
    }
}
