package DSAndAlgos;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public abstract class ArrayList<T> implements IList<T>
{
	protected final static int DEFAULT_CAPACITY = 100;
	protected int _rear;
	protected T[] _list;
	protected int _modCount;

	public ArrayList()
	{
		this(DEFAULT_CAPACITY);
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList(int initialCapacity)
	{
		_rear = 0;
		_list = (T[]) new Object[initialCapacity];
		_modCount = 0;
	}
	
	@Override
	public int size() 
	{
		return _rear;
	}

	@Override
	public boolean isEmpty() 
	{
		return _rear <= 0;
	}

	@Override
	public T remove(T element) throws IllegalArgumentException 
	{
		if(element == null)
			throw new IllegalArgumentException("element cannot be null");
		
		int foundAtIndex = find(element);
		
		if(foundAtIndex < 0)
			return null;
		
		T result = _list[foundAtIndex];
		--_rear;
		
		for(int i = foundAtIndex; i < _rear ; ++i)
			_list[i] = _list[i + 1];
		
		_list[_rear] = null;
		++_modCount;
		
		return result;
	}

	@Override
	public boolean contains(T element) throws IllegalArgumentException 
	{
		if(element == null)
			throw new IllegalArgumentException("element cannot be null");
		
		return find(element) >= 0;
	}
	
	protected int find(T element)
	{
		int foundAtIndex = -1;
		for(int i=0; i <_rear; i++)
			if(element.equals(_list[i])) 	
				return i;		
		return -1;
	}
	
	
	protected void ensureCapacity()
	{
		if(size() >= _list.length)
			_list = Arrays.copyOf(_list,_list.length * 2);
	}
	
	@Override
	public String toString()
	{
		String output = "[";
		
		for(int i = 0; i < _rear; ++i)
			output += _list[i] + " ";
		
		return output + "]";
	}
	
	@Override
	public Iterator<T> iterator() 
	{
		return new ArrayListIterator(this);
	}
	
	private class ArrayListIterator implements Iterator<T>
	{
		private ArrayList<T> _iteratingList;
		private int _initialModCount;
		private int _currentIndex;
		
		public ArrayListIterator(ArrayList<T> iteratingList)
		{
			_iteratingList = iteratingList;
			_initialModCount = _iteratingList._modCount;
			_currentIndex = 0;
		}

		@Override
		public boolean hasNext() 
		{
			if(_initialModCount != _iteratingList._modCount)
				throw new ConcurrentModificationException();
			
			return _currentIndex < _rear;
		}

		@Override
		public T next() 
		{
			if(! hasNext())
				throw new NoSuchElementException();
			
			++_currentIndex;
			
			return _list[_currentIndex - 1];
		}
		
	}

}
