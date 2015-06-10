/* HashTableChained.java */

package dict;
import list.*;

/**
 *  HashTableChained implements a Dictionary as a hash table with chaining.
 *  All objects used as keys must have a valid hashCode() method, which is
 *  used to determine which bucket of the hash table an entry is stored in.
 *  Each object's hashCode() is presumed to return an int between
 *  Integer.MIN_VALUE and Integer.MAX_VALUE.  The HashTableChained class
 *  implements only the compression function, which maps the hash code to
 *  a bucket in the table's range.
 *
 *  DO NOT CHANGE ANY PROTOTYPES IN THIS FILE.
 **/

public class HashTableChained implements Dictionary {

  /**
   *  Place any data fields here.
   **/
protected List[] hashtable;
protected int size;
protected int cap;

  /** 
   *  Construct a new empty hash table intended to hold roughly sizeEstimate
   *  entries.  (The precise number of buckets is up to you, but we recommend
   *  you use a prime number, and shoot for a load factor between 0.5 and 1.)
   **/

  public HashTableChained(int sizeEstimate) {
    // Your solution here.
	  cap = prime(sizeEstimate);
	  hashtable=new DList[cap];
	  size=0;
  }

  /** 
   *  Construct a new empty hash table with a default size.  Say, a prime in
   *  the neighborhood of 100.
   **/

  public HashTableChained() {
    // Your solution here.
	  cap=prime(150);
	  hashtable=new DList[cap];
	  size=0;
  }

  /**
   *  Converts a hash code in the range Integer.MIN_VALUE...Integer.MAX_VALUE
   *  to a value in the range 0...(size of hash table) - 1.
   *
   *  This function should have package protection (so we can test it), and
   *  should be used by insert, find, and remove.
   **/

  int compFunction(int code) {
    // Replace the following line with your solution.
    return ((127*code + 9) % 16908799) % (cap);
  }

  /** 
   *  Returns the number of entries stored in the dictionary.  Entries with
   *  the same key (or even the same key and value) each still count as
   *  a separate entry.
   *  @return number of entries in the dictionary.
   **/

  public int size() {
    // Replace the following line with your solution.
    return size;
  }

  /** 
   *  Tests if the dictionary is empty.
   *
   *  @return true if the dictionary has no entries; false otherwise.
   **/

  public boolean isEmpty() {
    // Replace the following line with your solution.
    if(size==0)
	  return true;
    else 
    	return false;
  }

  /**
   *  Create a new Entry object referencing the input key and associated value,
   *  and insert the entry into the dictionary.  Return a reference to the new
   *  entry.  Multiple entries with the same key (or even the same key and
   *  value) can coexist in the dictionary.
   *
   *  This method should run in O(1) time if the number of collisions is small.
   *
   *  @param key the key by which the entry can be retrieved.
   *  @param value an arbitrary object.
   *  @return an entry containing the key and value.
   **/

  public Entry insert(Object key, Object value) {
    // Replace the following line with your solution.
    Entry ins=new Entry();
    ins.key=key;
    ins.value=value;
    int n=Math.abs(compFunction(key.hashCode()));
    if(hashtable[n]==null){
    	hashtable[n]=new DList();
    }
    //insert the Entry object into the Dlist
    hashtable[n].insertBack(ins);
    size++;
	 return ins;
  }

  /** 
   *  Search for an entry with the specified key.  If such an entry is found,
   *  return it; otherwise return null.  If several entries have the specified
   *  key, choose one arbitrarily and return it.
   *
   *  This method should run in O(1) time if the number of collisions is small.
   *
   *  @param key the search key.
   *  @return an entry containing the key and an associated value, or null if
   *          no entry contains the specified key.
   **/

  public Entry find(Object key) {
    // Replace the following line with your solution.
    int n = Math.abs(compFunction(key.hashCode()));
    try{
    	DListNode current=(DListNode) hashtable[n].front();
    	for(int i =0;i<hashtable[n].length();i++){
    		if(((Entry) current.item()).key.equals(key)){
    			return ((Entry) current.item());
    		}
    	
    		current=(DListNode)current.next();
    	}
    }catch(InvalidNodeException ivn){
    	System.out.print(ivn);
    }
    return null;
  }

  /** 
   *  Remove an entry with the specified key.  If such an entry is found,
   *  remove it from the table and return it; otherwise return null.
   *  If several entries have the specified key, choose one arbitrarily, then
   *  remove and return it.
   *
   *  This method should run in O(1) time if the number of collisions is small.
   *
   *  @param key the search key.
   *  @return an entry containing the key and an associated value, or null if
   *          no entry contains the specified key.
   */

  public Entry remove(Object key) {
    // Replace the following line with your solution.
	 int n = Math.abs(compFunction(key.hashCode()));
	 try{
	    	DListNode current=(DListNode) hashtable[n].front();
	    	for(int i =0;i<hashtable[n].length();i++){
	    		if(((Entry) current.item()).key.equals(key)){
	    			current.remove();
	    			size--;
	    			return ((Entry) current.item());
	    		}
	    	
	    		current=(DListNode)current.next();
	    	}
	    }catch(InvalidNodeException ivn){
	    	System.out.print(ivn);
	    }
	    return null;
  }

  /**
   *  Remove all entries from the dictionary.
   */
  public void makeEmpty() {
    size=0;
    hashtable=new DList[cap];
  }


public int prime(int n ){
	int[] arry=new int[n];
	for(int i =2;i<n;i++){
		if(isPrime(i)){
			arry[i]=1;
		}
	}
	//get the prime number that is closet to the n
	for(int i=n;i>0;i--){
		if (arry[i]==1){
			return i;
		}
	}
	return 0;
}

boolean isPrime(int n) {
    //check if n is a multiple of 2
    if (n%2==0) return false;
    //if not, then just check the odds
    for(int i=3;i*i<=n;i+=2) {
        if(n%i==0)
            return false;
    }
    return true;
}
}