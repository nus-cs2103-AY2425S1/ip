# Bee
A chatbot that takes care of your todo list and task tracking!

## Adding todos
Type into chatbot: `todo <task name>`

## Adding deadlines
Type into chatbot: `deadline <task name> /by <time>`

## Adding events
Type into chatbot: `event <task name> /from <time> /to <time>`

## Getting help in the app
type `help` into the chatbot and you will receive the list of commands:

    There are three types of task, you can add them by typing:
        todo <task name>
        deadline <task name> /by <time>
        event <task name> /from <time> /to <time>
    You can view all your tasks and their respective indices by:
        list
    You edit your tasks by:
        mark <task index>
        unmark <task index>
        delete <task index>
    You search your tasks by:
        find <partial name>
    
    Format your time in:
        yyyy-MM-dd HHmm (24Hr)
            or yyyy-MM-dd hh:mm am (12Hr).
        You can replace - with \ as well;