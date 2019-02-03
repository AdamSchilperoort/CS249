/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p8_package;

/**
 *
 * @author adamschilperoort
 */
public class TwoThreeTreeClassMain {
    
    public static void main(String[] args)
       {
        TwoThreeTreeClass class1 = new TwoThreeTreeClass();
        System.out.println(class1.inOrderTraversal());
        class1.addItem(5);
        System.out.println(class1.inOrderTraversal());
        class1.addItem(7);
        System.out.println(class1.inOrderTraversal());
        class1.addItem(2);
        System.out.println(class1.inOrderTraversal());
        class1.addItem(1);
        System.out.println(class1.inOrderTraversal());
        class1.addItem(9);
        System.out.println(class1.inOrderTraversal());
        class1.addItem(-1);
        System.out.println(class1.inOrderTraversal());
        class1.addItem(4);
        class1.addItem(-10);
        
        TwoThreeTreeClass class2 = new TwoThreeTreeClass( class1 );
       // class2.clear(); //having some issue with my copy constructor
        //class2.addItem(3);
        class1.addItem(3);
       
        System.out.println(class2.inOrderTraversal());
        
       // System.out.println("Is 7 in class 2? " +class2.search(7));
        
        System.out.println("Is 7 in class 1? " +class1.search(7));
        
        class1.clear();
        
        System.out.println("Is 7 in class 1? " +class1.search(7));
       }
    
}
