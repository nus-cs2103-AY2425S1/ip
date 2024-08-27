package carly;

import java.text.MessageFormat;
import java.util.Scanner;

import carly.exception.*;
import carly.exception.CarlyMissingDateTimeException;
import carly.exception.CarlyNoTaskDescription;
import carly.tasks.TaskList;

public class Carly {
    private final TaskList taskList;
    private String username;
    private enum Command {
        TODO, DEADLINE, EVENT, MARK, UNMARK, LIST, BYE
    }

    public Carly() {
        this.taskList = new TaskList();
        this.username = "";
    }

    private void welcomeMsg() {
        System.out.println("Hey " + this.username + "! I'm Carly 👩🏼‍💼️.\nWhat can I do for you?");
    }

    private void byeMsg() {
        System.out.println("Bye " + this.username + ". I'll see you next time!");
    }

    private void updateUsername(String username) {
        this.username = username;
    }

    private Command getCommand(String action) throws CarlyUnknownIInputException{
        try {
            return Command.valueOf(action.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new CarlyUnknownIInputException(action);
        }
    }

    private String getDetailsAfterCommand(String input, Integer firstSpaceIndex) throws CarlyNoTaskDescription {
        /*
        exception: for commands like "deadline xxx", if no task descriptions are given, e.g. "deadline" or "deadline  ",
        an exception to remind users to enter description will be raised.

        output: contains the description without the command type.
         */
        String taskDescription = input.substring(firstSpaceIndex + 1).trim();
        if((!input.contains(" ")) || (taskDescription.isEmpty())) {
            throw new CarlyNoTaskDescription();
        }
        return taskDescription;
    }

    private void chat() throws CarlyException {
        Scanner scan = new Scanner(System.in);
        System.out.println("What is your name?");
        String username = scan.nextLine();
        updateUsername(username);

        String input;
        String taskNum;
        String taskDescription;
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

            Command command;
            try {
                command = getCommand(action);
            } catch (CarlyUnknownIInputException e) {
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
                case TODO:
                    try {
                        taskDescription = this.getDetailsAfterCommand(input, firstSpaceIndex);
                        this.taskList.addToDo(taskDescription);
                    } catch (CarlyException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case DEADLINE:
                    try {
                        taskDescription = this.getDetailsAfterCommand(input, firstSpaceIndex);
                        this.taskList.addDeadLine(taskDescription);
                    } catch(CarlyNoTaskDescription | CarlyMissingDateTimeException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case EVENT:
                    try {
                        taskDescription = this.getDetailsAfterCommand(input, firstSpaceIndex);
                        this.taskList.addEvent(taskDescription);
                    } catch (CarlyNoTaskDescription | CarlyMissingDateTimeException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case MARK:
                    try {
                        taskNum = this.getDetailsAfterCommand(input, firstSpaceIndex);
                        this.taskList.mark(taskNum);
                    } catch (CarlyException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case UNMARK:
                    try {
                        taskNum = this.getDetailsAfterCommand(input, firstSpaceIndex);
                        this.taskList.unmark(taskNum);
                        break;
                    } catch (CarlyException e) {
                        System.out.println(e.getMessage());
                    }
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
