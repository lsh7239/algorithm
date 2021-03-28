package programmers.graph;

public class Fare {

    public static void main(String[] args) {
        System.out.println(new Fare().solution(6,4,6,2,new int[][]{
                {4, 1, 10}, {3, 5, 24}, {5, 6, 2}, {3, 1, 41}, {5, 1, 24}, {4, 6, 50}, {2, 4, 66}, {2, 3, 22}, {1, 6, 25}
        }));
//
//        System.out.println(new Fare().solution(6,1,6,2,new int[][]{
//                {4, 1, 10}, {3, 5, 24}, {5, 6, 2}, {3, 1, 41}, {5, 1, 24}, {4, 6, 50}, {2, 4, 66}, {2, 3, 22}, {1, 6, 25}
//        }));

//        System.out.println(new Fare2().solution(6,4,6,2,new int[][]{
//                {4, 1, 10}, {3, 5, 1}, {5, 6, 1}, {3, 1, 41}, {5, 1, 50}, {4, 6, 50}, {2, 4, 66}, {2, 3, 12}, {1, 6, 2}
//        }));

//        System.out.println(new Fare().solution(7,3,4,1,new int[][]{
//                {5, 7, 9}, {4, 6, 4}, {3, 6, 1}, {3, 2, 3}, {2, 1, 6}
//        }));
//
//        System.out.println(new Fare().solution(6,4,5,6,new int[][]{
//                {2,6,6}, {6,3,7}, {4,6,7}, {6,5,11}, {2,5,12}, {5,3,20}, {2,4,8}, {4,3,9}
//        }));
    }

    public int solution(int n, int s, int a, int b, int[][] fares) {
//        int answer = Integer.MAX_VALUE;

        // 간선 그래프
        int[][] map = new int[n+1][n+1];

        for(int i = 1 ; i<map.length ; i++){
            for(int j = 1; j<map.length ; j++){
                if(i != j) map[i][j] = 200 * 100000 * 2;
            }
        }

        for(int i = 0; i<fares.length; i++){
            map[fares[i][0]][fares[i][1]] = fares[i][2];
            map[fares[i][1]][fares[i][0]] = fares[i][2];
        }

        for(int k = 1 ; k<n+1 ;k++){
            // 합승 구간 찾기
            for(int i = 1; i< n+1 ; i++){
                for(int j = 1 ; j<n+1; j++){
                    if(map[i][k] + map[k][j] <  map[i][j]){
                        map[i][j] = map[i][k] + map[k][j];
                    }
                }
            }

        }

        int answer = map[s][a]+map[s][b];

        for(int i = 1; i<map.length ; i++){
            //if(map[s][i] == Integer.MAX_VALUE || map[i][a] == Integer.MAX_VALUE || map[i][b] == Integer.MAX_VALUE) continue;
            answer = Math.min(answer, map[s][i] + map[i][a] + map[i][b]);
        }

        return answer;
    }

}
