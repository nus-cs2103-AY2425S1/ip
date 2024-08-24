import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class SumoTaskList {

    private final List<Task> tasks;
    private Storage storage;

    public SumoTaskList(Storage storage) throws IOException {
        //initialising
        this.tasks = new ArrayList<>();
        this.storage = storage;

        //adding tasks based on data
        String[] datas = storage.load();
        for (int i = 0; i < datas.length; i++) {
            try {
                tasks.add(Task.createFromData(datas[i]));
            } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException e) {
                System.out.println(
                        "Your saved file at line " + (i + 1) + " is corrupted. "
                        + "Sumo cannot read so Sumo will skip that and continue with the rest!"
                );
            }
        }


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
            storage.save(this.tasks);
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
            storage.save(this.tasks);
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
            storage.save(this.tasks);
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
                storage.save(this.tasks);
                break;
            default:
                throw new UnknownCommandException(command);
        }
        return false;
    }
}
