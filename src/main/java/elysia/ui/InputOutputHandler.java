package elysia.ui;

import elysia.tasks.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;

public class InputOutputHandler {
    TaskList taskList;
    FileReaderWriter fileReaderWriter;

    public InputOutputHandler() {
        taskList = new TaskList();
        fileReaderWriter = new FileReaderWriter(taskList);
        String msg = fileReaderWriter.readFile();
        if (!msg.isEmpty()) {
            Message.print(msg);
        }
    }

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
                output += "Wow! You now have " + taskList.size() + " tasks in your list!";
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
                output += "Wow! You now have " + taskList.size() + " tasks in your list!";
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
                output += "Wow! You now have " + taskList.size() + " tasks in your list!";
            }
        } else if (input.startsWith("delete")) {
            if (input.equals("delete")) {
                output = "Hmph! What do you even want me to delete?";
            } else {
                int index = Integer.parseInt(input.substring(7));
                try {
                    Task deletedTask = taskList.deleteTask(index);
                    output = "You don't need this task below anymore? Ok deleting it~\n";
                    output += deletedTask.toString();
                    output += "Wow! You now have " + taskList.size() + " tasks in your list!";
                } catch (IndexOutOfBoundsException e) {
                    output = "Uh oh, this task number does not exist...";
                }
            }
        } else if (input.startsWith("find")) {
            if (input.equals("find")) {
                output = "Hmph! What do you even want me to find?";
            } else {
                TaskList searchResults = taskList.searchByKeyword(input.substring(5));
                if (Objects.equals(searchResults.size(), "0")) {
                    output = "I couldn't find anything...";
                } else {
                    output = "Here's the tasks matching your search string! " +
                            "I hope you found what you're looking for!\n";
                    output += searchResults.toString();
                }
            }
        } else {
            throw new ElysiaException("unknown command");
        }
        Message.print(output);
        return true;
    }
}
