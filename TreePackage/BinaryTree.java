package TreePackage;

import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import StackPackage.*;
import QueuePackage.*;

public class BinaryTree<T> implements BinaryTreeInterface<T>
{
    protected BinaryNode<T> root;

    public BinaryTree()
    {
        root = null;
    }

    public BinaryTree(T rootData)
    {
        root = new BinaryNode<>(rootData);
    }

    public BinaryTree(T rootData, BinaryTree<T> leftTree, BinaryTree<T> rightTree)
    {
        initializeTree(rootData, leftTree, rightTree);
    }

    @Override
    public T getRootData()
    {
        if(!isEmpty())
        {
            return root.getData();
        }
        return null;
    }

    @Override
    public int getHeight()
    {
        return root.getHeight();
    }

    @Override
    public int getNumberOfNodes()
    {
        return root.getNumberOfNodes();
    }

    @Override
    public Iterator<T> getPreorderIterator()
    {
        return new PreOrderIterator();
    }

    @Override
    public Iterator<T> getPostorderIterator()
    {
        return new PostorderIterator();
    }

    @Override
    public void clear()
    {
        root = null;
    }

    @Override
    public Iterator<T> getInorderIterator()
    {
        return new InOrderIterator();
    }

    @Override
    public Iterator<T> getLevelOrderIterator()
    {
        return new LevelOrderIterator();
    }

    @Override
    public void setTree(T rootData)
    {
        setTree(rootData, null, null);
    }

    @Override
    public void setTree(T rootData, BinaryTreeInterface<T> leftTree, BinaryTreeInterface<T> rightTree)
    {
        initializeTree(rootData, (BinaryTree<T>) leftTree, (BinaryTree<T>) rightTree);
    }

    private void initializeTree(T rootData, BinaryTree<T> leftTree, BinaryTree<T> rightTree)
    {
        root = new BinaryNode<>(rootData);

        if((leftTree != null) && !leftTree.isEmpty())
            root.setLeftChild(leftTree.root);
        if((rightTree != null) && !rightTree.isEmpty())
        {
            if (rightTree != leftTree)
            {
                root.setRightChild(rightTree.root);
            }
            else
                root.setRightChild(rightTree.root.copy());
        }
        if((leftTree != null) && (leftTree != this))
        {
            leftTree.clear();
        }
        if((rightTree != null) && (rightTree != this))
        {
            rightTree.clear();
        }
    }

    @Override
    public boolean isEmpty()
    {
        return root == null;
    }

    // traversal that doesn't use an iterator (for demonstration purposes only)
    public void iterativeInorderTraverse()
    {
        StackInterface<BinaryNode<T>> nodeStack = new ArrayStack<>();
        BinaryNode<T> currentNode = root;

        while (!nodeStack.isEmpty() || (currentNode != null))
        {
            while (currentNode != null)
            {
                nodeStack.push(currentNode);
                currentNode = currentNode.getLeftChild();
            }

            if (!nodeStack.isEmpty())
            {
                BinaryNode<T> nextNode = nodeStack.pop();

                System.out.println(nextNode.getData());
                currentNode = nextNode.getRightChild();
            }
        }
    }
    private class LevelOrderIterator implements Iterator<T>
    {
    	private LinkedQueue<BinaryNode<T>> nodeStack;
    	private BinaryNode<T> currentNode;
    	
    	public LevelOrderIterator()
    	{
    		nodeStack = new LinkedQueue();
    		nodeStack.enqueue(root);
    	}
		@Override
		public boolean hasNext() {
			return !nodeStack.isEmpty();
		}

		@Override
		public T next() {
			BinaryNode<T> Node = nodeStack.dequeue();
			
			if(Node.hasLeftChild()) {
				nodeStack.enqueue(Node.getLeftChild());
			}
			if(Node.hasRightChild()) {
				nodeStack.enqueue(Node.getRightChild());
			}
			
			return Node.getData();
		}
    	
    }
    private class PreOrderIterator implements Iterator<T>
    {
    	private StackInterface<BinaryNode<T>> nodeStack;
    	private BinaryNode<T> currentNode;
    	
    	public PreOrderIterator() {
    	
    		nodeStack = new ArrayStack<>();
    		nodeStack.push(root);
    	}
		@Override
		public boolean hasNext() {
			
			return (!nodeStack.isEmpty());
		}

		@Override
		public T next() {
			currentNode = nodeStack.pop();
				if(currentNode.hasRightChild()) {
					nodeStack.push(currentNode.getRightChild());
				}
				if(currentNode.hasLeftChild()) {
					nodeStack.push(currentNode.getLeftChild());
				}
				
			return currentNode.getData();
		}
 	
    }
    
    private class PostorderIterator implements Iterator<T>
    {	
    	private StackInterface<BinaryNode<T>> nodeStack;
    	private BinaryNode<T> currentNode;
   
    	public PostorderIterator()
    	{
    		nodeStack = new ArrayStack<>();
    		currentNode = root;
    		nodeStack.push(currentNode);
    	}
		@Override
		public boolean hasNext() {
			
			return !nodeStack.isEmpty() || (currentNode != null);
		}

		@Override
		public T next() {
			BinaryNode<T> nextNode = null;
			while(!currentNode.isLeaf()) {
				if(currentNode.hasLeftChild()) {
					currentNode = currentNode.getLeftChild();
				}else {
					currentNode = currentNode.getRightChild();
				}	
				nodeStack.push(currentNode);
			}
			
			if(!nodeStack.isEmpty()) {
				
				nextNode = nodeStack.pop();	
				
				if(!nodeStack.isEmpty()){
					if(nodeStack.peek().getLeftChild() == nextNode && nodeStack.peek().hasRightChild()) {
						currentNode = nodeStack.peek().getRightChild();
						nodeStack.push(currentNode);
					}
				}else {
					currentNode = null;
				}
			}
			
		
			
			return nextNode.getData();
		}
    	
    }
    private class InOrderIterator implements Iterator<T>
    {
        private StackInterface<BinaryNode<T>> nodeStack;
        private BinaryNode<T> currentNode;

        public InOrderIterator()
        {
            nodeStack = new ArrayStack<>();
            currentNode = root;
        }

        @Override
        public boolean hasNext()
        {
            return !nodeStack.isEmpty() || (currentNode != null);
        }

        @Override
        public T next()
        {
            BinaryNode<T> nextNode = null;

            // find leftmost node with no left child
            while(currentNode != null)
            {
                nodeStack.push(currentNode);
                currentNode = currentNode.getLeftChild();
            }

            if(!nodeStack.isEmpty())
            {
                nextNode = nodeStack.pop();
                currentNode = nextNode.getRightChild();
            }
            else
                throw new NoSuchElementException();
            return nextNode.getData();
        }
    }
}