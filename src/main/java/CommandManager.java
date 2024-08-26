import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.PatternSyntaxException;

public class CommandManager {

    private final static String REGEX_MARK = "^mark.*";
    private final static String REGEX_UNMARK = "^unmark.*";

    private final static String REGEX_TODO = "^todo.*";

    private final static String REGEX_DEADLINE = "^deadline.*";

    private final static String REGEX_EVENT = "^event.*";

    private final static String REGEX_DELETE = "^delete.*";

    private final static String FLAG_LIST = "list";
    private static final String FLAG_BYE = "bye";
    private final ArrayList<Task> listOfText;

    private enum CommandTypes {
        MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, LIST, BYE, UNKNOWN
    }

    public final static String COMMAND_MESSAGE = """
            Usage: 
                list                             - Shows the list of all tasks 
                mark <item-index>                - Marks the item at the specified index as done
                unmark <item-index>              - Marks the item at the specified index as undone 
                delete <item-index>              - Removes the item at the specified index from the list            
                todo <description>               - Creates a new todo task and adds it to the list
                deadline <description> /by <date> - Creates a new deadline task and adds it to the list
                event <description> /from <date> /to <date> - Creates a new event task and adds it to the list""";

    public CommandManager(ArrayList<Task> listOfText) {
        this.listOfText = listOfText;
    }
    public static Task parseSavedTask(String savedLine) {
        // uses CSV
        String[] splitLine = savedLine.split("\\|");
        String taskType = splitLine[0];
        try {
            int taskStatus = Integer.parseInt(splitLine[1]);
            boolean isTaskDone = taskStatus != 0;
            switch (taskType) {
            case "todo":
                return ToDo.createToDo(isTaskDone, splitLine[2]);
            case "deadline":
                return Deadline.createDeadline(isTaskDone, splitLine[2], splitLine[3]);
            case "event":
                return Event.createEvent(isTaskDone, splitLine[2], splitLine[3], splitLine[4]);
            }
        } catch (NedException e) {
            Ned.print(e.getMessage());
        } catch (IndexOutOfBoundsException e) {
            Ned.print(String.format("M'lord, it appears that this line: %s is saved in the wrong format.", savedLine));
        } catch (NumberFormatException e) {
            Ned.print(String.format("M'lord, it appears that this line: %s is saved with an invalid status number.",
                    savedLine));
        }
        return null;
    }

    private void addTaskToList(Task newTask, ArrayList<Task> listOfText) {
        listOfText.add(newTask);
        Ned.print("Aye, I've added this task m'lord:");
        Ned.print(Ned.INDENTATIONS + newTask);
        Ned.print("Now you've " + listOfText.size() + " tasks left. Get to it then!");
        appendTaskToCache(newTask, "cachedTasks.txt");
    };

    private void appendTaskToCache(Task newTask, String cacheFilePath) {
        try {
            FileWriter fw = new FileWriter(cacheFilePath, true);
            fw.write(newTask.toTextForm() + "\n");
            fw.close();
        } catch (IOException e) {
            Ned.print("M'lord, it appears there was an error saving this task to the list");
        }
    }

    private void rewriteTaskListIntoCache(String cacheFilePath) {
        //will rewrite the whole file
        int sizeOfList = this.listOfText.size();
        try {
            FileWriter fw = new FileWriter(cacheFilePath);
            for (int i = 0; i < sizeOfList; i++) {
                 Task currentTask = this.listOfText.get(i);
                 fw.write(currentTask.toTextForm() + "\n");
            };
            fw.close();
        } catch (FileNotFoundException e) {
            Ned.print(e.getMessage());
        } catch (IOException e) {
            Ned.print("M'lord, it appears there was an error accessing the cached file, please check that I am able " +
                    "to access it");
        }
    }

    private void executeTaskDeletion(int taskIndex) {
        Task selectedTask = listOfText.get(taskIndex);
        Ned.print("Noted m'lord. The following task has been removed:");
        Ned.print(Ned.INDENTATIONS + selectedTask);
        listOfText.remove(taskIndex); //removes the index specified
        Ned.print(String.format("Now you've %d tasks in the list. Get to it then.", listOfText.size()));
        rewriteTaskListIntoCache("cachedTasks.txt");
    }

    private void executeTaskMark(int taskIndex){
        try {
            Task selectedTask = listOfText.get(taskIndex);
            selectedTask.markAsDone();
            Ned.print("Good work. One down, more to go!");
            Ned.print(selectedTask.toString());
            rewriteTaskListIntoCache("cachedTasks.txt");
        } catch (PatternSyntaxException e) {
            Ned.print("Sorry m'lord, seems there was a typo in the command try again.");
            Ned.print(CommandManager.COMMAND_MESSAGE);
        } catch (NumberFormatException e) {
            //never executed because of regex
            Ned.print("Sorry m'lord, your command must specify a valid number");
            Ned.print(CommandManager.COMMAND_MESSAGE);
        } catch (IndexOutOfBoundsException e) {
            Ned.print("Sorry m'lord, seems the item number you specified is not valid");
            Ned.print(CommandManager.COMMAND_MESSAGE);
        }
    }

    private void executeTaskUnmark(int taskIndex) {
        try {
            Task selectedTask = listOfText.get(taskIndex);
            selectedTask.markAsNotDone();
            Ned.print("Oh no. One back up, more to go!");
            Ned.print(selectedTask.toString());
            rewriteTaskListIntoCache("cachedTasks.txt");
        } catch (PatternSyntaxException e) {
            Ned.print("Sorry m'lord, seems there was a typo in the command try again.");
            Ned.print(CommandManager.COMMAND_MESSAGE);
        } catch (NumberFormatException e) {
            //never executed because of regex
            Ned.print("Sorry m'lord, your command must specify a valid number");
            Ned.print(CommandManager.COMMAND_MESSAGE);
        } catch (IndexOutOfBoundsException e) {
            Ned.print("Sorry m'lord, seems the item number you specified is not valid");
            Ned.print(CommandManager.COMMAND_MESSAGE);
        }
    }
    public void processCommand(String input, FlagWrapper flag) {
        CommandTypes command = checkForCommands(input);
        int taskIndex;
        try {
            switch (command) {
            case MARK:
                taskIndex = new MarkAndUnmarkCommand().parseMarkOrUnmarkCommand(input);
                if (taskIndex != -1) {
                    executeTaskMark(taskIndex);
                }
                break;
            case UNMARK:
                taskIndex = new MarkAndUnmarkCommand().parseMarkOrUnmarkCommand(input);
                if (taskIndex != -1) {
                    executeTaskUnmark(taskIndex);
                }
                break;
            case TODO:
                addTaskToList(ToDo.createTask(input), this.listOfText);
                break;
            case DEADLINE:
                addTaskToList(Deadline.createTask(input), this.listOfText);
                break;
            case EVENT:
                addTaskToList(Event.createTask(input), this.listOfText);
                break;
            case DELETE:
                int indexToRemove = new DeleteCommand(input).parseDeleteCommand();
                if (indexToRemove != -1) {
                    executeTaskDeletion(indexToRemove);
                };
                break;
            case LIST:
                new ListCommand().executeListCommand(this.listOfText);
                break;
            case BYE:
                flag.setStatus(false);
                break;
            case UNKNOWN:
            default:
                throw new NedException("M'lord, you seem to have given me a nonsensical command." +
                        " Input a correct command, for we have little time! Winter is coming....");
            }

        } catch (NedException e) {
            Ned.print(e.getMessage());
            Ned.print(CommandManager.COMMAND_MESSAGE);
        }
    }

    private CommandTypes checkForCommands(String input) {
        //checks whether the input fits into all existing command types known
        if (isMarkCommand(input)) return CommandTypes.MARK;
        if (isUnmarkCommand(input)) return CommandTypes.UNMARK;
        if (isListCommand(input)) return CommandTypes.LIST;
        if (isByeCommand(input)) return CommandTypes.BYE;
        if (isDeleteCommand(input)) return CommandTypes.DELETE;
        if (isTodoTask(input)) return CommandTypes.TODO;
        if (isDeadlineTask(input)) return CommandTypes.DEADLINE;
        if (isEventTask(input)) return CommandTypes.EVENT;
        else return CommandTypes.UNKNOWN;
    }

    private boolean isListCommand(String input) {
        return input.equalsIgnoreCase(CommandManager.FLAG_LIST);
    }

    private boolean isByeCommand(String input) {
        return input.equalsIgnoreCase(CommandManager.FLAG_BYE);
    }

    private boolean isDeleteCommand(String input) {
        return Pattern.matches(REGEX_DELETE, input);
    }

    private boolean isMarkCommand(String input) {
        return Pattern.matches(REGEX_MARK, input);
    }

    private boolean isUnmarkCommand(String input) {
        return Pattern.matches(REGEX_UNMARK, input);
    }

    private boolean isTodoTask(String input) {
        return Pattern.matches(REGEX_TODO, input);
    }

    private boolean isDeadlineTask(String input) {
        return Pattern.matches(REGEX_DEADLINE, input);
    }

    private boolean isEventTask(String input) {
        return Pattern.matches(REGEX_EVENT, input);
    }
}
