package DSAndAlgos;

public class LinkedOrderedList<T extends Comparable<T>> 
extends LinkedList<T> 
implements IOrderedList<T>
{
	@Override
	public void add(T element) throws IllegalArgumentException 
	{
		if(element == null)
			throw new IllegalArgumentException("element cannot be null");	
		
		ForwardLinkNode<T> newNode = new ForwardLinkNode<T>(element);
		
		if(isEmpty())
		{
			_head = newNode;
			_tail = newNode;
		}
		else if(element.compareTo(_head.getElement()) <= 0)
		{
			newNode.setNext(_head);
			_head= newNode;
		}
		else if(element.compareTo(_tail.getElement()) > 0)
		{
			_tail.setNext(newNode);
			_tail = newNode;
		}
		else
		{
			ForwardLinkNode<T> current = _head;
			ForwardLinkNode<T> previous = null;
			
			while(element.compareTo(current.getElement()) > 0)
			{
				previous = current;
				current = current.getNext();
			}
			
			newNode.setNext(current);
			previous.setNext(newNode);
		}
		
		++_count;
		++_modCount;
	}

	@Override
	public T peek() throws EmptyCollectionException
	{
		if(isEmpty())
			throw new EmptyCollectionException();
		
		return _head.getElement();
	}
	
}
