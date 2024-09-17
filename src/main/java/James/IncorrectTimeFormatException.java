package james;

public class IncorrectTimeFormatException extends JamesException{
    public IncorrectTimeFormatException() {
        super("Oops! looks like you entered an incorrect date/time format. Please enter it in the following format\n" +
                "yyyy-mm-ddThh:MM:ss\n" +
                "where yyyy is the year, mm is the month, dd is the day," +
                " hh is the hour, MM is the minutes and ss is the seconds");
    }
}
