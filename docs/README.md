# Alice User Guide

![Representative Screenshot]("./Ui.png")

`Alice` is a chatbot for task management.

To run `Alice`,

```
gradlew run
```

To run `Alice` from the `.jar` release,

```
java -jar alice.jar
```

To run tests,

```
gradlew test
```

To check that the source code complies with style guidelines,

```
gradlew checkstyleMain checkstyleTest 
```

To export `Alice`,

```
gradlew clean shadowJar
```

This will create `alice.jar` within `build/libs`.

## Adding Tasks

`Alice` supports 3 different types of tasks:

- `ToDo`
- `Deadline`
- `Event`

When an event is added, the UI will render the task that was just added.

### To-Dos

```
todo <name>
```

### Deadlines

```
deadline <name> /by <deadline datetime>
```

### Event

```
event <name> /from <start datetime> /to <end datetime>
```

## View Tasks

To view existing tasks,

```
list
```

The UI will render a list of all current tasks.

## Delete Tasks

To delete a task,

```
delete <task number>
```

The UI will render the deleted task.

## Mark/Unmark Tasks

To mark a task,

```
mark <task number>
```

To unmark a task,

```
unmark <task number>
```

The UI will render the marked/unmarked task.

## Tag Tasks

To assign a tag to a task,

```
tag <task number> <tag description>
```

The UI will render the tagged task.

## Saving Tasks

Tasks are saved whenever any task is added/deleted/modified. Each task is serialized in `JSON` format, and stored as lines of `JSON` in `data/tasks.jsonl`.

## Exitting

To exit, 

```
bye
```

This will quit the application.