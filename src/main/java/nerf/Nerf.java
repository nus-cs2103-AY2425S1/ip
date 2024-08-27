package nerf;

import nerf.error.FilePermissionsException;
import nerf.error.InvalidDataException;
import nerf.io.Ui;
import nerf.storage.Storage;
import nerf.task.TaskList;


public class Nerf {
    private final Ui ui;
    private final Storage storage;
    private final TaskList taskList;
    

    public static void main(String[] args) {
        new Nerf("data/taskStorage.txt").run();
    }

    public Nerf(String filePath){
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        TaskList temp;
        try {
            temp = new TaskList(storage.load());
        } catch (FilePermissionsException e) {
            this.ui.showLoadingError();
            temp = new TaskList();
        }
        this.taskList = temp;
    }

    public void run(){
        this.ui.greetings();
        String input;
        do {
            input = ui.getInput();
            this.ui.printDivider();
            switch(input){
            case "bye" -> this.ui.exit();
            case "list" -> {
                this.taskList.printList();
                this.ui.printDivider();
            }
            default -> {
                try {
                    if (input.startsWith("mark")) {
                        this.taskList.markTask(input);
                    } else if (input.startsWith("unmark ")) {
                        this.taskList.unmarkTask(input);
                    } else if (input.startsWith("todo ")){
                        this.taskList.addTodo(input);
                    } else if (input.startsWith("deadline ")){
                        this.taskList.addDeadline(input);
                    } else if (input.startsWith("event ")){
                        this.taskList.addEvent(input);
                    } else if (input.startsWith("delete ")){
                        this.taskList.deleteTask(input);
                    } else {
                        System.out.println("""
                                           Sorry, I dont understand what u are asking of me.
                                           You may use list/bye or mark/unmark/todo/deadline/event/delete + required syntax
                                           """);
                    }
                    this.storage.save(this.taskList.getSaveable());
                } catch (InvalidDataException e) {
                    System.out.println("Sorry, your input is seems to be missing some data.");
                } finally{
                    this.ui.printDivider();
                }
                }
            }
        } while (!input.equals("bye"));   
    }
    
}
