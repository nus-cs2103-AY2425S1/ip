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
                if (command.equals(Commands.BYE)) {
                    break;
                }
                if (command.equals(Commands.LIST)) {
                    ui.printTasks(items);
                } else if (command.equals(Commands.MARK)) {
                    new MarkTask(input, items).run();
                } else if (command.equals(Commands.UNMARK)) {
                    new UnmarkTask(input, items).run();
                } else if (command.equals(Commands.DELETE)) {
                    new DeleteTask(input, items).run();
                } else {
                    if (command.equals(Commands.DEADLINE)) {
                        new HandleDeadline(input, items).run();
                    } else if (command.equals(Commands.EVENT)) {
                        new HandleEvent(input, items).run();
                    } else if (command.equals(Commands.TODO)) {
                        new HandleTodo(input, items).run();
                    } else {
                        throw new CitadelInvalidCommandException();
                    }
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







