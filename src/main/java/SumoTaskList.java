import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class SumoTaskList {

    private final List<Task> tasks;
    private FileWriter writer;

    public SumoTaskList(String filePath) throws IOException {
        this.tasks = new ArrayList<>();
        File f = new File(filePath);

        if (f.exists()) {
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                tasks.add(Task.createFromData(s.nextLine()));
            }
        } else {
            if(!f.createNewFile()) {
                System.out.println("Welp! Sumo unable to save data due to unknown error!\n"
                        + "Please exit and try again if u wanna save");
            }
        }
        this.writer = new FileWriter(filePath, true);

    }

    public SumoTaskList() {
        this.tasks = new ArrayList<>();

    }

    private void printTask() {
        System.out.println("Below is the list of tasks:");
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task == null) {
                break;
            }
            System.out.println((i+1) + ". "+ task);
        }
    }

    public boolean execute(Command command, String item)
            throws NonExistentTaskException, UnknownCommandException, WrongSyntaxForCommandException {
        switch(command) {
            case BYE:
            case EXIT:  // added this to allow flexibility though not required by qn
                return true;
            case LIST:
                this.printTask();
                break;
            case MARK:
            {
                int index;
                try {
                    index = Integer.parseInt(item);
                } catch (IllegalArgumentException e) {
                    throw new WrongSyntaxForCommandException(command);
                }

                if (index > tasks.size() || index <= 0) {
                    throw new NonExistentTaskException(index);
                }
                tasks.get(index-1).mark();
            }
            break;
            case UNMARK:
            {
                int index;
                try {
                    index = Integer.parseInt(item);
                } catch (IllegalArgumentException e) {
                    throw new WrongSyntaxForCommandException(command);
                }

                if (index > tasks.size() || index <= 0) {
                    throw new NonExistentTaskException(index);
                }
                tasks.get(index - 1).unmark();
            }
            break;
            case DELETE:
            {
                int index;
                try {
                    index = Integer.parseInt(item);
                } catch (IllegalArgumentException e) {
                    throw new WrongSyntaxForCommandException(command);
                }
                if (index > tasks.size() || index <= 0) {
                    throw new NonExistentTaskException(index);
                }
                System.out.println(
                        "Sumo removed this task for you.\n"
                                + tasks.get(index - 1)
                                + "\n"
                                + "There are now "
                                + (tasks.size()-1)
                                + " task(s) in total!"
                );
                tasks.remove(index - 1);
            }
            break;
            case TODO:
            case DEADLINE:
            case EVENT:
                Task newlyAdded = Task.of(command, item);
                tasks.add(newlyAdded);  // used factory method to be more neat and OOP
                System.out.println("Sumo has added this task for you.\n"
                        + newlyAdded
                        + "\n"
                        + "There are now "
                        + (tasks.size())
                        + " task(s) in total!");
                try {
                    writer.write(newlyAdded.savedString() + "\n");
                } catch (IOException e) {
                    System.out.println("Sumo cannot save file. Neither can I save you. :(");
                }

                break;
            default:
                throw new UnknownCommandException(command);
        }
        return false;

    }

    public void finaliseChange() {
        try {
            writer.close();
        } catch (IOException e) {
            System.out.println("DIE. SUMO UNABLE TO SAVED EVERYTHING IN THIS SESSION!");
        }

    }

}
