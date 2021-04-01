package programmers.dfsbfs;

public class Network {

    public static void main(String[] args) {
        System.out.println(new Network().solution(3,new int[][]{{1,1,0},{1,1,0},{0,0,1}}));
        System.out.println(new Network().solution(3,new int[][]{{1,1,0},{1,1,1},{0,1,1}}));
        System.out.println(new Network().solution(6,new int[][]{
                {1,1,0,0,0,0},
                {1,1,0,1,0,1},
                {0,0,1,0,1,0},
                {0,1,0,1,0,0},
                {0,0,1,0,1,0},
                {0,1,0,0,0,1}
        }));

        System.out.println(new Network().solution(6,new int[][]{
                {1, 0, 1, 1, 0, 0},
                {0, 1, 0, 0, 1, 1},
                {1, 0, 1, 1, 1, 1},
                {1, 0, 1, 1, 1, 1},
                {0, 1, 1, 1, 1, 1},
                {0, 1, 1, 1, 1, 1}
        }));

    }

    public int solution(int n, int[][] computers) {
        int answer = 0;

        boolean[] visit = new boolean[n];

        for(int i = 0; i<n ;i++){
            if(!visit[i]){
                dfs(i, computers, visit);
                answer++;
            }
        }

        return answer;
    }

    private void dfs(int index, int[][] computers, boolean[] visit){

        visit[index] = true;
        for(int i = 0; i < computers.length ; i++){
            if(!visit[i] && computers[index][i] == 1){
                dfs(i,computers,visit);
            }
        }
    }
}
