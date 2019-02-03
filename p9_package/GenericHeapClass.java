
package p9_package;

/**
 *
 * @author adamschilperoort
 */
public class GenericHeapClass<GenericData extends Comparable<GenericData>> 
   {
    
    /**
     * Initial array capacity
     */
    public final int DEFAULT_ARRAY_CAPACITY = 10;
    
    /**
     * Management data for array
     */
    private int arraySize;
            
    /**
     * Management data for array
     */
    private int arrayCapacity;   
            
    /**
     * Display flag can be set to observe bubble up and trickle down operations
     */
    private boolean displayFlag;
            
    /**
     * Array for heap
     */ 
    private java.lang.Object[] heapArray;
    
    /**
     * Default constructor sets up array management conditions and default 
     * display flag setting
     */
    public GenericHeapClass()
       {
        displayFlag = false;
        arrayCapacity = DEFAULT_ARRAY_CAPACITY;
        arraySize = 0;
        heapArray = new Object[ arrayCapacity ];
       }
    
    /**
     * Copy constructor copies array and array management conditions and 
     * default display flag setting
     * @param copied- GenericHeapClass object to be copied
     */
    public GenericHeapClass( GenericHeapClass<GenericData> copied )
       {
        int index;   
           
        displayFlag = copied.displayFlag;
        arrayCapacity = copied.arrayCapacity;
        arraySize = copied.arraySize;
        heapArray = new Object[ arrayCapacity ];
        
        for( index = 0; index < arraySize; index++ )
           {
            heapArray[ index ] = copied.heapArray[ index ]; 
           }
       }
    
    /**
     * Accepts GenericData item and adds it to heap
     * Note: uses bubbleUpArrayHeap to resolve unbalanced heap after 
     * data addition
     * @param newItem - GenericData item to be added
     */
    public void addItem( GenericData newItem )
       {
        checkForResize();
        heapArray[ arraySize ] = newItem;
        if( displayFlag )
           {
               System.out.println( "\nAdding new process: " +newItem);
           }
        bubbleUpArrayHeap( arraySize );
        arraySize++;
        System.out.println();
       }
    
    /**
     * Recursive operation to reset data in the correct order for the min heap 
     * after new data addition
     * @param currentIndex - index of current item being assessed, 
     * and moved up as needed
     */
    @SuppressWarnings( "unchecked" )
    private void bubbleUpArrayHeap( int currentIndex )
       {
        int parentIndex = ( currentIndex - 1 ) / 2;
           
        if( currentIndex != 0 )
           {
            
            GenericData castedCurrent = (GenericData) heapArray[ currentIndex ];
            GenericData castedParent = (GenericData) heapArray[ parentIndex ];
            
            if( castedCurrent.compareTo(castedParent) < 0 ) 
               {
                   
                heapArray[ currentIndex ] = castedParent;
                heapArray[ parentIndex ] = castedCurrent;
                
                if( displayFlag )
                   {
                    System.out.println("\t-Bubble Up: " );
                    System.out.println( "\t\t-Swapping parent: " + 
                          castedParent + " with " + "child: " + castedCurrent );
                   }
               
                bubbleUpArrayHeap( parentIndex );
                
               }
            
           }
           
       }
    
    /**
     * Automatic resize operation used prior to any new data addition in the 
     * heap
     * Tests for full heap array, and resizes to twice the current capacity as
     * required
     */
    private void checkForResize()
       {
        int index;
        Object[] tempArray;
       
        if( arraySize == arrayCapacity )
           {
            arrayCapacity *= 2;
           
            tempArray = new Object[ arrayCapacity ];
           
            for( index = 0; index < arraySize; index++ )
               {
                   tempArray[ index] = heapArray[ index ];
               }
           
            heapArray = tempArray;
           }
       }
    
    /**
     * Tests for empty heap
     * @return boolean result of test
     */
    public boolean isEmpty()
       {
        return ( arraySize == 0 );
       }

    /**
     * Removes GenericData item from top of min heap, thus being the operation 
     * with the lowest priority value
     * Note: Uses trickleDownArrayHeap to resolve unbalanced heap after data 
     * removal
     * @return GenericData item removed
     */
    @SuppressWarnings( "unchecked" )
    public GenericData removeItem()
       { 
        if( !isEmpty() )
           {
               
            GenericData valToRemove = ( GenericData ) heapArray[ 0 ];
            heapArray[ 0 ] = heapArray[ arraySize - 1 ];
            arraySize--;
            
            if ( displayFlag )
               {
                System.out.println( "\nRemoving process: " + valToRemove );
               }
            if( !isEmpty() )  //this instead of checking current in trickleDown
               {
                trickleDownArrayHeap( 0 ); 
               }
            return valToRemove;
            
           }
        return null;
       }
    
    /**
     * Utility method to set the display flag for displaying internal operations
     * of the heap bubble and trickle operations
     * @param setState - flag used to set the state to display, or not
     */
    public void setDisplayFlag( boolean setState )
       {
        displayFlag = setState;
       }
    
    /**
     * Recursive operation to reset data in the correct order for the min heap
     * after data removal
     * @param currentIndex - index of current item being assessed, and moved 
     * down as required
     */
    @SuppressWarnings("Unchecked")
    private void trickleDownArrayHeap( int currentIndex )
       {
        int leftChildIndex = ( currentIndex * 2 ) + 1;
        int rightChildIndex = ( currentIndex * 2 ) + 2;
        
        if( leftChildIndex < arraySize )
           {
            GenericData current = (GenericData) heapArray[ currentIndex ]; 
               
            if( rightChildIndex < arraySize )
               {     
                //assume has both children
                   
                GenericData rightChild = (GenericData) 
                                                   heapArray[ rightChildIndex ]; 
                GenericData leftChild = (GenericData)
                                                    heapArray[ leftChildIndex ]; 
                
                if( current.compareTo(rightChild) > 0 || 
                                              current.compareTo(leftChild) > 0 )
                   { 
                    //assume one of the children is less than the currentIndex
                    if( displayFlag )
                       {
                        System.out.println("\t- Trickle down:");
                       }
                    if ( rightChild.compareTo(leftChild) > 0 )
                       {
                        // left is the min, swap left and current, recurse left
                        if( displayFlag )
                           {
                            System.out.println("\t\t- Swapping Parent: " 
                                    +current+ " with left child: " +leftChild );
                           }
                        heapArray[ leftChildIndex ] = current;
                        heapArray[ currentIndex ] = leftChild;
                        trickleDownArrayHeap( leftChildIndex );
                       }
                    else
                       {
                        // right is the min, swap right and current, recurse r
                        if( displayFlag )
                           {
                            System.out.println("\t\t- Swapping Parent: "
                                  +current+ " with right child: " +rightChild );
                           }
                        heapArray[ rightChildIndex ] = current;
                        heapArray[ currentIndex ] = rightChild;
                        trickleDownArrayHeap( rightChildIndex );
                       }
                   }
               }
            else
               {
                //only has left child
                GenericData leftChild = (GenericData) heapArray[ leftChildIndex ]; 
                
                if( current.compareTo( leftChild ) < 0 )
                   {
                    //assume left is less than current
                    if( displayFlag )
                       {
                        System.out.println("\t- Trickle down:");
                        System.out.println("\t\t- Swapping Parent: " +current+
                                              " with left child: " +leftChild );
                       }
                    heapArray[ leftChildIndex ] = current;
                    heapArray[ currentIndex] = leftChild;
                    trickleDownArrayHeap( leftChildIndex );
                   }
               }
           }
       }
    
    /**
     * Dumps array to screen as is, no filtering or management
     */
    public void showArray()
       {
        int index;
        
        for( index = 0; index < arraySize; index++ )
           {
            System.out.print( heapArray[ index ] + " " );
           }
       }
    
    
   }
