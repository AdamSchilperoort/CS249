
package p6_package;

/**
 *
 * @author adamschilperoort
 */
public class LinkListIteratorClass extends java.lang.Object
   {
     
    /**
     * Provides constant -999999 for access failure messaging
     */
    static int FAILED_ACCESS = -999999;
    
    /**
     * reference to current/cursor node
     */
    private LinkListIteratorClass.NodeClass cursorRef;
        
    /**
     * reference to head node
     */
    private LinkListIteratorClass.NodeClass headRef;
    
    /**
     * Default Constructor
     */
    public LinkListIteratorClass()
       {
        cursorRef = headRef = null;
       }
    
    /**
     * Copy Constructor
     * @param copied - LinkListIteratorCLass Object to be copied
     */
    public LinkListIteratorClass(LinkListIteratorClass copied)
       {
        NodeClass newNode = new NodeClass();
        NodeClass secondNode = new NodeClass();
        
        if (copied.headRef == null )
           {
            headRef = cursorRef = null;
           }
        else
           {
            NodeClass copiedWorkingRef = copied.headRef;
            newNode = new NodeClass( copied.headRef.nodeData );
            headRef = newNode;
            if( copied.cursorRef == copied.headRef )
               {
                cursorRef = newNode;
               }
            
            while( copiedWorkingRef.nextRef != null )
               { 
                secondNode = new NodeClass( copiedWorkingRef.nextRef.nodeData ); 
                
                if( copied.cursorRef == copiedWorkingRef.nextRef )
                   {
                    cursorRef = secondNode;
                   }   
                
                newNode.nextRef = secondNode;
                newNode = secondNode;
                copiedWorkingRef = copiedWorkingRef.nextRef;
                
               }
            
            newNode.nextRef = null;
            
           }
        
       }
    
    /**
     * Clears List
     */
    public void clear()
       {
        cursorRef = headRef = null;
       }
    
    /**
     * Displays linked list for diagnostic purposes
     */
    public void displayList()
       {
       NodeClass workingRef = headRef;
       
        while( workingRef != null )
           {
            if ( workingRef == cursorRef )
               {
                System.out.print("[" + workingRef.nodeData + "] "); 
               }
            else
               {
                System.out.print(workingRef.nodeData + " ");
               }
            workingRef = workingRef.nextRef;
           }
        System.out.println();
           
       }
    
    /**
     * Acquires data at cursor 
     * @return integer value at cursor location if available, else FAILED_ACCESS 
     */
    public int getDataAtCursor()
       {
        if( isEmpty() )
           {
            return FAILED_ACCESS; 
           }
        
        return cursorRef.nodeData;
       }
    
    /**
     * Recursive method finds a reference to the node just prior to the cursor; 
     * initially called with head reference
     * @param workingRef - current NodeClass reference in the list
     * @return NodeClass reference to the item just prior to the cursor location
     */
    private LinkListIteratorClass.NodeClass getRefBeforeCursor(
                                    LinkListIteratorClass.NodeClass workingRef)
       {
        if( cursorRef == null || workingRef == null || workingRef == cursorRef )
           {
            return null;
           }
        if( workingRef.nextRef == cursorRef )
           {
            return workingRef; 
           }
        else
           {
            return getRefBeforeCursor( workingRef.nextRef );
           }
       }
    
    
    /**
     * Inserts value after cursor
     * @param newValue - Value to be inserted in list
     */
    public void insertAfterCursor(int newValue)
       {
           
        NodeClass newNode = new NodeClass( newValue );
        
        if ( isEmpty() )
           {
            headRef = newNode;
            cursorRef = newNode;
           }
        else
           {
            newNode.nextRef = cursorRef.nextRef;
            cursorRef.nextRef = newNode;
           }
      
       }

    /**
     * Inserts value prior to cursor
     * @param newValue - Value to be inserted in list
     */
    public void insertBeforeCursor(int newValue)
       {
        NodeClass newNode = new NodeClass( newValue );
        
        if( headRef == cursorRef )
           {
            headRef = newNode; 
            newNode.nextRef = cursorRef;
           }
        else
           {
            NodeClass refBef = getRefBeforeCursor( headRef );
            refBef.nextRef = newNode;
            newNode.nextRef = cursorRef;
           }
       }
    
    /**
     * Checks for at last item in list
     * @return Boolean result of test
     */
    public boolean isAtEndOfList()
       {
        return ( cursorRef.nextRef == null );
       }
    
    /**
     * Reports list empty status
     * @return Boolean evidence of empty list
     */
    public boolean isEmpty()
       {
        return headRef == null;
       }
    
    
    /**
     * Moves cursor to next node if it is not at end
     */
    public void moveNext()
       {
        if ( !isAtEndOfList() )
           {
             cursorRef = cursorRef.nextRef;
           }
       }
    
    /**
     * Moves cursor to previous node if it is not already at head
     */
    public void movePrevious()
       {
        if ( cursorRef != headRef )
           { 
            cursorRef = getRefBeforeCursor( headRef );
           }
       }
    
    /**
     * Removes item at current location/cursor if available
     * Sets cursor to previous node unless cursor is at head
     * @return integer value removed if available, FAILED_ACCESS otherwise
     */
    public int removeDataAtCursor()
       {
        int value;
        
        if( isEmpty() )
           {
            return FAILED_ACCESS;
           }
        if( cursorRef == headRef )
           {
            value = headRef.nodeData;
            headRef = cursorRef = null;
            return value;
           }
        
        value = cursorRef.nodeData;
        NodeClass refBef = getRefBeforeCursor( headRef );
        refBef.nextRef = cursorRef.nextRef;
        
        return value;
       }

    /**
     * Sets cursor to first item in list
     */
    public void setToFirstItem()
       {
        cursorRef = headRef;
       }
    
    /**
     * Sets cursor to last item in list
     */
    public void setToLastItem()
       {
        while ( !isAtEndOfList() )
           {
            moveNext();
           }
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
