package Citadel;

import java.io.IOException;
import java.time.format.DateTimeParseException;

import Citadel.Commands.Commands;
import Citadel.Commands.deleteTask;
import Citadel.Commands.handleDeadline;
import Citadel.Commands.handleEvent;
import Citadel.Commands.handleTodo;
import Citadel.Commands.markTask;
import Citadel.Commands.unmarkTask;

import Citadel.Task.TaskList;

import Citadel.exception.CitadelException;
import Citadel.exception.CitadelInvalidCommandException;
import Citadel.ui.TextUI;

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
                    new markTask(input, items).run();
                } else if (command.equals(Commands.UNMARK)) {
                    new unmarkTask(input, items).run();
                } else if (command.equals(Commands.DELETE)) {
                    new deleteTask(input, items).run();
                } else {
                    if (command.equals(Commands.DEADLINE)) {
                        new handleDeadline(input, items).run();
                    } else if (command.equals(Commands.EVENT)) {
                        new handleEvent(input, items).run();
                    } else if (command.equals(Commands.TODO)) {
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







