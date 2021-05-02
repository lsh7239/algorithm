package boj.graph;

import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class MooTube {

    public static void main(String[] args) {

        int maxNum = 2000000000;
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int q = sc.nextInt();

        int[][] arr = new int[n][n];
        List<int[]>[] arq = new ArrayList[n];

        for(int i = 0; i<arq.length;i++){
            arq[i] = new ArrayList<>();
        }

        sc.nextLine();

        for(int i = 0 ;i<n-1 ; i++){
            String[] number = sc.nextLine().split(" ");
            int x = Integer.valueOf(number[0])-1;
            int y = Integer.valueOf(number[1])-1;
            int val = Integer.valueOf(number[2]);

            arq[x].add(new int[]{y,val});
            arq[y].add(new int[]{x,val});
        }

        StringBuilder builder = new StringBuilder();
        for(int i = 0; i<q ; i++){

            String[] testCase = sc.nextLine().split(" ");
            int root = Integer.valueOf(testCase[1])-1;
            int count = search(root, arq, Integer.valueOf(testCase[0]), n);
            builder.append(count).append("\n");
//            System.out.println(count);
        }
        System.out.println(builder);
    }

    private static int search(int root, List<int[]>[] arq, int usado, int n) {

        int count = 0;

        boolean[] visit = new boolean[n];
        visit[root] = true;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(root);

        while(!queue.isEmpty()){
//            Maps nextMaps = queue.poll();
            int index = queue.poll();

            System.out.println("index:"+index);

            for(int[] ar : arq[index]){
                if(!visit[ar[0]] && ar[1] >= usado){
                    queue.add(ar[0]);
                    visit[ar[0]] = true;
                    count ++;
                }
            }

//            int[] nextMap = arr[index];
//            for(int i = 0; i<nextMap.length;i++){
//                if(i != root && !visit[i] && nextMap[i] >= usado){
////                    queue.add(new Maps(nextMaps.y,i,nextMap[i]));
//                    queue.add(i);
//                    visit[i] = true;
//                    count ++;
//                }
//            }
        }

        return count;
    }

    public static class Maps{
        int x;
        int y;
        int value;

        public Maps(int x, int y, int value){
            this.x = x;
            this.y = y;
            this.value =value;
        }

        @Override
        public String toString() {
            return String.format("[x:%s,y:%s,value:%s]",x,y,value);
        }
    }
}
