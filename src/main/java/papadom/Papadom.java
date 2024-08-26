package papadom;

import papadom.Exceptions.*;
import papadom.Storage.Storage;
import papadom.Storage.TaskList;
import papadom.commands.*;

import java.util.Scanner;

public class Papadom {
    enum CommandType {
        LIST, BYE, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, FIND, UNKNOWN;
        public static CommandType fromString(String command) {
            return switch (command.toLowerCase()) {
                case "list" -> LIST;
                case "bye" -> BYE;
                case "mark" -> MARK;
                case "unmark" -> UNMARK;
                case "todo" -> TODO;
                case "deadline" -> DEADLINE;
                case "event" -> EVENT;
                case "delete" -> DELETE;
                case "find" -> FIND;
                default -> UNKNOWN;
            };
        }
    }
    private static final Ui ui = new Ui();
    private static final Storage storage = new Storage("./src/main/java/papadom/Storage/tasks.txt");
    private static final Parser parser = new Parser();
    private static final TaskList taskList = new TaskList(storage);
    private static final Scanner scanner = new Scanner(System.in);
    private static void run() {
        ui.welcomeMessage();
        storage.createFileIfNotPresent();

        while (true) {
            Command command = null;
            try {
                String text = scanner.nextLine();
                String commandText = text.split(" ")[0];
                CommandType commandType = CommandType.fromString(commandText);
                switch (commandType) {
                    case LIST:
                        command = new ListCommand();
                        break;
                    case BYE:
                        command = new ExitCommand();
                        break;
                    case MARK:
                        command = new MarkCommand(text);
                        break;
                    case UNMARK:
                        command = new UnmarkCommand(text);
                        break;
                    case TODO:
                        command = new AddTodoCommand(text);
                        break;
                    case DEADLINE:
                        command = new AddDeadlineCommand(text);
                        break;
                    case EVENT:
                        command = new AddEventCommand(text);
                        break;
                    case DELETE:
                        command = new DeleteEventCommand(text);
                        break;
                    case FIND:
                        command = new FindEventCommand(text);
                        break;
                    default:
                        throw new UnknownCommandException();
                }
                command.execute(taskList, ui, storage);
                if (command instanceof ExitCommand) {
                    return;
                }
            } catch (Exception e) {
                ui.output(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        Papadom.run();
    }
}
