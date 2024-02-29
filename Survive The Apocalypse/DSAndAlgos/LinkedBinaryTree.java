package DSAndAlgos;
import java.util.ArrayList;
import java.util.Iterator;

public class LinkedBinaryTree<T, TNode extends BinaryTreeNode<T>> implements IBinaryTree<T>, Iterable<T> 
{

	protected TNode _root = null;

	public LinkedBinaryTree() {}

	public LinkedBinaryTree(TNode rootNode) throws IllegalArgumentException 
	{
		if (rootNode == null || rootNode.getElement() == null)
			throw new IllegalArgumentException("Neither the root node nor its element can be null");

		_root = rootNode;
	}

	@Override
	public int size()
	{
		if (_root == null)
			return 0;
		return 1 + _root.numChildern();
	}

	@Override
	public boolean isEmpty() 
	{
		return _root == null;
	}

	public TNode getRootNode() 
	{
		return _root;
	}

	@Override
	public T getRootElement()
	{
		if (_root == null)
			return null;
		return _root.getElement();
	}

	@Override
	public boolean contains(T targetElement) throws IllegalArgumentException 
	{
		if (targetElement == null)
			throw new IllegalArgumentException("Target element cannot be null");

		return findNode(targetElement, _root) != null;
	}

	@Override
	public T find(T targetElement) throws IllegalArgumentException
{
		if (targetElement == null)
			throw new IllegalArgumentException("Target element cannot be null");

		BinaryTreeNode<T> found = findNode(targetElement, _root);

		if (found == null)
			return null;

		return found.getElement();
	}

	private BinaryTreeNode<T> findNode(T targetElement, BinaryTreeNode<T> next) 
	{
		if (next == null)
			return null;

		if (next.getElement().equals(targetElement))
			return next;

		BinaryTreeNode<T> temp = findNode(targetElement, next.getLeft());

		if (temp == null)
			temp = findNode(targetElement, next.getRight());

		return temp;
	}

	public int getHight() 
	{
		return getHight(_root) - 1;
	}

	protected int getHight(BinaryTreeNode<T> node)
	{
		if (node == null)
			return 0;

		int leftHight = 1 + getHight(node.getLeft());
		int rightHight = 1 + getHight(node.getRight());

		return Math.max(leftHight, rightHight);
	}

	@Override
	public Iterator<T> iterator() 
	{
		return iterateInOrder();
	}

	@Override
	public Iterator<T> iterateInOrder() 
	{
		ArrayIndexedList<T> collector = new ArrayIndexedList<>();
		inOrder(_root, collector);
		return collector.iterator();
	}

	@Override
	public Iterator<T> iteratorPreOrder() 
	{
		ArrayIndexedList<T> collector = new ArrayIndexedList<>();
		preOrder(_root, collector);
		return collector.iterator();
	}

	@Override
	public Iterator<T> iteratorPostOrder()
	{
		ArrayIndexedList<T> collector = new ArrayIndexedList<>();
		postOrder(_root, collector);
		return collector.iterator();
	}

	protected void inOrder(BinaryTreeNode<T> node, ArrayIndexedList<T> collector)
	{
		if (node != null) 
		{
			inOrder(node.getLeft(), collector);
			collector.add(node.getElement());
			inOrder(node.getRight(), collector);
		}
	}

	protected void preOrder(BinaryTreeNode<T> node, ArrayIndexedList<T> collector) 
	{
		if (node != null) 
		{
			collector.add(node.getElement());
			preOrder(node.getLeft(), collector);
			preOrder(node.getRight(), collector);
		}
	}

	protected void postOrder(BinaryTreeNode<T> node, ArrayIndexedList<T> collector) 
	{
		if (node != null) 
		{
			postOrder(node.getLeft(), collector);
			postOrder(node.getRight(), collector);
			collector.add(node.getElement());
		}
	}

	public String toString() 
	{
		ArrayList<BinaryTreeNode<T>> nodes = new ArrayList<>();
		ArrayList<Integer> levelList = new ArrayList<>();
		BinaryTreeNode<T> current;
		String result = "";
		int printDepth = this.getHight();
		int possibleNodes = (int) Math.pow(2, printDepth + 1);
		int countNodes = 0;
		Integer currentLevel = 0;
		Integer previousLevel = -1;

		nodes.add(_root);
		levelList.add(currentLevel);

		while (countNodes < possibleNodes) 
		{
			countNodes = countNodes + 1;
			current = nodes.remove(0);
			currentLevel = levelList.remove(0);

			if (currentLevel > previousLevel)
			{
				result = result + "\n\n";
				previousLevel = currentLevel;
				double stop = Math.pow(2, (printDepth - currentLevel)) - 1;
				for (int i = 0; i < stop; ++i)
					result = result + " ";
			} else 
			{
				double stop = Math.pow(2, (printDepth - currentLevel + 1)) - 1;
				for (int i = 0; i < stop; ++i)
					result = result + " ";
			}

			if (current != null)
			{
				result = result + (current.getElement()).toString();
				nodes.add(current.getLeft());
				levelList.add(currentLevel + 1);
				nodes.add(current.getRight());
				levelList.add(currentLevel + 1);
			} else 
			{
				nodes.add(null);
				levelList.add(currentLevel + 1);
				nodes.add(null);
				levelList.add(currentLevel + 1);
				result = result + " ";
			}
		}

		while (result.length() > 0 && result.charAt(0) == '\n')
			result = result.substring(1);

		while (result.length() > 0 && Character.isWhitespace(result.charAt(result.length() - 1)))
			result = result.substring(0, result.length() - 1);

		return result;
	}
}
