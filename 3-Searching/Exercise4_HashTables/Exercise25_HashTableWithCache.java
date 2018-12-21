package Exercise4_HashTables;

import edu.princeton.cs.algs4.Date;

/**
 * Exercise 3.4.25
 * @author baozzz1 
 * 2018年12月20日
 */
public class Exercise25_HashTableWithCache {
	private static int hashTableSize = 997;
	private class Transaction {
		private final String who;
		private final Date when;
		private final double amount;
		private int hash = -1;

		public Transaction(String who, Date when, double amount) {
			this.who = who;
			this.when = when;
			this.amount = amount;
			hash = -1;
		}

		public int hashCode() {
			if (hash != -1)
				return hash;
			int hash = 17;
			hash = (31 * hash + who.hashCode()) % hashTableSize;
			hash = (31 * hash + when.hashCode()) % hashTableSize;
			hash = (31 * hash + ((Double) amount).hashCode()) % hashTableSize;
			this.hash = hash;
			return hash;
		}
	}
}
