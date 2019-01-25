/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p1_package;

import static p1_package.SetClass.EVEN;
import static p1_package.SetClass.ODD;
import static p1_package.SetClass.INCREMENTED;
import static p1_package.SetClass.PRIME;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ME
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        
        SetClass mySet1 = new SetClass();
        mySet1.addItem(5);
        mySet1.addItem(7);
        mySet1.addItem(11);
        mySet1.addItem(13);
        mySet1.addItem(17);
        mySet1.addItem(19);
        mySet1.addItem(23);
        mySet1.addItem(29);
        mySet1.addItem(31);
        mySet1.addItem(37);
        mySet1.addItem(37);
        mySet1.removeValue(37);
        System.out.println("Set 1: " +mySet1);
        System.out.println("size: " +mySet1.arraySize);
        System.out.println("capacity: " +mySet1.arrayCapacity);
        
        SetClass mySet2 = new SetClass();
        mySet2.addItem(5);
        mySet2.addItem(7);
        mySet2.addItem(9);
        mySet2.addItem(11);
        mySet2.addItem(13);
        mySet2.addItem(15);
        mySet2.addItem(17);
        mySet2.addItem(19);
        mySet2.addItem(21);
        mySet2.addItem(23);
        mySet2.addItem(25);
        mySet2.addItem(27);
        mySet2.addItem(29);
        mySet2.addItem(31);
        mySet2.addItem(33);
   
        //mySet2.loadItems(3, 7, ODD, 2);
        mySet2.removeValue(3);
        System.out.println("Set 2: " +mySet2);
        System.out.println("size: " +mySet2.arraySize);
        System.out.println("capacity: " +mySet2.arrayCapacity);
        
        
        System.out.println("Is 1 subset of 2? " +mySet1.isSubsetOf(mySet2));
        
        SetClass mySet3 = new SetClass();
        mySet3.addItem(10);
        mySet3.addItem(20);
        mySet3.addItem(30);
        mySet3.removeValue(30);
        mySet3.addItem(30);
        System.out.println("Set 3: " +mySet3);
        System.out.println("size: " +mySet3.arraySize);
        System.out.println("capacity: " +mySet3.arrayCapacity);
        
        System.out.println("Union Sets 1/2: " + mySet1.findUnion(mySet2));
        //union is changing one of the sets
        
        SetClass comp2 = mySet1.findIntersection(mySet2);
        System.out.println("Intersection 1/2: " +comp2.toString());
        
        SetClass comp = mySet1.findRelativeComplementOfThisSetIn(mySet2);
        System.out.println("Relative Complement 1/2: " +comp);
        
        //mySet3.findPowerSet();
        //System.out.println("Power Set of 3: " +mySet3.powerSetToString());
       // System.out.println("2 ^ 3: " + mySet3.toPower(2,2));
     
        
       
      /*
        mySet3.findPowerSet();
        System.out.println(mySet3.powerSetToString());
      */  
       
    }
    
}
