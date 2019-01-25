/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p1_package;
/**
 *
 * @author adamschilperoort
 */


/**
 * Homework 1, CS 249
 * @author Adam Schilperoort
 */

public class SetClass
   {

    private static final int BASE_TWO = 2;
    public static final int DEFAULT_ARRAY_CAPACITY = 10;
    public static final int EVEN = 103;
    public static final int INCREMENTED = 101;
    public static final int ODD = 102;
    public static final int PRIME = 104;
    int arrayCapacity;
    int arraySize;
    SetClass[] powerSetArray;
    int[] setArray;

    /*
     * Default Constructor
     * Initializes set array but sets power set array to null
     */
    SetClass()
       {
        arrayCapacity = DEFAULT_ARRAY_CAPACITY;
        arraySize = 0;
        setArray = new int[ arrayCapacity ];
        powerSetArray = null;
       }

    /*
     * Initialization Constructor
     * Allows specification of set array capacity
     * Initializes set array but sets power set array to null
     * @param initialCapacity - integer that specifies array capacity
     */
    SetClass( int initialCapacity )
       {
        arrayCapacity = initialCapacity;
        arraySize = 0;
        setArray = new int[ arrayCapacity ];
        powerSetArray = null;
       }

    /*
     * Copy Constructor
     * Duplicates copied set class
     * Also responsible for correct initialization/update of set class array
     * @param copied - SetClass object to be copied
     */
    SetClass( SetClass copied )
       {
        arrayCapacity = copied.arrayCapacity;
        arraySize = copied.arraySize;
        powerSetArray = copied.powerSetArray;
        int index;

        for( index = 0; index < arraySize; index++ )
           {
            addItem( copied.setArray[ index ] );
           }

       /* if ( copied.powerSetArray != null )
           {
            for( element = 0; element < copied.powerSetArray.length; element++ )
            powerSetArray = copied.powerSetArray[element];
           }
          */
       }

    /*
     * addItem
     * adds integer to set
     * increases capacity using checkForResize if array is full
     * does not allow duplicate values in set
     * @param item - integer value to be added to set
     */
    public void addItem( int item )
       {
        if ( !hasElement( item ) )
           {
            checkForResize();
            setArray[ arraySize ] = item;
            arraySize++;
           }
       }

    /*
     * Loads a number of specified integers to set
     * Characteristics may be odd, even, incremented, or prime
     * Parameter four is only used with INCREMENTED
     * @param startValue - integer value indicates starting value
     * @param numItems - integer number of items to load
     * @param valueCharacteristic - integer characteristic code( ODD, EVEN,
     *                                                     INCREMENTED, PRIME )
     * @param incrementBy - integer value used to specify increment if
     * INCREMENTED characteristic is set
     */
    public void loadItems( int startValue, int numItems,
                           int valueCharacteristic, int incrementBy)
       {
        int value;
        int endsize = numItems + arraySize;
        for ( value = startValue; arraySize < endsize; value++)
           {
            switch ( valueCharacteristic )  
               {
                case ODD:
                    if ( !isEven( value ) )
                       {
                        addItem(value);
                       }
                    break;
                    
                case EVEN:
                    if ( isEven( value ) )
                       {
                        addItem(value);
                       }
                    break;
                    
                case INCREMENTED:
                    if ( incrementBy == 0 )
                       {
                          addItem( startValue );
                          endsize = arraySize;
                       }
                    else
                       {
                        addItem(value);
                        value += incrementBy -1;
                       }
                    break;
                    
                case PRIME:
                    if ( isPrime( value ) )
                       {
                        addItem(value);
                       }
                    break;
               }
           }
       }

    /*
     * Removes value if it is found in set
     * All values equal to given value will be removed in case there is
     * more than one
     * @param valToRemove - integer value to be removed
     * @return boolean result of operation success
     */
    public boolean removeValue( int valToRemove )
       {
        int index;
        for( index = 0; index < arraySize; index++ )
           {
            if (valToRemove == setArray[index])
               {
                removeAtIndex(index);
                return true;
               }
           }
        return false;
       }

    /*
     * Removes value at given index; moves all succeeding data down in array
     * @param indexToRemove - integer index of element value to remove
     */
    private void removeAtIndex( int indexToRemove )
       {
        int index;
        arraySize--;
        for( index = indexToRemove; index < arraySize; index++ )
           {
            setArray[index] = setArray[index+1];
           }
       }

    /*
     * Returns the intersection of THIS set and the given other set
     * @param other - SetClass data with which intersection is found
     * @return SetClass object with intersection of two sets
     */
    public SetClass findIntersection( SetClass other )
       {
        int index;
       
        SetClass intersection = new SetClass(); 
        
        for ( index = 0; index < arraySize; index++ ) 
           {
            if ( other.hasElement( setArray[ index ] ) )
                    {
                        intersection.addItem( setArray[ index ] );
                    }
           } 
        return intersection;
       }

    /*
     * Returns the union of THIS set and the given other set
     * @param other - SetClass data with which union is found
     * @return SetClass object with union of two sets
     */
    public SetClass findUnion( SetClass other )
       {
        int index;
        SetClass duplicate = new SetClass(); //has error when other
        
        for ( index = 0; index < arraySize; index++ ) 
           {
                duplicate.addItem( setArray[ index ] );
           } 
        for ( index = 0; index < other.arraySize; index++ )
           {
                duplicate.addItem( other.setArray[ index ] );
           }
        return duplicate;
       }

    /*
     * Finds relative complement of THIS set in given other set
     * Returns other set having removed any items intersection with THIS set
     * @param other - SetClass object from which THIS SetClass item will
     * be removed
     * @return SetClass object with data as specified
     */
    public SetClass findRelativeComplementOfThisSetIn( SetClass other )
       {
        
        int index;
        SetClass duplicate = new SetClass();
        
        for ( index = 0; index < other.arraySize; index++ ) 
           {
            if ( !hasElement( other.setArray[ index ] ) )
                    {
                        duplicate.addItem( other.setArray[ index ] );
                    }
           } 
        return duplicate;
       }
    
    /*
     * Tests to indicate if set is subclass another given set
     * @param other - SetClass object to be tested if THIS set is a subset
     * of it
     * @return boolean result of test
     */
    public boolean isSubsetOf( SetClass other )
       {
        int index;
        for( index = 0; index < arraySize; index++ )
           {
            if ( !other.hasElement( setArray[ index ] ) )
               {
                  return false;  
               }
           }
        return true;
       }

    /*
     * Tests to indicate if integer value is one of the set elements
     * @param testElement - integer element to be found in set
     * @return boolean result of test
     */
    public boolean hasElement( int testElement )
       {
        int index;
        for ( index = 0; index < arraySize; index++ )
           {
            if ( setArray[ index ] == testElement )
               {
                return true; 
               }
           }
        return false;
       }

    /*
     * Seeks and finds prime starting at given value
     * @param value - integer value to start search for prime
     * @return next prime number
     */
    private int getNextPrime( int value )
       {
        boolean isFound = false;
        int index = value +1;
        if ( !isPrime (index) )
                {
                    getNextPrime ( index );
                }
        
        return index;
        
       }

    /*
     * Tests to indicate if given integer value is prime
     * @param testVal - integer value given
     * @return boolean result of test
     */
    private boolean isPrime( int testVal )
       {
        int index;
        if ( testVal == 2 )
           {
            return true; 
           }
        if ( testVal < 2 )
           {
            return false;
           }
        for ( index = 2; index*index < testVal; index++ )
           {
            if ( testVal % index == 0)
               {
                return false;
               }
           }
        return true;
       }
    
    /*
     * Tests for even, reports
     * @param value - integer value to be tested
     * @return boolean result as specified
     */
    private boolean isEven( int value )
       {
        return ( value % 2 ) == 0;
       }
    
    /*
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
            int[] tempArray = new int[ arrayCapacity ];
            for ( index = 0; index < arraySize; index++ )
               {
                tempArray[ index ] = setArray[ index ];
               }
            setArray = tempArray;
            return true;
           }
        return false;
       }

    /*
     * Provides list of set array elements as comma-delimited string
     * @Overrides toString in class java.lang.Object
     */
    @Override
    public java.lang.String toString()
       {
        String result = "";
        int index;
        for( index = 0; index < arraySize; index++ )
           {
             result += setArray[ index ];
             if ( index != arraySize - 1 )
                {
                 result += ", "; 
                }
           }
        return result;
       }

    /*
     * Provides list of power set elements
     * Set elements are comma delimited; Sets are semicolon delimited
     * Uses dash ('-') to indicate null set
     * @return String holding power set from member power set array
     */
    public java.lang.String powerSetToString()
       {
        
       }
    
    /*
     * Calculates the power set of the data in THIS set; stores in member array
     * No action if set is empty
     */
    public void findPowerSet()
       {
        powerSetArray = new SetClass[ toPower ( BASE_TWO, arraySize ) ];
        int index;
       }

    /*
     * Finds the nth set of data for a given power set from THIS set
     * @param set - SetClass object that has power set Nth item result
     * @param currentIndex - integer index used in recursion to get data
     * from THIS data set array
     * @param currentValue - integer value indicating the Nth set
     */
    private void getPowerSet(SetClass set, int currentIndex, int currentValue)
       {

       }

    /*
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
