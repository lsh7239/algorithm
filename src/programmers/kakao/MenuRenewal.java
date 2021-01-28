package programmers.kakao;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MenuRenewal {

    public static void main(String[] args) {
        MenuRenewal m = new MenuRenewal();
        System.out.println(m.solution(new String[]{"ABC", "BCD", "CDE","DEF","EFG","FGH"}, new int[]{2,3,4}));
    }

    public String[] solution(String[] orders, int[] course) {

        Set<String> set = new HashSet<>();
        Map<String,Integer> characters = new HashMap<>();
        for(String order : orders){
//            set.addAll(Stream.of(order.split("")).collect(Collectors.toList()));
            for(String o : order.split("")){
                int cnt = characters.getOrDefault(o,0);
                characters.put(o, cnt+1);
            }
        }
        set = characters.entrySet().stream().filter(e -> e.getValue() > 1).map(Map.Entry::getKey).collect(Collectors.toSet());

        List<String> values = new ArrayList<>();

        for (int i = 0; i < course.length; i++) {
            int count = course[i];
            boolean[] visit = new boolean[set.size()];

            Map<String, Integer> map = new HashMap<>();
            solv(visit, orders,  "", count, 0, map, set.stream().collect(Collectors.toList()));

            Optional<Map.Entry<Integer,List<Map.Entry<String,Integer>>>> optional = map.entrySet().stream()
                    .collect(Collectors.groupingBy(Map.Entry::getValue))
                    .entrySet().stream().max(Comparator.comparing(Map.Entry::getKey));

            List<String> val = new ArrayList<>();
            if(optional.isPresent()){
                val = optional.get().getValue().stream()
                        .filter(e -> e.getValue() > 1)
                        .map(Map.Entry::getKey).collect(Collectors.toList());
            }

            values.addAll(val);
        }

        return values.stream().sorted().toArray(String[]::new);
    }

    public void solv(boolean[] visit, String[] orders, String candidate, int count, int idx, Map<String,Integer> map,
                     List<String> set){
        // contains
        if(candidate.length() == count){
            String[] candidates = candidate.split("");
            // check
            for(String order : orders){
                if(check(order,candidates)){
                    int cnt = map.getOrDefault(candidate,0);
                    map.put(candidate, cnt+1);
                }
            }
        }

        for(int i = idx ; i < set.size() ; i++){
            if(!visit[i]){
                visit[i] = true;
                solv(visit, orders, candidate+set.get(i), count, i+1,map, set);
                visit[i] = false;
            }
        }
    }

    private boolean check(String order, String[] candidates) {
        boolean isCandidate = true;
        for(String cand : candidates){
            if(!order.contains(cand)){
                isCandidate = false;
                break;
            }
        }
        return isCandidate;
    }

}
