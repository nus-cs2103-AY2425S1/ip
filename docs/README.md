# Elster User Guide
> "Plan for what it is difficult while it is easy, do what is great while it is small.” - Sun Tzu [(source)](https://www.goodreads.com/quotes/964849-plan-for-what-it-is-difficult-while-it-is-easy)

https://SeanFoongjt.github.io/ip/Ui.png

Elster is a chatbot that does it's best to help you out. It's
- **Speedy**
- **Intuitive**
- **And ~~maybe~~ just a bit sarcastic**

But above all, it'll ensure that all your tasks are **done on time!**

## Features

### <span style="color:#9457eb">Adding a todo task</span>
Have Elster add a todo task to your list.

Format:`todo {description}` \
Example:`todo borrow book`


### <span style="color:#9457eb">Adding a deadline task</span>
Have Elster add a deadline task to your list.

Format:`deadline {description} /by {deadline time}` \
- Note that the deadline time has to be in yyyy-MM-dd or yyyy-MM-dd HH:mm

Example:`deadline return book to library /by 2024-09-22`

### <span style="color:#9457eb">Adding an event task</span>
Have Elster add an event task to your list. (We're getting a bit 
repetitive here, but hey the format is still slightly different)

Format:`event {description} /from {start time} /to {end time}`
- Note that the start and end times have to be in yyyy-MM-dd or yyyy-MM-dd HH:mm

Example:`event Party at Elm /from 2024-09-24 12:00 /to 2024-09-24 14:00`

### <span style="color:#9457eb">Looking at your task list</span>
Take a look at the task list Elster maintains so you know what you need to do.

Format:`list` \
Example:`list`

https://SeanFoongjt.github.io/ip/List.png

### <span style="color:#9457eb">Mark a task if done</span>
Have Elster mark a task if done. (Good job!)

Format:`mark {task index}` \
Example:`mark 2`

### <span style="color:#9457eb">Unmark a task if undone</span>
Have Elster unmark a task if undone. (It happens)

Format:`unmark {task index}` \
Example:`unmark 2`

### <span style="color:#9457eb">Delete a task</span>
Have Elster delete a task from the list.

Format:`delete {task index}` \
Example:`delete 3`

### <span style="color:#9457eb">Find a task by description</span>
Find any task Elster is keeping track of by its description.

Format:`find {task description}` \
Example:`find book`

### <span style="color:#9457eb">Untagging a task</span>
Add a descriptive task to your task! (Or really, anything you want)

Format:`tag {task index} /tag {description}` \
Example:`tag 1 /tag urgent`


### <span style="color:#9457eb">Untagging a task</span>
Just like how you can remove a task, you can also remove a tag from a task.

Format:`untag {task index} /tag {description}` \
Example:`untag 1 /tag urgent`


### <span style="color:#9457eb">Exiting Elster</span>
To exit Elster, simply tell her bye.

Format:`bye` \
Example:`bye`
