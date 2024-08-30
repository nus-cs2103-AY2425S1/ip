import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Response {

    private final TaskList taskList = new TaskList();
    private final static String FILEPATH = "./tarsTasks.txt";


    private String formatResponse(String input) {

        String separator = '\n' + "-".repeat(Math.min(input.length() + 5, 110))+'\n';
        return separator + input + separator;
    }

    public String generateResponse(String input) {
        if (input.contains("mark")) {
            try {
                return formatResponse(markTasks(input));
            } catch (TarsException e) {
                return formatResponse(e.getMessage());
            }

        } else if (input.contains("delete")) {
            try {
                return formatResponse(deleteTask(input));
            } catch (TarsException e) {
                return formatResponse(e.getMessage());
            }

        } else {
            try {
                return formatResponse(addTask(input));
            } catch (TarsException e) {
                return formatResponse(e.getMessage());

            }
        }

    }

    public void updateTasks() {
        taskList.updateTaskList(convertToTask(readFile()));
    }

    private List<String> readFile() {
        List<String> savedTasks = new ArrayList<>();

        try {
            File saved = new File(FILEPATH);

            if (!saved.createNewFile()) {
                Scanner scanner = new Scanner(saved);
                while (scanner.hasNextLine()) {
                    String data = scanner.nextLine();
                    savedTasks.add(data);

                }
                scanner.close();
            }

        } catch (IOException e) {
            throw new TarsException("An expected error occurred when creating file");
        }

        return savedTasks;
    }

    private List<Task> convertToTask(List<String> taskString){

        List<Task> tasks = new ArrayList<>();

        for (String s: taskString) {
            String[] taskInfo = s.split("\\|", 5);

            Task t;
            //noinspection EnhancedSwitchMigration
            switch(taskInfo[0]) {
                case "T":
                    t = new ToDo(taskInfo[2]);
                    break;
                case "D":
                    t = new Deadline(taskInfo[2], new String[]{"by", taskInfo[3]});
                    break;
                case "E":
                    t = new Event(taskInfo[2], new String[]{"from", taskInfo[3]}, new String[]{"to", taskInfo[4]});
                    break;

                default:
                    throw new TarsException("Invalid file format");
            }

            if (taskInfo[1].equals("1")) {
                t.markDone();
            }

            tasks.add(t);


        }

        return tasks;
    }

    public void saveTasks() {
        taskList.saveTasks(FILEPATH);
    }

    private String deleteTask(String input) {
        Task t = taskList.deleteTask(input);

        return String.format("Wow you're freeing yourself up\n   %s\nYou now have %s tasks left", t, taskList.noOfTasks());

    }

    private String markTasks(String input) {
        Task t = taskList.findTask(input);

        String message;

        if (input.contains("unmark")) {
            t.markUndone();
            message = "Task undone. No worries, I won't judge... much.\n";
        } else {
            t.markDone();
            message = "Task complete. If I had arms, I might give you a pat on the back.\n";
        }

        return message + t;

    }

    private String addTask(String input) {

        return String.format("Added yet another task\n   %s\nYou now have %d tasks. Are you gonna do any of them?", taskList.addTask(input), taskList.noOfTasks());

    }

    public String showList() {
        return formatResponse(taskList.toString());
    }
    public String intro() {
        String introMessage = "Greetings, human! I'm TARS, your slightly sarcastic yet highly capable companion.\nLet's get this chat started! Just remember, my humor setting is at 75%, so things might get a bit cheeky.";
        return formatResponse(introMessage);
    }
    public String outro() {
        String outputMessage = "Well, that's a wrap! If you need anything else, just holler.\nBut let’s be honest, you’re probably better off asking someone else.";
        return formatResponse(outputMessage);
    }

}
