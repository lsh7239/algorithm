package programmers.kakao;

import java.util.Stack;

public class StringCompression {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		StringCompression compression = new StringCompression();
		System.out.println(compression.solution("xababcdcdababcdcd"));

	}

	public int solution(String s) {
		int answer = Integer.MAX_VALUE;

		for(int i = 1; i <= s.length() ; i++){
			System.out.println(" === Start "+i+" ===");
			Stack<String> stack = new Stack<>();
			int startIdx = 0;
			int max = 0;
			while(startIdx < s.length()){
				String nextStr = null;
				if(startIdx + i >s.length()){
					nextStr = s.substring(startIdx);
				}else{
					nextStr = s.substring(startIdx, (startIdx+i));
				}
				if(stack.isEmpty()){
					stack.add(nextStr);
				}else{
					String peek = stack.peek();
					if(!peek.equals(nextStr)){
						System.out.println(String.format("[%s:%s]",peek,stack.size()));
						int size = stack.size();
						if(size > 1){
							max += i + String.valueOf(stack.size()).length();
						}else{
							max += i;
						}
						stack.clear();
					}
					stack.add(nextStr);
				}
				startIdx += i;
			}

			if(!stack.isEmpty()){
				String peek = stack.peek();
				System.out.println(String.format("[%s:%s]",peek,stack.size()));
				int size = stack.size();
				if(size > 1){
					max += i + String.valueOf(stack.size()).length();
				}else{
					max += peek.length();
				}
			}

			answer = Math.min(max, answer);
			System.out.println("answer : "+answer);
		}

		return answer;
	}
}
