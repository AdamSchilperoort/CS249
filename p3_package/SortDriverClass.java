package p3_package;

/**
 * Class used to test merge, quick, and Shell sorts
 * @author adamschilperoort
 */
public class SortDriverClass 
   {
    
    /**
     * Main function driver for sort operations
     * @param args String array not used
     */
    public static void main(String[] args)
      {
       int[] testArray;
       int numValues = 25, lowLimit = 10, highLimit = 99;
       String resultString;
     
       testArray = loadRands( numValues, lowLimit, highLimit );
       resultString = arrayToString( testArray, numValues );
       System.out.println( "\nArray before Merge Sort: " + resultString );
       runMergeSort( testArray, numValues );
       resultString = arrayToString( testArray, numValues );
       System.out.println( "Array after Merge Sort: " + resultString );
     
       testArray = loadRands( numValues, lowLimit, highLimit );
       resultString = arrayToString( testArray, numValues );
       System.out.println( "\nArray before Quick Sort: " + resultString );
       runQuickSort( testArray, numValues );
       resultString = arrayToString( testArray, numValues );
       System.out.println( "Array after Quick Sort: " + resultString );
     
       testArray = loadRands( numValues, lowLimit, highLimit );
       resultString = arrayToString( testArray, numValues );
       System.out.println( "\nArray before Shell's Sort: " + resultString );
       runShellSort( testArray, numValues );
       resultString = arrayToString( testArray, numValues );
       System.out.println( "Array after Shell's Sort: " + resultString );            
     }
    
    /**
     * constructor
     */
    public SortDriverClass()
       {
          //no code
       }
    
    /**
     * Data sorted using merge sort algorithm
     * <p>
     * Note: Call runMergeSortHelper with lower and upper indices of array to
     * be sorted
     * 
     * @param localArray - integer array holding unsorted values
     * 
     * @param size - integer value holding number of values in array
     */
    public static void runMergeSort( int[] localArray, int size )
       {
           runMergeSortHelper( localArray, 0, size - 1 );
       }
    
    /**
     * Merge sort helper, places low and high indices of 
     * array segment to be processed into recursive method,
     * then sorts data using merge sort algorithm
     * 
     * @param localArray - integer array holding unsorted values
     * 
     * @param lowIndex - lowest index of array segment to be managed;
     * this varies as the segments are broken down recursively
     * 
     * @param highIndex - highest index of array segment to be managed;
     * this varies as the segments are broken down recursively
     */
    private static void runMergeSortHelper(int[] localArray,
                                             int lowIndex,
                                               int highIndex )
       {
        if( lowIndex < highIndex )
           {
            int mid = ( highIndex + lowIndex )/2; 
            
            runMergeSortHelper( localArray, lowIndex, mid );
            runMergeSortHelper( localArray, mid+1, highIndex );
            runMerge( localArray, lowIndex, mid, highIndex );
            
           }
       }
    
    /**
     * Merges values brought in between a low and high index segment of an array
     * 
     * Note: uses locally sized single array for temporary storage
     * 
     * @param localArray - integer array holding unsorted values
     * 
     * @param lowIndex - lowest index of array segment to be managed
     * 
     * @param middleIndex - middle index of array segment to be managed
     * 
     * @param highIndex - high index of array segment to be managed
     */
    private static void runMerge( int[] localArray, int lowIndex,
                                    int middleIndex, int highIndex )
       {
         int midPoint = middleIndex - lowIndex + 1;
         int sizeArr = highIndex - lowIndex + 1;
         
         int array[] = new int [ sizeArr ];
         
         int indexLeft, indexRight, indexLocalArr;
         int index;
         
         for (index = 0; index < sizeArr; index++) 
            {
             array[ index ] = localArray[ index + lowIndex ]; 
            }
         
         indexLeft = 0;
         indexRight = midPoint; 
         indexLocalArr = lowIndex;
         
         while( ( indexLeft < midPoint ) && ( indexRight < sizeArr ) ) 
            {
             if ( array[ indexLeft ] <= array[ indexRight ])
                {
                 localArray[ indexLocalArr ] = array[ indexLeft ];
                 indexLeft++;
                }
             else 
                {
                 localArray[ indexLocalArr ] = array[ indexRight ];
                 indexRight++;
                }
             indexLocalArr++;
            }
         
         while ( indexRight < sizeArr )
            {
             localArray[ indexLocalArr ] = array[ indexRight ];
             indexRight++;
             indexLocalArr++;
            }
         while ( indexLeft < midPoint )
            {
             localArray[ indexLocalArr ] = array[ indexLeft ];
             indexLeft++;
             indexLocalArr++;
            } 
       }
    
    /**
     * Data sorted using quick sort algorithm
     * <p>
     * Note: Call runQuickSortHelper with lower and upper
     * indices of array to be sorted
     * 
     * @param localArray integer array holding unsorted values
     * 
     * @param size integer value holding the number of values in the array
     */
    public static void runQuickSort( int[] localArray, int size )
       {
        runQuickSortHelper( localArray, 0, size-1 );
       }
            
    /**
     * helper method run with parameters that support
     * recursive access
     * 
     * @param localArray integer array holding unsorted values
     * 
     * @param lowIndex low index of the segment of the array 
     * to be processed
     * 
     * @param highIndex high index of the segment of the array 
     * to be processed
     */
    private static void runQuickSortHelper( int[] localArray, 
                                               int lowIndex, int highIndex )
       {
        if (lowIndex < highIndex) 
           { 
            int partitionIndex = runPartition( localArray, lowIndex, 
                                                                   highIndex ); 
  
            runQuickSortHelper( localArray , lowIndex , partitionIndex -1 ); 
            runQuickSortHelper( localArray , partitionIndex + 1, highIndex ); 
           } 
       }
   
    /**
     * Partitions array using the first value as the partition;
     * when this method is complete the partition value is
     * in the correct location in the array
     * 
     * @param localArray integer array holding unsorted values
     * 
     * @param lowIndex low index of array segment to be partitioned
     * 
     * @param highIndex high index of array segment to be partitioned
     * 
     * @return integer index of partition pivot
     */
    private static int runPartition( int[] localArray, int lowIndex, 
                                                         int highIndex )
       {
       
        int pivot = localArray[highIndex];  
        int smallerIndex = (lowIndex-1); 
        int increment;
        
        for ( increment = lowIndex; increment < highIndex; increment++ ) 
           { 
            if ( localArray[ increment ] <= pivot ) 
               { 
                smallerIndex++; 
  
                swapValues( localArray, smallerIndex, increment );
               } 
           } 
  
        swapValues( localArray, smallerIndex+1, highIndex );
  
        return smallerIndex+1;
       }
   
    /**
     * Uses Shell's sorting algorithm to sort an array of integers
     * <p>
     * Shell's sorting algorithm is an optimized insertion algorithm
     * 
     * @param localArray integer array holding unsorted values
     * 
     * @param size integer number of items in array
     */
    public static void runShellSort( int[] localArray, int size )
       {
        int gap, gapPassIndex, insertionIndex, temp;
    
        for( gap = size / 2; gap > 0; gap /= 2 )
           {
            for( gapPassIndex = gap; gapPassIndex < size; gapPassIndex++ )
               {
                temp = localArray[ gapPassIndex ];

                for( insertionIndex = gapPassIndex; insertionIndex >= gap && 
               localArray[ insertionIndex - gap] > temp; insertionIndex -= gap )
                   {
                    localArray[ insertionIndex ] = 
                                            localArray[ insertionIndex - gap ];
                   }

                localArray[ insertionIndex ] = temp;                 
               }
           }    
       }
    
    /**
     * Method to load a given integer array with random values
     * 
     * @param numRands integer identifies number of values to generate
     * 
     * @param lowLimit integer identifies low limit of generated randoms
     * 
     * @param highLimit integer identifies high limit of generated randoms
     * 
     * @return integer array with data loaded
     */
    public static int[] loadRands( int numRands, int lowLimit, int highLimit )
       {
        int index;
        int[] localArray = new int[ numRands ];
       
        for( index = 0; index < numRands; index++ )
           {
            localArray[ index ] = generateRandBetween( lowLimit, highLimit );
           }

        return localArray;
       }

    /**
     * Generate random value between the lowest and highest
     * specified limits inclusive
     * 
     * @param lowLimit specified integer low limit of random value range
     * 
     * @param highLimit specified integer high limit of random value range
     * 
     * @return random integer value generated between the inclusive limits
     */
    public static int generateRandBetween( int lowLimit, int highLimit )
       {
        int randVal, range;
       
        if( highLimit > lowLimit )
           {
            // create range of numbers
            range = highLimit - lowLimit + 1;
           
            // find random integer value from value between 0 and 1
            randVal = (int)( Math.random() * 1000000 );
           
            return randVal % range + lowLimit;
           }
       
        return 0;
       }

    /**
     * Converts array of integers into string for output or display
     * 
     * @param localArray integer array of values
     * 
     * @param size number of values in the array
     * 
     * @return String holding a list of all values, comma-delimited
     */
    public static String arrayToString( int[] localArray, int size )
       {
        int index;
        String outString ="";
        
        for( index = 0; index < size; index++ )
           {
            if( index > 0 )
               {
                outString += ", ";
               }
            
            outString += localArray[ index ];
           }
        
        return outString;
       }
    
    /**
     * Swaps values within given array
     * 
     * @param localArray array of Objects used for swapping
     * 
     * @param indexOne integer index for one of the two items to be swapped
     * 
     * @param indexOther integer index for the other of the two items 
     * to be swapped
     */
    public static void swapValues( int[] localArray, int indexOne,
                                                                int indexOther )
       {
        int temp = localArray[ indexOne ];
        
        localArray[ indexOne ] = localArray[ indexOther ];
        
        localArray[ indexOther ] = temp;        
       }

   }

