package programmers.kakao.blind_2018;

import java.util.LinkedList;
import java.util.Queue;

public class Cache {

    public static void main(String[] args) {
        System.out.println(new Cache().solution(2, new String[]{"Jeju", "Pangyo", "NewYork", "newyork"}));
    }

    public int solution(int cacheSize, String[] cities) {
        int answer = 0;

        // LRU
        // cache hit 1
        // cache miss 5

        Queue<String> cache = new LinkedList<>();
        for(int i = 0; i<cities.length; i++){
            String city = cities[i].toLowerCase();
            if(cache.contains(city)){
                // cache hit
                answer += 1;
                cache.remove(city); // 지웠다가
                cache.add(city); // 다시 최신으로
            }else{
                answer += 5;
                // cache miss
                if(cache.size() == cacheSize){
                    cache.poll();
                }
                if(cacheSize > 0){
                    cache.add(city);
                }
            }
            System.out.println(String.format("cache : %s",cache.toString()));
        }

        return answer;
    }
}
