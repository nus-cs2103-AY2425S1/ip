package maxine;

import maxine.task.Task;

import java.util.Scanner;

public class Maxine {
    
    static String[] arr;
    static Scanner scanner = new Scanner(System.in);
    private Ui ui;
    private Storage storage;
    private TaskList tasks;
    
    public Maxine() {
        ui = new Ui();
        storage = new Storage("data/maxine.txt");
        tasks = new TaskList(storage.load());
    }
    
    public void run() {
        ui.greet();
        storage.load();

        while (true) {
            String answer = ask();
            if (answer.equals("bye")) {
                ui.goodbye();
                break;
            } else if (answer.equals("list")) {
                ui.showList(tasks);
            } else if (arr[0].equals("mark") || arr[0].equals("unmark")) {
                changeMark();
                storage.refreshStorage(tasks);
            } else if (arr[0].equals("todo")) {
                try {
                    tasks.addTodo(arr);
                    storage.refreshStorage(tasks);
                } catch (Exception e) {
                    System.out.println("Please follow this format: todo [enter maxine.task]");
                }
            } else if (arr[0].equals("deadline")) {
                try {
                    tasks.addDeadline(arr);
                    storage.refreshStorage(tasks);
                } catch (Exception e) {
                    System.out.println("Please follow this format: deadline [enter maxine.task] /by [enter deadline]");
                }
            } else if (arr[0].equals("event")) {
                try {
                    tasks.addEvent(arr);
                    storage.refreshStorage(tasks);
                } catch (Exception e) {
                    System.out.println("Please follow this format: event [enter event] /from [start date] /to [end date]");
                }
            } else if (arr[0].equals("delete")) {
                delete();
                storage.refreshStorage(tasks);
            } else {
                System.out.println("please type in a command starting with todo, deadline, event, mark, unmark or list");
            }
        }

        scanner.close();
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
     * This method helps to mark or unmark the maxine task
     */
    public void changeMark() {
        int mark = Integer.parseInt(arr[1]) - 1;
        Task curr = tasks.get(mark);
        curr.changeStatus();
        ui.changeMark(curr);
    }
    
    public void delete() {
        int mark = Integer.parseInt(arr[1]) - 1;
        Task curr = tasks.get(mark);
        curr.delete();
        tasks.delete(curr);
        ui.delete(curr);
    }

    /**
     * This is the main method
     * @param args Command-line arguments passed to the program
     */
    public static void main(String[] args) {
        new Maxine().run();
    }
    
}
