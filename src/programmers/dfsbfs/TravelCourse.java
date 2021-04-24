package programmers.dfsbfs;

import java.util.*;
import java.util.stream.Stream;

public class TravelCourse {

//    List<String[]> arrayList = new ArrayList<>();
    String[] answer = null;

    public static void main(String[] args) {

        System.out.println(new TravelCourse().solution(new String[][]{
                {"ICN", "A"}, {"ICN", "A"}, {"ICN", "A"}, {"A","ICN"},{"A","ICN"}
        }));

        System.out.println(new TravelCourse().solution(new String[][]{
                {"ICN", "SFO"}, {"ICN", "ATL"}, {"SFO", "ATL"}, {"ATL", "ICN"}, {"ATL", "SFO"}
        }));


    }

    public String[] solution(String[][] tickets) {
//        String[] answer = {};

        boolean[] visited = new boolean[tickets.length];

        Map<String, List<String>> map = new HashMap<>();
        for(String[] ticket: tickets){
            if(!map.containsKey(ticket[0])){
                map.put(ticket[0], new ArrayList<>());
            }
            map.get(ticket[0]).add(ticket[1]);
        }

        search("ICN", map, visited, new ArrayList<>(), tickets);

        return answer;
    }

    private void search(String from, Map<String, List<String>> map, boolean[] visited, List<String> list, String[][] tickets) {
        list.add(from);
        List<String> to = map.get(from);
        if(isAllVisited(visited)){

            System.out.println(list.toString());
            compare(list);

        }
        if(to != null){
            int size = to.size();

            for(int i = 0 ; i<size ; i++){
                String next = to.get(i);
//            to.remove(i);
                int index = index(from, next, tickets, visited);
                if(index > -1 &&!visited[index]){
                    visited[index] = true;
                    List<String> newList = new ArrayList<>();
                    newList.addAll(list);
                    search(next, map, visited, newList, tickets);
//                to.add(i, next);
                    visited[index] = false;
                }

            }
        }

    }

    private void compare(List<String> list) {
        boolean isChanged = false;
        if(answer == null){
            isChanged = true;
        }else{
            StringBuilder newBuilder = new StringBuilder();
            list.forEach(s -> newBuilder.append(s));

            StringBuilder arrayBuilder = new StringBuilder();
            Stream.of(answer).forEach(s -> arrayBuilder.append(s));

            if(arrayBuilder.toString().compareTo(newBuilder.toString()) > 0){
                isChanged = true;
            }

        }

        if(isChanged){
            answer = list.stream().toArray(String[]::new);
        }
    }

    private boolean isAllVisited(boolean[] visited) {
        boolean result = true;
        for(int i = 0; i<visited.length ;i++){
            if(!visited[i]){
                result = false;
                break;
            }
        }
        return result;
    }

    private int index(String from, String to, String[][] tickets, boolean[] visited) {
        for(int i = 0; i<tickets.length;i++){
            if(tickets[i][0].equals(from) && tickets[i][1].equals(to) && !visited[i]) {
                return i;
            }
        }
        return -1;
    }
}
