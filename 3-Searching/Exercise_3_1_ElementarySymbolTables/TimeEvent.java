package Exercise_3_1_ElementarySymbolTables;

import edu.princeton.cs.algs4.ST;

/**
 * Exercise 3.1.4
 * @author baozzz1
 * 2018年11月26日
 */
public class TimeEvent<Key,Value> {
	
	private class Time{
		int hour;
		int minute;
		int second;
		public Time(String time) {
			String[] times = time.split(":");
			hour = Integer.parseInt(times[0]);
			minute = Integer.parseInt(times[1]);
			second = Integer.parseInt(times[2]);
		}
	}
	
	public static void main(String[] args) {
		private ST<Time,String> st;
		
	}
}
