package TreePackage;
public class BinarySearchTree<T extends Comparable<? super T>>
        extends BinaryTree<T>
        implements SearchTreeInterface<T>
{
    public BinarySearchTree()
    {
        super();
    }

    public BinarySearchTree(T rootEntry)
    {
        super();
        setRootNode(new BinaryNode<>(rootEntry));
    }

    @Override
    public void setTree(T rootData, BinaryTreeInterface<T> leftTree, BinaryTreeInterface<T> rightTree)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public T getEntry(T anEntry)
    {
        return findEntry(getRootNode(), anEntry);
    }

    private T findEntry(BinaryNode<T> rootNode, T anEntry)
    {
        T result = null;
        if(rootNode != null)
        {
            T rootEntry = rootNode.getData();
            if(anEntry.equals(rootEntry))
            {
                result = rootEntry;
            }
            else if(anEntry.compareTo(rootEntry) < 0)
            {
                result = findEntry(rootNode.getLeftChild(), anEntry);
            }
            else
            {
                result = findEntry(rootNode.getRightChild(), anEntry);
            }
        }
        return result;
    }

    @Override
    public T add(T anEntry)
    {
        T result = null;
        if(isEmpty())
            setRootNode(new BinaryNode<>(anEntry));
        else
            result = addIterative(getRootNode(), anEntry);
        return result;
    }

    private T addIterative(BinaryNode<T> rootNode, T anEntry)
    {
        // TODO
        return null;
    }

    @Override
    public T remove(T anEntry)
    {
        ReturnObject oldEntry = new ReturnObject(null);
        BinaryNode<T> newRoot = removeEntry(getRootNode(), anEntry, oldEntry);
        setRootNode(newRoot);
        return oldEntry.getData();
    }
    private BinaryNode<T> getRootNode(){
    	return root;
    }
    private void setRootNode(BinaryNode<T> node) {
    	
    	root = node;
    }

    private BinaryNode<T> removeEntry(BinaryNode<T> rootNode, T anEntry, ReturnObject oldEntry)
    {
        if (rootNode != null)
        {
            T rootData = rootNode.getData();
            int comparison = anEntry.compareTo(rootData);

            if (comparison == 0) // anEntry is in the root entry of this subtree
            {
                oldEntry.setData(rootData);
                rootNode = removeFromRoot(rootNode);
            }

            else if (comparison < 0) // anEntry < root entry
            {
                BinaryNode<T> leftChild = rootNode.getLeftChild();
                BinaryNode<T> subTreeRoot = removeEntry(leftChild, anEntry, oldEntry);
                rootNode.setLeftChild(subTreeRoot);
            }

            else
            {
                BinaryNode<T> rightChild = rootNode.getRightChild();
                BinaryNode<T> subTreeRoot = removeEntry(rightChild, anEntry, oldEntry);
                rootNode.setRightChild(subTreeRoot);
            }
        }
        return rootNode;
    }

    private BinaryNode<T> removeFromRoot(BinaryNode<T> rootNode)
    {
        // Case 1: rootNode has two children
        if (rootNode.hasLeftChild() && rootNode.hasRightChild())
        {
            // find node with largest entry in left subtree
            BinaryNode<T> leftSubtreeRoot = rootNode.getLeftChild();
            BinaryNode<T> largestNode = findLargest(leftSubtreeRoot);

            // replace entry in root with largest from left subtree
            rootNode.setData(largestNode.getData());

            // remove node with largest entry in left subtree
            rootNode.setLeftChild(removeLargest(leftSubtreeRoot));
        }

        // Case 2: rootNode has at most one child
        else if (rootNode.hasRightChild())
            rootNode = rootNode.getRightChild();
        else
            rootNode = rootNode.getLeftChild();
        return rootNode;
    }

    // Finds the node containing the largest entry in a given tree
    // rootNode is the root node of the tree
    // Returns the node containing the largest entry in the tree.
    private BinaryNode<T> findLargest(BinaryNode<T> rootNode)
    {
        if (rootNode.hasRightChild())
            rootNode = findLargest(rootNode.getRightChild());
        return rootNode;
    }

    // Removes the node containing the largest entry in a given tree
    // rootNode is the root node of the tree
    // Returns the root node of the revised tree.
    private BinaryNode<T> removeLargest(BinaryNode<T> rootNode)
    {
        if (rootNode.hasRightChild())
        {
            BinaryNode<T> rightChild = rootNode.getRightChild();
            rightChild = removeLargest(rightChild);
            rootNode.setRightChild(rightChild);
        }
        else
            rootNode = rootNode.getLeftChild();
        return rootNode;
    }

    public T getMax()
    {
        return null;
    }

    public T getMin()
    {
        return null;
    }

    private class ReturnObject
    {
        private T data;
        public ReturnObject(T newData)
        {
            data = newData;
        }

        public void setData(T newData)
        {
            data = newData;
        }

        public T getData() { return data; }
    }

    private T addEntry(BinaryNode<T> rootNode, T anEntry)
    {
        T result = null;
        int comparison = anEntry.compareTo(rootNode.getData());

        if(comparison == 0)
        {
            result = rootNode.getData();
            rootNode.setData(anEntry);
        }
        else if(comparison < 0)
        {
            if(rootNode.hasLeftChild())
                result = addEntry(rootNode.getLeftChild(), anEntry);
            else
                rootNode.setLeftChild(new BinaryNode<>(anEntry));
        }
        else
        {
            if(rootNode.hasRightChild())
                result = addEntry(rootNode.getRightChild(), anEntry);
            else
                rootNode.setRightChild(new BinaryNode<>(anEntry));
        }
        return result;
    }

    @Override
    public boolean contains(T anEntry)
    {
        return getEntry(anEntry) != null;
    }
}
