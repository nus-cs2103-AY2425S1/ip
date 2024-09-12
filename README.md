# Not-gpt

This is a project is a basic chatbot which can be used to keep track of your tasks at hand.
Head to [releases](https://github.com/flyingsalsa/ip/releases/tag/v0.1.1) on how to use it! 

## Commands   
### Note that all commands are NOT case-sensitive and the very FIRST word before a whitespace will be read as the command. <br> Most commands must be of the form (command) xxx where xxx is follow up text. For example,
`todo new task`
### where "todo" is the (command) and "new task" is the follow up text xxx.

## Basic commands
1. **list** - gives a list of all current tasks, all text after list will not be read      
1. **mark** - marks task given in the follow up text as completed by filling in the empty [  ] with a cross [X].  Follow up text must be a valid integer. 
1. **unmark** - unmarks a task given in the follow up text as completed reverting the [X] to a [  ].  Follow up text must be a valid integer.   
1. **delete** - deletes task given in the follow up text.  Follow up text must be a valid integer.
2. **find** - searches through all tasks and returns a list of tasks that contain the followup text.
## Additional commands
5. **todo** -
6. **event** -
7. **deadline** -


