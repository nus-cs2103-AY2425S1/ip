package alpha;

import tasktypes.*;
import exception.AlphaException;
import utility.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.stream.Stream;

public class Alpha {
    
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    
    public Alpha(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (AlphaException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }
    
    public String processInput(String input) {
        StringBuilder response = new StringBuilder();
        String inputCommand = Parser.extractCommand(input);
        
        if (input.equalsIgnoreCase(Commands.BYE.getCommand())) {
            storage.synchronizeTasks(tasks.getTaskLists());
            response.append(ui.byeMessage());
        } else if (input.equalsIgnoreCase(Commands.LIST.getCommand())) {
            response.append(ui.listTask(tasks));
        } else if (input.equalsIgnoreCase(Commands.SORT.getCommand())) {
            this.tasks.sortTaskList();
            storage.synchronizeTasks(tasks.getTaskLists());
            response.append(ui.sortTaskMessage());
        } else if (inputCommand.equalsIgnoreCase(Commands.FIND.getCommand())) {
            String searchParam = Parser.excludeFirstWord(input);
            ArrayList<Task> searchResult = tasks.findLists(searchParam);
            response.append(ui.searchTask(searchResult));
        } else if (inputCommand.equalsIgnoreCase(Commands.MARK.getCommand())) {
            try {
                Integer indexInvolved = Parser.extractIntegerInvolved(input);
                response.append(ui.doneMessage(tasks.modifyOperation(indexInvolved, true)));
            } catch (ArrayIndexOutOfBoundsException e) {
                response.append("OOPS!!! Must specify which task to mark\n");
            }
        } else if (inputCommand.equalsIgnoreCase(Commands.UNMARK.getCommand())) {
            try {
                Integer indexInvolved = Parser.extractIntegerInvolved(input);
                response.append(ui.undoneMessage(tasks.modifyOperation(indexInvolved, false)));
            } catch (ArrayIndexOutOfBoundsException e) {
                response.append("OOPS!!! Must specify which task to unmark\n");
            }
        } else if (inputCommand.equalsIgnoreCase(Commands.DELETE.getCommand())) {
            try {
                Integer indexInvolved = Parser.extractIntegerInvolved(input);
                String deletedTaskNotice = tasks.deleteOperation(indexInvolved);
                String numberOfTasks = tasks.getLength();
                response.append(ui.deleteTaskMessage(deletedTaskNotice, numberOfTasks));
            } catch (ArrayIndexOutOfBoundsException e) {
                response.append("OOPS!!! Must specify which task to delete\n");
            }
        } else if (inputCommand.equalsIgnoreCase(Commands.TODO.getCommand())) {
            try {
                ToDo newToDo = new ToDo(String.join(" ", Arrays.copyOfRange(input.split(" "), 1, input.split(" ").length)));
                tasks.storeTask(newToDo);
                response.append(ui.addTaskMessage(tasks));
            } catch (ArrayIndexOutOfBoundsException e) {
                response.append("OOPS!!! The description of a todo cannot be empty.\n");
            }
        } else if (inputCommand.equalsIgnoreCase(Commands.DEADLINE.getCommand())) {
            try {
                String processedString = Parser.excludeFirstWord(input);
                String description = Parser.extractDescription(processedString);
                LocalDate byDate = LocalDate.parse(Parser.extractFirstDate(input));
                tasks.storeTask(new Deadline(description, byDate));
                response.append(ui.addTaskMessage(tasks));
            } catch (ArrayIndexOutOfBoundsException e) {
                response.append("Deadline creation should be in this format: deadline <Description> /by <Deadline>\n");
            } catch (DateTimeParseException e) {
                response.append("Date should be in format yyyy-mm-dd\n");
            }
        } else if (inputCommand.equalsIgnoreCase(Commands.EVENT.getCommand())) {
            try {
                String processedString = Parser.excludeFirstWord(input);
                String description = Parser.extractDescription(processedString);
                LocalDate startDate = LocalDate.parse(Parser.extractFirstDate(input));
                LocalDate endDate = LocalDate.parse(Parser.extractSecondDate(input));
                tasks.storeTask(new Event(description, startDate, endDate));
                response.append(ui.addTaskMessage(tasks));
            } catch (ArrayIndexOutOfBoundsException e) {
                response.append("Event creation should be in this format: event <Description> /from <Start> /to <End>\n");
            } catch (DateTimeParseException e) {
                response.append("Command should be in format: event project meeting /from YYYY-MM-DD /to YYYY-MM-DD\n");
            }
        } else {
            response.append("Sorry User, command is not understood. Alpha only accepts todo, deadline, event, list, bye, mark, and unmark commands.\n");
        }
        return response.toString();
    }
    
    public String getResponse(String input) {
        return processInput(input);
    }
    
    public String taskReminder() {
        StringBuilder response = new StringBuilder();
        try {
            TaskList reminderTaskList = this.tasks.giveReminder();
            String reminderMessage = ui.reminderMessage(reminderTaskList.listWord());
            response.append(reminderMessage);
        } catch (AlphaException e) {
            response.append("No Reminders to Give");
        }
        return response.toString();
    }
}
