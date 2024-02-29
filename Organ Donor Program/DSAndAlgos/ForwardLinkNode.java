package DSAndAlgos;
/* 
* @author (Tyler Babcock) 
* <p> (File Name) 
* <p> (Assignment) 
* <p> (Describe, in general, the code contained.) 
*/

public class ForwardLinkNode<T> 
{
	private ForwardLinkNode<T> _next;
	private T _element;
	public ForwardLinkNode()
	{
		_next = null;
		_element = null;
	}
	
	public ForwardLinkNode(T elem)
	{
		_next = null;
		_element = elem;
	}
	
	public T getElement() { return _element; }
	public void setElement(T elem) { _element = elem;}
	
	public ForwardLinkNode<T> getNext() { return _next;}
	public void setNext(ForwardLinkNode<T> node) {_next = node;}
}