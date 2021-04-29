package programmers.practice;

public class Ground {

    public static void main(String[] args) {
        System.out.println(new Ground().solution(
                new int[][]{
                        {1,2,3,5},
                        {5,6,7,8},
                        {4,3,2,1}
                }
        ));
    }

    int solution(int[][] land) {
        int answer = 0;

        for(int i = 1 ;i<land.length ;i++){
            for(int j=0; j<4; j++){
                land[i][j] += search(j , land[i-1]);
            }
        }

        // answer는 마지막 열에서만 찾으면 됨
        for(int i = 0; i<4; i++){
            answer = Math.max(land[land.length-1][i], answer);
        }
        return answer;
    }

    private int search(int index, int[] land) {

        int max = Integer.MIN_VALUE;
        for(int i = 0; i<4; i++){
            if(i != index){
                max = Math.max(land[i], max);
            }
        }

        return max;
    }
}
