package p2_package;


/**
 * Homework 2, CS 249
 * @author Adam Schilperoort
 */
@SuppressWarnings("unchecked")
public class GenericSetClass<GenericData extends 
                                            java.lang.Comparable<GenericData>>
   {
    
    private static final int BASE_TWO = 2;
    public static final int DEFAULT_ARRAY_CAPACITY = 10;
    private int arrayCapacity;
    private int arraySize;
    private GenericSetClass<GenericData>[] genericPwrSetArray;
    private java.lang.Object[] genericSetArray; //Object aray for data

    /*
     * Default Constructor
     * Initializes set array but sets power set array to null
     */
    GenericSetClass()
       {
        arrayCapacity = DEFAULT_ARRAY_CAPACITY;
        arraySize = 0;
        genericSetArray = new Object [ arrayCapacity ];
        genericPwrSetArray = null;
       }

    /**
     * Initialization Constructor
     * Allows specification of set array capacity
     * Initializes set array but sets power set array to null
     * @param initialCapacity - integer that specifies array capacity
     */
    GenericSetClass( int initialCapacity )
       {
        arrayCapacity = initialCapacity;
        arraySize = 0;
        genericSetArray = new Object [ arrayCapacity ];
        genericPwrSetArray = null;
       }

    /**
     * Copy Constructor
     * Duplicates copied set class
     * Also responsible for correct initialization/update of set class array
     * @param copied - GenericSetClass object to be copied
     */
    GenericSetClass( GenericSetClass copied )
       {
        arrayCapacity = copied.arrayCapacity;
        arraySize = copied.arraySize;
        genericPwrSetArray = copied.genericPwrSetArray;
        int index;

        for( index = 0; index < arraySize; index++ )
           {
            genericSetArray[ index ] = copied.genericSetArray[ index ];
           }
        
       }

    /**
     * addItem
     * adds integer to set
     * increases capacity using checkForResize if array is full
     * does not allow duplicate values in set
     * @param item - integer value to be added to set
     */
    public void addItem( GenericData item )
       {
        if ( !hasElement( item ) )
           {
            checkForResize();
            genericSetArray[ arraySize ] = item;
            arraySize++;
           }
       }

    /**
     * Removes element if it is found in set
     * All values equal to given value will be removed in case there is
     * more than one
     * @param valToRemove - integer value to be removed
     * @return boolean result of operation success
     */
    public boolean removeItem( GenericData valToRemove )
       {
        int index;
        for( index = 0; index < arraySize; index++ )
           {
            if (valToRemove == genericSetArray[index])
               {
                removeAtIndex(index);
                return true;
               }
           }
        return false;
       }
    
    /**
     * Swaps values within given array
     * @param localArray - array of Objects used for swapping
     * @param indexOne - integer index for one of the two items to be swapped
     * @param indexOther - integer index for the other of the 
     * two items to be swapped
     */
    private void swapValues(Object[] localArray, int indexOne, int indexOther)
       {
           
        Object savedValue = localArray[indexOne];
        localArray[indexOne] = localArray[indexOther];
        localArray[indexOther] = savedValue;
        
       }

    /**
     * Removes value at given index; moves all succeeding data down in array
     * @param indexToRemove - integer index of element value to remove
     */
    private void removeAtIndex( int indexToRemove )
       {
        int index;
        arraySize--;
        for( index = indexToRemove; index < arraySize; index++ )
           {
            genericSetArray[index] = genericSetArray[index+1];
           }
       }

    /**
     * Returns the intersection of THIS set and the given other set
     * @param other - GenericSetClass data with which intersection is found
     * @return GenericSetClass object with intersection of two sets
     */
    @SuppressWarnings("unchecked")
    GenericSetClass<GenericData> findIntersection(GenericSetClass<GenericData>
                                                                        other)
       {
        int index;
        GenericSetClass<GenericData> duplicate = new GenericSetClass<GenericData>();
        GenericData castedData;
        
        for ( index = 0; index < arraySize; index++ ) 
           {
            castedData = (GenericData) genericSetArray[ index ];
            if ( other.hasElement( castedData ) )
                    {
                        duplicate.addItem( castedData );
                    }
           } 
        return duplicate;
       }

    /**
     * Returns the union of THIS set and the given other set
     * @param other - GenericSetClass data with which union is found
     * @return GenericSetClass object with union of two sets
     */
    @SuppressWarnings("unchecked")
    public GenericSetClass<GenericData> findUnion( GenericSetClass<GenericData> other)
       {
        int index;
        GenericSetClass<GenericData> duplicate = new GenericSetClass<GenericData>();
        GenericData castedData;
        
        for ( index = 0; index < arraySize; index++ )
           {
             castedData = (GenericData) genericSetArray[ index ];
             duplicate.addItem(castedData);
           }
        for ( index = 0; index < other.arraySize; index++ )
           {
             castedData = (GenericData) other.genericSetArray[ index ];
             duplicate.addItem(castedData);
           }
        return duplicate;
       }

    /**
     * Finds relative complement of THIS set in given other set
     * Returns other set having removed any items intersection with THIS set
     * @param other - GenericSetClass object from which THIS GenericSetClass
     * item will be removed
     * @return GenericSetClass object with data as specified
     */
    @SuppressWarnings("unchecked")
    public GenericSetClass<GenericData> findRelativeComplementOfThisSetIn( 
                                           GenericSetClass<GenericData> other )
       {
        
        int index;
        GenericSetClass<GenericData> duplicate = 
                                            new GenericSetClass<GenericData>();
        GenericData castedData;
        
        for ( index = 0; index < other.arraySize; index++ ) 
           {
            castedData = (GenericData) other.genericSetArray[ index ];
            if ( !hasElement( castedData) )
                    {
                        duplicate.addItem( castedData );
                    }
           } 
        return duplicate;
       }
    
    /**
     * Tests to indicate if set is subclass another given set
     * @param other - GenericSetClass object to be tested if THIS set 
     * is a subset of it
     * @return boolean result of test
     */
    @SuppressWarnings("unchecked")
    public boolean isSubsetOf( GenericSetClass<GenericData> other )
       {
        int index;
        GenericData castedData;
        
        for( index = 0; index < other.arraySize; index++ )
           {
            castedData = (GenericData) other.genericSetArray[ index ];
            if ( !hasElement( castedData ) )
               {
                  return false;  
               }
           }
        return true;
       }

    /**
     * Tests to indicate if integer value is one of the set elements
     * @param testElement - integer element to be found in set
     * @return boolean result of test
     */
    @SuppressWarnings("unchecked")
    public boolean hasElement( GenericData testElement )
       {
        int index;
        GenericData castedData;
        
        for ( index = 0; index < arraySize; index++ )
           {
            castedData = (GenericData) genericSetArray[ index ];
            if ( testElement.compareTo( castedData ) == 0 )
               {
                return true; 
               }
           }
        return false;
       }

 
    /**
     * Local function tests for resize of the set array.  If array needs to be
     * resized, array capacity is doubled; otherwise, no change
     * @return boolean report that resize was conducted
     */
    private boolean checkForResize()
       { 
        int index;
        if ( arraySize == arrayCapacity )
           {
            arrayCapacity = arrayCapacity * 2;
            Object[] tempArray = new Object[ arrayCapacity ];
            for ( index = 0; index < arraySize; index++ )
               {
                tempArray[ index ] = genericSetArray[ index ];
               }
            genericSetArray = tempArray;
            return true;
           }
        return false;
       }

    /**
     * Provides list of set array elements as comma-delimited string
     * @Overrides toString in class java.lang.Object
     */
    @Override
    public java.lang.String toString()
       {
        return toStringHelper(genericSetArray);
       }
    
    /**
     * Provides list of set array elements as comma-delimited string
     * @param localArray - Object array holding Generic Data
     * @return String holding objects from array
     */
    public String toStringHelper(Object[] localArray)
       {
        String result = "";
        int index;
        
        for( index = 0; index < arraySize; index++ )
           {
             result += localArray[ index ];
             if ( index != arraySize - 1 )
                {
                 result += ", "; 
                }
           }
        return result;
        
       }
    
    /**
     * copies one Object array to another
     * @param destArray - Object array being copied to
     * @param sourceArray - Object array being copied from
     */
    private void copyArray(java.lang.Object[] destArray,
                       java.lang.Object[] sourceArray)
       {
        int index;
        
        for ( index = 0; index < arraySize; index++ ) 
           {
            destArray[ index ] = sourceArray[ index ]; 
           }
       }
    
    /**
     * Sorts elements using the bubble sort algorithm
     * The data is sorted using the compareTo method of the given data set;
     * the compareTo method decides the key and the order resulting
     * @return String data holding list of sorted objects
     */
    @SuppressWarnings("unchecked")
    public String runBubbleSort()
       {
        int index; 
        boolean isSorted = false; 
        GenericData castedData1, castedData2;
        Object[] duplicate = new Object [ arraySize ];
        
        copyArray(duplicate, genericSetArray);
        
        
        while( !isSorted )
           {
            
            isSorted = true;
            for( index = 0; index < arraySize - 1; index++ )
               {  
                castedData1 = (GenericData) duplicate[ index ];
                castedData2 = (GenericData) duplicate[ index + 1 ];
                
                if ( castedData1.compareTo( castedData2 ) > 0) 
                   {
                    isSorted = false;
                    swapValues( duplicate , index, index +1 );
                   }
               }
            }  
        return toStringHelper(duplicate);
       }
    
    /**
     * Sorts elements using the selection sort algorithm
     * The data is sorted using the compareTo method of the given data set; 
     * the compareTo method decides the key and the order resulting
     * @return String data holding list of sorted objects
     */
    @SuppressWarnings("unchecked")
    public java.lang.String runSelectionSort()
       {
        int index, index1, minVal;
        Object temp;
        
        Object[] duplicate = new Object [ arraySize ];
        copyArray(duplicate, genericSetArray);
        
        GenericData castedData1, castedData2;
        
        for( index = 0; index < arraySize; index++ )
           {
            minVal = index;
            for( index1 = index + 1; index1 < arraySize; index1++)
               {
                castedData1 = ( GenericData ) duplicate [ minVal ];
                castedData2 = ( GenericData ) duplicate [ index1 ];
                if( castedData2.compareTo(castedData1) < 0)
                   {
                    minVal = index1;
                   }
               }
            temp = duplicate[ minVal ];
            duplicate[ minVal ] = duplicate[ index ];
            duplicate[ index ] = temp;
           }
         
        return toStringHelper(duplicate);
       }
    
    /**
     * Sorts elements using the insertion sort algorithm
     * The data is sorted using the compareTo method of the given data set; 
     * the compareTo method decides the key and the order resulting
     * @return String data holding list of sorted objects
     */
    @SuppressWarnings("unchecked")
    public java.lang.String runInsertionSort()
       {
           
        int index, index2;
        GenericData key, castedData2;
        Object[] duplicateArr = new Object [ arraySize ];
        
        copyArray( duplicateArr, genericSetArray );
        
        for ( index = 1; index < arraySize; index++ )
           {
     
            index2 = index - 1;
            key = (GenericData) duplicateArr[ index ];
            castedData2 = (GenericData) duplicateArr[ index2 ];
           
            
            while ( index2 >= 0 && ( castedData2.compareTo( key ) > 0 ))
               {
                duplicateArr[ index2+1 ] = duplicateArr[ index2 ];
                index2--;
                castedData2 = (GenericData) duplicateArr[ index2 ];
               }
            duplicateArr[index2+1] = key;
          
           }
        
        return toStringHelper(duplicateArr);
       }

    
     /**
     * Calculates the power set of the data in THIS set; stores in member array
     * No action if set is empty
     */
    public void findPowerSet()
       {
        int powerSize = toPower ( BASE_TWO, arraySize );
        genericPwrSetArray = new GenericSetClass[ powerSize ];
        int index;
       
            for( index = 0; index < powerSize; index++ )
               {
                   //getPowerSet(genericPwrSetArray, index, )
               }
           
       }

    /**
     * recursively calculates integer base to power (exponent)
     * @param base - integer base value
     * @param exponent - int exponent value
     * @return integer base to the exponent result
     */
    private int toPower( int base, int exponent )
       {
        if( exponent > 0 )
           {
            return ( base * toPower( base, exponent - 1 ) );
           }
        return 1;
       }
  
   }
