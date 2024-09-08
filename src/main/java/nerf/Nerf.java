package nerf;

import nerf.error.FilePermissionsException;
import nerf.error.InvalidDataException;
import nerf.io.Ui;
import nerf.storage.Storage;
import nerf.task.TaskList;

/**
 * Main class
 */
public class Nerf {
    private final Ui ui;
    private final Storage storage;
    private final TaskList taskList;
    
    /**
     * Provides the save file name and run main loop.
     * 
     * @param args command line parameters.
     */
    public static void main(String[] args) {
        new Nerf("data/taskStorage.txt").run();
    }

    /**
     * Initializes variables and load in the given save file.
     * 
     * @return string format of task.
     */
    public Nerf(String filePath) {
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

    /**
     * Main loop
     */
    public void run() {
        Ui.printGreetings();
        String input;
        do {
            input = ui.getInput();
            System.out.println(Ui.printDivider());
            System.out.println(handleCommand(input));
        } while (!input.equals("bye"));   
    }

    /**
     * Returns greeting.
     * 
     * @return greeting message.
     */
    public String getGreeting() {
        return this.ui.getGreetings();
    }

    /**
     * Handles user input and return the resulting response string.
     * 
     * @param input user command.
     * @return chatbot response.
     */
    public String handleCommand(String input) {
        switch(input){
        case "bye":
            return this.ui.getExit();
        
        case "list": 
            return this.taskList.printList();
        
        default:
            try {
                if (input.startsWith("mark")) {
                    return this.taskList.markTask(input);
                } else if (input.startsWith("unmark ")) {
                    return this.taskList.unmarkTask(input);
                } else if (input.startsWith("todo ")){
                    return this.taskList.addTodo(input);
                } else if (input.startsWith("deadline ")){
                    return this.taskList.addDeadline(input);
                } else if (input.startsWith("event ")){
                    return this.taskList.addEvent(input);
                } else if (input.startsWith("delete ")){
                    return this.taskList.deleteTask(input);
                } else if (input.startsWith("find ")) {
                    return this.taskList.findTasks(input);
                } else {
                    return  """
                            Sorry, I dont understand what u are asking of me.
                            You may use list/bye or mark/unmark/todo/deadline/event/delete/find + required syntax
                            """;
                }
                
            } catch (InvalidDataException e) {
                return "Sorry, your input is seems to be missing some data.";
            } finally {
                this.storage.save(this.taskList.getSaveable());
            }
            }
    }
    
}
