# Avo
> "A lot of hard work is hidden behind nice things." &ndash; [Ralph Lauren](https://en.wikipedia.org/wiki/Ralph_Lauren)

<br>
Avo frees your mind of having to remember things you need to do. It's,
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
      <a href="https://www.youtube.com/watch?v=xvFZjo5PgG0">here</a>.
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
      Storage<Task, String> storage = new FileStorage<Task>("data", new TaskParser());
              AppUI ui = new AppUI();
              TaskManager taskManager = new TaskManager(storage, ui);
              CommandParser parser = new CommandParser(new CommandManager(taskManager));
              Avo chatBot = new Avo(ui, parser);
              chatBot.start();
              chatBot.run();
              chatBot.stop();
    }
```