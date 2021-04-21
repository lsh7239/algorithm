package programmers.dynamic;

public class OneTheWayToSchool {

    public static void main(String[] args) {
        System.out.println(new OneTheWayToSchool().solution(4,3, new int[][]{{2,2}}));
    }

    public int solution(int m, int n, int[][] puddles) {
        int answer = 0;

        int[][] map = new int[m][n];
        boolean[][] isPuddles = new boolean[m][n];
        for(int i = 0; i<puddles.length;i++){
            isPuddles[puddles[i][0]-1][puddles[i][1]-1] = true;
        }

        if(!isPuddles[0][1]) map[0][1] = 1;
        if(!isPuddles[1][0]) map[1][0] = 1;

        for(int i = 0;i<map.length;i++){
            for(int j = 0;j<map[i].length;j++){
                if(!isPuddles[i][j] && i+j > 0){
//                    map[i][j] = map[i-1][j] + map[i][j-1];
                    if(j > 0 && i > 0){
                        map[i][j] = (map[i-1][j] + map[i][j-1]) % 1000000007;
                    }else{
                        if(j == 0 && map[i][j] == 0) map[i][j] = map[i-1][j] % 1000000007;
                        else if(i == 0 && map[i][j] == 0) map[i][j] = map[i][j-1] % 1000000007;
                    }
                }
            }
        }

        return map[m-1][n-1] ;
    }
}
