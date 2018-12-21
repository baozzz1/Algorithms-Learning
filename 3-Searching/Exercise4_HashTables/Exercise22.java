package Exercise4_HashTables;

/**
 * Exercise 3.4.22
 * @author baozzz1
 * 2018年12月21日
 */
public class Exercise22 {
	private static final int HASH_TABLE_SIZE = 997;

	private class Point2D {
		private double x, y;

		Point2D(double x, double y) {
			this.x = x;
			this.y = y;
		}

		public int hashCode() {
			int hashX = ((Double) x).hashCode();
			int hashY = ((Double) y).hashCode();
			return 31 * hashX + hashY;
		}
	}

	private class Interval {
		private double min, max;

		Interval(double min, double max) {
			this.min = min;
			this.max = max;
		}

		public int hashCode() {
			int hashMin = ((Double) min).hashCode();
			int hashMax = ((Double) max).hashCode();
			return 31 * hashMin + hashMax;
		}
	}

	private class Interval2D {
		private Interval Interval1, Interval2;

		Interval2D(Interval Interval1, Interval Interval2) {
			this.Interval1 = Interval1;
			this.Interval2 = Interval2;
		}

		public int hashCode() {
			int hash1 = Interval1.hashCode();
			int hash2 = Interval2.hashCode();
			return 31 * hash1 + hash2;
		}
	}

	private class Date {
		private final int day, month, year;

		Date(int day, int month, int year) {
			this.day = day;
			this.month = month;
			this.year = year;
		}

		public int hashCode() {
			int hash = 17;
			hash = 31 * hash + day;
			hash = 31 * hash + month;
			hash = 31 * hash + year;
			return hash;
		}
	}
}
