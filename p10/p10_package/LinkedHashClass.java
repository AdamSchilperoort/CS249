package p10_package;

/**
 *
 * @author adamschilperoort
 */
public class LinkedHashClass
   {
    
    /**
     * Table size default
     * Table size (capacity) is recommended to be a prime number
     */
    private final int DEFAULT_TABLE_SIZE = 11;
    
    /**
     * Size of the base table
     */
    private int tableSize;
    
    /**
     * Constant used to control access operation
     */
    private boolean REMOVE = true;
    
    /**
     * Constant used to control access operation
     */
    private boolean SEARCH = false;
    
    /**
     * Array for hash table
     */
    private LinkedNodeClass[] tableArray;
    

    private class LinkedNodeClass
       {
        private SimpleStudentClass data;
        private LinkedNodeClass nextRef;

        private LinkedNodeClass( SimpleStudentClass inData )
           {
            data = inData;
            nextRef = null;
           }
       }

    /**
     * Default constructor
     */
    public LinkedHashClass()
       {
        tableSize = DEFAULT_TABLE_SIZE;
        tableArray = new LinkedNodeClass[ tableSize ];
       }

    /**
     * Initialization constructor
     * @param inTableSize
     */
    public LinkedHashClass( int inTableSize )
       {
        tableSize = inTableSize;
        tableArray = new LinkedNodeClass[ tableSize ];
       }

    /**
     * Copy constructor
     * @param copied LinkedHashClass object to be copied
     */
    public LinkedHashClass( LinkedHashClass copied )
       {
        tableSize = copied.tableSize;
        tableArray = new LinkedNodeClass[ tableSize ];

        int index;
        for ( index = 0; index < tableSize; index++ )
           {
            if ( copied.tableArray[ index ] != null )
               {
                   
                tableArray[ index ] = new LinkedNodeClass( 
                    new SimpleStudentClass( copied.tableArray[ index ].data ) );

                LinkedNodeClass workingRef = copied.tableArray[ index ];
                LinkedNodeClass temp = tableArray[ index ];

                while ( workingRef.nextRef != null )
                   {
                     
                    temp.nextRef = new LinkedNodeClass(
                            new SimpleStudentClass( workingRef.nextRef.data ) );
                    temp = temp.nextRef;
                    
                    workingRef = workingRef.nextRef;
                   }
               }
           }
       }

    /**
     * Helper method that handles both searching and removing items in linked
     * list at specified index
     * @param linkIndex  - integer index specifying location in array
     * @param studentID  - integer key for searching and/or removing node
     * @param removeFlag - boolean flag that indicates whether to search or
     *                     remove (use SEARCH, REMOVE constants to call this
     *                     method)
     * @return SimpleStudentClass data found and/or removed
     */
    private SimpleStudentClass accessLinkedData( int linkIndex, int studentID,
                                                    boolean removeFlag )
       {
        if ( tableArray[ linkIndex ] != null )
           {
            LinkedNodeClass workingRef = tableArray[ linkIndex ];
            LinkedNodeClass workingPrevious = null;
            while ( workingRef != null )
               {
                if ( workingRef.data.studentID == studentID )
                   {
                    if ( removeFlag == REMOVE )
                       {
                        if ( workingPrevious == null )
                           {
                            tableArray[ linkIndex ] = workingRef.nextRef;
                           }
                        else
                           {
                            workingPrevious.nextRef = workingRef.nextRef;
                           }
                       }
                    return workingRef.data;
                   }
                workingPrevious = workingRef;
                workingRef = workingRef.nextRef;
               }
           }
        return null;
       }

    /**
     * Adds item to hash table
     * Uses overloaded addItem with object
     * @param inName - name of student
     * @param inStudentID - student ID
     * @param inGender - gender of student
     * @param inGPA - gpa of student
     * @return Boolean success of operation
     */
    public boolean addItem( String inName, int inStudentID, char inGender,
                           double inGPA )
       {
        return addItem( new SimpleStudentClass( inName, inStudentID, inGender,
                          inGPA ) );
       }
    
    /**
     * Adds item to hash table
     * Overloaded method that accepts SimpleStudentClass object
     * @param newItem - student class object
     * @return boolean success of operation
     */
    public boolean addItem( SimpleStudentClass newItem )
       {
        if ( newItem != null )
           {
            int hashCode = generateHash( newItem );
            appendToList( hashCode, newItem );
            return true;
           }
        return false;
       }

    /**
     * Appends new data to end of list at given list index; handles empty node
     * at that index as needed
     * @param listIndex - specified integer index of array
     * @param newNode - SimpleStudentClass node to be appended to array/list
     */
    private void appendToList( int listIndex, SimpleStudentClass newNode )
       { 
        if ( tableArray[ listIndex ] == null )
           {
            tableArray[ listIndex ] = new LinkedNodeClass( newNode );
           }
        else
           {
            LinkedNodeClass workingRef = tableArray[ listIndex ];
            while ( workingRef.nextRef != null )
               {
                workingRef = workingRef.nextRef;
               }
            workingRef.nextRef = new LinkedNodeClass( newNode );
           }
       }

    /**
     * Clears hash table by clearing all linked list elements
     */
    public void clearHashTable()
       {
        int index;
        for( index = 0; index < tableSize; index++ )
           {
            tableArray[ index ] = null; 
           }
       }

    /**
     * Method recursively counts number of nodes in a given linked list
     * @param workingRef - LinkedNodeClass reference used for recursion;
     *                     initially set to head
     * @return integer number of nodes found
     */
    private int countNodes( LinkedNodeClass workingRef )
       {
        if ( workingRef != null )
           {
            return 1 + countNodes( workingRef.nextRef );
           }
        return 0;
       }


    /**
     * Searches for item in hash table using student ID as key
     * @param studentID - used for requesting data
     * @return SimpleStudentClass object removed, or null if not found
     */
    public SimpleStudentClass findItem( int studentID )
       {
        int hash = generateHash( new SimpleStudentClass( "", studentID,
                                                                 ' ', 0.00 ) );
        return accessLinkedData( hash, studentID, SEARCH );
       }

    /**
     * Method uses student ID to generate hash value for use as index in hash 
     * table
     * Process sums the Unicode values of the student ID numbers converted to
     * characters
     * @param studentData - SimpleStudentClass object from which hash value
     *                      will be generated
     * @return integer hash value to be used as array index
     */
    public int generateHash( SimpleStudentClass studentData )
       {
        int studentID = studentData.studentID;
        int sum = 0;
        
        while( studentID > 0 )
           {
            sum += (int) ( '0'+ ( studentID % 10 ) );
            studentID /= 10;
           }

        return sum % tableSize;
       }


    /**
     * Removes item from hash table, using student ID as key
     * @param studentID - used for requesting data
     * @return SimpleStudentClass object removed, or null if not found
     */
    public SimpleStudentClass removeItem( int studentID )
       {
        int hash = generateHash(
                new SimpleStudentClass( "", studentID, ' ', 0.00 ) );
        return accessLinkedData( hash, studentID, REMOVE );
       }


    /**
     * traverses through all array bins, finds lengths of each linked list, then
     * displays as table
     * Shows table of list lengths, then shows table size, then shows the number
     * of empty bins and the longest linked list of the set; adapts to any size
     * array
     */
    public void showHashTableStatus()
       { 
        int longestNode = 0;
        int nodeCount;
        int emptyBins = 0;
        int index;
        
        System.out.println( "Array node report: " );
        String firstRow = " Index:\t\t  ";
        String secondRow = "\t\t";
        String thirdRow = "\t\t  ";
        
        for ( index = 0; index < tableSize; index++ )
           {
            nodeCount = countNodes( tableArray[ index ] );
            firstRow += index + "\t ";
            secondRow += "----\t";
            thirdRow += nodeCount + "\t ";
            if ( nodeCount == 0 )
               {
                emptyBins++;
               }
            else if ( nodeCount > longestNode )
               {
                longestNode = nodeCount;
               }
           }
        System.out.println( firstRow );
        System.out.println( secondRow );
        System.out.println( thirdRow );
        System.out.println( "With a table size of " + tableSize );
        System.out.println( "The number of empty bins was " + emptyBins +
                            ", and the longest linked node list was " 
                            + longestNode + " nodes" );

       }

   }