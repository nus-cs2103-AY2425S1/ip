import java.util.ArrayList;
import java.util.List;

public class TaskList {

    private List<Task> list;
    public TaskList() {
        this.list = new ArrayList<Task>();
    }

    public void answer(String response) throws InvalidCommandException, InvalidNumberException {
        String command = response.contains(" ")
                ? response.substring(0, response.indexOf(' '))
                : response;
        String name;
        String startTime;
        String endTime;
        int index;
        switch (command) {
            case "todo":
                try {
                    name = response.substring(response.indexOf(' ') + 1);
                    this.add(new Todo(name));
                } catch (StringIndexOutOfBoundsException e) {
                    throw new InvalidCommandException();
                }
                break;
            case "deadline":
                try {
                    name = response.substring(response.indexOf(' ') + 1, response.indexOf('/') - 1);
                    endTime = response.substring(response.indexOf("/by") + 4);
                    this.add(new Deadline(name, endTime));
                } catch (StringIndexOutOfBoundsException e) {
                    throw new InvalidCommandException();
                }
                break;
            case "event":
                try {
                    name = response.substring(response.indexOf(' ') + 1, response.indexOf('/') - 1);
                    startTime = response.substring(response.indexOf("/from") + 6, response.indexOf("/to") - 1);
                    endTime = response.substring(response.indexOf("/to") + 4);
                    this.add(new Event(name, startTime, endTime));
                } catch (StringIndexOutOfBoundsException e) {
                    throw new InvalidCommandException();
                }
                break;
            case "mark":
                try {
                    index = Integer.parseInt(response.substring(response.indexOf(' ') + 1, response.indexOf(' ') + 2)) - 1;
                    this.markTask(index);
                } catch (StringIndexOutOfBoundsException e) {
                    throw new InvalidCommandException();
                } catch (NumberFormatException e) {
                    throw new InvalidNumberException();
                }
                break;
            case "unmark":
                try {
                    index = Integer.parseInt(response.substring(response.indexOf(' ') + 1, response.indexOf(' ') + 2)) - 1;
                    this.unmarkTask(index);
                } catch (StringIndexOutOfBoundsException e) {
                    throw new InvalidCommandException();
                } catch (NumberFormatException e) {
                    throw new InvalidNumberException();
                }
                break;
            case "delete":
                try {
                    index = Integer.parseInt(response.substring(response.indexOf(' ') + 1, response.indexOf(' ') + 2)) - 1;
                    this.delete(index);
                } catch (StringIndexOutOfBoundsException e) {
                    throw new InvalidCommandException();
                } catch (NumberFormatException e) {
                    throw new InvalidNumberException();
                }
            case "list":
                this.listOut();
                break;
            default:
                throw new InvalidCommandException();
        }
    }

    public void add(Task task) {
        this.list.add(task);
        System.out.println("I've added the task: ");
        System.out.println(task);
        System.out.println("Now you have " + this.list.size() + " tasks in the list");
    }

    public void listOut() {
        for (int i = 1; i <= this.list.size(); i++) {
            System.out.println(i + "." + this.list.get(i - 1));
        }
    }

    public void printTask(int index) {
        System.out.println(this.list.get(index));
    }

    public void markTask(int index) {
        this.list.get(index).mark();
        System.out.println("You have marked the following task as done!");
        System.out.println(this.list.get(index));
    }

    public void unmarkTask(int index) {
        this.list.get(index).unmark();
        System.out.println("You have unmarked the following task!");
        System.out.println(this.list.get(index));
    }

    public void delete(int index) {
        
    }
}
