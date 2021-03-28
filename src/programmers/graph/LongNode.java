package programmers.graph;

import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class LongNode {

    static final int INF = 20000 * 2;

    public static void main(String[] args) {
        System.out.println(new LongNode().solution2(6, new int[][]{
                {3, 6}, {4, 3}, {3, 2}, {1, 3}, {1, 2}, {2, 4}, {5, 2}
        }));
    }

    public int solution(int n, int[][] edge) {
        int answer = 0;

        int[][] arr = new int[n][n];

        for(int i = 0; i<n ; i++){
            for(int j = 0; j<n;j++){
                if(i != j) arr[i][j] = INF;
            }
        }

        for(int i = 0; i<edge.length ; i++) {
            arr[edge[i][0] - 1][edge[i][1] - 1] = 1;
            arr[edge[i][1] - 1][edge[i][0] - 1] = 1;
        }
        for(int k = 0; k<n ; k++){
            for(int i = 0;i<n;i++){
                for(int j = 0; j<n;j++){
                    arr[i][j] = Math.min(arr[i][j], arr[i][k]+arr[k][j]);
                }
            }
        }
        int max = IntStream.of(arr[0]).max().getAsInt();
        return Long.valueOf(IntStream.of(arr[0]).filter(num -> num ==max).count()).intValue();
    }

    public int solution2(int n, int[][] edge) {

        // distance[i] = 0부터 i까지 최소 거리
        List<Integer>[] adj = new ArrayList[n];
        int[] distance = new int[n];
        // distance[] 초기화
        for(int i = 1; i < distance.length ; i++){
            distance[i] = -1;
        }

        for(int[] eg: edge){
            if(eg[0]-1 == 0){
                distance[eg[1]-1] = 1;
            }
            if(eg[1]-1 == 0){
                distance[eg[0]-1] = 1;
            }
            if(adj[eg[0]-1] == null) adj[eg[0]-1] = new ArrayList<>();
            if(adj[eg[1]-1] == null) adj[eg[1]-1] = new ArrayList<>();
            adj[eg[0]-1].add(eg[1]-1);
            adj[eg[1]-1].add(eg[0]-1);
        }

        Queue<Integer> queue = new LinkedList<>();
        queue.addAll(adj[0]);

        while (!queue.isEmpty()){
            int here = queue.poll();
            System.out.println("visit : "+here);

            for(int i = 0 ; i<adj[here].size();i++){
                int next = adj[here].get(i);
                if(distance[next] == -1){
                    // 아직 방문하지 않은 노드
                    queue.add(next);
                    distance[next] = distance[here]+1;
                }
            }

        }

        int max = IntStream.of(distance).max().getAsInt();
        return Long.valueOf(IntStream.of(distance).filter(dis -> dis == max).count()).intValue();
    }

    public class Node{
        int v;
        int e;

        public Node(int v){
            this.v = v;
            this.e = 1;
        }

        public Node(int v, int e){
            this.v = v;
            this.e = Math.min(e,this.e);
        }

        public void minEdge(int e){
            this.e = Math.min(e,this.e);
        }

        @Override
        public String toString() {
            return String.format("[v:%s,e:%s]",v,e);
        }
    }
}
