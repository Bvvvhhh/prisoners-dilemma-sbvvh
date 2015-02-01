import java.lang.reflect.Array;


// only enqueing
public class CircularQueue<E>
{
    private E[] q = null; 
    private int insertionIndex = 0;
    private int removalIndex = -1;
    private int actualSize = 0;
    private final int maxSize;

    private Class<E> c;

    public CircularQueue(Class<E> c, int maxSize)
    {
	// XXX: maxSize must be checked positive
	this.maxSize = maxSize;
	this.q =  (E[]) Array.newInstance(c, this.maxSize);
	this.c = c;
    }

    public int getActualSize()
    {
	return this.actualSize;
    }

    public int getMaxSize()
    {
	return this.maxSize;
    }

    // increase index "circularly"
    private int increaseIndexByOne(int oldVal)
    {
	int newVal = (oldVal+1) % this.getMaxSize();
	return newVal;
    }

    private int decreaseIndexByOne(int oldVal)
    {
	if (oldVal == 0)
	    return this.getMaxSize()-1;
	else
	    return oldVal-1;
    }

    public void enqueue(E e)
    {
	// if indeces coincide (i.e. queue is full) or queue is empty
	if (this.getActualSize() == this.getMaxSize() || this.getActualSize() == 0) {
	    // oldest item is being replaced by the newest
	    //  so we move to the second oldest
	    this.removalIndex = 
		this.increaseIndexByOne(this.removalIndex);
	    }

	q[this.insertionIndex] = e;
	
	this.insertionIndex = 
	    this.increaseIndexByOne(this.insertionIndex);

	if (this.actualSize < this.getMaxSize())
	    this.actualSize++;

    }

    public E dequeue() throws Exception
    {
	if (this.getActualSize() == 0) {
	    throw new Exception("Sorry, empty queue!");
	}

	E ret = this.q[this.removalIndex];

	this.removalIndex = this.increaseIndexByOne(this.removalIndex);
	this.actualSize--;

	return ret;
    }

    public E[] getWholeArray()
	{
	    int n = this.getActualSize();
	    E[] ret = (E[]) Array.newInstance(c, n);

	    int i, curIndex;
	    for (i = 0, curIndex = this.removalIndex;
		 i < n;
		 i++, curIndex = this.increaseIndexByOne(curIndex)) {
		ret[i] = q[curIndex];
	    }
	    
	    return ret;
	}

    public static void main(String args[]) throws Exception
    {
	CircularQueue<String> q = new CircularQueue<String>(String.class, 3);

	for (String a : args)
	    {
		q.enqueue(a);
	    }

       	q.dequeue();

	String ar[] = (String[]) q.getWholeArray();
	for (String el : ar)
	    System.out.println(el);
    }

}
