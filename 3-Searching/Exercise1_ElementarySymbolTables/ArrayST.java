package Exercise1_ElementarySymbolTables;
/**
 * Exercise 3.1.2
 * @author baozzz1
 * 2018年11月26日
 */
public class ArrayST<Key,Value> {
	private Key[] keys;
	private Value[] vals;
	private int N;
	
	public ArrayST(int capacity) {
		keys = (Key[]) new Comparable[capacity];
		vals = (Value[]) new Comparable[capacity];
	}
	
	public Value get(Key key) {
		for(int i=0;i<N;i++)
			if(key.equals(keys[i]))
				return vals[i];
		return null;
	}
	
	public void put(Key key, Value val) {
		for(int i=0;i<N;i++)
			if(key.equals(keys[i])) {
				vals[i] = val;
				return;
			}
		keys[N] = key;
		vals[N] = val;
		N++;
	}
	
	public void delete(Key key) {
		for(int i= 0;i<N;i++)
			if(key.equals(keys[i])) {
				for (int j = i; j < N - 1; j++) {
					keys[j] = keys[j + 1];
					vals[j] = vals[j + 1];
				}
				keys[N - 1] = null;
				vals[N - 1] = null;
				N--;
				return;
			}
	}
	
	public boolean contains(Key key) {
		for(int i=0;i<N;i++)
			if(key.equals(keys[i]))
				return true;
		return false;
	}
	
	public boolean isEmpty() {
		return size()==0;
	}
	
	public int size() {
		return N;
	}
}
