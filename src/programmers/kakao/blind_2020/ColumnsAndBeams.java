package programmers.kakao.blind_2020;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ColumnsAndBeams {

    static int[][] dir = {{-1,0},{0,1},{1,0},{0,-1}};

    public static void main(String[] args) {
        int[][] result = new ColumnsAndBeams().solution(5, new int[][]{
                {1,0,0,1},
                {1,1,1,1},
                {2,1,0,1},
                {2,2,1,1},
                {5,0,0,1},
                {5,1,0,1},
                {4,2,1,1},
                {3,2,1,1}
        });

        System.out.println();

        int[][] result2 = new ColumnsAndBeams().solution(5, new int[][]{
                {0,0,0,1},
                {2,0,0,1},
                {4,0,0,1},
                {0,1,1,1},
                {1,1,1,1},
                {2,1,1,1},
                {3,1,1,1},
                {2,0,0,0},
                {1,1,1,0},
                {2,2,0,1}
        });
    }


    public int[][] solution(int n, int[][] build_frame) {
        int[][] answer = {};

        // map init
        int[][][] map = new int[n+1][n+1][2];

        for(int i = 0 ;i<build_frame.length ; i++){
            int[] frame = build_frame[i];
            int x = frame[0];
            int y = frame[1];
            int a = frame[2];
            int b = frame[3];
            if(b == 1){
                boolean isValid = isValid(x,y,a,map);
                if(isValid){
//                    map[x][y] = a;
                    map[x][y][a] = 1;
                }
            }else{
                // 삭제 시 현재 만들어진 모든 map을 다 확인한다.
                search(x,y,a,map);
            }
        }

        List<Result> results = new ArrayList<>();
        for(int i = 0 ; i<map.length ;i++){
            for(int j = 0 ;j<map.length ; j++){
                if(map[i][j][0] > 0) {
                    results.add(new Result(i,j,0));
                }
                if(map[i][j][1] > 0){
                    results.add(new Result(i,j,1));
                }
            }
        }

        System.out.println(results.toString());

        Object[] obj = results.stream()
                .sorted(Comparator.comparing(Result::getX)
                .thenComparing(Result::getY)
                .thenComparing(Result::getA))
                .map(r -> new int[]{r.x, r.y, r.a})
                .toArray();

        answer = new int[obj.length][];
        for(int i = 0 ;i<obj.length ;i++){
            answer[i] = (int[])obj[i];
        }
        return answer;
    }

    private void search(int x, int y, int a, int[][][] map) {
        // 삭제되었다고 가정
        map[x][y][a] = 0;

        for(int i = 0; i<map.length ; i++){
            for(int j = 0; j<map.length ;j++){
                if(map[i][j][0] > 0){
                    boolean isValid = isValid(i,j,0,map);
                    if(!isValid){
                        // 원상복귀
                        map[x][y][a] = 1;
                        return;
                    }
                }
                if(map[i][j][1] > 0){
                    boolean isValid = isValid(i,j,1,map);
                    if(!isValid){
                        // 원상복귀
                        map[x][y][a] = 1;
                        return;
                    }
                }
            }
        }
    }

    private boolean isValid(int x, int y, int a, int[][][] map){
        if(a == 0){
            // 기둥
            if(y == 0){
                // 벽이라면 통과
                return true;
            }else{
                // 보의 한쪽 끝 부분 위에 있거나 : x-1,y,1 || x,y,1 == 1
                boolean isTrue = false;
                if(x-1 >= 0){
                    isTrue = map[x-1][y][1] == 1;
                }
                isTrue = isTrue || (map[x][y][1] == 1);
                if(isTrue) return true;

                // 다른 기둥 위에 있어야 합니다. : x,y-1,0 == 1
                if(y-1 >= 0){
                    isTrue = map[x][y-1][0] == 1;
                    if(isTrue) return true;
                }
            }
        } else{
            // 보
            // 한쪽 끝 부분이 기둥 위에 있거나 : x,y-1,0 || x+1,y-1,0 == 1
            boolean isTrue = false;
            if(y-1 >= 0){
                isTrue = map[x][y-1][0] == 1;
                if(x+1 < map.length){
                    isTrue = isTrue || (map[x+1][y-1][0] == 1);
                }
                if(isTrue) return true;
            }
            // 양쪽 끝 부분이 다른 보와 동시에 연결되어 있어야 합니다. :  x+1,y,1 || x-1,y,1 == 1
            if(x-1 >= 0){
                isTrue = map[x-1][y][1] == 1;
            }
            if(x+1 < map.length){
                isTrue = isTrue && map[x+1][y][1] == 1;
            }
            if(isTrue) return true;

        }
        return false;
    }

    public class Result{
        int x; // 가로좌표
        int y; // 세로좌표
        int a; // 0:기둥, 1:보

        public Result(int x, int y, int a){
            this.x = x;
            this.y = y;
            this.a = a;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public int getA() {
            return a;
        }

        @Override
        public String toString() {
            return String.format("[%s,%s,%s]",x,y,a);
        }
    }
}
