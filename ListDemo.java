import java.util.ListIterator;
/**
   A program that demonstrates the LinkedList class
*/
public class ListDemo
{
   public static void main(String[] args)
   {
      //create LinkedList and add to it
      LinkedList<String> staff = new LinkedList<String>();
      staff.addFirst("z");
      staff.addFirst("a");

      // | in the comments indicates the iterator position
      ListIterator<String> itr = staff.listIterator();
      itr.next();

      // Add more elements after second element
      itr.add("b");
      itr.add("c");


      // Remove last traversed element



      // Print all elements
      staff.reverse();
      
      itr = staff.listIterator();
      while (itr.hasNext())
      {
         System.out.print(itr.next() + " ");
      }
      System.out.println();
   }
}
