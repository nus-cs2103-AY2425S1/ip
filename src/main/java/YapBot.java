import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class YapBot {
    private static final String PREFIXLINE = "\n-------------------------------------------";
    private static final String POSTFIXLINE = "-------------------------------------------\n";
    private static ArrayList<Task> storedTasks = new ArrayList<>();
    private enum Tasktype {TODO, DEADLINE, EVENT};

    public static void list() throws YapBotException {
        if (storedTasks.isEmpty()) {
            throw new YapBotException("Error, no Tasks found in database.");
        }

        Iterator<Task> iterateStored = storedTasks.iterator();
        System.out.println(PREFIXLINE + "\nRetrieving Tasks...\nSuccess\nCurrent Tasks:");

        int index = 1;
        while (iterateStored.hasNext()) {
            System.out.println("  " + index + "." + iterateStored.next());
            index++;
        }
        System.out.println(POSTFIXLINE);
    }

    public static void markOrUnmark(boolean mark, int index) throws YapBotException {
        if (mark) {

            if (index == 0 || index >= storedTasks.size() + 1) {
                throw new YapBotException("Finding Task...\nFailure\nError, requested Task does not exist.\nUse command \"list\" to view your tasks.");
            }

            Task task = storedTasks.get(index - 1);
            task.changeDone(true);
            System.out.println(PREFIXLINE + "\nFinding Task...\nSuccess\nTask Completed:\n  " + task + "\n" + POSTFIXLINE);
        } else {
            if (index == 0 || index >= storedTasks.size() + 1) {
                throw new YapBotException("Finding Task...\nFailure\nError, requested Task does not exist.\nUse command \"list\" to view your tasks.");
            }
            Task task = storedTasks.get(index - 1);
            task.changeDone(false);

            System.out.println(PREFIXLINE + "\nFinding Task...\nSuccess\nTask Incomplete:\n  " + task + "\n" + POSTFIXLINE);
        }
    }

    public static void createTask(Tasktype tasktype, String taskDetails) throws YapBotException {
        Task task = new Task("Dummy");

        switch (tasktype) {
            case TODO: {
                task = new ToDo(taskDetails);
                storedTasks.add(task);
                System.out.println(PREFIXLINE + "\nAdding Task...\nSuccess\nTask added to database:\n" + "  " + task + "\n" + "Total tasks: " + storedTasks.size() +"\n" + POSTFIXLINE);
                break;
            }

            case DEADLINE: {
                if (!taskDetails.contains("/by")) {
                    throw new YapBotException("Error, Deadline Prediction module offline.\nSupply a deadline using \"/by\" (eg. /by Monday 1pm).");
                }
                String taskName = taskDetails.substring(0, taskDetails.indexOf("/by")).strip();
                String deadline = taskDetails.substring(taskDetails.indexOf("/by") + 3).strip();

                if (taskName.isEmpty()) {
                    throw new YapBotException("Error, Automated Task Suggestion module offline.\nTask details must be manually entered.");
                }

                if (deadline.isEmpty()) {
                    throw new YapBotException("Error, Deadline Prediction module offline.\nManually input a deadline or use command \"todo\" for tasks without deadlines.");
                }

                task = new Deadline(taskName, deadline);
                storedTasks.add(task);
                System.out.println(PREFIXLINE + "\nAdding Task...\nSuccess\nTask added to database:\n" + "  " + task + "\n" + "Total tasks: " + storedTasks.size() + "\n" + POSTFIXLINE);
                break;
            }

            case EVENT: {
                if (!taskDetails.contains("/from") && !taskDetails.contains("/to")) {
                    throw new YapBotException("Error, start and end times not detected.\nSupply start time using \"/from\" (eg. /from Monday 1pm).\nSupply end time using \"/to\" (eg. /to Tuesday 1pm).");
                }

                if (!taskDetails.contains("/from")) {
                    throw new YapBotException("Error, start time not detected.\nSupply start time using \"/from\" (eg. /from Monday 1pm).");
                }

                if (!taskDetails.contains("/to")) {
                    throw new YapBotException("Error, end time not detected.\nSupply end time using \"/to\" (eg. /to Tuesday 1pm).");
                }
                String taskName = taskDetails.substring(0, taskDetails.indexOf("/")).strip();
                String taskDeadlines = taskDetails.substring(taskDetails.indexOf("/"));
                int fromIndex = taskDeadlines.indexOf("/from");
                int toIndex = taskDeadlines.indexOf("/to");
                String from = "";
                String to = "";

                if (toIndex > fromIndex) {
                    from = taskDeadlines.substring(taskDeadlines.indexOf("/from") + 5, taskDeadlines.indexOf("/to")).strip();
                    to = taskDeadlines.substring(taskDeadlines.indexOf("/to") + 3).strip();
                } else {
                    to = taskDeadlines.substring(taskDeadlines.indexOf("/to") + 3, taskDeadlines.indexOf("/from") ).strip();
                    from = taskDeadlines.substring(taskDeadlines.indexOf("/from") + 5).strip();
                }

                if (taskName.isEmpty()) {
                    throw new YapBotException("Error, Automated Task Suggestion module offline.\nTask details must be manually entered.");
                }

                if (to.isEmpty() && from.isEmpty()) {
                    throw new YapBotException("Error, start and end times not detected.\nUse command \"todo\" for tasks without deadlines.");
                }

                if (to.isEmpty()) {
                    throw new YapBotException("Error, end time not detected.\nManual input of end time required.");
                }

                if (from.isEmpty()) {
                    throw new YapBotException("Error, start time not detected.\nUse command \"deadline\" for tasks without start times.");
                }

                task = new Event(taskName, from, to);
                storedTasks.add(task);
                System.out.println(PREFIXLINE + "\nAdding Task...\nSuccess\nTask added to database:\n" + "  " + task + "\n" + "Total tasks: " + storedTasks.size() + "\n" + POSTFIXLINE);
                break;
            }

            default:
                System.out.println(PREFIXLINE + "\nAdding Task...\nFailure\nInvalid Task Type: " + tasktype + "\n" + POSTFIXLINE);

        }


    }

    public static void main(String[] args) {
        System.out.println(PREFIXLINE + "\nPowering up...\nSystem booted successfully.\nYapBot online.\nExtensive damage detected.\nCore Systems 28% functional.\n" + POSTFIXLINE);

        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        boolean continueLoop = true;

        while (continueLoop) {
            try {
                int spaceCharPos = input.indexOf(" ");
                String command = "";
                String commandDetails = "";

                if (spaceCharPos == -1) {
                    command = input;
                } else {
                    command = input.substring(0, input.indexOf(" "));
                    commandDetails = input.substring(input.indexOf(" ") + 1).strip();
                }

                if (command.isEmpty()) {
                    throw new YapBotException("Error, User Input Prediction module offline.\nManual input required.");
                }

                switch (command) {
                    case "bye": {
                        in.close();
                        continueLoop = false;
                        System.out.println(PREFIXLINE + "\nShutting down...\nYapBot process terminated.\n" + POSTFIXLINE);
                        break;
                    }

                    case "list": {
                        list();
                        input = in.nextLine();
                        break;
                    }

                    case "mark": {
                        if (commandDetails.isEmpty()) {
                            throw new YapBotException("Error, User Input Prediction module offline.\nTask number must be manually entered (eg. \"1\", \"2\").");
                        }

                        int index = 0;

                        if (commandDetails.contains(" ")) {
                            index = Integer.parseInt(commandDetails.substring(0, commandDetails.indexOf(" ")));
                        } else {
                            index = Integer.parseInt(commandDetails);
                        }

                        markOrUnmark(true, index);
                        input = in.nextLine();
                        break;
                    }

                    case "unmark": {
                        if (commandDetails.isEmpty()) {
                            throw new YapBotException("Error, User Input Prediction module offline.\nTask number must be manually entered (eg. \"1\", \"2\").");
                        }

                        int index = 0;

                        if (commandDetails.contains(" ")) {
                            index = Integer.parseInt(commandDetails.substring(0, commandDetails.indexOf(" ")));
                        } else {
                            index = Integer.parseInt(commandDetails);
                        }

                        markOrUnmark(false, index);
                        input = in.nextLine();
                        break;
                    }

                    case "todo": {
                        if (commandDetails.isEmpty()) {
                            throw new YapBotException("Error, Automated Task Suggestion module offline.\nTask details must be manually entered.");
                        }
                        createTask(Tasktype.TODO, commandDetails);
                        input = in.nextLine();
                        break;
                    }

                    case "deadline": {
                        if (commandDetails.isEmpty()) {
                            throw new YapBotException("Error, Automated Task Suggestion module offline.\nTask details must be manually entered.");
                        }
                        createTask(Tasktype.DEADLINE, commandDetails);
                        input = in.nextLine();
                        break;
                    }

                    case "event": {
                        if (commandDetails.isEmpty()) {
                            throw new YapBotException("Error, Automated Task Suggestion module offline.\nTask details must be manually entered.");
                        }
                        createTask(Tasktype.EVENT, commandDetails);
                        input = in.nextLine();
                        break;
                    }

                    default:
                        throw new YapBotException("Error, supporting module for user command: \"" + command + "\" not found.\nYapBot may not support this feature.");

                }
            } catch (NumberFormatException e) {
                System.out.println( PREFIXLINE + "\nError, Natural Language Processing Module offline...\nSpecify Task number instead (eg. \"1\", \"2\").\n" + POSTFIXLINE);
                input = in.nextLine();
            } catch (YapBotException e) {
                System.out.println(e.getMessage());
                input = in.nextLine();
            }
        }


    }
}
