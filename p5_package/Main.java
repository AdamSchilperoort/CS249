package p5_package;

/**
 *
 * @author adamschilperoort
 */
public class Main 
   {
    
    public static void main(String[] args)
       {
        /*
        SimpleQueueClass queue1 = new SimpleQueueClass(1);
        
        System.out.println( queue1.dequeue() );
        
        queue1.enqueue(6);
        
        System.out.println( queue1.dequeue() );
        
        queue1.enqueue(5);
        queue1.clear();
        queue1.enqueue(4);
        queue1.enqueue(3);
        queue1.enqueue(2);
        queue1.enqueue(1);
        
        
        SimpleQueueClass queue2 = new SimpleQueueClass( queue1 );
        
        System.out.println( queue1.peekFront() );
        
        queue1.dequeue();
        
        System.out.println( queue1.dequeue() );
        System.out.println( queue1.dequeue() );
        
        SimpleQueueClass queue3 = new SimpleQueueClass( queue1 );
        
        System.out.println( queue1.dequeue() );
        System.out.println( queue1.dequeue() );
        System.out.println( queue1.dequeue() );
        System.out.println( queue1.dequeue() );
        
        System.out.println( queue3.dequeue() );
        System.out.println( queue3.dequeue() );
        System.out.println( queue3.dequeue() );
        System.out.println( queue3.dequeue() );
        
        System.out.println( queue2.dequeue() );
        System.out.println( queue2.dequeue() );
        System.out.println( queue2.dequeue() );
        System.out.println( queue2.dequeue() );
        System.out.println( queue2.dequeue() );
        System.out.println( queue2.dequeue() );
        */
           
           
          SimpleStackClass stack1 = new SimpleStackClass(1);
          
          stack1.push(1);
          stack1.push(3);
          stack1.push(5);
          
          SimpleStackClass stack2 = new SimpleStackClass( stack1 );
          
          System.out.println( stack1.peekTop ());
          System.out.println( stack1.pop ());
          System.out.println( stack1.pop ());
          System.out.println( stack1.pop ());
          System.out.println( stack1.pop ());
          
          stack2.clear();
          
          System.out.println( stack2.peekTop());
          System.out.println( stack1.peekTop());
          
          
          stack2.push(5);
          
          System.out.println( stack2.isEmpty());
          
          
          
          
          
        
       }
             
        
        
        
        
   }
    