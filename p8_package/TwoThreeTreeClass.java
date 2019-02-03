
package p8_package;

/**
 *
 * @author adamschilperoort
 */
public class TwoThreeTreeClass extends java.lang.Object 
   {
    
    private class TwoThreeNodeClass
       {
        /**
         * internal 2-3 node data
         */
        private int leftData, centerData, rightData, numItems;
        
        /**
         * references from 2-3 node
         */
        private TwoThreeNodeClass leftChildRef, centerChildRef, rightChildRef;
        
        /**
         * references for managing 2-3 node adjustments
         */
        private TwoThreeNodeClass auxLeftRef, auxRightRef;
        
        /**
         * parent reference for 2-3 node
         */
        private TwoThreeNodeClass parentRef;
        
        /**
         * Default 2-3 node class constructor
         */
        private TwoThreeNodeClass()
           {
            leftData = centerData = rightData = numItems = 0;
            
            leftChildRef = centerChildRef = rightChildRef = null;
            
            auxLeftRef = auxRightRef = parentRef = null;
           }
        
        /**
         * Initialization 2-3 node class constructor
         * 
         * @param centerIn integer data sets first node initialization
         */
        private TwoThreeNodeClass( int centerIn )
           {
            centerData = centerIn;

            leftData = rightData = 0;
            
            numItems = 1;
            
            leftChildRef = centerChildRef = rightChildRef = null;
            
            auxLeftRef = auxRightRef = parentRef = null;
           }

        /**
         * Private constructor used to create new left or right child node
         * of given parent with the children linked from
         * a current three-node object
         *
         * @param childSelect integer selection value that informs 
         * the constructor to create a left or a right child
         * along with making all the sub-child links; 
         * uses class constants LEFT_CHILD_SELECT and RIGHT_CHILD_SELECT
         * 
         * @param localRef TwoThreeNodeClass reference
         * with three-node data and associated references
         * 
         * @param parRef TwoThreeNodeclass parent reference
         * for linking with new left or right child node that is created
         */
        private TwoThreeNodeClass( int childSelect, 
                       TwoThreeNodeClass localRef, TwoThreeNodeClass parRef )
           {
            if( childSelect == LEFT_CHILD_SELECT )
               {
                this.centerData = localRef.leftData;
                this.leftChildRef = localRef.leftChildRef;
                this.rightChildRef = localRef.auxLeftRef;
                
                if( leftChildRef != null )
                   {
                    this.leftChildRef.parentRef = this;
                    this.rightChildRef.parentRef = this;
                   }
               }
            
            else  // assume right child select
               {
                this.centerData = localRef.rightData;
                this.leftChildRef = localRef.auxRightRef;
                this.rightChildRef = localRef.rightChildRef;

                if( rightChildRef != null )
                   {
                    this.leftChildRef.parentRef = this;
                    this.rightChildRef.parentRef = this;
                   }
               }

            this.leftData = this.rightData = 0;
            this.numItems = 1;
            this.centerChildRef = null;
            this.auxLeftRef = this.auxRightRef = null;
            this.parentRef = parRef;
           }
        
        /**
         * Copy 2-3 node class constructor
         * <p>
         * Note: Only copies data; does not copy links 
         * as these would be incorrect for the new tree 
         * (i.e., they would be related to the copied tree)
         * 
         * @param copied TwoThreeNodeClass object to be copied
         */
        private TwoThreeNodeClass( TwoThreeNodeClass copied )
           {
            leftData = copied.leftData;
            centerData = copied.centerData;
            rightData = copied.rightData;
            
            numItems = copied.numItems;

            leftChildRef = centerChildRef = rightChildRef = null;
            
            auxLeftRef = auxRightRef = parentRef = null;
           }
       }
    
    
    /**
     * constant used for identifying one data item stored
     */
    private final int ONE_DATA_ITEM = 1;
    
    /**
     * constant used for identifying two data items stored
     */
    private final int TWO_DATA_ITEMS = 2;
    
    /**
     * constant used for identifying three data items stored
     */
    private int THREE_DATA_ITEMS = 3;
    
    /**
     * constant used in constructor to indicate which child to create - Left
     * Note: set as package private
     */
    private int LEFT_CHILD_SELECT = 101;
    
    /**
     * constant used in constructor to indicate which child to create - Right
     * Note: set as package private
     */
    private int RIGHT_CHILD_SELECT = 102;
    
    /**
     * Used for acquiring ordered tree visitations in String form
     */
    private java.lang.String outputString;
    
    /**
     * root of tree
     */
    private TwoThreeNodeClass root;
    
    /**
     * Default 2-3 tree constructor
     */
    TwoThreeTreeClass()
       {
        root = null;
       }
    
    /**
     * Copy 2-3 tree constructor
     * @param copied - TwoThreeTreeClass object to be duplicated; 
     * data is copied, but new nodes and references must be created
     */
    TwoThreeTreeClass( TwoThreeTreeClass copied )
       {
        root = copyConstructorHelper( copied.root );
       }
    
    /**
     * Implements tree duplication effort with recursive method; copies data 
     * into newly created nodes and creates all new references to child nodes
     * @param workingCopiedRefTwoThreeNodeClass reference that is updated to 
     * lower level children with each recursive call
     * @return TwoThreeNodeClass reference to next higher level node; last 
     * return is to root node of THIS object
     */
    private TwoThreeTreeClass.TwoThreeNodeClass copyConstructorHelper( 
                                           TwoThreeNodeClass workingCopiedRef )
       {
        TwoThreeNodeClass newNode = null;
        
        if ( workingCopiedRef != null )
           {
        
            newNode = new TwoThreeNodeClass( workingCopiedRef );
             
            newNode.leftChildRef = copyConstructorHelper( 
                                               workingCopiedRef.leftChildRef );
            if( newNode.leftChildRef != null )
               {
                newNode.leftChildRef.parentRef = workingCopiedRef;
               }
            newNode.centerChildRef = copyConstructorHelper( 
                                             workingCopiedRef.centerChildRef );
            if( newNode.centerChildRef != null )
               {
                newNode.centerChildRef.parentRef = workingCopiedRef;
               }
            newNode.rightChildRef = copyConstructorHelper( 
                                              workingCopiedRef.rightChildRef );
            if( newNode.rightChildRef != null )
               {
                newNode.rightChildRef.parentRef = workingCopiedRef;
               }
        
            return newNode;
           }
        
        return newNode;
       }
    
    /**
     * Method clears tree so that new items can be added again
     */
    public void clear()
       {
        root = null;
       }
    
    /**
     * Adds item to 2-3 tree using addItemHelper as needed
     * @param itemVal - integer value to be added to the tree
     */
    public void addItem( int itemVal )
       {
        if( root == null )
           {
            TwoThreeNodeClass newNode =  new TwoThreeNodeClass( itemVal );
            root = newNode;
           }
        else
           {
            addItemHelper( null, root, itemVal ); 
           }
       }
    
    /**
     * Helper method searches from top of tree to bottom using divide and 
     * conquer strategy to find correct location (node) for new added value; 
     * once location is found, item is added to node using addAndOrganizeData 
     * and then fixUpInsert is called in case the updated node has become
     * a three-value node
     * @param parRef - TwoThreeNodeClass reference to the parent of the current 
     * reference at a given point in the recursion process
     * @param localRef - TwoThreeNodeClass reference to the current item at 
     * the same given point in the recursion process
     * @param itemVal - integer value to be added to the tree
     */
    private void addItemHelper( TwoThreeNodeClass parRef,   
                                    TwoThreeNodeClass localRef, int itemVal )
       {
        if ( localRef.numItems == ONE_DATA_ITEM  && 
                                                localRef.leftChildRef != null ) 
           {
               if( itemVal < localRef.centerData )
                  {
                   addItemHelper( localRef, localRef.leftChildRef, itemVal  ); 
                  }
               else 
                  { 
                   addItemHelper( localRef, localRef.rightChildRef, itemVal );
                  }
           }
        else if ( localRef.numItems == TWO_DATA_ITEMS && 
                                                localRef.centerChildRef != null )
           {
               if( itemVal < localRef.leftData )
                  {
                   addItemHelper( localRef, localRef.leftChildRef, itemVal );
                  }
               else if ( itemVal > localRef.rightData )
                  {
                   addItemHelper( localRef, localRef.rightChildRef, itemVal ); 
                  }
               else   //assume itemVal is between leftData and rightData
                  {
                   addItemHelper( localRef, localRef.centerChildRef, itemVal );
                  }
           }
        else  //assume this is a leaf
           { 
            addAndOrganizeData( localRef, itemVal );
            fixUpInsert( localRef );
           }
           
       }
    
    /**
     * Method is called when addItemHelper arrives at the bottom of the 2-3 
     * search tree (i.e., all node's children are null);
     * Assumes one- or two- value nodes and adds one more to the appropriate 
     * one resulting in a two- or three- value node
     * @param localRef TwoThreeNodeClass reference has original node data and 
     * contains added value when method completes; method does not manage any 
     * node links
     * @param itemVal - integer value to be added to 2-3 node
     */
    private void addAndOrganizeData( TwoThreeNodeClass localRef, int itemVal )
       {
        if( localRef.numItems == ONE_DATA_ITEM )
           {
            if( itemVal < localRef.centerData )
               {
                 localRef.rightData = localRef.centerData;
                 localRef.centerData = 0;
                 localRef.leftData = itemVal;
               }
            else //then assume itemVal is greater than center
               {
                localRef.leftData = localRef.centerData;
                localRef.centerData = 0;
                localRef.rightData = itemVal;
               }
            
            localRef.numItems = TWO_DATA_ITEMS;
            
           }
        
        else  //else assume is a 2 node
           {
            if( itemVal < localRef.leftData )
               {
                localRef.centerData = localRef.leftData;
                localRef.leftData = itemVal;
               }
            else if( itemVal > localRef.rightData )
               {
                localRef.centerData = localRef.rightData;
                localRef.rightData = itemVal;
               }
            else //vals will be in the center, or equal to left or right 
               {
                localRef.centerData = itemVal;
               }
            
            localRef.numItems = THREE_DATA_ITEMS;
            
           }
        
       }
    
    /**
     * Method used to fix tree any time a three-value node has been added to 
     * the bottom of the tree; it is always called but decides to act only
     * if it finds a three-value node
     * 
     * Resolves current three-value node which may add a value to the node 
     * above; if the node above becomes a three-value node, then this is 
     * resolved with the next recursive call
     * 
     * Recursively climbs from bottom to top of tree resolving any 
     * three-value nodes found
     * 
     * @param localRef - TwoThreeNodeClas reference initially given the
     * currently updated node, then is given parent node recursively each time
     * a three-value node was resolved
     */
    private void fixUpInsert(TwoThreeNodeClass localRef)
       {
        TwoThreeNodeClass tempRef = localRef.parentRef;
        if( localRef.numItems == THREE_DATA_ITEMS )
           {
            if( tempRef == null )
               {
                tempRef =  new TwoThreeNodeClass( localRef.centerData ); 
                root = tempRef;
                tempRef.leftChildRef = new TwoThreeNodeClass( 
                                  LEFT_CHILD_SELECT, localRef, tempRef );
                tempRef.rightChildRef = new TwoThreeNodeClass ( 
                                RIGHT_CHILD_SELECT, localRef, tempRef );
               }
            else if( tempRef.numItems == ONE_DATA_ITEM )
               {
                tempRef.numItems = TWO_DATA_ITEMS;
                   
                if( tempRef.leftChildRef == localRef )
                   {
                    tempRef.rightData = localRef.parentRef.centerData;
                    tempRef.centerData = 0;
                    tempRef.leftData = localRef.centerData;
                    tempRef.leftChildRef = new TwoThreeNodeClass(
                             LEFT_CHILD_SELECT, localRef, tempRef);
                    tempRef.centerChildRef = new TwoThreeNodeClass(
                              RIGHT_CHILD_SELECT, localRef, tempRef);  
                   }
                else //assume this is the right child of parent
                   {
                    tempRef.leftData = localRef.parentRef.centerData;
                    tempRef.centerData = 0;
                    tempRef.rightData = localRef.centerData;
                    tempRef.centerChildRef = new TwoThreeNodeClass( 
                              LEFT_CHILD_SELECT, localRef, tempRef); 
                    tempRef.rightChildRef = new TwoThreeNodeClass( 
                             RIGHT_CHILD_SELECT, localRef, tempRef);
                   }
               }
            else  //assume is two-item parent 
               { 
                tempRef.numItems = THREE_DATA_ITEMS;
                
                if( tempRef.leftChildRef == localRef )
                   {
                    tempRef.centerData = tempRef.leftData;
                    tempRef.leftData = localRef.centerData;
                    tempRef.leftChildRef = new TwoThreeNodeClass(
                              LEFT_CHILD_SELECT, localRef, tempRef );
                    tempRef.auxLeftRef = new TwoThreeNodeClass(
                             RIGHT_CHILD_SELECT, localRef, tempRef );
                    tempRef.auxRightRef = localRef.centerChildRef;
                    tempRef.centerChildRef = null;
                   }
                else //Assume right child 
                   {
                    tempRef.centerData = localRef.parentRef.rightData;
                    tempRef.rightData = localRef.centerData;
                    tempRef.rightChildRef = new TwoThreeNodeClass(
                             RIGHT_CHILD_SELECT, localRef, tempRef );
                    tempRef.auxRightRef = new TwoThreeNodeClass(
                             LEFT_CHILD_SELECT, localRef, tempRef );
                    tempRef.auxLeftRef = localRef.centerChildRef;
                    tempRef.centerChildRef = null;
                   }
               
               }
            
            fixUpInsert(tempRef); //gives error
           }
       }
    
    /**
     * Public method called by user to display data in order
     * @return String result to be displayed and/or analyzed
     */
    public java.lang.String inOrderTraversal()
       {
        outputString = "";
        inOrderTraversalHelper( root );
        return outputString;
       }
    
    /**
     * Helper method conducts in order traversal with 2-3 tree
     * Shows location of each value in a node: "C" at center of single node 
     * "L" or "R" at left or right of two-value node
     * @param localRef - TwoThreeNodeClass reference to current location at any
     * given point in the recursion process
     */
    private void inOrderTraversalHelper(TwoThreeNodeClass localRef)
       {
        if (localRef != null )
           {
               
            inOrderTraversalHelper( localRef.leftChildRef );
            
            if( localRef.numItems == ONE_DATA_ITEM )
               {
                outputString += "C" + localRef.centerData + " ";
               }
            else //assume 2 or 3 items 
               {
                outputString += "L" +localRef.leftData + " ";
            
                if ( localRef.centerChildRef != null )
                   {
                    inOrderTraversalHelper( localRef.centerChildRef );
                   }
            
                outputString += "R" + localRef.rightData + " ";
               }
            
            inOrderTraversalHelper ( localRef.rightChildRef );
            
           }
        
       }
    
    /**
     * Search method used by programmer to find specified item in 2-3 tree
     * @param searchVal - integer value to be found
     * @return boolean result of search effort
     */
    public boolean search(int searchVal)
       {
        return searchHelper( root, searchVal ); 
       }
    
    /**
     * Search helper method that traverses through tree in a recursive divide 
     * and conquer search fashion to find given integer in 2-3 tree
     * @param localRef - TwoThreeNodeClass reference to given node at any 
     * point during the recursive process
     * @param searchVal - integer value to be found in tree
     * @return boolean result of search effort
     */
    private boolean searchHelper( TwoThreeNodeClass localRef, int searchVal )
       {
        if( localRef == null )
           {
            return false;
           }
        if( foundInNode( localRef, searchVal ))
           {
            return true;
           }
        if( localRef.numItems == 1 )
           {
            if ( searchVal < localRef.centerData )
               {
                return searchHelper( localRef.leftChildRef, searchVal );
               }
            else // assume searchVal > localRef.centerData
               {
                return searchHelper( localRef.rightChildRef, searchVal );
               }
           }
        else //assume 2 items
           {  
            if ( searchVal < localRef.leftData )
               {
                return searchHelper( localRef.leftChildRef, searchVal ); 
               }
            else if ( searchVal > localRef.leftData  && searchVal <
                                                            localRef.rightData )
               {
                return searchHelper( localRef.centerChildRef, searchVal ); 
               }
            else //assume searchVal is greater than localRef.rightData
               {
                return searchHelper( localRef.rightChildRef, searchVal ); 
               }
           }
        
      }
    
    /**
     * Tests center value if single node, tests left and right values if 
     * two-value node; returns true if specified data is found in any given node
     * Note: Method does not use any branching operations such as if/else/etc.
     * @param localRef - TwoThreeNodeClass reference to node with one or 
     * two data items in it
     * @param searchVal - integer value to be found in given node
     * @return boolean result of test
     */
    private boolean foundInNode( TwoThreeNodeClass localRef, int searchVal )
       {
        return( localRef.leftData == searchVal || 
         localRef.centerData == searchVal || localRef.rightData == searchVal );
       }
    
   }
