import java.util.Arrays;
import java.util.Random;

public class App {
    public static void main(String[] args) throws Exception {

        System.out.println("Testing with array of length 5:");
        sortingTest(genArray(5));

        System.out.println();

        System.out.println("Testing with array of length 50:");
        sortingTest(genArray(50));

        System.out.println();
        
        System.out.println("Testing with array of length 500:");
        sortingTest(genArray(500));

        System.out.println();
        
        System.out.println("Testing with array of length 50000:");
        sortingTest(genArray(50000));

        
    }

    // Test each sorting algorithm, measuring how long each takes to execute.
    static void sortingTest(int[] arr) {
        long T_start, T_end;
        int[] arr2 = arr; // copy of arr for bubblesort to sort. Attempting to avoid conflicts

        T_start = System.nanoTime();
        mergeSort(arr);
        T_end = System.nanoTime();
        long T_mergeSort = (T_end - T_start);
        
        T_start = System.nanoTime();
        bubbleSort(arr2);
        T_end = System.nanoTime();
        long T_bubbleSort = (T_end - T_start);
        
        System.out.println("Merge Sorting took: " + T_mergeSort/1000000.0 + " ms");
        System.out.println("Bubble Sorting took: " + T_bubbleSort/1000000.0 + " ms");
        
        double T_diff = Math.abs(T_mergeSort/1000000.0-T_bubbleSort/1000000.0);


        if (T_bubbleSort < T_mergeSort) {
            System.out.println(">Bubble Sort< was faster by " + T_diff + " ms");
        } else {
            System.out.println(">Merge Sort< was faster by " + T_diff + " ms");
        }
    }


    // Returns a random integer array n-length long, of value 0 .. n*2
    static int[] genArray(int n) {
        int[] arr = new int[n];
        Random r = new Random();
        for (int i = 0; i < n; i++) {
            arr[i] = r.nextInt(n*2);
        }
        return arr;
    }


    // Main driver function for merge sort
    static int[] mergeSort(int[] arr) {
        int L = arr.length;
        if (L == 1) 
            return arr;

        int[] arr1 = Arrays.copyOfRange(arr, 0, L/2);
        int[] arr2 = Arrays.copyOfRange(arr, L/2, L);

        arr1 = mergeSort(arr1);
        arr2 = mergeSort(arr2);

        return merge(arr1, arr2);
    }

    // Takes two arrays and iterates through them, incrementally adding the 
    //   smallest number between the two to a new array. Returns this new array. 
    static int[] merge(int[] arr1, int[] arr2) {
        int L1 = arr1.length;
        int L2 = arr2.length;

        int[] arr3 = new int[L1+L2];

        // i1 & i2 are counters for which number we're on in arr1 and arr2;
        // Using this method because java arrays are fixed in length and 
        //   thus cannot have elements simply removed or "popped" like in
        //   a queue system (which would be preferable, but I decided to stick
        //   with simple arrays)
        int i1 = 0;
        int i2 = 0;

        // iterate through all possible elements of arr3
        for (int i = 0; i < arr3.length; i++) {
            if (i1 >= L1) {  // Use the rest of arr2 because arr1 is "consumed"
                arr3[i] = arr2[i2];
                i2++;
                continue;
            } else if (i2 >= L2) {  // Use the rest of arr1 because arr2 is "consumed"
                arr3[i] = arr1[i1];
                i1++;
                continue;
            }
            
            // (assuming arr1 & arr2 are sorted) 
            //   if first element of arr1 is smaller, send that to arr3
            //   and increment appropriate counter. Same true for arr2<arr1.
            if (arr1[i1] <= arr2[i2]) {
                arr3[i] = arr1[i1];
                i1++;
            } else {
                arr3[i] = arr2[i2];
                i2++;
            }
        }

        return arr3;
    }

    // Bubble sort function from previous assignment
    public static void bubbleSort(int[] arr) {
        boolean swapped = false;
        // run this. then keep looping as long as we keep swapping numbers
        do {
            swapped = false;
            for (int i = 0; i < arr.length -1; i++) {
                if (arr[i] > arr[i+1]) {
                    // swap i and i+1
                    int x = arr[i];
                    arr[i] = arr[i+1];
                    arr[i+1] = x;
        
                    swapped = true;
                }
            }
        } while (swapped);
    }
}
