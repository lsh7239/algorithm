package programmers.kakao;

import java.util.*;
import java.util.stream.Collectors;

public class MenuRenewal {

    public static void main(String[] args) {
        MenuRenewal m = new MenuRenewal();
        String[] result = m.solution(new String[]{
                "XYZ", "XWY", "WXA"
//                "QWERTYUIOP",
//                "ASDFGHJKLP",
//                "ZXCVBNMLPO",
//                "ABCDEFGHIJ",
//                "POIULKJHMN",
//                "QAZWSXEDCR",
//                "UHBNMKIOJL",
//                "ZMXNALSKQW",
//                "TPLAKSBMCN"
        }, new int[]{2,3,5});

        for (String rst:
             result) {
            System.out.println(rst);
        }
    }

    public String[] solution(String[] orders, int[] course) {

        List<String> values = new ArrayList<>();
        // course 갯수에 따른
        for(int count : course){
            // 메뉴들의 모든 조합을 구하기
            Map<String, Integer> maps = new HashMap<>();
            for(String order : orders){
                String[] o = order.split("");
                Arrays.sort(o);
                boolean[] visit = new boolean[o.length];
                combination(o,count, visit, 0,"", maps);
            }
            Optional<Map.Entry<Integer,List<Map.Entry<String,Integer>>>> optional = maps.entrySet().stream()
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

    private void combination(String[] order, int count, boolean[] visit, int index, String result, Map<String,Integer> maps) {

        if(result.length() == count){
            int value = maps.getOrDefault(result,0);
            maps.put(result,value+1);
        }

        for(int i = index ; i<order.length ; i++){
            if(!visit[i]){
                visit[i] = true;
                combination(order,count,visit,i+1,result+order[i],maps);
                visit[i] = false;
            }
        }

    }

}
