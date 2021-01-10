package programmers.dynamic;

import java.util.*;
import java.util.stream.Collectors;

public class ExpressionByN {



    public static void main(String[] args) {
        ExpressionByN e = new ExpressionByN();
        System.out.println(e.solution(2,11));
    }

    public int solution(int N, int number) {

        int answer = 0;
        Map<Integer, Set<Integer>> numberSet = new HashMap<>();

        for(int i = 1 ; i<=8 ; i++){

            Set<Integer> set = numberSet.getOrDefault(i, new HashSet<>());
            set.add(getRepeatNumber(N,i));
            if(!numberSet.containsKey(i)){
                numberSet.put(i,set);
            }
            System.out.println("Start i >> "+i);

            for(int j = 1 ; j >= 1 && j<i ; j++){
                List<Integer> firstSet = numberSet.get(j).stream().collect(Collectors.toList());
                List<Integer> secondSet = numberSet.get(i-j).stream().collect(Collectors.toList());;

                System.out.println(String.format("index : %s | %s",j ,firstSet.toString()));
                System.out.println(String.format("index : %s | %s",(i-j) ,secondSet.toString()));
                // a 와 b 사칙연산
//                List<Integer> result = calculation(firstSet,secondSet);
                set.addAll(calculation(firstSet,secondSet));
            }

            if(set.contains(number)){
                return i;
            }

            System.out.println("result : "+set.toString());
            System.out.println("----------------------");

        }

        return -1;
    }

    private Set<Integer> calculation(List<Integer> first, List<Integer> second){
        Set<Integer> result = new HashSet<>();
        for(int i = 0 ; i<first.size() ; i++){
            for(int j = 0 ; j<second.size() ; j++){
                result.add(first.get(i)+second.get(j));
                result.add(first.get(i)-second.get(j));
                result.add(first.get(i)*second.get(j));
                if(second.get(j) != 0){
                    result.add(first.get(i)/second.get(j));
                }
            }
        }
        return result;
    }

    public int getRepeatNumber(int n, int count){
        String num = "";
        for(int i = 0 ; i < count ; i++){
            num += n;
        }
        return Integer.valueOf(num);
    }

//
//    public void init(int n){
//
//        numberSet.put(1, Arrays.asList(n));
//    }
}
