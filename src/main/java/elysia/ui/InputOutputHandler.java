package elysia.ui;

import elysia.tasks.TaskList;
import elysia.tasks.Todo;
import elysia.tasks.Event;
import elysia.tasks.Deadline;
import elysia.tasks.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Handles user input and output for the Elysia application.
 * Manages the parsing of user commands and interactions with the task list.
 */
public class InputOutputHandler {
    TaskList taskList;
    FileReaderWriter fileReaderWriter;

    /**
     * Constructs an InputOutputHandler and initializes the task list and file reader/writer.
     * Loads any previously saved tasks from the file.
     */
    public InputOutputHandler() {
        taskList = new TaskList();
        fileReaderWriter = new FileReaderWriter(taskList);
        String msg = fileReaderWriter.readFile();
        if (!msg.isEmpty()) {
            Message.print(msg);
        }
    }

    /**
     * Parses user input and executes the corresponding command.
     * Supports commands for adding, deleting, marking, unmarking tasks, and more.
     *
     * @param input The user's command as a string.
     * @return {@code true} if the application should continue running; {@code false} if the application should exit.
     * @throws ElysiaException If the input command is unknown.
     * @throws StringIndexOutOfBoundsException If there is an error processing the input string.
     */
    public boolean parseInput(String input) throws ElysiaException, StringIndexOutOfBoundsException {
        String output = "";
        if (input.equals("bye")) {
            output += fileReaderWriter.createFile() + "\n";
            output += fileReaderWriter.writeFile();
            Message.print(output);
            return false;
        } else if (input.equals("list")) {
            output = "Here's your list! \n";
            output += taskList.toString();
        } else if (input.startsWith("mark")) {
            int taskNumber = Integer.parseInt(input.substring(5));
            try {
                taskList.markTask(taskNumber);
                output = "Amazing! You've completed this task! \n";
                output += taskList.printTask(taskNumber);
            } catch (IndexOutOfBoundsException | NullPointerException e) {
                output = "Uh oh, this task number does not exist...";
            }
        } else if (input.startsWith("unmark")) {
            int taskNumber = Integer.parseInt(input.substring(7));
            try {
                taskList.unmarkTask(taskNumber);
                output = "Making a pretty girl undo her work is not good for her health! \n";
                output += taskList.printTask(taskNumber);
            } catch (IndexOutOfBoundsException | NullPointerException e){
                output = "Uh oh, this task number does not exist...";
            }
        } else if (input.startsWith("todo")) {
            if (input.equals("todo")) {
                output = "Hmph! You don't expect me to read your mind for this todo, do you?";
            } else {
                Todo newTodo = new Todo(input.substring(5));
                taskList.addTask(newTodo);
                output = "Added the task below to your list~\n" + newTodo.toString() + "\n";
                output += "Wow! You now have " + taskList.getSizeAsString() + " tasks in your list!";
            }
        } else if (input.startsWith("deadline")) {
            if (input.equals("deadline")) {
                output = "Hmph! You don't expect me to read your mind for this deadline, do you?";
            } else {
                int index = input.indexOf("/");
                String rawDate = input.substring(index + 4);
                String date;
                try {
                    LocalDate parsedDate = LocalDate.parse(rawDate);
                    date = parsedDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
                } catch (DateTimeParseException e) {
                    output = "This doesn't look like a date... I won't be able to do anything with it but as long" +
                            "as you understand it :)" + "\n";
                    date = rawDate;
                }

                Deadline newDeadline = new Deadline(input.substring(9,index), date);
                taskList.addTask(newDeadline);
                output += "Added the task below to your list~\n" + newDeadline.toString() + "\n";
                output += "Wow! You now have " + taskList.getSizeAsString() + " tasks in your list!";
            }
        } else if (input.startsWith("event")) {
            if (input.equals("event")) {
                output = "Hmph! You don't expect me to read your mind for this event, do you?";
            } else {
                int index0 = input.indexOf("/");
                int index1 = input.lastIndexOf("/");
                Event newEvent = new Event(input.substring(6,index0),
                        input.substring(index0 + 6, index1),
                        input.substring(index1+4));
                taskList.addTask(newEvent);
                output = "Added the task below to your list~\n" + newEvent.toString() + "\n";
                output += "Wow! You now have " + taskList.getSizeAsString() + " tasks in your list!";
            }
        } else if (input.startsWith("delete")) {
            if (input.equals("delete")) {
                output = "Hmph! What do you even want me to delete?";
            }   else {
                int index = Integer.parseInt(input.substring(7));
                try {
                    Task deletedTask = taskList.deleteTask(index);
                    output = "You don't need this task below anymore? Ok deleting it~\n";
                    output += deletedTask.toString();
                    output += "Wow! You now have " + taskList.getSizeAsString() + " tasks in your list!";
                } catch (IndexOutOfBoundsException e) {
                    output = "Uh oh, this task number does not exist...";
                }
            }
        }
        else {
            throw new ElysiaException("unknown command");
        }
        Message.print(output);
        return true;
    }
}
