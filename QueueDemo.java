public class QueueDemo
{
    public static void main(String[] args)
    {
        CircularArrayQueue q = new CircularArrayQueue();

        //add to q
        q.add("a");
        q.add("b");
        q.add("c");
        q.add("d");
        q.add("e");
        q.add("f");
        q.add("g");
        q.add("h");
        q.add("i");
        System.out.println(q);
        q.firstToLast();
        System.out.println(q);
        q.lastToFirst();
        System.out.println(q);
        q.addFirst("??");
        System.out.println(q);
        q.removeLast();
        System.out.println(q);
        System.out.println("Size: " + q.size());

        while (!q.empty())
        {
            q.remove();
            System.out.println(q);
        }
    }
}
