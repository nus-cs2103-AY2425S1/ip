package carly;

import java.io.IOException;

import carly.exception.CarlyException;
import carly.ui.Ui;
import carly.utils.Parser;
import carly.utils.Storage;
import carly.utils.TaskList;

/**
 *  Represents a chatbot named Carly that manages a list of tasks.
 *  Can interact with the chatbot using Commands.
 */
public class Carly {

    /** Manages the list of tasks for the chatbot. */
    private final TaskList taskList;

    public Carly() {
        this.taskList = new TaskList();
    }

    public String getResponse(String input) {
        String response = "";
        try {
            Ui ui = new Ui();
            ui.welcomeMsg();
            String taskDescription;
            Parser.Command command;
            Storage listStorage = new Storage("./data/CarlyList.txt");

            Parser parser = new Parser(input);
            command = parser.getCommand();
            taskDescription = parser.getDetailsAfterCommand(command);

            switch (command) {
            case BYE:
                response = ui.byeMsg();
                break;
            case LIST:
                response = this.taskList.printTaskList();
                break;
            case MARK:
                try {
                    response = this.taskList.mark(taskDescription);
                } catch (CarlyException e) {
                    response = e.getMessage();
                }
                break;
            case UNMARK:
                try {
                    response = this.taskList.unmark(taskDescription);
                } catch (CarlyException e) {
                    response = e.getMessage();
                }
                break;
            case DELETE:
                try {
                    response = this.taskList.delete(taskDescription);
                    break;
                } catch (CarlyException e) {
                    response = e.getMessage();
                }
                break;
            case FIND:
                try {
                    response = this.taskList.find(taskDescription);
                    break;
                } catch (CarlyException e) {
                    response = e.getMessage();
                }
                break;
            case TODO:
                try {
                    response = this.taskList.addToDo(taskDescription);
                } catch (CarlyException e) {
                    response = e.getMessage();
                }
                break;
            case DEADLINE:
                try {
                    response = this.taskList.addDeadLine(taskDescription);
                } catch (CarlyException e) {
                    response = e.getMessage();
                }
                break;
            case EVENT:
                try {
                    response = this.taskList.addEvent(taskDescription);
                } catch (CarlyException e) {
                    response = e.getMessage();
                }
                break;
            default:
                response = "Oops, what are you trying to say again?";
            }

            listStorage.savesFile(this.taskList);

        } catch (IOException | CarlyException e) {
            response = e.getMessage();
        }

        assert !response.isEmpty() : "Response must not be empty";
        return response;
    }

    public static void main(String[] args) {
    }
}

