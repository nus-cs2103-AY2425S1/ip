# Boss chatbot

Welcome to the project of the Boss chatbot that keeps track of your tasks just for you!
It's named after my friends whom I like to call my bosses! I've included below instructions on how to use it.

## User Commands

Firstly, you can say "hello" to the Boss, which will generate the following response:

````
WHATS GOOD, MY HOMIE! I'm the boss!
How can I help you?
````

You can use the boss to create tasks!
1. {description} to create a simple task.
2. todo {description} to create a Todo task.
3. deadline {description} /by {date/string} to create a Deadline task.
4. event {description} /from {date/string} /to {date/string} to create an Event task.


You can mark/unmark/delete tasks using these commands:
- mark {number}
- unmark {number}
- delete {number}

There are also more advanced features:
- find {description} -> finds tasks with the corresponding description you are looking for!
- remind -> reminds you of upcoming tasks that have a deadline in the next 7 days
- list -> shows you all the tasks in the list

Lastly, you can say goodbye to the boss once you are done using it!

````
Bye! Have a wonderful day legend!
````
Note:
If you enter an invalid command, the boss will make sure to let you know by printing out an error message!


If you're a boss, there might be some hidden secret responses hehe! Thank you!