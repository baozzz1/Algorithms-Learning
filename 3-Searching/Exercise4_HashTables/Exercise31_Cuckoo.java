package Exercise4_HashTables;

import edu.princeton.cs.algs4.Queue;

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
	        if (key == null) throw new IllegalArgumentException("first argument to put() is null");

	        if (val == null) {
	            delete(key);
	            return;
	        }

	        // double table size if 50% full
	        if (n >= m/2) resize(2*m);

	        int i;
	        for (i = hash(key); keys[i] != null; i = (i + 1) % m) {
	            if (keys[i].equals(key)) {
	                vals[i] = val;
	                return;
	            }
	        }
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

	        // find position i of key
	        int i = hash(key);
	        while (!key.equals(keys[i])) {
	            i = (i + 1) % m;
	        }

	        // delete key and associated value
	        keys[i] = null;
	        vals[i] = null;

	        // rehash all keys in same cluster
	        i = (i + 1) % m;
	        while (keys[i] != null) {
	            // delete keys[i] an vals[i] and reinsert
	            Key   keyToRehash = keys[i];
	            Value valToRehash = vals[i];
	            keys[i] = null;
	            vals[i] = null;
	            n--;
	            put(keyToRehash, valToRehash);
	            i = (i + 1) % m;
	        }

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
		
		
		
		int hashIndex = lphST1.hash(key);
		boolean isTable1 = true;
		Key targetKey = lphST1.keys[hashIndex];
		while(targetKey!=null) {
			if(isTable1) {
				if(targetKey.equals(key)) {
					lphST1.vals[hashIndex] = val;
					return;
				}
				exch(key,val,lphST1.keys[hashIndex],lphST1.vals[hashIndex]);
				hashIndex = lphST2.hash(key);
				isTable1 = false;
				targetKey = lphST2.keys[hashIndex];
			}
			else {
				if(targetKey.equals(key)) {
					lphST2.vals[hashIndex] = val;
					return;
				}
				exch(key,val,lphST2.keys[hashIndex],lphST2.vals[hashIndex]);
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
			keys2[hashIndex] = key;
			vals2[hashIndex] = val;
			N2++;
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

/*
	private Key[] keys1;
	private Value[] vals1;
	private Key[] keys2;
	private Value[] vals2;
	private int N1;
	private int N2;
	private int M1 = 31;
	private int M2 = 31;

	public Exercise31_Cuckoo() {
		keys1 = (Key[]) new Object[M1];
		vals1 = (Value[]) new Object[M1];
		keys2 = (Key[]) new Object[M2];
		vals2 = (Value[]) new Object[M2];
	}

	public Exercise31_Cuckoo(int cap) {
		M1 = cap;
		M2 = cap;
		keys1 = (Key[]) new Object[M1];
		vals1 = (Value[]) new Object[M1];
		keys2 = (Key[]) new Object[M2];
		vals2 = (Value[]) new Object[M2];
	}

	protected int hash1(Key key) {
		return ((11 * key.hashCode()) & 0x7fffffff) % M1;
	}

	protected int hash2(Key key) {
		return ((17 * key.hashCode()) & 0x7fffffff) % M2;
	}

	public void put(Key key, Value val) {
		// 限定散列表的使用率小于1/2
		if (N1 >= M / 2 || N2 >= M / 2) 
			resize(2 * M);
		int hashIndex = hash1(key);
		boolean isTable1 = true;
		Key targetKey = keys1[hashIndex];
		while(targetKey!=null) {
			if(isTable1) {
				if(targetKey.equals(key)) {
					vals1[hashIndex] = val;
					return;
				}
				exch(key,val,keys1[hashIndex],vals1[hashIndex]);
				hashIndex = hash2(key);
				isTable1 = false;
				targetKey = keys2[hashIndex];
			}
			else {
				if(targetKey.equals(key)) {
					vals2[hashIndex] = val;
					return;
				}
				exch(key,val,keys2[hashIndex],vals2[hashIndex]);
				hashIndex = hash1(key);
				isTable1 = true;
				targetKey = keys1[hashIndex];
			}
		}
		if(isTable1) {
			keys1[hashIndex] = key;
			vals1[hashIndex] = val;
			N1++;
		}
		else {
			keys2[hashIndex] = key;
			vals2[hashIndex] = val;
			N2++;
		}
	}

	public Value get(Key key) {
		if(keys1[hash1(key)].equals(key))
			return vals1[hash1(key)];
		if(keys2[hash2(key)].equals(key))
			return vals2[hash2(key)];
		return null;
	}

	public void delete(Key key) {
		if (!contains(key))
			return;
		int hashIndex = hash1(key);
		if(keys1[hashIndex].equals(key)) {
			keys1[hashIndex] = null;
			vals1[hashIndex] = null;
			N1--;
		}
		hashIndex = hash2(key);
		if(keys2[hashIndex].equals(key)) {
			keys2[hashIndex] = null;
			vals2[hashIndex] = null;
			N2--;
		}
		
		// 限定散列表的使用率大于1/8
		if (N1 <= M / 8 && N2 <= M / 8)
			resize(M / 2);
	}
	
	protected void resize(int cap) {
		Exercise31_Cuckoo<Key, Value> t;
		t = new Exercise31_Cuckoo<Key, Value>(cap);
		for (int i = 0; i < M; i++)
			if (keys[i] != null)
				t.put(keys[i], vals[i]);
		keys = t.keys;
		vals = t.vals;
		M = t.M;
	}

	public boolean contains(Key key) {
		for (int i = hash(key); keys[i] != null; i = (i + 1) % M)
			if (keys[i].equals(key))
				return true;
		return false;
	}
	
	public static void main(String[] agrs) {
		Exercise31_Cuckoo<String, Integer> lphST;
		lphST = new Exercise31_Cuckoo<String, Integer>(4);
		String s = "EASYQUESTION";
		for (int i = 0; i < s.length(); i++)
			lphST.put((String) s.substring(i, i + 1), i);
	}
	*/
}
