package Exercise1_ElementarySymbolTables;

import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * Exercise 3.1.1
 * @author baozzz1
 * 2018年11月26日
 */
public class readGrade {
	public static void main(String[] args) {
		ST<String, Double> st = new ST<String, Double>();
		st.put("A+", 4.33);
		st.put("A", 4.00);
		st.put("A-", 3.67);

		st.put("B+", 3.33);
		st.put("B", 3.00);
		st.put("B-", 2.67);

		st.put("C+", 2.33);
		st.put("C", 2.00);
		st.put("C-", 1.67);

		st.put("D", 1.00);
		st.put("F", 0.00);
		
		double sum=0.0;
		int n = 0;
		while(!StdIn.isEmpty()) {
			String key = StdIn.readString();
			sum+=st.get(key);
			n++;
		}
		StdOut.printf("The GPA is %.2f\n" , sum/n);
	}
}
