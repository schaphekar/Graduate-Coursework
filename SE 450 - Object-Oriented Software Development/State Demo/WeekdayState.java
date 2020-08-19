public class WeekdayState implements IState {
    @Override
    public String formatDayOfWeek(String dayOfWeek) {
        return dayOfWeek.toLowerCase();
    }

    @Override
    public String formatDate(String date) {
        return date;
    }

    @Override
    public String getDoIHaveWorkTodayString() {
        return "Yes";
    }

    @Override
    public void soundAlarm() {
        System.out.println("Alarm!");
    }
}
