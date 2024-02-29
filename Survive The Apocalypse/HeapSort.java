/* 
* @author (Tyler Babcock) 
* <p> (HeapSort) 
* <p> (HW5) 
* <p> (Takes 10 numbers and does pre and post sort on the sorted heap) 
*/
import java.util.Arrays;

import DSAndAlgos.*;

public class HeapSort {
    public static void main(String[] args) {
        Integer[] numbers = {2, 8, 5, 6, 7, 4, 3, 1, 10, 9};

        System.out.println("Heap Sort Demo");
        System.out.println("------------------------------------------");
        System.out.println("Pre-sort : " + Arrays.toString(numbers));

        LinkedMinHeap<Integer> heap = new LinkedMinHeap<>();
        for (Integer number : numbers) {
            heap.addElement(number);
        }

        Integer[] sortedNumbers = new Integer[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            sortedNumbers[i] = heap.removeMin();
        }

        System.out.println("Post-sort: " + Arrays.toString(sortedNumbers));
    }
}