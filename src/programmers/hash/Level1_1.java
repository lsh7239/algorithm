package programmers.hash;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Level1_1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String rst = solution(new String[] {"leo", "kiki", "eden"}, new String[] {"kiki", "eden"});
		System.out.println(rst);
	}
	
	
    public static String solution(String[] participant, String[] completion) {
    	
        String answer = "";
        
        // #1 for
        for(int i = 0; i<participant.length; i++) {
        	boolean finish = false;
        	
        	for(int j = 0 ; j<completion.length ; j++) {
        		if(participant[i] == completion[j]) {
        			finish = true;
        			break;
        		}
        	}
        	
        	if(finish == false) {
        		answer=participant[i];
        		break;
        	}
        }
        
        // #2 HashCode + Map
        Map<String, Long> participantMap = Stream.of(participant)
        		.collect(Collectors.groupingBy(x-> x, Collectors.counting()));
        
        for (String string : completion) {
			participantMap.put(string, participantMap.get(string)+1);
		}
        
        answer = participantMap.entrySet().stream().filter(e -> e.getValue()%2 == 1).findFirst().get().getKey();
        
        // #3 Sort
        Arrays.sort(participant);
        Arrays.sort(completion);
        int i = 0;
        
        for(; i< completion.length ; i++) {
        	if(!participant[i].equals(completion[i])) {
        		answer = participant[i];
        		break;
        	}
        }
        
        return answer;
    }

}
