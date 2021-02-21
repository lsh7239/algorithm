package programmers.practice;

public class OneTwoFour {

    public static void main(String[] args) {

        for(int i = 1 ; i<=50 ; i++){
            System.out.println(new OneTwoFour().solution(i));
        }

        System.out.println(new OneTwoFour().solution(500000000));

    }

    public String solution(int n) {
        System.out.print(n + "\t");
        String answer = "";
        StringBuilder sb = new StringBuilder();
        int[] arr = {4,1,2,4};

        while( n > 0){
            int a = n/3;
            int b = n%3;

//            answer = arr[b] + answer;
            sb.insert(0, arr[b]);
            if(b == 0){
                a = a-1;
            }

            n = a;
        }

        return sb.toString();
    }

    private String solv(int n, String answer, int[] arr) {

        int a = n/3;
        int b = n%3;

        answer = arr[b] + answer;
        if(b == 0){
            a = a-1;
        }
        if(a > 0){
            answer = solv(a,answer,arr);
        }

        return answer;
    }

}
