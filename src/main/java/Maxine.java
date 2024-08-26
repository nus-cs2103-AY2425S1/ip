import java.util.Scanner;
import java.util.ArrayList;

public class Maxine {

    static ArrayList<Task> list = new ArrayList<>();
    static String[] arr;
    static Scanner scanner = new Scanner(System.in);
    
    
    public Maxine() {
        // nothing
    }

    /**
     * This method displays the list of tasks
     */
    public static void showList() {
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + ". " + list.get(i));
        }
    }

    /**
     * 
     * @return
     */
    public static String ask() {
        System.out.print("What can I do for you today? : ");
        String answer = scanner.nextLine().toLowerCase();
        arr = answer.split(" ");
        return answer;
    }

    /**
     * This method helps to mark or unmark the task
     */
    public static void changeMark() {
        int mark = Integer.parseInt(arr[1]) - 1;
        Task curr = list.get(mark);
        curr.changeStatus();
    }

    /**
     * This method adds a todo task to the list
     */
    public static void todo() {
        StringBuilder sb = new StringBuilder();
        sb.append(arr[1]);
        for (int i = 2; i < arr.length; i++) {
            String word = " " + arr[i];
            sb.append(word);
        }
        Todo task = new Todo(sb.toString());
        list.add(task);
    }

    /**
     * This method adds a deadline task to the list
     * @throws MaxineException
     */
    public static void deadline() throws MaxineException {
        StringBuilder desc = new StringBuilder();
        StringBuilder ddl = new StringBuilder();
        desc.append(arr[1]);
        boolean isChecked = false;
        for (int i = 1; i < (arr.length - 1); i++) {
            if (arr[i].equals("/by")) {
                isChecked = true;
            }
        }
        if (!isChecked || arr[1].equals("/by")) {
            throw new MaxineException("Please follow this format: deadline [enter task] /by [enter deadline]");
        }
        boolean hasBy = false;
        for (int i = 2; i < arr.length; i++) {
            if (arr[i].equals("/by")) {
                hasBy = true;
            }
            else if (hasBy) {
                String word = " " + arr[i];
                ddl.append(word);
            } else {
                String word = " " + arr[i];
                desc.append(word);
            }
        }
        Deadline task = new Deadline(desc.toString(), ddl.toString());
        list.add(task);
    }
    
    public static void event() throws MaxineException {
        StringBuilder desc = new StringBuilder();
        StringBuilder start = new StringBuilder();
        StringBuilder end = new StringBuilder();
        desc.append(arr[1]);
        boolean hasFrom = false;
        boolean hasTo = false;
        for (int i = 2; i < (arr.length - 1); i++) {
            if (arr[i].equals("/from")) {
                hasFrom = true;
            }
            if (arr[i].equals("/to")) {
                hasTo = true;
            }
        }

        if (!hasFrom|| !hasTo || arr[1].equals("/from")) {
            throw new MaxineException("Please follow this format: event [enter event] /from [start date] /to [end date]");
        }
        boolean isAfterFrom = false;
        boolean isAfterTo = false;
        for (int i = 2; i < arr.length; i++) {
            if (arr[i].equals("/from")) {
                isAfterFrom = true;
            }
            else if (arr[i].equals("/to")) {
                isAfterFrom = false;
                isAfterTo = true;
            } else if (isAfterFrom) {
                String word = " " + arr[i];
                start.append(word);
            } else if (isAfterTo) {
                String word = " " + arr[i];
                end.append(word);
            } else {
                String word = " " + arr[i];
                desc.append(word);
            }
        }
        Event task = new Event(desc.toString(), start.toString(), end.toString());
        list.add(task);
    }
    
    public static void delete() {
        int mark = Integer.parseInt(arr[1]) - 1;
        Task curr = list.get(mark);
        curr.delete();
        list.remove(curr);
    }

    /**
     * This is the main method
     * @param args Command-line arguments passed to the program
     */
    public static void main(String[] args) throws Exception {
        System.out.println("Hi! Nice to meet you :) I am Maxine");
        while (true) {
            String answer = ask();
            if (answer.equals("bye")) {
                System.out.println("\nBye! I have been maxed out and am going to sleep. Hope to see you again soon!");
                break;
            } else if (answer.equals("list")) {
                showList();
            } else if (arr[0].equals("mark") || arr[0].equals("unmark")) {
                changeMark();
            } else if (arr[0].equals("todo")) {
                try {
                    todo();
                } catch (Exception e) {
                    System.out.println("Please follow this format: todo [enter task]");
                }
            } else if (arr[0].equals("deadline")) {
                try {
                    deadline();
                } catch (Exception e) {
                    System.out.println("Please follow this format: deadline [enter task] /by [enter deadline]");
                }
            } else if (arr[0].equals("event")) {
                try {
                    event();
                } catch (Exception e) {
                    System.out.println("Please follow this format: event [enter event] /from [start date] /to [end date]");
                }
            } else if (arr[0].equals("delete")) {
                delete();
            } else {
                System.out.println("please type in a command starting with todo, deadline, event, mark, unmark or list");
            }
        }

        scanner.close();
    }
    
}
