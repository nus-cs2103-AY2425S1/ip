import java.io.IOException;
import java.time.format.DateTimeParseException;

public class Parser {

    public static void parseCommand(String userInput) throws KafkaException, IOException, DateTimeParseException {
        String[] splitInput = userInput.trim().split(" ", 2);

        String command = splitInput[0].toLowerCase();
        String arguments = splitInput.length > 1 ? splitInput[1] : "";

        switch (command) {
            case "list":
                TaskList.getList();
                break;
            case "mark":
                int taskNumber = Integer.parseInt(arguments);
                kafka.mark(taskNumber);
                KafkaTextWriter.writeToFile(filePath, kafka.tasks);
                break;
            case "unmark":
                int taskNumber = Integer.parseInt(arguments);
                kafka.unmark(taskNumber);
                KafkaTextWriter.writeToFile(filePath, kafka.tasks);
                break;
            case "delete":
                int taskNumber = Integer.parseInt(arguments);
                kafka.delete(taskNumber);
                KafkaTextWriter.writeToFile(filePath, kafka.tasks);
                break;
            case "todo":
                if (arguments.isEmpty()) {
                    throw new KafkaException("The description of a todo cannot be empty.");
                }
                Task todo = new Todo(arguments, false);
                kafka.addTask(todo);
                KafkaTextWriter.writeToFile(filePath, kafka.tasks);
                break;
            case "deadline":
                if (arguments.isEmpty()) {
                    throw new KafkaException("The description of a deadline cannot be empty.");
                }
                String[] deadlineParts = arguments.split("/by ");
                if (deadlineParts.length < 2) {
                    throw new KafkaException("A deadline task must have a valid /by time.");
                }
                LocalDateTime by = LocalDateTimeConverter.getLocalDateTime(deadlineParts[1]);
                Task deadline = new Deadline(deadlineParts[0], by, false);
                kafka.addTask(deadline);
                KafkaTextWriter.writeToFile(filePath, kafka.tasks);
                break;
            case "event":
                if (arguments.isEmpty()) {
                    throw new KafkaException("The description of an event cannot be empty.");
                }
                String[] eventParts = arguments.split("/from |/to ");
                if (eventParts.length < 3) {
                    throw new KafkaException("An event must have a valid /from and /to time.");
                }
                Task event = new Event(eventParts[0], eventParts[1], eventParts[2], false);
                kafka.addTask(event);
                KafkaTextWriter.writeToFile(filePath, kafka.tasks);
                break;
            default:
                throw new KafkaException("Unknown command: " + command);
        }
    }
}
