import java.io.IOException;
import java.time.format.DateTimeParseException;

import Commands.Commands;
import Commands.deleteTask;
import Commands.handleDeadline;
import Commands.handleEvent;
import Commands.handleTodo;
import Commands.markTask;
import Commands.unmarkTask;

import Task.TaskList;

import exception.CitadelException;
import exception.CitadelInvalidCommandException;
import ui.TextUI;

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
                String command = input.split(" ")[0].toUpperCase();
                if (Commands.valueOf(command).equals(Commands.BYE)) {
                    break;
                }

                if (Commands.valueOf(command).equals(Commands.LIST)) {
                    ui.printTasks(items);
                } else if (Commands.valueOf(command).equals(Commands.MARK)) {
                    new markTask(input, items).run();
                } else if (Commands.valueOf(command).equals(Commands.UNMARK)) {
                    new unmarkTask(input, items).run();
                } else if (Commands.valueOf(command).equals(Commands.DELETE)) {
                    new deleteTask(input, items).run();
                } else {
                    if (Commands.valueOf(command).equals(Commands.DEADLINE)) {
                        new handleDeadline(input, items).run();
                    } else if (Commands.valueOf(command).equals(Commands.EVENT)) {
                        new handleEvent(input, items).run();
                    } else if (Commands.valueOf(command).equals(Commands.TODO)) {
                        new handleTodo(input, items).run();
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

































