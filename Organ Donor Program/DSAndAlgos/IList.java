package DSAndAlgos;

public interface IList<T> extends ICollegtion, Iterable<T>
{
	public T remove(T element) throws IllegalArgumentException;
	
	public boolean contains(T element) throws IllegalArgumentException;
}
