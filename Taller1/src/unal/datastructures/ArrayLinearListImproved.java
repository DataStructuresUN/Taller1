package unal.datastructures; 

import java.util.*; 
import java.io.*; 

public class ArrayLinearListImproved 
<T extends Serializable & Comparable<? super T>> extends ArrayLinearList<T> 
implements LinearListImproved<T> 
{ 
	// constructors 
	public ArrayLinearListImproved(int initialCapacity) 
	{ 
		super( initialCapacity ); 
	} 

	public ArrayLinearListImproved( ) 
	{ 
		this( 10 ); 
	} 

	// methods 
	/** Save the list into a file */
	public void save( String fn ) 
	{ 
		try( ObjectOutputStream os = new
				ObjectOutputStream( new FileOutputStream( fn ) ) ) 
				{ 
			os.writeInt( size ); 
			for( T x : this ) os.writeObject( x ); 
				} 
		catch( IOException e ) 
		{ 
			e.printStackTrace( ); 
		} 
		System.out.println( "Save done" ); 
	} 

	/** Load from a file into the list. 
	 * The list is not reset beforehand */
	@SuppressWarnings( "unchecked" ) 
	public void load( String fn ) 
	{ 
		try( ObjectInputStream is = new
				ObjectInputStream( new FileInputStream( fn ) ) ) 
				{ 
			int n = is.readInt( ); 
			for( int i = 0; i < n; i++ ) 
				add( i, ( T ) is.readObject( ) ); 
				} 
		catch( IOException | ClassNotFoundException e ) 
		{ 
			e.printStackTrace( ); 
		} 
		System.out.println( "Load done" ); 
	} 

	/** sort the list using default compareTo */
	public void sort( ) 
	{ 
		Arrays.sort( element, 0, size ); 
	} 

	/** sort the list using specific comparator */
	public void sort( Comparator<T> c ) 
	{ 
		Arrays.sort( element, 0, size, c ); 
	} 
}