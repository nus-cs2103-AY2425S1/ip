import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {

    public static Task convertStringToTask(String line) {
        Task task;
        String[] parts = line.split(" \\| ");
        String taskType = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];
        if (taskType.equals("T")) {
            Todo todo = new Todo(description);
            if (isDone) {
                todo.markAsDone();
            }
            task = todo;
        } else if (taskType.equals("D")) {
            String[] removeT = parts[3].split("T", 2);
            String timeToConvert = removeT[0] + " " + removeT[1];
            Deadline deadline = new Deadline(description, convertStringToDate(timeToConvert));
            if (isDone) {
                deadline.markAsDone();
            }
            task = deadline;
        } else {
            String[] removeTFrom = parts[3].split("T", 2);
            String timeToConvertFrom = removeTFrom[0] + " " + removeTFrom[1];
            String[] removeTTo = parts[4].split("T", 2);
            String timeToConvertTo = removeTTo[0] + " " + removeTTo[1];
            Event event = new Event(description, convertStringToDate(timeToConvertFrom),
                    convertStringToDate(timeToConvertTo));
            if (isDone) {
                event.markAsDone();
            }
            task = event;
        }
        return task;
    }

    public static LocalDateTime convertStringToDate(String dateTimeString) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            return LocalDateTime.parse(dateTimeString, formatter);
        } catch (DateTimeParseException e) {
            System.err.println("Invalid date-time format. " +
                    "Please provide the date-time in 'yyyy-MM-dd HH:mm' format. " +
                    "Time should be in 24 hours format.");
        }
        return null;
    }

    private static Todo parseTodoCommand(String command) {
        try {
            String description = command.substring(4).trim(); // Extract description
            if (description.isEmpty()) {
                throw new IllegalArgumentException("Description for 'todo' cannot be empty.");
            }
            return new Todo(description);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    private static Deadline parseDeadlineCommand(String command) {
        try {
            String[] parts = command.split("/by");
            if (parts.length != 2) {
                throw new IllegalArgumentException(
                        "Deadline command must contain '/by' followed by a date-time.");
            }

            String description = parts[0].substring(8).trim();
            if (description.isEmpty()) {
                throw new IllegalArgumentException("Description for 'deadline' cannot be empty.");
            }

            String dateTimeString = parts[1].trim();
            LocalDateTime by = convertStringToDate(dateTimeString);
            if (by == null) {
                return null;
            }
            return new Deadline(description, by);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    private static Event parseEventCommand(String command) {
        try {
            String[] partsFrom = command.split("/from");
            if (partsFrom.length != 2) {
                throw new IllegalArgumentException(
                        "Event command must contain '/from' followed by a start date-time.");
            }

            String[] partsTo = partsFrom[1].split("/to");
            if (partsTo.length != 2) {
                throw new IllegalArgumentException(
                        "Event command must contain '/to' followed by an end date-time.");
            }

            String description = partsFrom[0].substring(5).trim();
            if (description.isEmpty()) {
                throw new IllegalArgumentException("Description for 'event' cannot be empty.");
            }

            String fromDateTimeString = partsTo[0].trim(); // Extract start date-time
            String toDateTimeString = partsTo[1].trim(); // Extract end date-time

            LocalDateTime from = convertStringToDate(fromDateTimeString);
            LocalDateTime to = convertStringToDate(toDateTimeString);
            if (from == null || to == null) {
                return null;
            }
            return new Event(description, from, to);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    private static int parseIndexCommand(String[] getInstr) {
        try {
            int index;
            if (getInstr.length <= 1) {
                index = -1;
            } else {
                index = Integer.parseInt(getInstr[1]);
            }
            if (index - 1 < 0 || index - 1 >= TaskList.getSize()) {
                throw new InvalidIndexException("Invalid index provided, please provide proper index.");
            }
            return index;
        } catch (InvalidIndexException e) {
            System.out.println(e.toString());
        }
        return -1;
    }

    public static void parseCommand(String command) {
        String[] getInstr = command.split(" ", 2);
        String instr = getInstr[0];
        int index;
        switch (instr) {
            case "mark":
                index = parseIndexCommand(getInstr);
                if (index != -1) {
                    TaskList.mark(index);
                }
                break;
            case "unmark":
                index = parseIndexCommand(getInstr);
                if (index != -1) {
                    TaskList.unmark(index);
                }
                break;
            case "delete":
                index = parseIndexCommand(getInstr);
                if (index != -1) {
                    TaskList.delete(index);
                }
                break;
            case "list":
                Ui.printList();
                break;
            case "todo":
                Task todo = parseTodoCommand(command);
                if (todo != null) {
                    TaskList.add(todo);
                }
                break;
            case "deadline":
                Task deadline = parseDeadlineCommand(command);
                if (deadline != null) {
                    TaskList.add(deadline);
                }
                break;
            case "event":
                Task event = parseEventCommand(command);
                if (event != null) {
                    TaskList.add(event);
                }
                break;
            default:
                try {
                    throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
        }
    }
}
