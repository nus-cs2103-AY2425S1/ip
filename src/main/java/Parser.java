import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Objects;
import java.util.regex.Pattern;

public class Parser {

    public static CommandType parseInput(String input) throws MomoException {

        // Check if input is empty
        if (Objects.equals(input, "")) {
            throw new MomoException("I dare you to send an empty command again...");
        }

        // Check if input is bye
        if (Objects.equals(input, "bye")) {
            return CommandType.BYE;
        }

        // Check if input is list
        if (Objects.equals(input, "list")) {
            return CommandType.LIST;
        }

        // Check if input is delete
        if (input.startsWith("delete")) {
            if (Pattern.matches("delete\\s\\d+", input)) {
                int index = Integer.parseInt(input.split(" ")[1]) - 1;
                return CommandType.DELETE;
            }
            throw new MomoException("You're supposed to indicate the task number you want to delete!");
        }

        // Checking for mark and unmark input
        if (input.startsWith("mark") || input.startsWith("unmark")) {
            // Check if input has format "mark x" or "unmark x"
            if (Pattern.matches("mark\\s\\d+", input)) {
                int index = Integer.parseInt(input.split(" ")[1]) - 1;
                return CommandType.MARK;
            }

            else if (Pattern.matches("unmark\\s\\d+", input)) {
                int index = Integer.parseInt(input.split(" ")[1]) - 1;
                return CommandType.UNMARK;
            }

            throw new MomoException("Mark my words - You must enter a number x next to mark or unmark in the exact format `mark x`.");
        }

        // Checking for proper task input
        String taskType = input.split(" ")[0];

        switch (taskType) {
            case "todo" -> {
                if (input.split(" ").length < 2) {
                    throw new MomoException("Where's the description of your task??");
                }
                return CommandType.TODO;
            }
            case "deadline" -> {

                try {
                    String desc = input.split(" ", 2)[1];
                    String task = desc.split("/", 2)[0];
                    String by = desc.split("/by", 2)[1];
                    return CommandType.DEADLINE;
                }
                catch (Exception e) {
                    throw new MomoException("You likely did not format your deadline properly\nIt should be in the format `deadline task /by date`");
                }
            }
            case "event" -> {
                try {
                    String desc = input.split(" ",2)[1];
                    String task =  desc.split("/",2)[0];
                    String from = desc.split("/from")[1].split("/to ")[0];
                    String to = desc.split("/to")[1];
                    return CommandType.EVENT;
                }
                catch (Exception e) {
                    throw new MomoException("You likely did not format your event properly\nIt should be in the format `event task /from date time /to time`");
                }
            }
            default -> {
                throw new MomoException("You did not properly specify the type of task (todo/deadline/event) or command (bye/list)");
            }
        }
    }


    public static LocalDate parseDate(String input) throws DateTimeException {
        try {
            return LocalDate.parse(input);
        } catch (DateTimeException e) {
            System.out.println("Your storage file seems to be corrupted. Consider deleting it or I might delete your existence.");
            return null;
        }
    }

}
