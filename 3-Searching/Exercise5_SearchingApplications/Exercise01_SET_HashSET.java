package Exercise5_SearchingApplications;

import Lesson3_BanlancedSearchTrees.RedBlackBST;

public class Exercise01_SET_HashSET<Key, Value> {
	private class SET<Key extends Comparable<Key>>{
		private RedBlackBST<Key,Boolean> set;
		
		SET(){ set = new RedBlackBST(); }
		
		/*public boolean isEmpty() {
			return set.isEmpty();
		}*/
		
		public int size() {
			return set.size();
		}
		
		/*public boolean contains(Key key) {
			return set.contains(key);
		}*/
		
		public void add(Key key) {
            if (key == null) throw new IllegalArgumentException("Key cannot be null");
			set.put(key, false);
		}
		
		public void delete(Key key) {
            if (key == null) throw new IllegalArgumentException("Key cannot be null");
			set.delete(key);
		}
		
		public Key min() {
			return set.min();
		}
		
		public Key max() {
			return set.max();
		}
		
		public Key floor(Key key) {
			return set.floor(key);
		}
		
		public Key ceiling(Key key) {
			return set.ceiling(key);
		}
		
		public Key select(int index) {
			return set.select(index);
		}
		
		public int rank(Key key) {
			return set.rank(key);
		}
		
		public void deleteMin() {
			set.deleteMin();
		}
		
		public void deleteMax() {
			set.deleteMax();
		}
		
		public Iterable<Key> keys(){
			return set.keys();
		}
		
		public Iterable<Key> keys(Key lo, Key hi){
			return set.keys(lo,hi);
		}
		
		public String toString() {
			if(isEmpty()) return "{ }";
			StringBuilder temp = new StringBuilder("{");
			boolean isFirstKey = true;
			for(Key key: keys()) {
				if(isFirstKey)
					isFirstKey=false;
				else
					temp.append(",");
				temp.append(" ").append(key);
			}
			temp.append(" }");
			return temp.toString();
		}
	}
}
