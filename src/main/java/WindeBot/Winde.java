package WindeBot;

import Commands.Command;
import Commands.ListCommand;

import Exceptions.EmptyDescriptionException;
import Exceptions.TooManyParametersException;
import Exceptions.UnsupportedCommandException;

public class Winde {

    private static History history;
    private static Reminder reminder;
    private static Ui ui;

    Winde(String filePath) {
        ui = new Ui();
        history = new History(filePath);
        reminder = new Reminder(history.load());
    }

    Winde() {
        ui = new Ui();
        history = new History();
        reminder = new Reminder(history.load());
    }

    public static void main(String[] args) {
        new Winde().run();
    }

    private static void run() {
        ui.greet();
        Command currentCommand = new ListCommand();
        boolean shouldContinue = true;
        while (shouldContinue) {
            try {
                String input = ui.read();
                ui.showLine();
                currentCommand = Parser.parse(input);
                shouldContinue = currentCommand.execute(input, reminder, ui);
            } catch (UnsupportedCommandException e) {
                throw new RuntimeException(e);
            } catch (EmptyDescriptionException e) {
                throw new RuntimeException(e);
            } catch (TooManyParametersException e) {
                throw new RuntimeException(e);
            } finally {
                ui.showLine();
            }
        }
        currentCommand.exit(history, reminder, ui);
    }

    public void task(int i) {
        System.out.print(reminder.getTask(i - 1).toString());
    }
    /*
    public static void add(String action) {
        Task t = new Task(action);
        reminder.add(t);
        System.out.println("added: " + action);
    }

     */


}

