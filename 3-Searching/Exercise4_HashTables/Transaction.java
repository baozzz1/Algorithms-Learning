package Exercise4_HashTables;

import edu.princeton.cs.algs4.Date;

/**
 * Exercise 3.4.25
 * @author baozzz1 
 * 2018年12月20日
 */
public class Transaction {
	private final String who;
	private final Date when;
	private final double amount;
	private int hash = -1;

	public Transaction(String who, Date when, double amount) {
		this.who = who;
		this.when = when;
		this.amount = amount;
	}

	public int hashCode() {
		if (hash != -1)
			return hash;
		int hash = 17;
		hash = 31 * hash + who.hashCode();
		hash = 31 * hash + when.hashCode();
		Double a = amount;
		hash = 31 * hash + a.hashCode();
		this.hash = hash;
		return hash;
	}
}
