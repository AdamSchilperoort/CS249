
package p2_package;

/**
 *
 * @author adamschilperoort
 */
public class IntDataClass implements Comparable<IntDataClass>{
   
    private int	dataVal;
    
    /*
     * Default Constructor
     */
    IntDataClass()
    {
        dataVal = 0;
    }
    
    /**
     * Initialization constructor
     * @param initialValue - integer value to be loaded into object
     */
    IntDataClass(int initialValue)
    {
        dataVal = initialValue;
    }
    
    /**
     * Copy Constructor
     * @param copied - IntDataClass object to be copied into THIS object
     */
    IntDataClass(IntDataClass copied)
    {
        dataVal = copied.dataVal;
    }
    
    /**
     * Setter method to update data in object
     * @param newValue newValue - integer value to be set in object
     */
    public void setValue(int newValue)
    {
        dataVal = newValue;
    }
    
    /**
     * Getter method to retrieve data from object
     * @return integer value retrieved from object
     */
    public int getValue()
    {
        return dataVal;
    }

    /**
     * Overrides toString method
     * Overrides toString in class java.lang.Object
     * @return String output of integer data value
     */
    @Override
    public java.lang.String toString()
    {
        return "" + dataVal;
    }
    
    /**
     * Method required of class extending Comparable; used by generic classes for management
     * compareTo in interface java.lang.Comparable<IntDataClass>
     * @param other DataClass given to method
     * @return 1 if greater than, 0 if equal, -1 if less than  
     */
    public int compareTo(IntDataClass other)
    {
       return (dataVal - other.dataVal);
    }
}


