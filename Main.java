import TreePackage.BinarySearchTree;
import TreePackage.SearchTreeInterface;

import java.util.Iterator;

public class Main
{
    public static void main(String[] args)
    {
        SearchTreeInterface<Integer> tree = new BinarySearchTree<>();
        tree.add(10);
        tree.add(12);
        tree.add(15);
        tree.add(8);
        tree.add(6);
        tree.add(4);
        tree.add(14);
        tree.add(16);
        Iterator<Integer> iterator = tree.getInorderIterator();
        while(iterator.hasNext())
        {
            System.out.print(iterator.next() + " ");
        }

        System.out.println();
        System.out.println(tree.getMax());
        System.out.println();
        System.out.println(tree.getMin());
    }
}
