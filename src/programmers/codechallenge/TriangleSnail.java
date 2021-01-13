package programmers.codechallenge;

public class TriangleSnail {

    int[][] dir = new int[][]{{1,0},{0,1},{-1,-1}};

    public static void main(String[] args) {
        TriangleSnail t = new TriangleSnail();
        System.out.println(t.solution(5));
    }

    public int[] solution(int n) {

        int[][] arr = new int[n][n];

        int total = 0;
        for(int i = 0 ; i< n ; i++){
            arr[i] = new int[(i+1)];
            total += arr[i].length;
        }

        int[] answer = new int[total];

        int idx = 1;
        int x = 0, y = 0;
        int d = 0;
        arr[x][y] = idx;
        idx ++;
        while (idx <= total){
            int nextX = x+dir[d][0];
            int nextY = y+dir[d][1];
            if(nextX < n && nextY < n && nextX >= 0 && nextY >= 0 && arr[nextX][nextY] == 0){
                arr[nextX][nextY] = idx;
                x = nextX;
                y = nextY;
                idx++;
            }else{
                d = (d+1)%3;
                continue;
            }
        }

        total = 0;

        for(int i = 0 ; i<arr.length;i++){
            for(int j = 0; j<arr[i].length; j++){
                answer[total] = arr[i][j];
                total++;
            }
        }

        return answer;
    }
}
