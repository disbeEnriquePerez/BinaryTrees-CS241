package TreePackage;

import java.io.PrintWriter;
import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.*;

import StackPackage.*;
import QueuePackage.*;
import java.io.*;

public class BinaryTree<T> implements BinaryTreeInterface<T>
{
	
	PrintWriter file;
    private final String FILE = "ANIMALS.txt"; 
	
	protected BinaryNode<T> root;
	protected boolean Loading = false;
	protected BinaryNode<T> currentNode = null;

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
	public BinaryNode<T> getCurrentNode() 
	{
		return currentNode;
	}
    
	@Override
	public void setCurrentNode(BinaryNode<T> currentNode) 
	{
		this.currentNode = currentNode;
		
	}

	@Override
	public void CreateNode() 
	{
		Loading = true;
		
	}
	@Override
	public void CloseFile()
	{
		file.close();
	}

	@Override
	public void CreateNodes(BinaryNode<T> ParentNode) 
	{
		BinaryNode<T> LeftChild = new BinaryNode<T>();
		BinaryNode<T> RightChild = new BinaryNode<T>();
		ParentNode.setLeftChild(LeftChild);
		ParentNode.setRightChild(RightChild);
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
    
    private class LevelOrderIterator implements Iterator<T>
    {
    	private LinkedQueue<BinaryNode<T>> nodeStack;
    	private BinaryNode<T> currentNode; 
    	Scanner AnimalsReader;
    	
    	public LevelOrderIterator()
    	{
    		try 
    		{
    			if(!Loading) {
    				file = new PrintWriter(FILE);
    				file.println(root.getData()); 
    			
    			}
   			}catch(Exception ex) {
   				System.out.println("ERROR:");
   			}
    		nodeStack = new LinkedQueue();
    		nodeStack.enqueue(root);
    		Loading = false;
    	}
		
    	@Override
		public boolean hasNext() {
			return !nodeStack.isEmpty();
		}
		
		
		@Override
		public T next() {
			
			BinaryNode<T> Node = nodeStack.dequeue();
			
		
		if(!Loading) 
		{
			if(Node.hasLeftChild() && Node.getLeftChild() != null) {
				nodeStack.enqueue(Node.getLeftChild());
				file.println(Node.getLeftChild().getData());
			}else{
				file.println("null");
			}
			
			if(Node.hasRightChild() && Node.getLeftChild() != null) {
				nodeStack.enqueue(Node.getRightChild());
				file.println(Node.getRightChild().getData());
			}else {
				file.println("null");
			}
		}else 
		{
			CreateNodes(Node);
			
			setCurrentNode(Node);
		}
		
			Loading = false;
			
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