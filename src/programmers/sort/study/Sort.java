package programmers.sort.study;

public class Sort {

    public static void main(String[] args) {
        System.out.println("insertion sort");
        InsertionSort.insert(new int[]{3,5,1,7,9,0});
        System.out.println();

        System.out.println("selection sort");
        SelectionSort.select(new int[]{3,5,1,7,9,0});
        System.out.println();

        System.out.println("bubble sort");
        BubbleSort.bubble(new int[]{3,5,1,7,9,0});
        System.out.println();
    }
}
