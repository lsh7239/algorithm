package programmers.kakao.intern_2020;

public class Keypad {

    public static void main(String[] args) {
        System.out.println(new Keypad().solution(new int[]{1, 3, 4, 5, 8, 2, 1, 4, 5, 9, 5}, "right"));
        System.out.println(new Keypad().solution(new int[]{7, 0, 8, 2, 8, 3, 1, 5, 7, 6, 2}, "left"));
        System.out.println(new Keypad().solution(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 0}, "right"));
    }

    public String solution(int[] numbers, String hand) {

        StringBuilder builder = new StringBuilder();

        int left = 9;
        int right = 11;

        for(int num : numbers){
            num = num > 0 ? num-1 : 10;
            if(num % 3 == 0){
                builder.append("L");
                left = num;
            }else if(num % 3 == 1){
                // compare
                int leftD = distance(num, left);
                int rightD = distance(num, right);
                if(leftD > rightD){
                    builder.append("R");
                    right = num;
                }else if(leftD < rightD){
                    builder.append("L");
                    left = num;
                }else{
                    if("left".equals(hand)){
                        builder.append("L");
                        left = num;
                    }else{
                        builder.append("R");
                        right = num;
                    }
                }

            }else{
                builder.append("R");
                right = num;
            }
        }

        return builder.toString();
    }

    private int distance(int num, int point) {

        int[] numPoint = point(num);
        int[] pPoint = point(point);
        return Math.abs(numPoint[0]-pPoint[0]) + Math.abs(numPoint[1]-pPoint[1]);
    }

    private int[] point(int point){
        return new int[]{point / 3, point % 3};
    }
}
