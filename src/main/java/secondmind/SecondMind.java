package secondmind;

import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.format.DateTimeParseException;

/**
 * Main class for the SecondMind application that manages tasks.
 * It initializes the required components and controls the main program flow.
 */
public class SecondMind {
    private static final String logo = "SecondMind";
    private static final String DATA_FILE_PATH = "./SecondMind.txt";
    private static final String DATE_TIME_PARSE_EXCEPTION_MESSAGE =
            "Warning! Invalid dateTime format detected!\n"
            + "Please use the following representation for dateTime strings:\n"
            + "\tyyyy-MM-ddTHH:mm:ss";
    private static final String EXIT_INSTRUCTION = "$$$EXIT_PROGRAM$$$";

    private Storage storage;
    private TaskList taskList;
    private Parser parser;

    /**
     * Main method that serves as the entry point for the application.
     */
    public SecondMind() {
        this.storage = new Storage(DATA_FILE_PATH);
        this.taskList = null;
        try {
            this.taskList = new TaskList(storage.loadTaskList());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (this.taskList == null) {
            this.taskList = new TaskList(new ArrayList<Task>());
        }
        this.parser = new Parser();
    }

    public String[] getInstructionsFromInput(String input) {
        String[] instruction = parser.processInput(input);
        return instruction;
    }

    private int getTaskNumberFromInstruction(String[] instruction)
            throws NumberFormatException {
        int taskNumber = Integer.parseInt(instruction[1]);
        return taskNumber;
    }

    private void modifyStatusInTaskList(int taskNumber, boolean isDone)
            throws InvalidTaskNumberException {
        if (!isDone) {
            taskList.markAsUndone(taskNumber);
        } else {
            taskList.markAsDone(taskNumber);
        }
    }

    private void modifyStatusInStorage(int taskNumber, boolean isDone)
            throws FileNotFoundException, IOException {
        storage.updateTaskInDataFile(taskNumber, isDone, taskList.getTaskCount());
    }

    private String getTaskUpdateMessage(int taskNumber, boolean isDone) {
        String taskDescription = taskList.getTask(taskNumber).getDescription();
        if (!isDone) {
            String message = "I've marked the following task as incomplete:\n" + taskDescription;
            return message;
        }
        String message = "Well done! You have completed the following task:\n" + taskDescription;
        return message;
    }

    private String formatInvalidTaskNumberExceptionMessage(InvalidTaskNumberException e) {
        String errorMessage = e.toString() + "\nThere are "
                + taskList.getTaskCount() + " tasks in your task list.";
        return errorMessage;
    }

    private String executeTaskStatusUpdateInstruction(String[] instruction, boolean isDone) {
        try {
            int taskNumber = getTaskNumberFromInstruction(instruction);
            modifyStatusInTaskList(taskNumber, isDone);
            modifyStatusInStorage(taskNumber, isDone);
            String message = getTaskUpdateMessage(taskNumber, isDone);
            return message;
        } catch (InvalidTaskNumberException e) {
            String errorMessage = formatInvalidTaskNumberExceptionMessage(e);
            return errorMessage;
        } catch (FileNotFoundException e) {
            return e.toString();
        } catch (IOException e) {
            return e.toString();
        }
    }

    private void deleteTaskFromStorage(int taskNumber, int taskCount)
            throws InvalidTaskNumberException, FileNotFoundException, IOException {
        storage.delete(taskNumber, taskList.getTaskCount());
    }

    private void deleteFromTaskList(int taskNumber)
            throws InvalidTaskNumberException {
        taskList.delete(taskNumber);
    }

    private String getDeletionMessage(Task deletedTask, int taskCount) {
        String message = "I've removed the following task:\n" + "\t" + deletedTask
                + "\nYou have a grand total of " + taskCount + " task(s)";
        return message;
    }

    private String executeDeleteInstruction(String[] instruction) {
        try {
            int taskNumber = getTaskNumberFromInstruction(instruction);
            int taskCount = taskList.getTaskCount();
            Task currTask = taskList.getTask(taskNumber);
            deleteTaskFromStorage(taskNumber, taskCount);
            deleteFromTaskList(taskNumber);
            String message = getDeletionMessage(currTask, taskCount-1);
            return message;
        } catch (InvalidTaskNumberException e) {
            String errorMessage = formatInvalidTaskNumberExceptionMessage(e);
            return errorMessage;
        } catch (FileNotFoundException e) {
            return e.toString();
        } catch (IOException e) {
            return e.toString();
        }
    }

    private String executeListInstruction() {
        StringBuilder sb = new StringBuilder();
        ArrayList<Task> tl = this.taskList.getTaskList();
        for (Task task : tl) {
            sb.append(task.toString());
            sb.append("\n");
        }
        return sb.toString();
    }

    private String[] getInstructionCopy(String[] instruction) {
        String[] copy = new String[instruction.length];
        for (int i = 0; i < instruction.length; i++) {
            copy[i] = instruction[i];
        }
        return copy;
    }

    private String getMatchFromInstruction(String[] instruction) {
        String[] instructionCopy = getInstructionCopy(instruction);
        instructionCopy[0] = "";
        String match = String.join(" ", instructionCopy);
        return match;
    }

    private String getMatchingTasks(String match) {
        ArrayList<Task> filteredTaskList = this.taskList.getMatchingTasks(match);
        StringBuilder sb = new StringBuilder();
        for (Task task : filteredTaskList) {
            sb.append(task.toString());
            sb.append("\n");
        }
        return sb.toString();
    }

    private String executeFindInstruction(String[] instruction) {
        String match = getMatchFromInstruction(instruction);
        String matchingTasks = getMatchingTasks(match);
        return matchingTasks;
    }

    private void addTaskToList(Task task)
            throws EmptyCommandException, EmptyToDoException, UnknownCommandException, DateTimeParseException {
        taskList.addToTaskList(task);
    }

    private void addTaskToStorage(Task task) throws IOException {
        String storageRepresentation = task.getStorageRepresentation();
        int taskCount = taskList.getTaskCount();
        storage.appendToFile(storageRepresentation, taskCount);
    }

    private String getTaskCreationMessage(Task currentTask) {
        String message = "Got it. I have added the following task:\n\t" + currentTask + "\n"
                + "You have a grand total of " + this.taskList.getTaskCount() + " task(s)";
        return message;
    }

    private String getDuplicateTaskMessage(Task task) {
        String message = "The following task already exists:\n\t" + task;
        return message;
    }

    private String executeTaskCreationInstruction(String[] instruction) {
        try {
            Task newTask = taskList.createTask(instruction);
            boolean containsDuplicates = taskList.checkForDuplicate(newTask);
            if (containsDuplicates) {
                return getDuplicateTaskMessage(newTask);
            }
            addTaskToList(newTask);
            addTaskToStorage(newTask);
            return getTaskCreationMessage(newTask);
        } catch (EmptyCommandException | EmptyToDoException | UnknownCommandException | IOException e) {
            return e.toString();
        } catch (DateTimeParseException e) {
            return DATE_TIME_PARSE_EXCEPTION_MESSAGE;
        }
    }

    public String execute(String[] instruction) {
        String command = instruction[0];
        String response;
        switch (command) {
        case "bye":
            return EXIT_INSTRUCTION;
        case "mark":
            response = executeTaskStatusUpdateInstruction(instruction, true);
            return response;
        case "unmark":
            response = executeTaskStatusUpdateInstruction(instruction, false);
            return response;
        case "delete":
            response = executeDeleteInstruction(instruction);
            return response;
        case "list":
            response = executeListInstruction();
            return response;
        case "find":
            response = executeFindInstruction(instruction);
            return response;
        default:
            response = executeTaskCreationInstruction(instruction);
            return response;
        }
    }

    public String getResponse(String userInput) {
        String[] instruction = getInstructionsFromInput(userInput);
        assert instruction != null;
        String response = execute(instruction);
        return response;
    }
}
