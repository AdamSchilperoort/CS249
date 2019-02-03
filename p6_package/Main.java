/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p6_package;

/**
 *
 * @author adamschilperoort
 */
public class Main
{
    
     public static void main(String[] args)
        {
           
           
           ////////// Queues below
           
           LinkListQueueClass class3 = new LinkListQueueClass();
           
           class3.enqueue(3);
           class3.enqueue(4);
           class3.enqueue(5);
           class3.enqueue(6);
           
           System.out.println(class3.dequeue());
            System.out.println(class3.dequeue());
           
           System.out.println(class3.peekFront());
           
           LinkListQueueClass class4 = new LinkListQueueClass(class3);
           
           
           System.out.println(class4.dequeue());
           System.out.println(class4.dequeue());
           System.out.println(class4.dequeue());
           System.out.println(class4.dequeue());
       
        }
    
}
