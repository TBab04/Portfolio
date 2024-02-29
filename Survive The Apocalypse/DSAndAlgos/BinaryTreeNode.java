package DSAndAlgos;

public class BinaryTreeNode<T>
{
	protected T _element;
	protected BinaryTreeNode<T> _left = null;
	protected BinaryTreeNode<T> _right = null;
	
	public BinaryTreeNode( T element)
	{
		_element = element;
	}
	
	public T getElement() {return _element;}
	public void setElement(T elem) {_element = elem;}
	
	public BinaryTreeNode<T> getLeft() {return _left;}
	public void setLeft(BinaryTreeNode<T> node) {_left = node;}
	
	public BinaryTreeNode<T> getRight() {return _right;}
	public void setRight(BinaryTreeNode<T> node) {_right = node;}
	
	public int numChildern()
	{
		int children = 0;
		
		if(_left != null)
			children += 1 + _left.numChildern();
		
		if(_right != null)
			children += 1 + _right.numChildern();
		
		return children;
	}
}
