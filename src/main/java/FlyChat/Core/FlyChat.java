package FlyChat.Core;

import java.util.InputMismatchException;


public class FlyChat {

    private static Ui ui = new Ui();
    private static Storage storage = new Storage();
    private static TaskList taskList = new TaskList();
    private static Parser parser = new Parser();
    private static boolean isChatOperational;
    public static void main(String[] args) {
        start();
        run();
        end();
    }

    private static void start() {
        storage.findSaveFile("./src/main/java/save.txt");
        storage.loadSaveFile(taskList);
        ui.greetUser();
    }

    private static void end() {
        ui.bye();
    }

    private static void run() {
        isChatOperational = true;
        while(isChatOperational) {
            try {
                processCommands();
            } catch(InputMismatchException e) {
                ui.announceString("I'm not sure what task you want me to do :((");
            }
        }
    }

    private static void processCommands() throws InputMismatchException {
        String inputString = ui.getNextLine();

        //while loop continues scanning until "bye" is typed by user
        while (!parser.parseCommand(inputString).equals("bye")) {
            //when user types "list", list of tasks is returned
            if(parser.parseCommand(inputString).equals("list")) {
                ui.announceString(taskList.announceItems());
            //when user types "mark [num]", the task with index num is marked as done
            } else if (parser.parseCommand(inputString).equals("mark")) {
                try {
                    ui.announceString(taskList.mark(parser.getTargetTaskIndex(inputString)));
                } catch (IndexOutOfBoundsException e) {
                    ui.announceString("Please ensure that you typed the correct task number");
                }
            //when user types "unmark [num]", the task with index num is marked as not done
            } else if (parser.parseCommand(inputString).equals("unmark")) {
                try {
                    ui.announceString(taskList.unmark(parser.getTargetTaskIndex(inputString)));
                } catch (IndexOutOfBoundsException e) {
                    ui.announceString("Please ensure that you typed the correct task number");
                }
            //when user types todo/deadline/event at the start, a new corresponding task is created
            } else if (parser.parseCommand(inputString).equals("todo") 
                    || parser.parseCommand(inputString).equals("deadline")
                    || parser.parseCommand(inputString).equals("event")) {
                ui.announceString(taskList.addTask(inputString));
            //when user types "delete [num]", the task with the index num is removed
            } else if (parser.parseCommand(inputString).equals("delete")) {
                try {
                    ui.announceString(taskList.deleteTask(parser.getTargetTaskIndex(inputString)));
                } catch (IndexOutOfBoundsException e) {
                    ui.announceString("Please ensure that you typed the correct task number");
                }
            } else {
                throw new InputMismatchException("Invalid input command");
            }
            storage.writeToSave(taskList.formatSaveString());
            inputString = ui.getNextLine();
        }
        isChatOperational = false;
    }
}
