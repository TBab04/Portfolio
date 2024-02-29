package DSAndAlgos;

public interface IIndexedList<T> extends IList<T>
{
	public void add(T element) throws IllegalArgumentException;
	
	public void add(int index, T element) throws IndexOutOfBoundsException, IllegalArgumentException;
	
	public void set(int index, T element) throws IndexOutOfBoundsException, IllegalArgumentException;

	public T removeAt(int index) throws IndexOutOfBoundsException;
	
	public T get(int index) throws IndexOutOfBoundsException;
	
	public int indexOf(T element) throws IllegalArgumentException;
	
}
