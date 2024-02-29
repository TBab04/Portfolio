package DSAndAlgos;

public class LinkedIndexedList<T> 
	extends LinkedList<T>
	implements IIndexedList<T>
{

	@Override
	public void add(T element) throws IllegalArgumentException
	{
		if(element == null)
			throw new IllegalArgumentException("element cannot be null");
		
		ForwardLinkNode<T> newNode = new ForwardLinkNode<>(element);
		
		if(_count == 0)
		{
			_head = _tail = newNode;
		}
		else
		{
			_tail.setNext(newNode);
			_tail = newNode;
		}
		
		++_count;
		++_modCount;
	}

	@Override
	public void add(int index, T element) throws IndexOutOfBoundsException, IllegalArgumentException 
	{
		if(element == null)
			throw new IllegalArgumentException("element cannot be null");
		
		if(index > _count || index < 0)
			throw new IndexOutOfBoundsException();
		
		ForwardLinkNode<T> previous = null;
		ForwardLinkNode<T> current = _head;
		
		for(int i = 0; i < index; ++i)
		{
			previous = current;
			current = current.getNext();
		}
		
		ForwardLinkNode<T> newNode = new ForwardLinkNode<>(element);
		
		newNode.setNext(current);
		if(previous == null)
			_head = newNode;
		else
			previous.setNext(newNode);
		
		if( current == null)
			_tail = newNode;
		
		++_count;
		++_modCount;
	}

	@Override
	public void set(int index, T element) throws IndexOutOfBoundsException, IllegalArgumentException
	{
		if(element == null)
			throw new IllegalArgumentException("element cannot be null");
		
		if(index >= _count || index < 0)
			throw new IndexOutOfBoundsException();
		
		ForwardLinkNode<T> current = _head;
		for(int i = 0; i < index; ++i)
			current = current.getNext();
		
		current.setElement(element);
		
		++_modCount;
	}

	@Override
	public T removeAt(int index) throws IndexOutOfBoundsException 
	{
		if(index >= _count || index < 0)
			throw new IndexOutOfBoundsException();
		
		ForwardLinkNode<T> previous = null;
		ForwardLinkNode<T> current = _head;
		
		for(int i = 0; i < index; ++i)
		{
			previous = current;
			current = current.getNext();
		}
		
		if(previous == null)
			_head = current.getNext();
		else
			previous.setNext(current.getNext());
		
		if(current.getNext() == null)
			_tail = previous;
		
		--_count;
		++_modCount;
		
		return current.getElement();
	}

	@Override
	public T get(int index) throws IndexOutOfBoundsException 
	{
		if(index >= _count || index < 0)
			throw new IndexOutOfBoundsException();
		
		ForwardLinkNode<T> current = _head;
		
		for(int i = 0; i < index; ++i)
			current = current.getNext();
		
		return current.getElement();
	}

	@Override
	public int indexOf(T element) throws IllegalArgumentException 
	{
		if(element == null)
			throw new IllegalArgumentException("element cannot be null");
		
		ForwardLinkNode<T> current = _head;
		
		for(int i = 0; i < _count; ++i)
		{
			if(element.equals(current.getNext()))
					return i;
			current = current.getNext();
		}
		
		return -1;
		
	}

}
