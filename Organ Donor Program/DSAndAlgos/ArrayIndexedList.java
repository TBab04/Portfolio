package DSAndAlgos;

public class ArrayIndexedList<T> 
	extends ArrayList<T>
	implements IIndexedList<T>
{

	@Override
	public void add(T element) throws IllegalArgumentException 
	{
		if(element == null)
			throw new IllegalArgumentException("element cannot be null");
		
		ensureCapacity();
		
		_list[_rear] = element;
		
		++_rear;
		++_modCount;
	}

	@Override
	public void add(int index, T element) throws IndexOutOfBoundsException, IllegalArgumentException 
	{
		if(element == null)
			throw new IllegalArgumentException("element cannot be null");
		
		if(index > _rear || index < 0)
			throw new IndexOutOfBoundsException();
	
		ensureCapacity();

		for(int i = _rear; i > index; --i)
			_list[i] = _list[i-1];
		
		_list[index] = element;
		
		++_rear;
		++_modCount;
	}

	@Override
	public void set(int index, T element) throws IndexOutOfBoundsException, IllegalArgumentException 
	{
		if(element == null)
			throw new IllegalArgumentException("element cannot be null");
		
		if(index >= _rear || index < 0)
			throw new IndexOutOfBoundsException();
		
		_list[index] = element;
		
		++_modCount;
	}

	@Override
	public T removeAt(int index) throws IndexOutOfBoundsException 
	{
		if(index >= _rear || index < 0)
			throw new IndexOutOfBoundsException();
		
		T output = _list[index];
		
		for(int i = index; i < _rear - 1; ++i)
			_list[i] = _list[i+1];
		
		--_rear;
		_list[_rear] = null;
		
		++_modCount;
		
		return output;
	}

	@Override
	public T get(int index) throws IndexOutOfBoundsException 
	{
		if(index >= _rear || index < 0)
			throw new IndexOutOfBoundsException();
		
		return _list[index];
	}

	@Override
	public int indexOf(T element) throws IllegalArgumentException 
	{
		if(element == null)
			throw new IllegalArgumentException("element cannot be null");
		
		return find(element);
	}

}
