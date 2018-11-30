package Exercise_3_1_ElementarySymbolTables;

import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * Exercise 3.1.4<br>
 * Input: <br>
 * 09:00:00 Chicago<br>
 * 09:30:00 Phoenix<br>
 * 10:07:28 Seattle<br>
 * ...
 * @author baozzz1 
 * 2018年11月26日
 */

public class TimeEvent {
	private static class Time implements Comparable<Time> {
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
			if (this.hour < that.hour)
				return -1;
			else if (this.hour > that.hour)
				return 1;
			else {
				if (this.minute < that.minute)
					return -1;
				else if (this.minute > that.minute)
					return 1;
				else {
					if (this.second < that.second)
						return -1;
					else if (this.second > that.second)
						return 1;
					else
						return 0;
				}
			}
		}
	}

	public static void main(String[] args) {
		ST<Time, String> st = new ST<Time, String>();
		Time time = new Time();
		String[] timeAndEvent;
		while (StdIn.hasNextLine()) {
			timeAndEvent = StdIn.readLine().split("\\s+");
			if (timeAndEvent.length == 0)
				break;
			time = new Time(timeAndEvent[0]);
			st.put(time, timeAndEvent[1]);
		}
		for (Time s : st.keys())
			StdOut.println(st.get(s));
	}
}
