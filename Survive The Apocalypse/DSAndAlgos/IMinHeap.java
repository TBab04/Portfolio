package DSAndAlgos;

public interface IMinHeap<T extends Comparable<T>> extends IBinaryTree<T>
{
	public T findMin();
	
	public T removeMin();
	
	public void addElement(T element) throws IllegalArgumentException;
}
