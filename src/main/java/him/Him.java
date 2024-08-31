package Bot;

import exceptions.AlreadyCompletedException;
import exceptions.StartAfterEndException;
import exceptions.TaskDoesNotExistException;
import storage.Storage;
import task.Deadline;
import task.Event;
import task.TaskList;
import task.ToDo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Him {

    private static TaskList list = new TaskList();
    private static final Storage storage = new Storage();

    private static void complete(int index) {
        try {
            list.complete(index);
            Bot.Ui.sayCompleted(list.taskAt(index));
        } catch (AlreadyCompletedException | TaskDoesNotExistException e) {
            Bot.Ui.say(e.getMessage());
        }
    }

    private static void delete(int index) {
        try {
            String task = list.delete(index);
            Bot.Ui.sayDeleted(task);
        } catch (TaskDoesNotExistException e) {
            Bot.Ui.say(e.getMessage());
        }
    }


    public static void main(String[] args) {
        try {
            list = storage.loadTaskList();
        } catch (FileNotFoundException e) {
            storage.initStorage();
        } catch (AlreadyCompletedException | StartAfterEndException e) {
            Bot.Ui.showLoadingFailure();
            System.exit(0);
        }
        Scanner scanner = new Scanner(System.in);
        Bot.Ui.greet();
        Bot.Ui.printUser();
        String[] input = scanner.nextLine().split(" ", 2);
        String command = input[0];
        while (!command.equals("bye")) {
            switch (command) {
                case "list":
                    if (list.isEmpty()) {
                        Bot.Ui.sayEmptyList();
                    } else {
                        Bot.Ui.sayList(list);
                    }
                    break;
                case "mark":
                    complete(Integer.parseInt(input[1]) - 1);
                    break;
                case "todo": {
                    try {
                        ToDo newToDo = new ToDo(input[1]);
                        list.add(newToDo);
                        Bot.Ui.sayAdded(newToDo);
                    } catch (IndexOutOfBoundsException e) {
                        Bot.Ui.sayInvalidTodoFormat();
                    } finally {
                        break;
                    }
                }
                case "deadline": {
                    try {
                        String[] details = input[1].split("/by");
                        Deadline newDeadline = Deadline.of(details[0].trim(), details[1].trim());
                        list.add(newDeadline);
                        Bot.Ui.sayAdded(newDeadline);
                    } catch (IndexOutOfBoundsException e) {
                        Bot.Ui.sayInvalidDeadlineFormat();
                    } finally {
                        break;
                    }
                }
                case "event": {
                    try {
                        String[] details = input[1].split("/start");
                        String[] interval = details[1].split("/end");
                        Event newEvent = new Event(details[0].trim(), interval[0].trim(), interval[1].trim());
                        list.add(newEvent);
                        Bot.Ui.sayAdded(newEvent);
                    } catch (IndexOutOfBoundsException e) {
                        Bot.Ui.sayInvalidEventFormat();
                    } catch (StartAfterEndException e) {
                        Bot.Ui.sayEventStartAfterEnd();
                    } catch (DateTimeParseException e) {
                        Bot.Ui.sayInvalidDateTimeFormat();
                    } finally {
                        break;
                    }
                }
                case "delete": {
                    try {
                        delete(Integer.parseInt(input[1]) - 1);
                    } catch (IndexOutOfBoundsException e) {
                        Bot.Ui.sayDeleteNoTask();
                    } finally {
                        break;
                    }
                }
                default: {
                    Bot.Ui.sayInvalidCommand(command);
                }
            }

            try {
                storage.saveTaskList(list);
            } catch (IOException e) {
                Bot.Ui.showSaveFailure();
            }

            Bot.Ui.printUser();
            input = scanner.nextLine().split(" ", 2);
            command = input[0];
        }
        scanner.close();
        Bot.Ui.exit();
    }
}
