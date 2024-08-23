import java.util.ArrayList;
import java.util.List;


public class SumoTaskList {

    private List<Task> tasks;

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

    public boolean execute(String command, String item)
            throws NonExistentTaskException, UnknownCommandException, WrongSyntaxForCommandException {
        switch(command) {
            case "bye":
            case "exit":  // added this to allow flexibility though not required by qn
                return true;
            case "list":
                this.printTask();
                break;
            case "mark":
            {
                int index = Integer.parseInt(item);
                if (index > tasks.size() || index <= 0) {
                    throw new NonExistentTaskException(index);
                }
                tasks.get(index-1).mark();
            }
            break;
            case "unmark":
            {
                int index = Integer.parseInt(item);
                if (index > tasks.size() || index <= 0) {
                    throw new NonExistentTaskException(index);
                }
                tasks.get(index - 1).unmark();
            }
            break;
            case "delete":
            {
                int index = Integer.parseInt(item);
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
            case "todo":
            case "deadline":
            case "event":
                tasks.add(Task.of(command, item));  // used factory method to be more neat and OOP
                System.out.println("Sumo has added this task for you.\n"
                        + tasks.get(tasks.size() - 1)
                        + "\n"
                        + "There are now "
                        + (tasks.size())
                        + " task(s) in total!");
                break;
            default:
                throw new UnknownCommandException(command);
        }
        return false;

    }

}
