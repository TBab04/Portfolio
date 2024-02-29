package DSAndAlgos ;

import java.util.ArrayList ;
import java.util.Iterator ;
import java.util.LinkedList ;
import java.util.Queue ;
import java.util.Stack ;

public class Graph<T> implements IGraph<T>
{
    protected final int DEFAULT_CAPACITY = 10 ;
    
    protected int        _numVertices ;
    protected Double[][] _adjMatrix   ;
    protected T[]        _vertices    ;
    
    @SuppressWarnings("unchecked")
    public Graph()
    {
        _numVertices = 0 ;
        _adjMatrix = new Double[ DEFAULT_CAPACITY ][ DEFAULT_CAPACITY ] ;
        _vertices = (T[])( new Object[ DEFAULT_CAPACITY ] ) ;
    }
    
    @Override
    public int size()
    {
        return _numVertices ;
    }
    
    @Override
    public boolean isEmpty()
    {
        return _numVertices == 0 ;
    }
    
    public Double[] getAdjacencyMatrixRow( int rowIndex )
    {
        return _adjMatrix[ rowIndex ] ;
    }
    
    public Double getAdjacencyMatrixValue( int rowIndex, int columnIndex )
    {
        return _adjMatrix[ rowIndex ][ columnIndex ] ;
    }
    
    public T getVertexByIndex( int index )
    {
        return _vertices[ index ] ;
    }
    
    protected int getIndex( T vertex )
    {
        for( int i = 0 ; i < _numVertices ; ++i )
            if( _vertices[ i ].equals( vertex ) )
                return i ;
        return -1 ;
    }
    
    protected boolean indexIsValid( int index )
    {
        return index < _numVertices && index >= 0 ;
    }
    
    @Override
    public void addEdge( T vertex1, T vertex2, Double cost )
    {
        int index1 = getIndex( vertex1 ) ;
        int index2 = getIndex( vertex2 ) ;

        if( indexIsValid( index1 ) && indexIsValid( index2 ) )
        {
            _adjMatrix[ index1 ][ index2 ] = cost ;
            _adjMatrix[ index2 ][ index1 ] = cost ;
        }
    }
    
    @Override
    public void removeEdge( T vertex1, T vertex2 )
    {
        int index1 = getIndex( vertex1 ) ;
        int index2 = getIndex( vertex2 ) ;

        if( indexIsValid( index1 ) && indexIsValid( index2 ) )
        {
            _adjMatrix[ index1 ][ index2 ] = Double.MAX_VALUE ;
            _adjMatrix[ index2 ][ index1 ] = Double.MAX_VALUE ;
        }
    }
    
    @Override
    public void addVertex( T vertex )
    {
        ensureCapacity() ;
        
        _vertices[ _numVertices ] = vertex ;
        for( int i = 0 ; i <= _numVertices ; ++i )
        {
            _adjMatrix[ _numVertices ][ i ] = Double.MAX_VALUE ;
            _adjMatrix[ i ][ _numVertices ] = Double.MAX_VALUE ;
        }      
        
        ++_numVertices ;
    }
    
    @Override
    public void removeVertex( T vertex )
    {
        int index = -1 ;

        for( int i = 0 ; i < _numVertices ; ++i )
        {
            if( vertex.equals( _vertices[ i ] ) )
            {
                index = 1 ;
                break ;
            }
        }

        if( ! indexIsValid( index ) )
            return ;

        --_numVertices ;
        
        for( int i = index ; i < _numVertices ; ++i )
            _vertices[ i ] = _vertices[ i + 1 ] ;

        for( int i = index ; i < _numVertices ; ++i )
            for( int j = 0 ; j < _numVertices ; ++j )
                _adjMatrix[ i ][ j ] = _adjMatrix[ i + 1 ][ j ] ;

        for( int i = index ; i < _numVertices ; ++i )
            for( int j = 0 ; j < _numVertices ; ++j )
                _adjMatrix[ j ][ i ] = _adjMatrix[ j ][ i + 1 ] ;
    }

    protected void ensureCapacity()
    {
        if( _numVertices < _vertices.length )
            return ;

        @SuppressWarnings("unchecked")
        T[] largerVertices = (T[])( new Object[ _vertices.length * 2 ] ) ;

        Double[][] largerAdjMatrix =
            new Double[ _vertices.length * 2 ][ _vertices.length * 2 ] ;
    
        for( int i = 0 ; i < _numVertices ; ++i )
        {
            for( int j = 0 ; j < _numVertices ; ++j )
            {
                largerAdjMatrix[ i ][ j ] = _adjMatrix[ i ][ j ] ;
            }
            largerVertices[ i ] = _vertices[ i ] ;
        }
    
        _vertices = largerVertices ;
        _adjMatrix = largerAdjMatrix ;
    }
    
    @Override
    public boolean isConnected()
    {
        if( isEmpty() )
            return false ;
      
        Iterator<T> it = iteratorBreadthFirst( _vertices[ 0 ] ) ;
        int count = 0 ;
    
        while( it.hasNext() )
        {
            it.next() ;
            ++count ;
        }
        
        return count == _numVertices ;
    }
    

    @Override
    public Iterator<T> iterator()
    {
        if( isEmpty() )
            return new ArrayList<T>().iterator() ;

        return iteratorDepthFirst( _vertices[ 0 ] ) ;
    }

    @Override
    public Iterator<T> iteratorDepthFirst( T startVertex )
    {
        int startIndex = getIndex( startVertex ) ;
        
        ArrayList<T> resultList = new ArrayList<>() ;

        if( ! indexIsValid( startIndex ) )
            return resultList.iterator() ;
    
        Stack<Integer> traversalStack = new Stack<>() ;
        boolean[] visited = new boolean[ _numVertices ] ;
    
        for( int i = 0 ; i < _numVertices ; ++i )
            visited[ i ] = false ;
      
        traversalStack.push( startIndex ) ;
        resultList.add( _vertices[ startIndex ] ) ;
        visited[ startIndex ] = true ;
      
        while( ! traversalStack.isEmpty() )
        {
            Integer x = traversalStack.peek() ;
            boolean found = false ;
    
            for( int i =  0; i < _numVertices && ! found ; ++i )
            {
                if( edgeExists( _adjMatrix[ x ][ i ] ) && ! visited[ i ] )
                {
                    traversalStack.push( i ) ;
                    resultList.add( _vertices[ i ] ) ;
                    visited[ i ] = true ;
                    found = true ;
                }
            }

            if( ! found && ! traversalStack.isEmpty() )
                traversalStack.pop() ;
        }

        return resultList.iterator() ;
    }
    
    @Override
    public Iterator<T> iteratorBreadthFirst( T startVertex )
    {
        int startIndex = getIndex( startVertex ) ;
        
        ArrayList<T> resultList = new ArrayList<T>() ;

        if( ! indexIsValid( startIndex ) )
            return resultList.iterator() ;
    
        Queue<Integer> traversalQueue = new LinkedList<Integer>() ;
        boolean[] visited = new boolean[ _numVertices ] ;

        for( int i = 0 ; i < _numVertices ; ++i )
            visited[ i ] = false ;
      
        traversalQueue.add( startIndex ) ;
        visited[ startIndex ] = true ;
      
        while( ! traversalQueue.isEmpty() )
        {
            Integer x = traversalQueue.remove() ;
            resultList.add( _vertices[ x ] ) ;
    
            for( int i = 0 ; i < _numVertices ; ++i )
            {
                if( edgeExists( _adjMatrix[ x ][ i ] ) && ! visited[ i ] )
                {
                    traversalQueue.add( i ) ;
                    visited[ i ] = true ;
                }
            }
        }

        return resultList.iterator() ;
    }
    
    private boolean edgeExists( Double cost )
    {
        return cost - 1 < Double.MAX_VALUE ;
    }
    
    @Override
    public String toString()
    {
        if( _numVertices == 0 )
            return "Graph is empty" ;
    
        String result = new String() ;
    
        result += "Vertex Values" ;
        result += "\n-------------\n" ;
        result += "index\tvalue\n\n" ;
    
        for( int i = 0 ; i < _numVertices ; ++i )
        {
            result += "" + i + "\t" ;
            result += _vertices[ i ].toString() + "\n" ;
        }

        result += "\n\nAdjacency Matrix\n" ;
        result += "----------------\n" ;
        result += "index\t" ;
    
        for( int i = 0 ; i < _numVertices ; ++i ) 
        {
            result += String.format( "%5d ", i ) ;
        }
        result += "\n\n" ;
    
        for( int i = 0 ; i < _numVertices ; ++i )
        {
            result += "" + i + "\t" ;
      
            for( int j = 0 ; j < _numVertices ; ++j )
            {
                if( _adjMatrix[ i ][ j ] != null && _adjMatrix[ i ][ j ] < Double.MAX_VALUE )
                    result += String.format( "%5.1f ", _adjMatrix[ i ][ j ] ) ;
                else
                    result += "      " ;
            }
            result += "\n" ;
        }
    
        result += "\n" ;
        return result ;
    }}
