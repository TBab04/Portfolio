package DSAndAlgos ;

import java.util.Iterator ;

public interface IGraph<T> extends ICollection, Iterable<T>
{
    public void addEdge( T vertex1, T vertex2, Double cost ) throws IllegalArgumentException  ;

    public void removeEdge( T vertex1, T vertex2 ) throws IllegalArgumentException ;

    public void addVertex( T vertex ) throws IllegalArgumentException ;

    public void removeVertex( T vertex ) throws IllegalArgumentException ;
    
    public boolean isConnected() ;

    public Iterator<T> iteratorBreadthFirst( T startVertex ) throws IllegalArgumentException ;
    
    public Iterator<T> iteratorDepthFirst( T startVertex ) throws IllegalArgumentException ;
}
