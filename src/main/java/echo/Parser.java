package echo;

import java.util.Objects;

public class Parser {
    public static String[] parseCommand(String input) {
        return input.split(" ", 2);
    }

    public static String[] parseDeadline(String part) {
        return part.split(" /by ");
    }

    public static String[] parseEventTime(String part) throws EchoException {
        //return part.split(" /from ")[1].split(" /to ");
        String[] details = part.split(" /from ", 2);
        if (details.length == 2) {
            String[] times = details[1].split(" /to ", 2);
            if (times.length == 2) {
                return times;
            }else {
                throw new EchoException("Please specify the task  deadline.");
            }
        } else {
            throw new EchoException("Please specify the task description and deadline.");

        }

    }

    public static String parseEventDes(String part) {
        return part.split(" /from ")[0];
    }

    public static boolean isPresent(String des, String toFind) {
        String[] parts = des.split(" ");
        for(int i = 0; i < parts.length; i++){
            if(Objects.equals(parts[i].toLowerCase(), toFind)){
                return true;
            }
        }
        return false;
    }
}
