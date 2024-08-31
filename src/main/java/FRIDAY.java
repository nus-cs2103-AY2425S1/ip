package main.java;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;

public class FRIDAY {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private boolean isActive = true;

    public FRIDAY() {
        //divider
        this.ui = new Ui();
        String filePath = "./storage/FRIDAY.txt";
        this.storage = new Storage(filePath);
        try {
            this.taskList = new TaskList(storage.load());
        } catch(FRIDAYException f) {
            System.out.println("Encountered unrecognizable task type");
        }
    }

    public void start() {
        this.activateBot();
        while(isActive) {
            try {
                //collect user input
                String userInput = ui.readUserInput();
                String keyword = Parser.parseCmd(userInput);
                //taskDetails is the user input without the keyword
                String taskDetails = Parser.parseTaskDetails(userInput);
                this.performAction(keyword, taskDetails);
            }
            catch(FRIDAYException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * activates the bot
     */
    public void activateBot() {
        this.isActive = true;
        ui.greeting();;
    }

    /**
     * deactivates the bot, printing an error message and exiting the program
     */
    public void deactivateBot() {
        ui.farewell();
        storage.updateStorage(this.taskList.getList());
        this.isActive = false;
    }

    /**
     * causes the bot to perform the action corresponding to the user input.
     * storage is updated after every performed action
     *
     * @param keyword indicates the type of action that the bot should perform
     * @param taskDetails contains miscellaneous information about the task, if applicable
     * @throws FRIDAYException exception is thrown when the type of action to be performed is not recognized
     */
    public void performAction(String keyword, String taskDetails) throws FRIDAYException {
        //keywords trigger respective actions
        switch (keyword) {
            //keywords
            case ("mark"):
                taskList.markTask(Integer.parseInt(taskDetails.substring(0, 1)) - 1);
                ui.printCheck();
                break;
            case ("unmark"):
                taskList.unMarkTask(Integer.parseInt(taskDetails.substring(0, 1)) - 1);
                ui.printUncheck();
                break;
            case ("todo"):
                if (taskDetails.isEmpty()) {
                    throw new FRIDAYException("ERROR: Please note that the description of a task cannot be left empty");
                }
                //create new to do task
                Task newToDo = new ToDo(taskDetails.strip(), 0);
                taskList.addTask(newToDo);
                ui.printAdd(newToDo, taskList.numTasks());
                break;
            case ("deadline"):
                if (taskDetails.isEmpty()) {
                    throw new FRIDAYException("ERROR: Please note that the description of a task cannot be left empty");
                }
                //create new deadline task
                Task newDeadline = Parser.parseDeadline(taskDetails);
                taskList.addTask(newDeadline);
                ui.printAdd(newDeadline, taskList.numTasks());
                break;
            case ("event"):
                if (taskDetails.isEmpty()) {
                    throw new FRIDAYException("ERROR: Please note that the description of a task cannot be left empty");
                }
                Task newEvent = Parser.parseEvent(taskDetails);
                taskList.addTask(newEvent);
                ui.printAdd(newEvent, taskList.numTasks());
                break;
            case ("delete"):
                if (taskDetails.isEmpty()) {
                    throw new FRIDAYException("ERROR: Please note that the description of a task cannot be left empty");
                }
                taskList.removeTask(Integer.parseInt(taskDetails.substring(0, 1)) - 1);
                ui.printRemove(taskList.getTaskAt(Integer.parseInt(taskDetails.substring(0, 1)) - 1), taskList.numTasks());
                break;

            case ("bye"):
                storage.updateStorage(this.taskList.getList());
                deactivateBot();
                break;
            case ("list"):
                System.out.println(taskList.displayTasks());
                break;
            //if there is no input then nothing added to list
            case (""):
                break;
            //to handle all normal inputs less empty strings
            default:
                throw new FRIDAYException("It appears that you have attempted to log an unrecognized class type. Please try again");
        }
    }

    public static void main(String[] args) {
        FRIDAY bot = new FRIDAY();
        bot.start();
    }
}
