package programmers.sort;

import java.util.Comparator;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class MaxNum {

    public static void main(String[] args) {
        MaxNum maxNum = new MaxNum();
        System.out.println(maxNum.solution(new int[]{10,101}));
    }

    public String solution(int[] numbers) {
        String answer = "";

        String[] arr = IntStream.of(numbers).boxed().map(i->new ExtendedString(i))
                .sorted(ExtendedString::compareTo)
                .map(ExtendedString::getValue)
                .toArray(String[]::new);

        for(int i = arr.length-1 ; i >= 0 ; i--){
            answer += arr[i];
        }
        if(answer.startsWith("0")){
            answer = String.valueOf(Long.valueOf(answer));
        }

        return answer;
    }

    public class ExtendedString implements Comparable<ExtendedString>{

        String value;

        public ExtendedString(int value){
            this.value = String.valueOf(value);
        }

        public String getValue() {
            return value;
        }

        @Override
        public int compareTo(ExtendedString o) {

            String val = this.value;
            String oVal = o.value;
            for(int i = 0; i<3 ; i++){
                val += this.value;
                oVal += o.value;
            }

            return val.compareTo(oVal);

//            if(this.value.length() > o.value.length()){
//                if(this.value.startsWith(o.value)){
//                    String val = this.value;
//                    int comp = 0;
//                    while(val.length() > o.value.length()){
//                        val = val.substring(o.value.length() , val.length() >= 2*o.value.length() ? 2*o.value.length() : val.length());
//                        comp = val.compareTo(o.value);
//                        if(comp > 0){
//                            return comp;
//                        }else{
//                            continue;
//                        }
//                    }
//                    return comp;
//                }else{
//                 return this.value.compareTo(o.value);
//                }
//            }else if(this.value.length() < o.value.length()){
//                if(o.value.startsWith(this.value)){
//                    String val = o.value;
//                    int comp = 0;
//                    while(val.length() > this.value.length()){
//                        val = val.substring(this.value.length() , val.length() >= 2*this.value.length() ? 2*this.value.length() : val.length());
//                        comp = this.value.compareTo(val);
//                        if(comp > 0){
//                            return comp;
//                        }else{
//                            continue;
//                        }
//                    }
//                    return comp;
//
//                }else{
//                    return this.value.compareTo(o.value);
//                }
//            }else{
//                return this.value.compareTo(o.value);
//            }

        }
    }

}
