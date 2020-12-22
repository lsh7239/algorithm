package programmers.kakao;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Tuple {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Tuple tuple = new Tuple();
//		System.out.println(tuple.solution("{{2,1},{2,1,3},{2,1,3,4},{2}}"));
		System.out.println(tuple.solution("{{20,111},{111}}"));

	}
	
    public int[] solution(String s) {
    	s = s.substring(1, s.length()-1);
    	String p = "[{](.*?)[}]";
    	Pattern pattern = Pattern.compile(p);
    	Matcher matcher = pattern.matcher(s);
    	List<int[]> list = new ArrayList<>();
    	
    	int cnt = Integer.MIN_VALUE;
    	while(matcher.find()) {
    		String[] arr = matcher.group().substring(1, matcher.group().length()-1).split(",");
    		list.add(Stream.of(arr).mapToInt(x->Integer.valueOf(x)).toArray());
    		if(arr.length > cnt) {
    			cnt = arr.length;
    		}
//    		array[arr.length] = Stream.of(arr).mapToInt(x->Integer.valueOf(x)).toArray();
    	} 	
    	
        int[] answer = new int[cnt];
        int[][] array = new int[cnt][];
        for (int[] arr : list) {
        	array[arr.length-1] = arr;
		}
        
        boolean[] visit = new boolean[100000];
        
        for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[i].length; j++) {
				if(!visit[array[i][j]]) {
					answer[i] = array[i][j];
					visit[array[i][j]] = true;
					break;
				}
			}
		}
        
        return answer;
    }

}
