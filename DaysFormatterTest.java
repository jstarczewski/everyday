public class DaysFormatterTest {

	public static String format(String day) {
		String formatted = day.substring(day.indexOf("/")+2, day.length());
		return formatted;
	}

	public static void main(String[] args) {
		String toFormat = "Day 1 / 30.08.2018";
		System.out.println(format(toFormat));
	}

}
