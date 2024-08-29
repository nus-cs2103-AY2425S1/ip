import java.time.LocalDate;
import java.time.LocalTime;

public class Parser {
    public static LocalDate convertDate(String date) {
        String[] splitDate = date.split("/");
        int[] splitDateint = new int[3];
        for (int i = 0; i < 3; i++) {
            splitDateint[i] = Integer.parseInt(splitDate[i]);
        }
        LocalDate convertedDate = LocalDate.of(splitDateint[2], splitDateint[1], splitDateint[0]);
        return convertedDate;
    }

    public static LocalTime convertTime(String time) {
        int hour = Integer.parseInt(time.substring(0,2));
        int minute = Integer.parseInt(time.substring(2));
        return LocalTime.of(hour, minute);
    }

    public static String extractFirstWord(String sentence) {
        int spaceIndex = sentence.indexOf(" ");
        return spaceIndex == -1 ? sentence : sentence.substring(0, spaceIndex);
    }

    public static int extractEndNumber(String sentence) {
        int spaceIndex = sentence.indexOf(" ");
        return Integer.parseInt(sentence.substring(spaceIndex + 1).trim());
    }

    public static String extractDeadlineTime(String sentence) {
        int index = sentence.indexOf("/by") + 4;
        return sentence.substring(index).trim();
    }

    public static String[] extractEventTimes(String sentence) {
        int fromIndex = sentence.indexOf("/from") + 6;
        int toIndex = sentence.indexOf("/to");
        int toIndexPlus4 = toIndex + 4;
        return new String[]{
                sentence.substring(fromIndex, toIndex - 1).trim(),
                sentence.substring(toIndexPlus4).trim()
        };
    }

    private static String extractEventName(String input) {
        return input.substring(6, input.indexOf("/from") - 1).trim();
    }

    private static String extractDeadlineName(String input) {
        return input.substring(9, input.indexOf("/by") - 1).trim();
    }

}
