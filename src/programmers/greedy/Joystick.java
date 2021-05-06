package programmers.greedy;

import java.util.stream.IntStream;

public class Joystick {

    public static void main(String[] args) {
        System.out.println(new Joystick().solution("JEROEN"));
        System.out.println(new Joystick().solution("JAN"));
        System.out.println(new Joystick().solution("JAZ"));
    }

    public int solution(String name) {
        int answer = 0;
        String fin = name.replaceAll("[A-Z]","A");
        char[] spell = name.toCharArray();

        int index = 0;
        while (true){
            String nowSpell = String.valueOf(spell);
            System.out.println(nowSpell);
            if(fin.equals(nowSpell)){
                break;
            }
            int value = Math.min(spell[index] - 'A', 'Z'-spell[index]+1);
            answer += value;
            spell[index] = 'A';
            
            int leftDistance = leftDistance(index, spell);
            int rightDistance = rightDistance(index, spell);

            if(leftDistance < rightDistance){
                // <- 로 가야 최소값
                index--;
                if(index < 0){
                    index = spell.length-1;
                }
            }else{
                // -> 로 가야 최소값
                index++;
                if(index >= spell.length){
                    index = 0;
                }
            }
            answer++;
        }
        
        return answer-1;
    }


    private int leftDistance(int index, char[] spell) {
        int cnt = 0;
        boolean fin = false;
        for(int i = index - 1 ; i>=0 ;i--){
            cnt ++;
            if('A' != spell[i]){
                fin = true;
                break;
            }
        }
        if(!fin){
            for(int i = spell.length-1 ; i > index ; i-- ){
                cnt++;
                if('A' != spell[i]){
//                    fin = true;
                    break;
                }
            }
        }
        return cnt;
    }

    private int rightDistance(int index, char[] spell) {
        int cnt = 0;
        boolean fin = false;
        for(int i = index + 1 ; i<spell.length ;i++){
            cnt ++;
            if('A' != spell[i]){
                fin = true;
                break;
            }
        }
        if(!fin){
            for(int i = 0 ; i < index ; i++ ){
                cnt++;
                if('A' != spell[i]){
//                    fin = true;
                    break;
                }
            }
        }
        return cnt;
    }
}
