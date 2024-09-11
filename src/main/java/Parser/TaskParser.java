package Parser;

import socchat.SocchatException;
import socchat.task.Task;
import socchat.task.deadline.Deadline;
import socchat.task.event.Event;
import socchat.task.todo.Todo;

import java.time.LocalDateTime;

public class TaskParser {

    public static Task todoParser(String todoDetails) throws SocchatException {
        try {
            // todo format is [description, tag]
            String taskPattern = "-tag";
            String[] strToken = todoDetails.split(taskPattern);
            String desc = strToken[0].trim();
            checkEmptyDescription(desc);

            if (todoDetails.contains("-tag")) {
                String tagName = getTag(strToken, 1);
                return new Todo(desc, tagName);
            }
            return new Todo(desc);
        } catch (IndexOutOfBoundsException e) {
            throw new SocchatException("Invalid Todo format: Description is empty");
        } catch (SocchatException e) {
            throw e;
        }
    }

    public static Task deadlineParser(String deadlineDetails) throws SocchatException {
        try {
            // deadline format is [description, by, tag]
            String taskPattern = "/by |-tag";
            String[] strToken = deadlineDetails.split(taskPattern);
            String desc = strToken[0].trim();
            checkEmptyDescription(desc);
            String by = strToken[1].trim();
            LocalDateTime formattedBy = Parser.parseDate(by);

            if (deadlineDetails.contains("-tag")) {
                String tagName = getTag(strToken, 2);
                return new Deadline(desc, formattedBy, tagName);
            }

            return new Deadline(desc, formattedBy);
        } catch (IndexOutOfBoundsException e) {
            throw new SocchatException("Invalid Deadline format: deadline <description> /by <deadline>");
        } catch (SocchatException e) {
            throw e;
        }
    }

    public static Task eventParser(String eventDetails) throws SocchatException {
        try {
            // event format is [description, from, to, tag]
            String taskPattern = " /from |/to |-tag";
            String[] strToken = eventDetails.split(taskPattern);
            String desc = strToken[0].trim();
            checkEmptyDescription(desc);
            String from  = strToken[1].trim();
            String to = strToken[2].trim();
            LocalDateTime formattedFrom = Parser.parseDate(from);
            LocalDateTime formattedTo = Parser.parseDate(to);

            if (eventDetails.contains("-tag")) {
                String tagName = getTag(strToken, 3);
                return new Event(desc, formattedFrom, formattedTo, tagName);
            }

            return new Event(desc, formattedFrom, formattedTo);
        } catch (IndexOutOfBoundsException e) {
            throw new SocchatException("Invalid Event format: event <description> /from <startTime> /to <endTime>");
        } catch (SocchatException e) {
            throw e;
        }


    }

    public static void checkEmptyDescription(String input) throws SocchatException {
        if (input.isEmpty()) {
            throw new SocchatException("Description cannot be empty. Meow~");
        }
    }

    public static String getTag(String[] strToken, int strTokenIndex) throws SocchatException {
        try {
            return strToken[strTokenIndex].trim();
        } catch (IndexOutOfBoundsException e) {
            throw new SocchatException("Tag Name cannot be empty. Meow~");
        }
    }
}
