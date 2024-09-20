# Not-gpt

This is a project is a basic chatbot which can be used to keep track of your tasks at hand.
Head to the Official [UserGuide](https://flyingsalsa.github.io/ip/) on how to use it! 

## Commands   
### Note that all commands are NOT case-sensitive and the very FIRST word before a whitespace will be read as the command. <br> Most commands must be of the form (command) xxx where xxx is follow up text. For example,
`todo task`
### where "todo" is the (command) and "new task" is the follow up text xxx.

## Basic commands
1. **list** - gives a list of all current tasks, all text after list will not be read      
1. **mark** - marks task given in the follow up text as completed by filling in the empty [  ] with a cross [X].  Follow up text must be a valid integer. 
1. **unmark** - unmarks a task given in the follow up text as completed reverting the [X] to a [  ].  Follow up text must be a valid integer.   
1. **delete** - deletes task given in the follow up text.  Follow up text must be a valid integer.
2. **find** - searches through all tasks and returns a list of tasks that contain the followup text.
3. **clear** - clears out the entire task list, including memory data.
## Task addition commands
5. **todo** - Creates a todo task, does not accept any additional text other than follow up text
6. **event** - Creates an event task, requires additional parameters marked by /from and /to in either order (eg. ```event xxx /from xxx /to xxx, where /to and /from can be flipped```)
7. **deadline** - Creates a Deadline task, requires additional parameter marked by /by (```eg. event xxx /by xxx```)


