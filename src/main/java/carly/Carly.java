package carly;
import java.util.Arrays;
import java.util.Scanner;
import carly.exception.*;
import carly.exception.CarlyNoTaskDescription;
import carly.tasks.TaskList;

public class Carly {
    private final TaskList taskList;
    private String username;
    private enum Command {
        BYE, LIST, MARK, UNMARK, DELETE, TODO, DEADLINE, EVENT
    }

    public Carly() {
        this.taskList = new TaskList();
        this.username = "";
    }

    private void updateUsername(String username) {
        this.username = username;
    }

    private void welcomeMsg() {
        System.out.println("Hey " + this.username + "! I'm Carly 👩🏼‍💼️.\nWhat can I do for you?");
    }

    private void byeMsg() {

        System.out.println("Bye " + this.username + ". I'll see you next time!");
    }

    private Command getCommand(String action) throws CarlyUnknownIInputException{
        try {
            return Command.valueOf(action.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new CarlyUnknownIInputException(action);
        }
    }

    private String getDetailsAfterCommand(Command command, String input, Integer firstSpaceIndex) throws CarlyNoTaskDescription {
        /*
        exception: for commands like "deadline xxx", if no task descriptions are given, e.g. "deadline" or "deadline  ",
        an exception to remind users to enter description will be raised.

        output: contains the description without the command type.
         */
        Command[] noDescriptionCommands = {Command.BYE, Command.LIST};
        boolean requiresDescription = !Arrays.asList(noDescriptionCommands).contains(command);

        if(requiresDescription) {
            String taskDescription = input.substring(firstSpaceIndex + 1).trim();
            if ((!input.contains(" ")) || (taskDescription.isEmpty())) {
                throw new CarlyNoTaskDescription();
            }
            return taskDescription;
        }
        return input.trim();
    }

    private void chat() {
        Scanner scan = new Scanner(System.in);
        System.out.println("What is your name?");
        String username = scan.nextLine();
        updateUsername(username);

        String input;
        String taskDescription;
        Command command;
        welcomeMsg();

        while (true) {
            if (scan.hasNextLine()) {
                input = scan.nextLine();
            } else {
                System.out.println("No input detected. Exiting...");
                break;
            }
            //input = scan.nextLine();

            int firstSpaceIndex = input.indexOf(" ");
            String[] parts = input.split(" ");
            String action = parts[0];

            try {
                command = getCommand(action);
                taskDescription = this.getDetailsAfterCommand(command, input, firstSpaceIndex);
            } catch (CarlyException e) {
                System.out.println(e.getMessage());
                continue;
            }

            switch (command) {
                case BYE:
                    byeMsg();
                    return;
                case LIST:
                    this.taskList.printTaskList();
                    break;
                case MARK:
                    try {
                        this.taskList.mark(taskDescription);
                    } catch (CarlyException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case UNMARK:
                    try {
                        this.taskList.unmark(taskDescription);
                    } catch (CarlyException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case DELETE:
                    try {
                        this.taskList.delete(taskDescription);
                        break;
                    } catch (CarlyException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case TODO:
                    try {
                        this.taskList.addToDo(taskDescription);
                    } catch (CarlyException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case DEADLINE:
                    try {
                        this.taskList.addDeadLine(taskDescription);
                    } catch(CarlyException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case EVENT:
                    try {
                        this.taskList.addEvent(taskDescription);
                    } catch (CarlyException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
            }
        }
    }



    public static void main(String[] args) {
        Carly carly = new Carly();
        try{
            carly.chat();
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

}
