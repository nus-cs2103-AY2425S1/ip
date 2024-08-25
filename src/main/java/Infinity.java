import java.io.IOException;

public class Infinity <T extends Task> {
    
    enum KnownCommands {
        BYE, 
        DEADLINE, 
        DELETE,
        EVENT, 
        LIST, 
        MARK, 
        SAVE,
        TODO
    }

    Ui botUI = new Ui();
    Storage storage = new Storage(botUI);
    TaskList botTasks;

    public Infinity() {

        this.botTasks = new TaskList(storage.readFile(), botUI);

        while (true) {
            String currentInput = botUI.nextInput();

            try {

                if (currentInput.equals(KnownCommands.BYE.toString().toLowerCase())) {

                    botUI.endBot();

                } else if (currentInput.equals(KnownCommands.LIST.toString().toLowerCase())) {

                    botUI.listTasks(this.botTasks);

                } else if (currentInput.startsWith(KnownCommands.MARK.toString().toLowerCase()) 
                        && currentInput.length() > 5) {

                    this.botTasks.markTask(currentInput);

                } else if (currentInput.startsWith(KnownCommands.TODO.toString().toLowerCase()) 
                        && currentInput.length() > 5) {

                    this.botTasks.addTask(new ToDos(currentInput.substring(5)));

                } else if (currentInput.startsWith(KnownCommands.DEADLINE.toString().toLowerCase()) 
                        && currentInput.length() > 9) {

                    this.botTasks.addTask(new Deadline(currentInput.substring(9)));

                } else if (currentInput.startsWith(KnownCommands.EVENT.toString().toLowerCase()) 
                        && currentInput.length() > 6) {

                    this.botTasks.addTask(new Event(currentInput.substring(6)));

                } else if (currentInput.startsWith(KnownCommands.DELETE.toString().toLowerCase()) 
                        && currentInput.length() > 7) {

                    this.botTasks.deleteTask(currentInput.substring(7));

                } else if (currentInput.startsWith(KnownCommands.SAVE.toString().toLowerCase())) {
                    
                    try {
                        storage.saveFile(this.botTasks.getTasks());
                    } catch (IOException e) {
                        botUI.botSays(
                                "I'm sorry, I'm a noob at this, I can't save the file, can you help me debug? " 
                                + e.getMessage());
                    }

                } else {

                    botUI.echo(currentInput);

                }

            } catch (InfinityException e) {

                botUI.botSays(e.getMessage());

            }
        }
    }

    public static void main(String[] args) {
        Infinity infinityBot = new Infinity();
    }

}
