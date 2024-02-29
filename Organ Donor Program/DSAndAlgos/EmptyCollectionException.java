package DSAndAlgos;

public class EmptyCollectionException extends RuntimeException 
{
	private static final long serialVersionUID = 5791063725621215304L;

	public EmptyCollectionException()
	{
		super();
	}
	
	public EmptyCollectionException(String message)
	{
		super(message);
	}
}
