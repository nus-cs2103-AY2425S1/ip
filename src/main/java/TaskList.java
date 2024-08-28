import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TaskList {

    private List<Task> list;
    public TaskList() {
        this.list = new ArrayList<Task>();
        try {
            File data = new File("./src/main/java/chatData.txt");
            Scanner dataReader = new Scanner(data);
            while (dataReader.hasNextLine()) {
                String line = dataReader.nextLine();
                char taskType = line.charAt(0);
                boolean done = false;
                switch (taskType) {
                case 'T':
                    done = line.charAt(1) == '1';
                    this.add(new Todo(line.substring(2), done), true);
                    break;
                case 'D':
                    done = line.charAt(1) == '1';
                    String deadline = line.substring(2, line.indexOf('|'));
                    this.add(new Deadline(line.substring(line.indexOf('|') + 1), deadline, done), true);
                    break;
                case 'E':
                    done = line.charAt(1) == '1';
                    String start = line.substring(2, line.indexOf('%'));
                    String end = line.substring(line.indexOf('%') + 1, line.indexOf('|'));
                    this.add(new Event(line.substring(line.indexOf('|') + 1), start, end, done), true);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("error");
        }
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
                    this.add(new Todo(name), false);
                } catch (StringIndexOutOfBoundsException e) {
                    throw new InvalidCommandException();
                }
                break;
            case "deadline":
                try {
                    name = response.substring(response.indexOf(' ') + 1, response.indexOf('/') - 1);
                    endTime = response.substring(response.indexOf("/by") + 4);
                    this.add(new Deadline(name, endTime), false);
                } catch (StringIndexOutOfBoundsException e) {
                    throw new InvalidCommandException();
                }
                break;
            case "event":
                try {
                    name = response.substring(response.indexOf(' ') + 1, response.indexOf('/') - 1);
                    startTime = response.substring(response.indexOf("/from") + 6, response.indexOf("/to") - 1);
                    endTime = response.substring(response.indexOf("/to") + 4);
                    this.add(new Event(name, startTime, endTime), false);
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
                break;
            case "list":
                this.listOut();
                break;
            default:
                throw new InvalidCommandException();
        }
    }

    public void add(Task task, boolean saved) {
        this.list.add(task);
        if (!saved) {
            System.out.println("I've added the task: ");
            System.out.println(task);
            System.out.println("Now you have " + this.list.size() + " tasks in the list");
        }
    }

    public void listOut() {
        for (int i = 1; i <= this.list.size(); i++) {
            System.out.println(i + "." + this.list.get(i - 1));
        }
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
        Task deleted = this.list.get(index);
        this.list.remove(index);
        System.out.println("Let's go deleting!");
        System.out.println("Deleted task " + deleted);
    }
}
