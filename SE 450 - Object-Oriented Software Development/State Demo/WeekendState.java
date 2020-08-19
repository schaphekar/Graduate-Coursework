public class WeekendState implements IState {
    @Override
    public String formatDayOfWeek(String dayOfWeek) {
        return dayOfWeek.toUpperCase();
    }

    @Override
    public String formatDate(String date) {
        return "->" + date;
    }

    @Override
    public String getDoIHaveWorkTodayString() {
        return "No";
    }

    @Override
    public void soundAlarm() {

    }
}
