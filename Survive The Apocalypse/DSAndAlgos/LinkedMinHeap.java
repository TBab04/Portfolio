package DSAndAlgos ;

public class LinkedMinHeap<T extends Comparable<T>>
    extends LinkedBinaryTree<T, HeapNode<T>>
    implements IMinHeap<T> 
{
    private HeapNode<T> _lastNode ;  

    public LinkedMinHeap() 
    {
        super() ;
    }

    @Override
    public T findMin()
    {
        return getRootElement() ;
    }

    @Override
    public T removeMin() 
    {
        if( isEmpty() )
            return null ;

        T minElement =  _root.getElement() ;

        if( size() == 1 )
        {
            _root     = null ;
            _lastNode = null ;
        }
        else
        {
            HeapNode<T> nextLast = getNewLastNode() ;
            if( _lastNode.getParent().getLeft() == _lastNode )
                _lastNode.getParent().setLeft( null ) ;
            else
                _lastNode.getParent().setRight( null ) ;

            _root.setElement( _lastNode.getElement() ) ;
            _lastNode = nextLast ;
            makeHeapValidAfterRemove() ;
        }

        return minElement ;
    }

    @Override
    public void addElement( T element ) throws IllegalArgumentException
    {
        if( element == null )
            throw new IllegalArgumentException( "element cannot be null" ) ;

        HeapNode<T> node = new HeapNode<T>( element ) ;

        if( _root == null )
        {
            _root = node ;
        }
        else
        {
            HeapNode<T> nextParent = getParentToAddNewNodeTo() ;
            if( nextParent.getLeft() == null )
                nextParent.setLeft( node ) ;
            else
                nextParent.setRight( node ) ;
            
            node.setParent( nextParent ) ;
        }
        
        _lastNode = node ;
        
        if( size() > 1 )
            makeHeapValidAfterAdd() ;
    }

    private HeapNode<T> getNewLastNode()
    {
        HeapNode<T> result = _lastNode ;

        while(  ( result != _root )
            &&  ( result.getParent().getLeft() == result ) )
            result = result.getParent() ;
        
        if( result != _root )
            result = result.getParent().getLeft() ;

        while( result.getRight() != null )
            result = result.getRight() ;

        return result ;
    }

    private HeapNode<T> getParentToAddNewNodeTo()
    {
        HeapNode<T> result = _lastNode ;

        while(  ( result != _root )
            &&  ( result.getParent().getLeft() != result ) )
            result = result.getParent() ;

        if( result != _root )
        {
            if( result.getParent().getRight() == null )
            {
                result = result.getParent() ;
            }
            else
            {
                result = result.getParent().getRight() ;
                while( result.getLeft() != null )
                    result = result.getLeft() ;
            }
        }
        else
        {
            while( result.getLeft() != null )
                result = result.getLeft() ;
        }
        
        return result ;
    }

    private void makeHeapValidAfterAdd()
    {
        HeapNode<T> next = _lastNode ;
        T temp = next.getElement() ;
        
        while(  (  next != _root )
            &&  ( temp.compareTo( next.getParent().getElement() ) < 0 ) )
        {
            next.setElement( next.getParent().getElement() ) ;
            next = next.getParent() ;
        }

        next.setElement( temp ) ;
    }

    private void makeHeapValidAfterRemove()
    {
        HeapNode<T> node  = _root ;
        HeapNode<T> left  = node.getLeft() ;
        HeapNode<T> right = node.getRight() ;
        HeapNode<T> next ;
        
        if( ( left == null ) && ( right == null ) )
            next = null ;
        else if( right == null )
            next = left ;
        else if( left.getElement().compareTo( right.getElement() ) < 0 )
            next = left ;
        else
            next = right ;

        T temp = node.getElement() ;
        
        while(  ( next != null )
            &&  ( next.getElement().compareTo( temp ) < 0 ) )
        {
            node.setElement( next.getElement() ) ;
            node  = next ;
            left  = node.getLeft() ;
            right = node.getRight() ;
            
            if( ( left == null ) && ( right == null ) )
                next = null ;
            else if( right == null )
                next = left ;
            else if( left.getElement().compareTo( right.getElement() ) < 0 )
                next = left ;
            else
                next = right ;
        }

        node.setElement( temp ) ;
    }
}
