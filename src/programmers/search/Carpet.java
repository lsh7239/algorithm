package programmers.search;

import java.util.ArrayList;
import java.util.List;

public class Carpet {

    public static void main(String[] args) {
        Carpet carpet = new Carpet();
        System.out.println(carpet.solution(24,24));
    }

    public int[] solution(int brown, int yellow) {
        int[] answer = new int[2];

        List<Yellow> yellows= new ArrayList<>();
        for(int i = 1 ; i <= yellow ; i++){
            if(yellow % i == 0){
                int x = yellow / i;
                int y = i;
                if(x >= y){
                    yellows.add(new Yellow(x,y));
                }else{
                    break;
                }
            }
        }

        for (Yellow ylw:
             yellows) {

            int totalX = ylw.x+2;
            int totalY = ylw.y+2;
            System.out.println(ylw.toString());
            System.out.println(totalX*totalY-yellow);

            if(totalX*totalY-yellow == brown){
                answer[0] = totalX;
                answer[1] = totalY;
                break;
            }
        }

        return answer;
    }

    public class Yellow{
        int x;
        int y;

        Yellow(int x, int y){
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return String.format("[%s,%s]",x,y);
        }
    }
}
