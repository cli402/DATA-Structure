/* Sorts.java */

package sort;

public class Sorts {

  /**
   *  Place any final static fields you would like to have here.
   **/
	public static final int bitmask=15;     //1111 for the bit masking job
	public static final int bucksize=16;	//for storing the base-16 item
	public static final int bit=4;         //for the 16 based, shift 1 means shift 0000 in binary 
	public static final int shiftdigit=8;
  /**
   *  countingSort() sorts an array of int keys according to the
   *  values of _one_ of the base-16 digits of each key.  "whichDigit"
   *  indicates which digit is the sort key.  A zero means sort on the least
   *  significant (ones) digit; a one means sort on the second least
   *  significant (sixteens) digit; and so on, up to a seven, which means
   *  sort on the most significant digit.
   *  @param key is an array of ints.  Assume no key is negative.
   *  @param whichDigit is a number in 0...7 specifying which base-16 digit
   *    is the sort key.
   *  @return an array of type int, having the same length as "keys"
   *    and containing the same keys sorted according to the chosen digit.
   *
   *    Note:  Return a _newly_ created array.  DO NOT CHANGE THE ARRAY keys.
   **/
  public static int[] countingSort(int[] keys, int whichDigit) {
    // Replace the following line with your solution.
    int[] array=new int[keys.length];
    int[] buck=new int[bucksize];
    for(int i=0;i<keys.length;i++){
    	buck[keys[i]>>(whichDigit*bit)&bitmask]++;
    }
    int item_before=0;
	for(int i=0;i<buck.length;i++){
		int item=buck[i];
		buck[i]=item_before;
		item_before+=item;
	}
	
	
	for(int i =0;i<keys.length;i++){
		array[buck[((keys[i]>>(whichDigit*bit))&bitmask)]]=keys[i];
		buck[(keys[i]>>(whichDigit*bit))&bitmask]++;
	}
	
	  return array;
  }

  /**
   *  radixSort() sorts an array of int keys (using all 32 bits
   *  of each key to determine the ordering).
   *  @param key is an array of ints.  Assume no key is negative.
   *  @return an array of type int, having the same length as "keys"
   *    and containing the same keys in sorted order.
   *
   *    Note:  Return a _newly_ created array.  DO NOT CHANGE THE ARRAY keys.
   **/
  public static int[] radixSort(int[] keys) {
    // Replace the following line with your solution.
    int[] array=keys;
    for(int i =0;i<shiftdigit;i++){
    	array=countingSort(array, i);
    }
	  return array;
  }

  /**
   *  yell() prints an array of int keys.  Each key is printed in hexadecimal
   *  (base 16).
   *  @param key is an array of ints.
   **/
  public static void yell(int[] keys) {
    System.out.print("keys are [ ");
    for (int i = 0; i < keys.length; i++) {
      System.out.print(Integer.toString(keys[i], 16) + " ");
    }
    System.out.println("]");
  }

  /**
   *  main() creates and sorts a sample array.
   *  We recommend you add more tests of your own.
   *  Your test code will not be graded.
   **/
  public static void main(String[] args) {
    int[] keys = { Integer.parseInt("60013879", 16),
                   Integer.parseInt("11111119", 16),
                   Integer.parseInt("2c735010", 16),
                   Integer.parseInt("2c732010", 16),
                   Integer.parseInt("7fffffff", 16),
                   Integer.parseInt("4001387c", 16),
                   Integer.parseInt("10111119", 16),
                   Integer.parseInt("529a7385", 16),
                   Integer.parseInt("1e635010", 16),
                   Integer.parseInt("28905879", 16),
                   Integer.parseInt("00011119", 16),
                   Integer.parseInt("00000000", 16),
                   Integer.parseInt("7c725010", 16),
                   Integer.parseInt("1e630010", 16),
                   Integer.parseInt("111111e5", 16),
                   Integer.parseInt("61feed0c", 16),
                   Integer.parseInt("3bba7387", 16),
                   Integer.parseInt("52953fdb", 16),
                   Integer.parseInt("40013879", 16) };

    yell(keys);
    keys = radixSort(keys);
    yell(keys);
  }

}
