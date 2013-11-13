package datastruct;

/**
 * this table owns several keys which are represented by E and are Comparable sub classes
 * and data
 * 
 * @author cyril
 * 
 * @param <E> the key which is a sub class of Comparable
 * @param <T> the data corresponding to the key
 */
public interface TableGen<E extends Comparable<E>, T> {
	
	/**
	 * inserts a data T in the tree with a given key E.
	 * returns true if the data has been inserted, false if the key is already existing
	 * @param E the key
	 * @param T the data
	 */
	public boolean insert(E cle, T donnee);
	
	/**
	 * returns the data corresponding to the key, returns null if the key doesn't exist
	 * @param cle the key corresponding to the data
	 * @return the data
	 */
	public T select(E cle);
	
	/**
	 * deletes the data corresponding to the key
	 * @param cle
	 * @return true if the element has been deleted
	 */
	public boolean delete(E cle);
	
}
