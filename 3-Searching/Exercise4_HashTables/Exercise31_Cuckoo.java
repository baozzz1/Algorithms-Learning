package Exercise4_HashTables;

import edu.princeton.cs.algs4.Queue;

/**
 * Wrong class
 * @author baozzz1
 * 2018年12月26日
 */
public class Exercise31_Cuckoo<Key, Value> {
	private LinearProbingHashST<Key, Value> lphST1;
	private LinearProbingHashST<Key, Value> lphST2;
	private static final int INIT_CAPACITY = 4;
	private static final int HASH_FORMULA1 = 11;
	private static final int HASH_FORMULA2 = 17;
	
	private class LinearProbingHashST<Key, Value>{
	    private int n;           // number of key-value pairs in the symbol table
	    private int m;           // size of linear probing table
	    private Key[] keys;      // the keys
	    private Value[] vals;    // the values
	    private final int R;

	    public LinearProbingHashST() {
	        this(INIT_CAPACITY, HASH_FORMULA1);
	    }

	    public LinearProbingHashST(int capacity) {
	        this(capacity,HASH_FORMULA1);
	    }
	    
	    public LinearProbingHashST(int capacity,int R) {
	        m = capacity;
	        n = 0;
	        this.R = R;
	        keys = (Key[])   new Object[m];
	        vals = (Value[]) new Object[m];
	    }

	    public int size() {
	        return n;
	    }

	    public boolean isEmpty() {
	        return size() == 0;
	    }

	    public boolean contains(Key key) {
	        if (key == null) throw new IllegalArgumentException("argument to contains() is null");
	        return get(key) != null;
	    }

	    // hash function for keys - returns value between 0 and M-1
	    private int hash(Key key) {
	        return ((R * key.hashCode()) & 0x7fffffff) % m;
	    }

	    // resizes the hash table to the given capacity by re-hashing all of the keys
	    private void resize(int capacity) {
	        LinearProbingHashST<Key, Value> temp = new LinearProbingHashST<Key, Value>(capacity);
	        for (int i = 0; i < m; i++) {
	            if (keys[i] != null) {
	                temp.put(keys[i], vals[i]);
	            }
	        }
	        keys = temp.keys;
	        vals = temp.vals;
	        m    = temp.m;
	    }

	    public void put(Key key, Value val) {
	        //if (key == null) throw new IllegalArgumentException("first argument to put() is null");

	        /*if (val == null) {
	            delete(key);
	            return;
	        }*/

	        // double table size if 50% full
	        if (n >= m/2) resize(2*m);

	        int i = hash(key);
	        keys[i] = key;
	        vals[i] = val;
	        n++;
	    }

	    public Value get(Key key) {
	        if (key == null) throw new IllegalArgumentException("argument to get() is null");
	        for (int i = hash(key); keys[i] != null; i = (i + 1) % m)
	            if (keys[i].equals(key))
	                return vals[i];
	        return null;
	    }

	    public void delete(Key key) {
	        if (key == null) throw new IllegalArgumentException("argument to delete() is null");
	        if (!contains(key)) return;

	        int i = hash(key);

	        // delete key and associated value
	        keys[i] = null;
	        vals[i] = null;
	        n--;

	        // halves size of array if it's 12.5% full or less
	        if (n > 0 && n <= m/8) resize(m/2);
	    }

	    public Iterable<Key> keys() {
	        Queue<Key> queue = new Queue<Key>();
	        for (int i = 0; i < m; i++)
	            if (keys[i] != null) queue.enqueue(keys[i]);
	        return queue;
	    }
	}
	
	public Exercise31_Cuckoo() {
		this(INIT_CAPACITY);
	}
	public Exercise31_Cuckoo(int cap) {
		lphST1 = new LinearProbingHashST(cap,HASH_FORMULA1);
		lphST2 = new LinearProbingHashST(cap,HASH_FORMULA2);
	}
	
	public void put(Key key, Value val) {
		
		Key tempKey;
		Value tempVal;
		
		int hashIndex = lphST1.hash(key);
		boolean isTable1 = true;
		Key targetKey = lphST1.keys[hashIndex];
		while(targetKey!=null) {
			if(isTable1) {
				if(targetKey.equals(key)) {
					lphST1.vals[hashIndex] = val;
					return;
				}
				//exch(key,val,lphST1.keys[hashIndex],lphST1.vals[hashIndex]);
				tempKey = key;
				tempVal = val;
				key = lphST1.keys[hashIndex];
				val = lphST1.vals[hashIndex];
				lphST1.keys[hashIndex] = tempKey;
				lphST1.vals[hashIndex] = tempVal;
				hashIndex = lphST2.hash(key);
				isTable1 = false;
				targetKey = lphST2.keys[hashIndex];
			}
			else {
				if(targetKey.equals(key)) {
					lphST2.vals[hashIndex] = val;
					return;
				}
				//exch(key,val,lphST2.keys[hashIndex],lphST2.vals[hashIndex]);

				tempKey = key;
				tempVal = val;
				key = lphST1.keys[hashIndex];
				val = lphST1.vals[hashIndex];
				lphST1.keys[hashIndex] = tempKey;
				lphST1.vals[hashIndex] = tempVal;
				
				hashIndex = lphST1.hash(key);
				isTable1 = true;
				targetKey = lphST1.keys[hashIndex];
			}
		}
		if(isTable1) {
			lphST1.keys[hashIndex] = key;
			lphST1.vals[hashIndex] = val;
			lphST1.n++;
		}
		else {
			lphST2.keys[hashIndex] = key;
			lphST2.vals[hashIndex] = val;
			lphST2.n++;
		}
	}

	private void exch(Key key1,Value val1,Key key2, Value val2) {
		Key tempk = key1;
		Value tempv = val1;
		key1 = key2;
		val1 = val2;
		key2 = tempk;
		val2 = tempv;
	}

	public static void main(String[] agrs) {
		Exercise31_Cuckoo<String, Integer> lphST;
		lphST = new Exercise31_Cuckoo<String, Integer>(4);
		String s = "EASYQUESTION";
		for (int i = 0; i < s.length(); i++)
			lphST.put((String) s.substring(i, i + 1), i);
		int x=1;
	}
	
}
