package programmers.kakao;

import java.util.Stack;

public class CraneGame {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int rst = solution(new int[][] {{0,0,0,0,0},{0,0,0,0,0},{0,0,0,0,0},{0,0,0,0,0},{0,0,0,0,0}}, 
				new int[] {1,5,3,5,1,2,1,4});
//		int rst = solution(new int[][] {{0,0,0,0,0},{0,0,1,0,3},{0,2,5,0,1},{4,2,4,4,2},{3,5,1,3,1}}, 
//				new int[] {1,5,3,5,1,2,1,4});
		
		System.out.println(rst);

	}
	
    public static int solution(int[][] board, int[] moves) {
        int answer = 0;
        
        Stack<Integer> dolls = new Stack<>();
        
        for (int i = 0; i < moves.length; i++) {
			int idx = moves[i]-1;
			
			for (int j = 0; j < board.length; j++) {
				
				if(board[j][idx] > 0) {
					// visit dolls
					dolls.add(board[j][idx]);
					board[j][idx] = 0;
					break;
					
				}
			}
			
			// check to remove dolls
			if(! dolls.isEmpty()) {
				int doll = dolls.peek();
				if(dolls.size() > 1 && doll == dolls.elementAt(dolls.size()-2)) {
					dolls.pop();
					dolls.pop();
					answer += 2;
				}				
			}
			
		}
        
        return answer;
    }

}
