package TreePackage;

	public class DecisionTree<T> extends BinaryTree<T> implements DecisionTreeInterface<T>
	{
	    BinaryNode<T> currentNode;
	    public DecisionTree(T data)
	    {
	        this(data, null, null);
	    }

	    public DecisionTree(T data, DecisionTree<T> left, DecisionTree<T> right)
	    {
	        setTree(data, left, right);
	    }

	    @Override
	    public T getCurrentData()
	    {
	        if (currentNode!= null)
	        {
	            return currentNode.getData();
	        }
	        return null;
	    }

	    @Override
	    public void setCurrentData(T newData)
	    {
	        currentNode.setData(newData);
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
		public void setCurrentNode(BinaryNode<T> currentNode) 
		{
			this.currentNode = currentNode;
			
		}

	    @Override
	    public void setResponses(T responseForNo, T responseForYes)
	    {
	    	BinaryNode no = new BinaryNode<T>(responseForNo, null, null);
	    	BinaryNode yes = new BinaryNode<T>(responseForYes, null, null);
	    	currentNode.setLeftChild(no);
	    	currentNode.setRightChild(yes);
	    }

	    @Override
	    public boolean isAnswer()
	    {
	    	boolean children = false;
	    	
	    	if(currentNode.getLeftChild()== null && currentNode.getRightChild() == null) {
	    		children = true;
	    	}
	        
	        return children;
	    }

	    @Override
	    public void advanceToNo()
	    {
	        currentNode = currentNode.getLeftChild();
	    }

	    @Override
	    public void advanceToYes()
	    {
	        currentNode = currentNode.getRightChild();
	    }

	    @Override
	    public void resetCurrentNode()
	    {
	        currentNode = root;
	    }

	    @Override
	    public void setTree(T rootData)
	    {
	        super.setTree(rootData);
	        currentNode = root;
	    }

	    @Override
	    public void setTree(T rootData, BinaryTreeInterface<T> leftTree, BinaryTreeInterface<T> rightTree)
	    {
	        super.setTree(rootData, leftTree, rightTree);
	        currentNode = root;
	    }


	}

