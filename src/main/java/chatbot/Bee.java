package chatbot;

import task.*;

import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Starts the chatbot "Bee".
 *
 * @author celeschai
 */
public class Bee {
    public static void main(String[] args) {
        TaskList taskList = new TaskList();
        Storage storage = new Storage();

        // Checks if source text file is successfully opened
        boolean isOpenFile = storage.readFromTaskListFile();
        if (isOpenFile) {
            // Parses input from saved text file
            Parser.parseFromTxtTaskList(storage.getScanner(), taskList);

            // Welcomes user
            System.out.println(Ui.LOGO);
            Ui.printBtnLines("Hello, I'm Bee! What can I do for you?");

            // Takes user input
            Scanner sc = new Scanner(System.in);
            while (sc.hasNextLine()) {
                try {
                    String next = sc.nextLine();

                    if (next.matches("bye.*")) {
                        boolean saved = storage.writeToTaskListFile(taskList.parseTaskListToTxt());
                        if (saved) {
                            Ui.printBtnLines("Bye~ Hope to see you again soon!");
                            break;
                        }
                        Ui.printBtnLines("Tasks not saved, please try again.");
                    } else if (next.equalsIgnoreCase("list")) {
                        Ui.printBtnLines(
                                "Here are the tasks in your list:\n".concat(taskList.toString()));

                    } else if (next.matches("mark (.+)")) {
                        // Uses regular expression to capture task index for completion
                        Pattern pattern = Pattern.compile("mark (\\d+)");
                        Matcher matcher = pattern.matcher(next);

                        // Obtains task index if match succeeds
                        if (matcher.matches()) {
                            String num = matcher.group(1);
                            int index = Integer.parseInt(num);

                            // Marks task as done
                            if (taskList.isTaskExist(index)) {
                                taskList.markTaskAsDone(index);

                                Ui.printBtnLines(
                                        String.format(
                                                "Nice! I've marked this task as done:\n    %s",
                                                taskList.getTask(index)));
                            } else {
                                // Warns user of invalid task specified
                                throw new TaskIndexException();
                            }
                        }

                    } else if (next.matches("unmark (.+)")) {
                        // Uses regular expression to capture task index for incompletion
                        Pattern pattern = Pattern.compile("unmark (\\d+)");
                        Matcher matcher = pattern.matcher(next);

                        // Obtains task index if match succeeds
                        if (matcher.matches()) {
                            String num = matcher.group(1);
                            int index = Integer.parseInt(num);

                            // Marks task as incomplete
                            if (taskList.isTaskExist(index)) {
                                taskList.markTaskAsIncomplete(index);

                                Ui.printBtnLines(
                                        String.format(
                                                "OK, I've marked this task as not done yet:\n    %s",
                                                taskList.getTask(index)));
                            } else {
                                // Warns user of invalid task specified
                                throw new TaskIndexException();
                            }
                        }

                    } else if (next.matches("delete (.+)")) {
                        // Uses regular expression to capture task index for deletion
                        Pattern pattern = Pattern.compile("delete (\\d+)");
                        Matcher matcher = pattern.matcher(next);

                        // Obtains task index if match succeeds
                        if (matcher.matches()) {
                            String num = matcher.group(1);
                            int index = Integer.parseInt(num);

                            // Deletes task
                            if (taskList.isTaskExist(index)) {
                                Task deletedTask = taskList.removeTask(index);

                                Ui.deleteTaskResponse(
                                        deletedTask.toString(), taskList.getTotalNumOfTasks());
                            }
                            // Warns user of invalid task specified
                        } else {
                            throw new TaskIndexException();
                        }

                    } else if (next.matches("todo (.*)")) {
                        // Uses regular expression to capture todo name
                        Pattern pattern = Pattern.compile("todo (\\S.*)");
                        Matcher matcher = pattern.matcher(next);

                        // Obtains parameters if match succeeds
                        if (matcher.matches()) {
                            String name = matcher.group(1);
                            Todo todo = new Todo(name);
                            taskList.addTask(todo);
                            Ui.addTaskResponse(
                                    todo.toString(), taskList.getTotalNumOfTasks());

                        } else {
                            throw new InvalidInputException("name", "task");
                        }

                    } else if (next.matches("deadline (.*)")) {
                        // Uses regular expression to capture deadline name and /by parameter
                        Pattern pattern = Pattern.compile("deadline (\\S.*) /by (\\S.*)");
                        Matcher matcher = pattern.matcher(next);

                        // Obtains parameters if match succeeds
                        if (matcher.matches()) {
                            String name = matcher.group(1);
                            String byParam = matcher.group(2);

                            // Instantiates a Task object and add it to todolist
                            Deadline deadline = new Deadline(name, byParam);
                            taskList.addTask(deadline);
                            Ui.addTaskResponse(deadline.toString(), taskList.getTotalNumOfTasks());

                        } else {
                            throw new InvalidInputException("name, and due date", "deadline");
                        }

                    } else if (next.matches("event (.*)")) {
                        // Uses regular expression to capture event name, /from and /to parameters
                        Pattern pattern = Pattern.compile("event (\\S.*) /from (\\S.*) /to (\\S.*)");
                        Matcher matcher = pattern.matcher(next);

                        // Obtains parameters if match succeeds
                        if (matcher.matches()) {
                            String name = matcher.group(1);
                            String fromParam = matcher.group(2);
                            String toParam = matcher.group(3);

                            // Instantiates a Task object and add it to todolist
                            Event event = new Event(name, fromParam, toParam);
                            taskList.addTask(event);
                            Ui.addTaskResponse(event.toString(), taskList.getTotalNumOfTasks());

                        } else {
                            throw new InvalidInputException("name, start, and end time", "event");
                        }

                    } else if (next.matches("help")) {
                        Ui.printBtnLines(Ui.help);
                    } else {
                        // Throws exception when nothing, whitespaces, or no name is provided
                        throw new BeeException("Say something helpful.");
                    }

                } catch (BeeException e) {
                    Ui.printBtnLines(e.getMessage());

                } catch (DateTimeParseException e) {
                    // Guides user on date time input formats
                    Ui.printBtnLines("Format your time in: " +
                            "yyyy-MM-dd HHmm (24Hr) or yyyy-MM-dd hh:mm am (12Hr).\n" +
                            "You can replace - with \\ as well");
                }
            }
            sc.close();
        }
    }
}
