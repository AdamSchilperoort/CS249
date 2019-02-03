package p7_package;

/**
 *
 * @author adamschilperoort
 */
public class BST_Class extends java.lang.Object
   {
   
    /**
     * Root of BST
     */
    private StudentClassNode BST_Root;
    
   /**
    * Used for acquiring ordered tree visitations in String form
    */
    private java.lang.String outputString;
    
    /**
     * Default class constructor, initializes BST
     */
    BST_Class()    
       {
        BST_Root = null;
       }
    
    /**
     * Copy constructor 
     * Note: Uses copyConstHelper
     * @param copied - BST_Class object to be copied
     */
    BST_Class( BST_Class copied )
       {
        BST_Root = copyConstHelper(copied.BST_Root);
       }
    
    /**
     * Copy constructor helper, recursively adds nodes to duplicate tree
     * @param copiedRef - StudentClassNode reference for accessing copied 
     *                      object data
     * @return StudentClassNode reference to node added at current level 
     *                     of recursion
     */
    private StudentClassNode copyConstHelper( StudentClassNode copiedRef )
       {
        StudentClassNode newNode = null;   
           
        if ( copiedRef != null )
           {
        
            newNode = new StudentClassNode( copiedRef );
                        
            newNode.leftChildRef = copyConstHelper( copiedRef.leftChildRef );
            newNode.rightChildRef = copyConstHelper( copiedRef.rightChildRef );
        
            return newNode;
           }
        
        return newNode; //identical to return null;
       }   
    
    /**
     * Insert method for BST
     * Note: uses insert helper method
     * @param inData - StudentClassNode data to be added to BST
     */
    public void insert( StudentClassNode inData )
       {
        if( isEmpty() )
           {
            BST_Root = inData;
           }
        else
           {
            insertHelper(BST_Root, inData);
           }
        }
    
    /**
     * Insert helper method for BST insert action
     * @param localRoot - StudentClassNode tree root reference at the current
     *                       recursion level
     * @param inData - StudentClassNode item to be added to BST
     */
    private void insertHelper( StudentClassNode localRoot,
                          StudentClassNode inData )
       {
           
        if( inData.studentID < localRoot.studentID )
           {
            if( localRoot.leftChildRef == null )
               {
                localRoot.leftChildRef = inData; 
               }
            else
               {
                insertHelper( localRoot.leftChildRef, inData ); 
               }
           }
        if( inData.studentID > localRoot.studentID )
           {
            if( localRoot.rightChildRef == null )
               {
                localRoot.rightChildRef = inData;
               }
            else
               {
                insertHelper( localRoot.rightChildRef, inData );  
               }
           }
        
       }
    
    /**
     * Removes data node from tree using given key
     * Note: uses remove helper method
     * @param inData - StudentClassNode that includes the necessary key
     * @return StudentClassNode result of remove action
     */
    public StudentClassNode removeNode( StudentClassNode inData )
       {
        if ( isEmpty() )
           {
            return null;
           }
        
        return removeNodeHelper( BST_Root, inData );
       }
    
    /**
     * Remove helper for BST remove action
     * Note: uses removeFromMin method
     * @param localRoot - StudentClassNode tree root reference at the current 
     *                       recursion level
     * @param outData - StudentClassNode item that includes the necessary key
     * @return StudentClassNode reference result of remove helper action
     */
    private StudentClassNode removeNodeHelper( StudentClassNode localRoot,
                                            StudentClassNode outData )
       {
           
        if( localRoot == null )
           {
            return localRoot;
           }
        if( outData.studentID < localRoot.studentID )
           {
            localRoot.leftChildRef = removeNodeHelper( localRoot.leftChildRef, 
                                                          outData );
           }
        else if( outData.studentID > localRoot.studentID )
           {
            localRoot.rightChildRef = removeNodeHelper( localRoot.rightChildRef,
                                                           outData );
           }
        else 
           {
            if( localRoot.leftChildRef == null && localRoot.rightChildRef == null )
               {
                return null; 
               }
            else if( localRoot.leftChildRef == null && 
                                               localRoot.rightChildRef != null )
               {
                return localRoot.rightChildRef; 
               }
            else if( localRoot.leftChildRef != null && 
                                               localRoot.rightChildRef == null )
               {
                return localRoot.leftChildRef; 
               }
            else 
               {
                return removeFromMin( localRoot, localRoot.rightChildRef );
               }
           }
        
        return localRoot;
        
       }
    
    /**
     * Searches tree from given node to minimum value node below it, 
     *    stores data value found, and then unlinks the node
     * @param minParent - StudentClassNode reference to current node
     * @param minChild - StudentClassNode reference to child node to be tested
     * @return StudentClassNode reference containing removed node
     */
    private StudentClassNode removeFromMin( StudentClassNode minParent,
                                       StudentClassNode minChild )
       {
        if( minChild.leftChildRef != null )
           {
            return removeFromMin( minParent, minChild.leftChildRef );
           }
        
        minParent.setStudentClassData ( minChild );
        minParent.rightChildRef = removeNodeHelper( minParent.rightChildRef, minChild );
        return minChild;
       }
    
    /**
     * Searches for data in BST given StudentClassNode with necessary key
     * @param searchData - StudentClassNode item containing key
     * @return StudentClassNode reference to found data
     */
    public StudentClassNode search( StudentClassNode searchData )
       {
        return searchHelper( BST_Root, searchData );
       }
    
    /**
     * Helper method for BST search action
     * @param localRoot - StudentClassNode tree root reference at the current 
     *                    recursion level
     * @param searchData - StudentClassNode item containing key
     * @return StudentClassNode item found
     */
    private StudentClassNode searchHelper( StudentClassNode localRoot,
                                      StudentClassNode searchData )
       {
        if( localRoot != null )
           {
            if( searchData.studentID < localRoot.studentID )
               {
                return searchHelper( localRoot.leftChildRef, searchData ); 
               }
            if( searchData.studentID > localRoot.studentID )
               {
                return searchHelper( localRoot.rightChildRef, searchData ); 
               }
           }
        
        return localRoot; //if not found or node we reached is null, returns null
       }
    
    /**
     * Provides preOrder traversal for user as a string
     * @return String containing pre order output
     */
    public java.lang.String outputPreOrder()
       {
        outputString = "";
        if( BST_Root == null )
           {
            return ""; 
           }
        outputPreOrderHelper( BST_Root );
        return outputString;
       }
    
    /**
     * Provides preOrder traversal action helper
     * @param localRoot - StudentClassNode tree root reference at the 
     *                    current recursion level
     */
    private void outputPreOrderHelper( StudentClassNode localRoot )
       {
        outputString += localRoot.toString() + "\n";
        if ( localRoot.leftChildRef != null )
           {
            outputPreOrderHelper( localRoot.leftChildRef );
           }
        if ( localRoot.rightChildRef != null )
           {
            outputPreOrderHelper( localRoot.rightChildRef );
           }
       }
    
    /**
     * Provides postOrder traversal for use as a string
     * @return String containing post order output
     */
    public java.lang.String outputPostOrder()
       {
        outputString = "";
        if( BST_Root == null )
           {
            return ""; 
           }
        outputPostOrderHelper( BST_Root );
        return outputString;
       }
    
    /**
     * Provides postOrder traversal action helper
     * @param localRoot - StudentClassNode tree root reference at the 
     *                    current recursion level
     */
    private void outputPostOrderHelper(StudentClassNode localRoot)
       {
        if ( localRoot.leftChildRef != null )
           {
            outputPostOrderHelper( localRoot.leftChildRef );
           }
        if ( localRoot.rightChildRef != null )
           {
            outputPostOrderHelper ( localRoot.rightChildRef );
           }
        outputString += localRoot.toString() + "\n";
       }
    
    /**
     * Provides inOrder traversal for user as a string
     * @return String containing in order output
     */
    public java.lang.String outputInOrder()
       {
        outputString = "";
        if( BST_Root == null )
           {
            return ""; 
           }
        outputInOrderHelper( BST_Root );
        return outputString;
       }
    
    /**
     * Provides inOrder traversal action helper
     * @param localRoot - StudentClassNode tree root reference at the current recursion level
     */
    public void outputInOrderHelper(StudentClassNode localRoot)
       {
        if ( localRoot.leftChildRef != null )
           {
            outputInOrderHelper( localRoot.leftChildRef );
           }
        
        outputString += localRoot.toString() + "\n";
        
        if ( localRoot.rightChildRef != null )
           {
            outputInOrderHelper( localRoot.rightChildRef );
           }
       }
    
    /**
     * Clears tree
     */
    public void clearTree()
       {
        BST_Root = null;
       }
    
    /**
     * Test for empty tree
     * @return Boolean result of test
     */
    public boolean isEmpty()
       {
        return BST_Root == null;
       }
   }