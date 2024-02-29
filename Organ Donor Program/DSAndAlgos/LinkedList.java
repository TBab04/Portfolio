package DSAndAlgos;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public abstract class LinkedList<T> implements IList<T>
{
	
	protected ForwardLinkNode<T> _head;
	protected ForwardLinkNode<T> _tail;
	protected int _count;
	protected int _modCount;
	
	public LinkedList()
	{
		_head = null;
		_tail = null;
		_count = 0;
		_modCount = 0;
	}

	@Override
	public int size()
	{
		return _count;
	}
	
	@Override
	public boolean isEmpty()
	{
		return _count <= 0;
	}

	@Override
	public T remove(T element) throws IllegalArgumentException 
	{
		if(element == null)
			throw new IllegalArgumentException("element cannot be null");
		
		boolean found = false;
		ForwardLinkNode<T> previous = null;
		ForwardLinkNode<T> current = _head;
		
		while(current != null && ! found)
		{
			if(element.equals(current.getElement()))
			{
				found = true;
			}
			else
			{
				previous = current;
				current = current.getNext();
			}
		}
		
		if(! found)
			return null;
		
		if(size() == 1)
		{
			_head = null;
			_tail= null;
		}
		else if(current.equals(_head))
		{
			_head = current.getNext();
		}
		else if(current.equals(_tail))
		{
			_tail = previous;
			_tail.setNext(null);
		}
		else
		{
			previous.setNext(current.getNext());
		}
		
		
		--_count;
		++_modCount;
		
		return current.getElement();
	
		
	
	}

	@Override
	public boolean contains(T element) throws IllegalArgumentException
	{
		if(element == null)
			throw new IllegalArgumentException("element cannot be null");

		boolean found = false;
		ForwardLinkNode<T> current = _head;
		
		while(current != null && ! found)
		{
			if(element.equals(current.getElement()))
				found = true;
			else
				current = current.getNext();
		}
		return found;
		
	}
	
	@Override
	public String toString()
	{
		String output = "[";
		
		ForwardLinkNode<T> current = _head;
		while(current != null)
		{
			output += current.getElement() + " ";
			current = current.getNext();			
		}
		return output + "]";
	}
	
	@Override
	public Iterator<T> iterator() 
	{
		return new LinkedListIterartor(this);
	}
	
	private class LinkedListIterartor implements Iterator<T>
	{
		private LinkedList<T> _iteratingList;
		private int _initialModCount;
		private ForwardLinkNode<T> _current;
		
		public LinkedListIterartor(LinkedList<T> iteratingList)
		{
			_iteratingList = iteratingList;
			_initialModCount = iteratingList._modCount;
			_current = iteratingList._head;
		}

		@Override
		public boolean hasNext() 
		{
			if(_initialModCount != _iteratingList._modCount)
				throw new ConcurrentModificationException();
			
			return _current != null;
		}

		@Override
		public T next() 
		{
			if(! hasNext())
				throw new NoSuchElementException();
			
			T result = _current.getElement();
			_current = _current.getNext();
			
			return result;
		}
	}


}
