package p5_package;

/**
 *
 * @author adamschilperoort
 */
public class SimpleQueueClass 
   {
   
    /**
     * Provides constant for default capacity
     */
    private final int DEFAULT_CAPACITY = 10;

    /**
     * Provides constant -999999 for access failure messaging
     */
    public static final int FAILED_ACCESS = -999999;
    
    /**
     * Stores current capacity of queue class
     */
    private int capacity;
    
    /**
     * Stores current size of queue class
     */
    private int size;
    
    /**
     * Stores queue head index
     */
    private int headIndex;
    
    /**
     * Stores queue tail index
     */
    private int tailIndex;
    
    /**
     * Integer array stores queue data
     */
    private int[] queueData;
    
    /**
     * Default constructor
     */
    public SimpleQueueClass()
       {
        capacity = DEFAULT_CAPACITY;
        size = 0;
        headIndex = 0;
        tailIndex = -1;
        queueData = new int[ capacity ];
       }
    
    /**
     * Initialization constructor
     * @param capacitySetting - initial capacity of queueData class
     */
    public SimpleQueueClass(int capacitySetting)
       {
        capacity = capacitySetting;
        size = 0;
        headIndex = 0;
        tailIndex = -1;
        queueData = new int[ capacity ];
       }
    
    /**
     * Copy constructor
     * <p>
     * Note: queue is copied so that head index is at zero and tail index 
     * is at size - 1; i.e., this resets the array to start at zero
     * @param copied - SimpleQueueClass object to be copied
     */
    public SimpleQueueClass(SimpleQueueClass copied)
       {
        capacity = copied.capacity;
        size = copied.size;
        headIndex = 0;
        tailIndex = size - 1;
        queueData = new int[ capacity ];
        
        int index;
        int copiedHeadIndex =  copied.headIndex;
        
        for( index = 0; index < size; index++ )
           {
            queueData[ index ] = copied.queueData[ copiedHeadIndex ];
          
            copiedHeadIndex++;
           
            if( copiedHeadIndex == size )
               {
                copiedHeadIndex = 0; 
               }
            
           }
        
       }
    
    /**
     * Checks for resize, then enqueues value
     * <p>
     * Note: Updates tail index, then appends value to array at tail index
     * @param newValue - Value to be enqueued
     */
    public void enqueue(int newValue)
       {
        checkForReSize();
        updateTailIndex();
        queueData[ tailIndex ] = newValue;
        size++;
       }
   
    /**
     * Removes and returns value from front of queue
     * <p>
     * Note: Acquires data from head of queue, then updates head index
     * @return Value if successful, FAILED_ACCESS if not
     */
    public int dequeue()
       { 
        int tempVal;
        
        if( size > 0 )
           {
            tempVal = ( peekFront() );
            updateHeadIndex(); 
            
            size--;
            
            return tempVal;
           }
        
        return FAILED_ACCESS;
       }
    
    /**
     * Provides peek at front of queue
     * @return - Value if successful, FAILED_ACCESS if not
     */
    public int peekFront()
       {
        if( size > 0 )
           {
            return queueData[ headIndex ];
           }
        
        return FAILED_ACCESS; 
       }
    
    /**
     * Reports queue empty state
     * Note: Does not use if/else
     * @return - Boolean evidence of empty list
     */
    public boolean isEmpty()
       {
        return ( size == 0 );
       }
    
    /**
     * Checks for resize and resizes to twice the current capacity if needed
     * <p>
     * Note: Returns true if resize is necessary and is conducted; 
     * returns false if no action is taken
     * @return success of operation
     */
    private boolean checkForReSize()
       {
        int index;
        
      	if ( size == capacity )
           {
            capacity *= 2;
            SimpleQueueClass rearrangedDuplicate = new SimpleQueueClass( this );
            int[] tempArr = new int[ capacity ];
          
            for ( index = 0; index < size; index ++ )
               {
              	tempArr[ index ] = ( rearrangedDuplicate.queueData[ index ] );
               }
          
            queueData = tempArr;
            tailIndex = rearrangedDuplicate.tailIndex;
            headIndex = rearrangedDuplicate.headIndex;
            
            return true;
           }
        
        return false; 
       }
    
    /**
     * Clears the queue by setting the size to zero,
     * the tail index to -1 and the head index to zero
     */
    public void clear()
       {
         size = 0;
         headIndex = 0;
         tailIndex = -1;
       }
    
    /**
     * Updates queue head index to wrap around array as needed
     * Note: Does not use if/else
     */
    private void updateHeadIndex()
       {
        headIndex = ( headIndex + 1 ) % capacity;
       }
    
    /**
     * Updates queue tail index to wrap around array as needed
     * Note: Does not use if/else
     */
    private void updateTailIndex()
       {
        tailIndex = ( tailIndex + 1 ) % capacity;
       }
  
    
   }