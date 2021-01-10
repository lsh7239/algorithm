package programmers.codechallenge;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ChooseNumber {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ChooseNumber chooseNumber = new ChooseNumber();
		System.out.println(chooseNumber.solution(new int[]{2,1,3,4,1}));
	}

	public int[] solution(int[] numbers) {
		Set<Integer> integers = new HashSet<>();
		for(int i = 0; i< numbers.length ; i++){
			for(int j = (i+1) ; j<numbers.length ; j++){
				integers.add(numbers[i]+numbers[j]);
			}
		}
		return integers.stream().mapToInt(Integer::intValue).sorted().toArray();
	}

}
