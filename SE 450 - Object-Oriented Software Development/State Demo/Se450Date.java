import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Se450Date {
	private Calendar cal;
	private IState currentState;
	private final IState weekendState = new WeekendState();
	private final IState weekdayState = new WeekdayState();

	public Se450Date() {
		cal = Calendar.getInstance();
		updateState();
	}
	
	public void addDate(){
		cal.add(Calendar.DATE, 1);
		updateState();
	}

	private void updateState(){
		currentState = cal.get(Calendar.DAY_OF_WEEK) == 1 || cal.get(Calendar.DAY_OF_WEEK) == 7
				? weekendState
				: weekdayState;
	}

	public void printDayOfWeek() {
		System.out.println(currentState.formatDayOfWeek(new SimpleDateFormat("EEEE").format(cal.getTime())));
	}

	public void printDate() {
		System.out.println(currentState.formatDate(cal.getTime().toString()));
	}

	public void doIHaveWorkToday() {
		System.out.println(currentState.getDoIHaveWorkTodayString());
	}
	public void soundAlarm(){
		currentState.soundAlarm();
	}
}
/*
 get Day of week:
 cal.get(Calendar.DAY_OF_WEEK)
 - returns 1-based day of week (1 and 7 are weekends)
 
 print out Day of week
new SimpleDateFormat("EEEE").format(cal.getTime())
 - returns the day of the week as a String 

*/