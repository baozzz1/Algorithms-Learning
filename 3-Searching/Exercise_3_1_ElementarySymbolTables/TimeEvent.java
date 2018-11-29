package Exercise_3_1_ElementarySymbolTables;

import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.StdIn;

/**
 * Exercise 3.1.4
 * @author baozzz1
 * 2018年11月26日
 */

public class TimeEvent {
	private class Time implements Comparable<Time>{
		int hour;
		int minute;
		int second;
		public Time() {
		}
		public Time(String time) {
			String[] times = time.split(":");
			hour = Integer.parseInt(times[0]);
			minute = Integer.parseInt(times[1]);
			second = Integer.parseInt(times[2]);
		}
		
		public int compareTo(Time that) {
			if(this.hour<that.hour)
				return -1;
			else if(this.hour>that.hour)
				return 1;
			else {
				if(this.minute<that.minute)
					return -1;
				else if(this.minute>that.minute)
					return 1;
				else {
					if(this.second<that.second)
						return -1;
					else if(this.second>that.second)
						return 1;
					else
						return 0;
				}
			}
		}
	}
	public void main(String args[]) {
		ST<Time,String> st = null;
		Time time;
		while(StdIn.hasNextLine()) {
			String[] oneEvent = StdIn.readAllLines();
			time = new Time(oneEvent[0]);
			st.put(time, oneEvent[1]);
		}
	}
}
