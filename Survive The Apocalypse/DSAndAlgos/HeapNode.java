package DSAndAlgos;

public class HeapNode<T> extends BinaryTreeNode<T>
{
	private HeapNode<T> _parent = null;
	
	public HeapNode(T element)
	{
		super(element);
	}
	
	public HeapNode<T> getParent() { return _parent;}
	public void setParent(HeapNode<T> node) {_parent = node;}
	
	@Override
	public HeapNode<T> getLeft() {return (HeapNode<T>) _left;}
	
	@Override
	public HeapNode<T> getRight() {return (HeapNode<T>) _right;}

}
