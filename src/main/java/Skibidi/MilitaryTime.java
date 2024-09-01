package Skibidi;

public class MilitaryTime {
    protected String time;
    public MilitaryTime(String time) throws SkibidiException {
        if (!isValidTime(time)) {
            throw new SkibidiException("Invalid time format. Please enter time in HHmm format.");
        }
        this.time = time;
    }

    public boolean isValidTime(String time) {
        if (time.length() != 4) {
            return false;
        }
        int hour = Integer.parseInt(time.substring(0, 2));
        int minute = Integer.parseInt(time.substring(2));
        return hour >= 0 && hour < 24 && minute >= 0 && minute < 60;
    }
    public String convertMilitaryTimeToStandardTime(String time) {

        int hour = Integer.parseInt(time.substring(0, 2));
        int minute = Integer.parseInt(time.substring(2));
        String period = "AM";
        if (hour >= 12) {
            period = "PM";
            if (hour > 12) {
                hour -= 12;
            }
        }
        return String.format("%d:%02d %s", hour, minute, period);
    }
    @Override
    public String toString() {
        return convertMilitaryTimeToStandardTime(time);
    }
}
