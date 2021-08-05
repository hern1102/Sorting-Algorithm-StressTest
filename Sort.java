/*
Description: THIS IS THE MAIN CLASS. In this code file I have four sorting algorithms
that all operate off of the array in the data section. This file will test each sorting 
algorithm for the number of times it iterates through the data, and the amount of time
it takes to go through the alogrithm.
 */

package project.pkg7;

import java.util.Random;


public class Sort{
    
    /*
    Below in the field, an instance of Timer is created in order to access some 
    of the functionality, like starting and stopping time. An array of double is 
    also created to store the array that the sorting alogrithms will be sorting.
    */
    
    private double[] array;
    private int iterations = 0;
    private Timer time = new Timer();
    
    public Sort(){ 
    }
    
    public Sort(int sizeOfArray){
        setSize(sizeOfArray);  
    }
    
    /*
    Function: setSize(int sizeOfArray)
    Description: This method sets the size of the array by passing an int as the 
    size of the array. It then generates a certain amount of random double depending
    on the size of the array.
    Inputs: An int is required
    Outputs: None
    */
    
    
    public void setSize(int sizeOfArray){
        
        array = new double[sizeOfArray];
        double min = 0.0;
        double max = 10.0;
        
        for(int i = 0; i < sizeOfArray; i++){
            
          //array[i] = 0.1 + i;
  
          double x = (Math.random() * ((max - min) + 1)) + min; 
          double xrounded = Math.round(x * 100.0) / 100.0; 
          array[i] = xrounded;
            
        }

    }

    /*
    Function: int getIterations()
    Description: This method gets the amount of times a certain sorting algorithm 
    sorts through an array. 
    Inputs: None
    Outputs: An int that represents the amount of iterations
    */
    
    public int getIterations(){
        int num = this.iterations;
        this.iterations = 0;
        return num;
    }
    
    
    
    /*
    Function: String getTime()
    Description: This method returns a string in the format used in the timer
    class. It returns the time in Micro, Milli, and Seconds it takes to sort
    through the array.
    Inputs: None
    Outputs: A string with the amount of time taken
    */
    
    public String getTime(){
        return "Micro " + time.getMicro() + "/ " + "Milli " + time.getMilli() + "/ " + "Seconds " + time.getSecond();
    }
    
    
    // ************************* MERGE SORT *************************************
    
    
    /*
    Function: double[] mergesort()
    Description: This method takes the array in the data section and uses private 
    methods to take an array of doubles and then splits the array in two and continues
    to do this recursively in order to sort the array. After this, it merges the 
    array back together into a sorted array.
    Inputs: None
    Outputs: A sorted array derived from the array in the field.
    */
    
    public double[] mergesort(){
        double[] arr = this.array.clone();
        time.startTimer();
        double[] temp = mergesort(arr, new double[arr.length], 0, arr.length - 1);
        time.stopTimer();
        return temp;
    }
    
    /*
    Function: private mergesort
    Description: This method takes the array in the data section and uses private 
    methods to take an array of doubles and then splits the array in two and continues
    to do this recursively in order to sort the array. After this, it merges the 
    array back together into a sorted array.
    Inputs: None
    Outputs: A sorted array derived from the array in the field.
    */
    
    private double[] mergesort(double[] arr, double[] temp, int leftStart, int rightEnd){
        
        iterations++;
        if(!(leftStart >= rightEnd)){
        int middle = (leftStart + rightEnd) / 2;
        mergesort(arr, temp, leftStart, middle);
        iterations++;
        mergesort(arr, temp, middle + 1, rightEnd);
        iterations++;
        mergeHalves(arr, temp, leftStart, rightEnd);
        }

        return arr;

    }
    
    /*
    Function: private double[] mergeHalves
    Description: This method is the one that merges the halves of each array 
    after they are split, compared, and sorted. 
    Inputs: 2 arrays, the leftstart index, and the rightend index
    Outputs: a combined array
    */
    
    private double[] mergeHalves(double[] arr, double[] temp, int leftStart, int rightEnd){
        
        int leftEnd = (rightEnd + leftStart)/2;
        int rightStart = leftEnd + 1;
        int size = rightEnd - leftStart + 1;
        
        int left = leftStart;
        int right = rightStart;
        int index = leftStart;
        
        while(left <= leftEnd && right <= rightEnd){
            if(arr[left] <= arr[right]){
                temp[index] = arr[left];
                left++;
            }else{
                temp[index] = arr[right];
                right++;
            }
            
            index++;
        }
        
        System.arraycopy(arr, left, temp, index, leftEnd - left + 1);
        System.arraycopy(arr, right, temp, index, rightEnd - right + 1);
        System.arraycopy(temp, leftStart, arr, leftStart, size);
        return arr;
    }
    
    

    // ************************* INSERTION SORT *************************************
    
    
    
    /*
    Function: double[] insertionsort()
    Description: This method goes through each element in the array, comparing 
    the data, and moving a wall (index), basically creating one side of the 
    array, a sorted side, and the other side of the array, an unsorted side. Until 
    the array is compelely sorted.
    Inputs: None
    Outputs: A sorted array derived from the array in the field.
    */
    
    public double[] insertionsort(){
        
        time.startTimer();
        double[] arr = this.array.clone();

        int i, j;
        double key, temp;
        
        for(i = 1; i < arr.length; i++){
            key = arr[i];
            j = i - 1;
            iterations++;
            
            while(j >= 0 && key < arr[j]){
                iterations++;
                temp = arr[j];
                arr[j] = arr[j + 1];
                arr[j + 1] = temp;
                j--;
            }
        }
        
        time.stopTimer();
        return arr;
    }
    
    // ************************* QUICK SORT *************************************
    
    
    /*
    Function: double[] quicksort()
    Description: This method chooses an index in the array as a pivot and then 
    starts from the far left and right side of the array and anything that is less 
    than the value of the pivot is swapped with anything on the left side that is 
    greater than the value of the pivot. 
    Inputs: None
    Outputs: A sorted array derived from the array in the field.
    */
    
    
    public double[] quicksort(){
        
        double[] arr = this.array.clone();
        time.startTimer();
        double[] temp = quicksort(arr, 0, arr.length - 1);
        time.stopTimer();
        return temp;
        
    }
    
    /*
    Function: This is the private method to the description above
    Description: This method chooses an index in the array as a pivot and then 
    starts from the far left and right side of the array and anything that is less 
    than the value of the pivot is swapped with anything on the left side that is 
    greater than the value of the pivot. 
    Inputs: None
    Outputs: A sorted array derived from the array in the field.
    */
    
    private double[] quicksort(double[] arr, int left, int right){
        
        iterations++;
        if(left < right + 1){
            int p = partition(arr, left, right);
            quicksort(arr, left, p - 1);
            iterations++;
            quicksort(arr, p + 1, right);
            iterations++;
        }
        return arr;
        
    }
    
    /*
    Function: int getPivot
    Description: This method simply generates a random pivot
    Inputs: left and right index
    Outputs: an int that represents a pivot
    */
    
    private int getPivot(int left, int right){
        
        Random rand = new Random();
        return rand.nextInt((right - left) + 1) + left;
    }
    
    /*
    Function: int partition
    Description: This method simply partitions the left and right side of the 
    array in order to use recursion
    Inputs: the array, and the left and right index
    Outputs: An int that represents the partition
    */
    
    private int partition(double[] arr, int left, int right){
        
        swap(arr, left, getPivot(left, right));
        int border = left + 1;
        for(int i = border; i <= right; i++){
            if(arr[i] < arr[left]){
                swap(arr, i, border++);
            }
        }
        swap(arr, left, border - 1);
        return border - 1;
        
    }
    
    /*
    Function: swap
    Description: This method simply swaps to values in the array
    Inputs: the array, and the left and right values
    Outputs: None
    */
    
    private void swap(double[] arr, int left, int right){
        
        double temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
    }
    
    // ************************* SELECTION SORT *************************************
    
    /*
    Function: double[] selectionSort()
    Description: This method searches the array of doubles and finds the smallest
    value and then puts that value to the smallest index available, and then continues
    this process until the entire array is sorted.
    Inputs: None
    Outputs: A sorted array derived from the array in the field.
    */
    
    public double[] selectionSort(){
        
        double[] arr = this.array.clone();
        
        return selectionSort(arr, arr.length - 1);
        
    }
    
    /*
    Function: private double[] selectionSort
    Description: This method is the private method to the one above
    Inputs: None
    Outputs: A sorted array derived from the array in the field
    */
    
    
    private double[] selectionSort(double[] arr, int last){
        
        time.startTimer();
        int smallest = 0;
        double holdData = 0;

        for(int current = 0; current < last; current++){
            smallest = current;
            iterations++;
            for(int index = current + 1; index <= last; index++){
                iterations++;
                if(arr[index] < arr[smallest]){
                    smallest = index;
                }
            }
            holdData = arr[current];
            arr[current] = arr[smallest];
            arr[smallest] = holdData;
        }
        
        time.stopTimer();
        return arr;
    }
    

    public static void main(String[] args) {
        
        /*
        In the code below, I basically call all four of the sorting algorithms, their
        iterations, and the amount of time it took for each algorithm. I also printed 
        the array that was randomly generated. I did this so we could see that it was 
        actually sorting the array. You can easily change the number in setSize to 
        change the size of the array for randomly generated doubles.
        
        When it comes to large data sets (an array of 10,000 random doubles), I found 
        that merge sort was consistently faster than the rest, with quicksort right
        behind it. I do believe that this is probably due to the divide and conquer 
        technique that the sorting alorithm uses, which proves to be beneficial with 
        larger data sets.
        */
        
               
        Sort st = new Sort();
        st.setSize(10000);
        
        
        st.selectionSort();
        System.out.println("Selection Sort Iterations: " + st.getIterations());
        System.out.println("Selection Sort Time: " + st.getTime() + "\n");

        st.insertionsort();
        System.out.println("Insertion Sort Iterations: " + st.getIterations());
        System.out.println("Insertion Sort Time: " + st.getTime() + "\n");
        
        
        st.mergesort();
        System.out.println("Merge Sort Iterations: " + st.getIterations());
        System.out.println("Merge Sort Time: " + st.getTime() + "\n");


        st.quicksort();
        System.out.println("Quick Sort Iterations: " + st.getIterations());
        System.out.println("Quick Sort Time: " + st.getTime() + "\n");
        

    }
    
}
