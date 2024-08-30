import java.io.FileNotFoundException;
import java.io.IOException;

public class Parser {

    private static TaskList taskList;
    private static Ui ui;
    private final String STORAGEFILEPATH = "Meerkat.txt";


    public Parser() {
        this.taskList = new TaskList();
        this.ui = new Ui();
    }

    public static void parseSaveFile(String thisTask) {
        String[] strArray = thisTask.split(",", 5);
        switch (strArray.length) {
            case 3:
                try {
                    taskList.createTodoTask(strArray[2]);
                    switch (strArray[1]) {
                        case "m":
                            taskList.setMostRecentTaskCompletionStatus(true);
                        case "u":
                            taskList.setMostRecentTaskCompletionStatus(false);
                    }
                } catch (IOException e) {
                    ui.printErrorWritingFileMessage();
                }
                break;
            case 4:
                try {
                    taskList.createDeadlineTask(strArray[2], strArray[3]);
                    switch (strArray[1]) {
                        case "m":
                            taskList.setMostRecentTaskCompletionStatus(true);
                        case "u":
                            taskList.setMostRecentTaskCompletionStatus(false);
                    }
                } catch (IOException e) {
                    ui.printErrorWritingFileMessage();
                }
                break;
            case 5:
                try {
                    taskList.createEventTask(strArray[2], strArray[3], strArray[4]);
                    switch (strArray[1]) {
                        case "m":
                            taskList.setMostRecentTaskCompletionStatus(true);
                        case "u":
                            taskList.setMostRecentTaskCompletionStatus(false);
                    }
                } catch (IOException e) {
                    ui.printErrorWritingFileMessage();
                }
                break;
        }
    }

    public void parse(String taskName) {
        String[] strArray = taskName.split(" ", 2);


        // create new todo task
        if (strArray[0].equalsIgnoreCase("todo")) {
            try {
                taskList.createTodoTask(strArray[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                ui.printNeedMoreInfoTodoMessage();
            } catch (IOException e) {
                ui.printErrorWritingFileMessage();
            }
        }

        // create new deadline task
        else if (strArray[0].equalsIgnoreCase("deadline")) {
            try {
                String[] todoStringArray = taskName.split(" /by ");
                String dueDate = todoStringArray[1];
                String name = todoStringArray[0].split(" ", 2)[1];
                taskList.createDeadlineTask(name, dueDate);
            } catch (ArrayIndexOutOfBoundsException e) {
                ui.printNeedMoreInfoDeadlineMessage();
            } catch (IOException e) {
                ui.printErrorWritingFileMessage();
            }
        }

        // create new event task
        else if (strArray[0].equalsIgnoreCase("event")) {
            try {
                String[] eventStringArray = taskName.split(" /from ");
                String[] duration = eventStringArray[1].split(" /to ");
                String name = eventStringArray[0].split(" ", 2)[1];
                taskList.createEventTask(name, duration[0], duration[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                ui.printNeedMoreInfoEventMessage();
            } catch (IOException e) {
                ui.printErrorWritingFileMessage();
            }
        }

        // to end program
        else if (taskName.equalsIgnoreCase("bye")) {
            ui.printGoodbyeMessage();
            System.exit(0);
        }
            // to display list of items
        else if (taskName.equalsIgnoreCase("list")) {
            ui.printList(taskList);
        }

        // mark task as done
        else if (strArray.length == 2 && strArray[0].equals("mark")) {
            try {
                int taskNum = Integer.parseInt(strArray[1]);
                taskList.markTaskAsDone(taskNum);
            } catch (NumberFormatException e) {
                ui.printTaskNonMarkableMessage();
            } catch (IOException e) {
                ui.printErrorWritingFileMessage();
            }

        // mark item as not done
        } else if (strArray.length == 2 && strArray[0].equals("unmark")) {
            try {
                int taskNum = Integer.parseInt(strArray[1]);
                taskList.markTaskAsUndone(taskNum);
            } catch (NumberFormatException e) {
                ui.printTaskNonUnmarkableMessage();
            } catch (IOException e) {
                ui.printErrorWritingFileMessage();
            }

        // delete task
        } else if (strArray.length == 2 && strArray[0].equals("delete")) {
            try {
                int taskNum = Integer.parseInt(strArray[1]);
                taskList.deleteTask(taskNum);
            } catch (NumberFormatException e) {
                ui.printUndeletableMessage();
            } catch (IOException e) {
                ui.printErrorWritingFileMessage();
            }
        }

        // invalid input
        else {
            ui.printNoIdeaMessage();
        }
    }
}