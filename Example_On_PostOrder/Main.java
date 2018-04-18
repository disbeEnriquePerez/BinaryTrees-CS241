package Example_On_PostOrder;
import TreePackage.BinaryTree;
import java.util.Iterator;

public class Main
{
    public static void main(String[] args)
    {
        BinaryTree<String> emptyTree = new BinaryTree<>();
        BinaryTree<String> gTree = new BinaryTree<>("g");
        BinaryTree<String> dTree = new BinaryTree<>("d");
        BinaryTree<String> eTree = new BinaryTree<>("e");
        BinaryTree<String> fTree = new BinaryTree<>("f", emptyTree, gTree);
        BinaryTree<String> bTree = new BinaryTree<>("b", dTree, eTree);
        BinaryTree<String> cTree = new BinaryTree<>("c", fTree, emptyTree);
        BinaryTree<String> aTree = new BinaryTree<>("a", bTree, cTree);

        Iterator<String> iterator = aTree.getInorderIterator();
        while(iterator.hasNext())
        {
            System.out.print(iterator.next() + " ");
        }
    }
}
