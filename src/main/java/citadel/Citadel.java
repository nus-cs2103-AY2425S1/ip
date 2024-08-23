package citadel;

import java.io.IOException;
import java.time.format.DateTimeParseException;

import citadel.commands.Commands;
import citadel.commands.DeleteTask;
import citadel.commands.HandleDeadline;
import citadel.commands.HandleEvent;
import citadel.commands.HandleTodo;
import citadel.commands.MarkTask;
import citadel.commands.UnmarkTask;

import citadel.Task.TaskList;

import citadel.exception.CitadelException;
import citadel.exception.CitadelInvalidCommandException;
import citadel.ui.TextUI;

public class Citadel {
    public static TaskList items = new TaskList();
    public static Storage db = new Storage("data/citadel");

    public static TextUI ui = new TextUI();

    public static void main(String[] args) throws IOException {

        String input;
        ui.printStart();

        items = db.getTasks();
        ui.printTasks(items);

        while (true) {
            try {
                input = ui.nextLine();
                Commands command = Parser.parseCommand(input);

                switch (command) {
                case BYE:
                    break;

                case LIST:
                    ui.printTasks(items);
                    continue;

                case MARK:
                    new MarkTask(input, items).run();
                    break;

                case UNMARK:
                    new UnmarkTask(input, items).run();
                    break;

                case DELETE:
                    new DeleteTask(input, items).run();
                    break;

                case DEADLINE:
                    new HandleDeadline(input, items).run();
                    break;

                case EVENT:
                    new HandleEvent(input, items).run();
                    break;

                case TODO:
                    new HandleTodo(input, items).run();
                    break;

                default:
                    throw new CitadelInvalidCommandException();
                }

                if (command.equals(Commands.BYE)) {
                    break;
                }
            } catch (CitadelException e) {
                ui.printCitadelException(e);
            } catch (DateTimeParseException e) {
                ui.printDateTimeParseException();
            } catch (Exception e) {
                ui.printException(e);
            }
        }


        ui.printGoodbye();
        db.saveData(items);
    }
}







