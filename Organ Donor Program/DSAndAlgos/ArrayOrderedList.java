package DSAndAlgos;

public class ArrayOrderedList<T extends Comparable<T>> 
	extends ArrayList<T>
	implements IOrderedList<T>
{
	public ArrayOrderedList()
	{
		this(DEFAULT_CAPACITY);
	}
	
	@SuppressWarnings("unchecked")
	public ArrayOrderedList(int initialCapacity)
	{
		_rear = 0;
		_list = (T[]) new Comparable[initialCapacity];
		_modCount = 0;
	}

	@Override
	public void add(T element) throws IllegalArgumentException 
	{
		if(element == null)
			throw new IllegalArgumentException("element cannot be null");
		
		ensureCapacity();
		
		int insertionPoint = 0;
		
		while(insertionPoint < _rear && element.compareTo(_list[insertionPoint]) > 0 )
			++insertionPoint;
		
		for(int i = _rear; i > insertionPoint; --i)
			_list[i] = _list[i -1];
		
		_list[insertionPoint] = element;
		++_rear;
		++_modCount;
	}

	@Override
	public T peek() throws EmptyCollectionException
	{
		if(isEmpty()) {
			throw new EmptyCollectionException("List is empty");
		}
		
		return _list[0];
	}
}
