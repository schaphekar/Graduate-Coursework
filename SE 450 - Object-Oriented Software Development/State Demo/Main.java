
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Se450Date date = new Se450Date();
		
		for(int i = 0; i < 7; i++){
			// Call methods on date
			date.printDayOfWeek();
			date.printDate();
			date.doIHaveWorkToday();
			date.soundAlarm();

			date.addDate();
		}
	}

}
