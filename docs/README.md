# User Guide to Ollie :dog:

## Set Up
1. Install [Java-17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html).
2. Download the latest [ollie.jar](https://github.com/NgZiXin/ip/raw/master/Ollie.jar) file.
3. Go to the folder where the .jar file is stored at.
4. Run the application using the following command:
```bash
java -jar ollie.jar
```
5. Referring to the key commands below, start playing around with Ollie the chatbot!

**Note:** Data is saved with the `bye` command and stored in the directory `./data/ollie.txt`.

## Key Commands

### Command Format
- Words surrounded by [square brackets] are the parameters to be supplied by the user.
- All date parameters are to be given in the YYYY-MM-DD format.

### 1. Displaying List of Tasks: `list` 
**Example:**
```
list
```

### 2. Adding Todo Task: `todo [Your Task]`
**Example:**  
```
todo Clean room
```

### 3. Adding Deadline Task: `deadline [Your Task] /by [Date in YYYY-MM-DD]`  
**Example:**  
```
deadline Submit project /by 2024-09-30
```

### 4. Adding Event Task: `event [Your Task] /from [Date in YYYY-MM-DD] /to [Date in YYYY-MM-DD]`
**Example:**
```
event Attend conference /from 2024-10-01 /to 2024-10-03
```

### 5. Deleting a Task: `delete [Date in YYYY-MM-DD]`
**Example:**
```
delete 2
```

### 6. Marking a Task as Completed: `mark [Task's Serial Number]`
**Example:**
```
mark 1
```

### 7. Unmarking a Task: `unmark [Task's Serial Number]`
**Example:**
```
unmark 1
```

### 8. Undoing the Previous Command: `undo`
**Example:**
```
undo
```

### 9. Finding a Task: `find [Keyword in Task Description]`
**Example:**  
```
find project
```

### 10. Exiting the Program: `bye`
**Example:**
```
bye
```

Thank you! :grinning: