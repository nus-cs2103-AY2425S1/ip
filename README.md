# Chatsy
> "We cannot solve problems with the kind of thinking we employed when we came up with them" &ndash; [Albert Einstein](https://en.wikipedia.org/wiki/Albert_Einstein)

<br>
Chatsy frees your mind of having to remember things you need to do. It's,
<br>

<ul>
   <li>text-based</li>
   <li>easy to learn</li>
   <li><strike>FAST</strike> SUPER FAST to use</li>
</ul>

<br>
All you need to do is,
<br>

<ol>
   <li>download it from
      <a href="https://nus-cs2103-ay2425s1.github.io/website/schedule/week4/project.html">here</a>.
   </li>                                 
   <li>double-click it.</li>                              
   <li>add your tasks.</li>  
   <li>let it manage your tasks for you 😉</li>
</ol>

<br>
And it is <strong>FREE!</strong>
<br>

Features: <br> 
✅ Managing tasks <br> 
⬜️ Managing deadlines (coming soon) <br> 
⬜️ Reminders (coming soon)  <br> 

If you are a Java programmer, you can use it to practice Java too. Here's the `main` method:
```
    public static void main(String[] args) {
        storage = new Storage(LOCAL_DIRECTORY_PATH, LOCAL_FILE_PATH);
        ui = new UI();
        taskManager = new TaskManager();
        commandHandler = new CommandHandler(taskManager, ui);

        try {
            taskManager.setTasks(storage.loadTasks());
        } catch (LocalFileException e) {
            ui.output("Failed to load tasks: " + e.getMessage());
        }

        ui.greet(name);
        while (isRunning) {
            try {
                commandHandler.handle(ui.readCommand());
            } catch (ChatsyException e) {
                ui.output("Oops! " + e.getMessage());
            } catch (Exception e) {
                ui.output("An unexpected error occurred: " + e.getMessage());
            }
        }

        exit();
    }
```
