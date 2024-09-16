# Echo - your personal task manager!

**Features:**

- Adding and removing tasks
- Marking and unmarking tasks (as completed)
- Task types
- Save tasks on hard drive

**Task Types:**
1. Todo
2. Deadline: able to specify deadline date
   (processed as `LocalDateTime` objects)
3. Event: able to specify start and end times

**Run the Echo chatbot by running main**
``` java 
public static void main (String args[]) throws InvalidCommandError, EmptyDescriptionError {
        Echo echo = new Echo();
        echo.run();
    }
``` 

**Project milestones:**
- [x] Tasks types and inputting dates
- [x] Graphical User Interface:


> [!NOTE]
> Saves tasks to a file called tasks.ser

[Click me](https://www.youtube.com/watch?v=dQw4w9WgXcQ&skip_registered_account_check=true):grinning:
