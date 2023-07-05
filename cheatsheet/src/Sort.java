import java.util.Arrays;

public class Sort {
    private static void quicksort( int[] array ){
        quicksort( array, 0, array.length - 1 );
    }

    private static void quicksort( int[] array, int lowIndex, int highIndex ){
        if( lowIndex >= highIndex )
            return;

        int pivotIndex = Random.range( lowIndex, highIndex );
        int pivot = array[ pivotIndex ];
        swap( array, pivotIndex, highIndex );

        int leftPointer = partition( array, lowIndex, highIndex, pivot );

        quicksort( array, lowIndex, leftPointer - 1 );
        quicksort( array, leftPointer + 1, highIndex );
    }

    private static int partition( int[] array, int lowIndex, int highIndex, int pivot ){
        int leftPointer = lowIndex;
        int rightPointer = highIndex - 1;

        while( leftPointer < rightPointer ){
            // Walk from the left until we find a number greater than the pivot, or hit the
            // right pointer.
            while( array[leftPointer] <= pivot && leftPointer < rightPointer )
                leftPointer++;

            // Walk from the right until we find a number less than the pivot, or hit the
            // left pointer.
            while( array[rightPointer] >= pivot && leftPointer < rightPointer )
                rightPointer--;

            swap( array, leftPointer, rightPointer );
        }

        if( array[leftPointer] > array[highIndex] ) 
            swap(array, leftPointer, highIndex);
        else
            leftPointer = highIndex;

        return leftPointer;
    }

    private static void swap( int[] array, int index1, int index2 ){
        int tmp = array[ index1 ];
        array[ index1 ] = array[ index2 ];
        array[ index2 ] = tmp;
    }

    public static void main( String[] args ) {
        // Criação do array com o Random
        int[] array_sort = new int[ 10000000 ];
        for( int i = 0; i < array_sort.length; i++ )
            array_sort[ i ] = Random.range( 0, 10000 );

        // Cópia do array
        int[] array_quicksort = array_sort.clone();
        int[] array_counting = array_sort.clone();

        // Sort default
        long inicio = System.currentTimeMillis();
        Arrays.sort( array_sort );
        long fim = System.currentTimeMillis();
        System.out.println( "Tempo do Arrays.Sort() em ms: " + (fim - inicio) );

        // QuickSort
        inicio = System.currentTimeMillis();
        Sort.quicksort( array_quicksort );
        fim = System.currentTimeMillis();
        System.out.println( "Tempo do Quicksort() em ms: " + (fim - inicio) );

        // Counting Sort
        inicio = System.currentTimeMillis();
        int max = Integer.MIN_VALUE;  
        for( int num: array_counting )
            if( Math.abs(num) > max ) max = num;
        
        int[] counting = new int[ max + 1 ];     
        for( int num: array_counting )
            counting[ num ]++;
        
        int arrayIndex = 0;
        for( int i = 0; i < counting.length; i++ )
            while( counting[i]-- >= 1 )
                array_counting[ arrayIndex++ ] = i;

        fim = System.currentTimeMillis();
        System.out.println( "Tempo do CoutingSort() em ms: " + (fim - inicio) );
    }
}