package programmers.kakao.blind_2019;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class BadUser {

    static Set<List<String>> sets = new HashSet<>();

    public static void main(String[] args) {

//        System.out.println(new BadUser().solution(new String[]{"frodo", "fradi", "crodo", "abc123", "frodoc"},
//                new String[]{"fr*d*", "abc1**"}));

//        System.out.println(new BadUser().solution(new String[]{"frodo", "fradi", "crodo", "abc123", "frodoc"},
//                new String[]{"*rodo", "*rodo", "******"}));

        System.out.println(new BadUser().solution(new String[]{"frodo", "fradi", "crodo", "abc123", "frodoc"},
                new String[]{"fr*d*", "*rodo", "******", "******"}));

    }

    public int solution(String[] user_id, String[] banned_id) {
        int answer = 0;

        // 정규식 활용

        List<Integer>[] banned = new ArrayList[banned_id.length];
        for(int i  = 0 ;i<banned.length; i++){
            banned[i] = new ArrayList<>();
        }

        for(int i = 0; i<banned_id.length; i++){
            String regex = "("+banned_id[i].replaceAll("\\*",".").concat(")$");
            for(int j = 0; j<user_id.length ; j++){
                if(Pattern.matches(regex, user_id[j])){
                    banned[i].add(j);
                }
            }
        }

        boolean[] visit = new boolean[user_id.length];

        search(0, banned, new ArrayList<>(), user_id, banned_id, visit);

        return sets.size();
    }

    private void search(int index, List<Integer>[] banned, List<String> match, String[] user_id, String[] banned_id, boolean[] visit) {
        if(index < banned.length){
            List<Integer> bannedUser = banned[index];
            for(int i = 0;i<bannedUser.size();i++){
                if(!visit[bannedUser.get(i)]){
                    String result = user_id[bannedUser.get(i)];
                    match.add(result);
                    visit[bannedUser.get(i)] = true;
                    search(index+1, banned, match, user_id, banned_id, visit);
                    visit[bannedUser.get(i)] = false;
                    match.remove(match.size()-1);
                }
            }
        }else{
            List<String> fin = new ArrayList<>();
            fin.addAll(match);
            fin.sort(String::compareToIgnoreCase);
            sets.add(fin);
        }
    }
}
