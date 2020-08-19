public interface IState {
    String formatDayOfWeek(String dayOfWeek);
    String formatDate(String date);
    String getDoIHaveWorkTodayString();

    void soundAlarm();
}
