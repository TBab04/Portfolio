package DSAndAlgos;

import java.util.Collection;
import java.util.Iterator;
public interface IBinaryTree<T> extends ICollection, Iterable<T>
{
	public T getRootElement();
	
	public boolean contains( T targetElement) throws IllegalArgumentException;
	
	public T find(T targetElement) throws IllegalArgumentException;
	
	public Iterator<T> iterateInOrder();
	
	public Iterator<T> iteratorPreOrder();
	
	public Iterator<T> iteratorPostOrder();
}
