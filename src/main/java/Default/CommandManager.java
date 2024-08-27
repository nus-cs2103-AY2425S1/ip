package Default;

import Commands.ByeCommand;
import Tasks.ToDo;
import Exceptions.NedException;
import Tasks.Deadline;
import Tasks.Event;
import Tasks.Task;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;

public class CommandManager {
    private static final String FLAG_BYE = "bye";
    private final ArrayList<Task> listOfTasks;


    public CommandManager(ArrayList<Task> listOfTasks) {
        this.listOfTasks = listOfTasks;
    }
    public void processCommand(String userInput, FlagWrapper flag) throws NedException{
            CommandTypes command = CommandTypes.findMatchingCommand(userInput);
            switch (command) {
            case BYE:
                ((ByeCommand)command.getCommand()).execute(flag);
                break;
            case UNKNOWN:
                throw new NedException("M'lord, you seem to have given me a nonsensical command." +
                        " Input a correct command, for we have little time! Winter is coming....");
            default:
                command.getCommand().execute(userInput, this.listOfTasks);
                rewriteTaskListIntoCache(Ned.cachedTasksPath);
                break;
            }
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
                return ToDo.createToDo(splitLine[2], isTaskDone);
            case "deadline":
                return Deadline.createDeadline(splitLine[2], splitLine[3], isTaskDone);
            case "event":
                return Event.createEvent(splitLine[2], splitLine[3], splitLine[4], isTaskDone);
            }
        } catch (NedException e) {
            Ui.print(e.getMessage());
        } catch (IndexOutOfBoundsException e) {
            Ui.print(String.format("M'lord, it appears that this line: %s is saved in the wrong format.", savedLine));
        } catch (NumberFormatException e) {
            Ui.print(String.format("M'lord, it appears that this line: %s is saved with an invalid status number.",
                    savedLine));
        }
        return null;
    }

    private void rewriteTaskListIntoCache(String cacheFilePath) {
        //will rewrite the whole file
        int sizeOfList = this.listOfTasks.size();
        try {
            FileWriter fw = new FileWriter(cacheFilePath);
            for (int i = 0; i < sizeOfList; i++) {
                 Task currentTask = this.listOfTasks.get(i);
                 fw.write(currentTask.toTextForm() + "\n");
            }
            fw.close();
        } catch (FileNotFoundException e) {
            Ui.print(e.getMessage());
        } catch (IOException e) {
            Ui.print("M'lord, it appears there was an error accessing the cached file, please check that I am able " +
                    "to access it");
        }
    }

}
