package programmers.dfsbfs;

import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class SwitchWords {

    public static void main(String[] args) {
        System.out.println(new SwitchWords().solution("hit","cog",new String[]{"hot", "dot", "dog", "lot", "log", "cog"}));
        System.out.println(new SwitchWords().solution("hit","cog",new String[]{"hot", "dot", "dog", "lot", "log"}));
    }

    public int solution(String begin, String target, String[] words) {
        int answer = 0;
        // words 안에 target 단어가 없다면 0 리턴
        if(!Stream.of(words).filter(w -> w.equals(target)).findAny().isPresent()){
            return answer;
        }

        boolean[] visit = new boolean[words.length];
        Map<String,Integer> maps = new HashMap<>();

        Queue<String> queue = new LinkedList<>();
        queue.add(begin);
        maps.put(begin,0);

        boolean isFin = false;
        while(!queue.isEmpty()){
            String word = queue.poll();
            if(!begin.equals(word)) visited(word, words, visit);
            System.out.println(word);
            if(word.equals(target)){
                isFin = true;
                break;
            }
            List<String> search = search(word, words,visit);
            if(search.isEmpty()){
                // ?
            }
            search.forEach(s -> {
                if(!maps.containsKey(s)){
                    maps.put(s,maps.get(word)+1);
                }else{
                    maps.put(s, Math.min(maps.get(word)+1, maps.get(s)));
                }
            });
            queue.addAll(search);
        }

        return isFin ? maps.get(target) : 0;
    }

    private void visited(String word, String[] words, boolean[] visit) {
        int index = IntStream.range(0,words.length).filter(i -> words[i].equals(word)).findFirst().getAsInt();
        visit[index] = true;
    }

    private List<String> search(String word, String[] words, boolean[] visit) {

        List<String> result = new ArrayList<>();
        for(int i = 0; i<words.length; i++){
            if(!visit[i]){
                String w = words[i];
                int count = 0;
                for(int j = 0 ;j<w.length(); j++){
                    if(w.charAt(j) != word.charAt(j)){
                        count ++;
                        if(count > 1) break;
                    }
                }
                if(count == 1){
                    result.add(w);
                }
            }
        }
        return result;
    }
}
