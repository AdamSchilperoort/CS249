
package p6_package;

/**
 *
 * @author adamschilperoort
 */
public class LinkListQueueClass extends java.lang.Object
   {
    
    /**
     * Provides constant -999999 for access failure messaging
     */
    static int FAILED_ACCESS = -999999;
    
    /**
     * Stores queue head reference
     */
    private LinkListQueueClass.NodeClass headRef;
    
    /**
     * Stores queue tail reference
     */
    private LinkListQueueClass.NodeClass tailRef;
    
    /**
     * Default constructor
     */
    LinkListQueueClass()
       {
        headRef = tailRef = null;
       }
    
    /**
     * Copy constructor
     * @param copied - SimpleQueueClass object to be copied
     */
    LinkListQueueClass(LinkListQueueClass copied)
       {
        NodeClass newNode = new NodeClass();
        NodeClass secondNode = new NodeClass();
        
        if (copied.headRef == null )
           {
            headRef = tailRef = null;
           }
        else
           {
            NodeClass copiedWorkingRef = copied.tailRef;
            newNode = new NodeClass( copied.tailRef.nodeData );
            headRef = newNode;
            tailRef = newNode;
            
            while( copiedWorkingRef.nextRef != null )
               { 
                secondNode = new NodeClass( copiedWorkingRef.nextRef.nodeData ); 
                
                newNode.nextRef = secondNode;
                newNode = secondNode;
                copiedWorkingRef = copiedWorkingRef.nextRef;
                
               }
            
            newNode.nextRef = null;
            headRef = newNode;
            
           }
       }
    
    /**
     * Clears the queue by setting the head and tail references to null
     */
    void clear()
       {
        headRef = tailRef = null;
       }
    
    /**
     * Removes and returns value from front of queue
     * @return - Value if successful, FAILED_ACCESS if not
     */
    int dequeue()
       {
        int value;
        
        if ( headRef == null )
           {
            return FAILED_ACCESS; 
           }
        
        value = peekFront();
        
        if( headRef == tailRef )
           {
            headRef = tailRef = null;
            return value;
           }
        
        NodeClass workingRef = tailRef;
        
        while( workingRef.nextRef != headRef )
           {
            workingRef = workingRef.nextRef; 
           }
        
        workingRef.nextRef = null;
        headRef = workingRef;
        return value;
       }
    
    /**
     * Appends value to end of queue
     * @param newValue - Value to be enqueued
     */
    void enqueue(int newValue)
       {
        NodeClass newNode = new NodeClass( newValue );
        
        if( tailRef == null )
           {
            tailRef = headRef = newNode;
           }
        else 
           {
            newNode.nextRef = tailRef;
            tailRef = newNode; 
           }
       }
    
    /**
     * Reports queue empty state
     * @return - Boolean evidence of empty list
     */
    boolean isEmpty()
       {
        return ( headRef == null );
       }
    
    /**
     * Provides peek at front of queue
     * @return - Value if successful, FAILED_ACCESS if not
     */
    int peekFront()
       {
        if( headRef == null )
           {
            return FAILED_ACCESS;
           }
        return headRef.nodeData;
       }
    
    
    private class NodeClass
       {
        int nodeData;
        
        NodeClass nextRef;
        
        private NodeClass()
           {
            nodeData = 0;
            
            nextRef = null;
           }
        
        private NodeClass( int newData )
           {
            nodeData = newData;
            
            nextRef = null;
           }
       }
    
   }
