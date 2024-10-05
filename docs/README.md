<!-- Project Title -->
<div align="center">
  <h1 style="font-weight:800;font-size:70px;">Blob User Guide</h1>
</div>

<!-- Table of Content -->
<details>
  <summary style="font-weight:600;font-size:30px;">Table of Contents</summary>

  + <a href="#introduction" style="font-size:20px;">Introduction</a>
  + <a href="#getting-started" style="font-size:20px;">Getting Started</a>
    + <a href="#installation-guide" style="font-size:15px;">Installation Guide</a>
  + <a href="#key-commands" style="font-size:20px;">Key Commands</a>
  + <a href="#support" style="font-size:20px;">Support</a>
</details>

___

<!-- Project Details -->
# Introduction
Introducing **Blob**, your personal, task-tracking assistant!

As a university student, it's easy to get swamped by the multitude of commitments and schoolwork.

With **Blob**, stay on top of your tasks with ease! Using a simple messenger interface, you can be kept informed on your existing tasks!


![Ui](Ui.png)

<!-- Project Installation Guide -->
# Getting Started
To get a local copy up and running, follow the these [simple steps below](#installation-guide).

<!-- Installation Guide -->
### Installation Guide
1. Download a local copy of **Blob** using this [link](https://github.com/superb-sushi/ip/releases).
- [X] Scroll to the latest *Blob* version
- [X] Click on the *blob.jar* hyperlink to download the `blob.jar` file
- [X] Create a new folder in your desktop and copy the `blob.jar` file over into that folder

2. Run the Application
Open up your command terminal and enter the following:
```sh
java -jar blob.jar
```
\[TIP] Do check that your java version is at least **Java 17** by entering the following below:
> ```sh
> java -version
> ```

<!-- Key Features -->
# Key Commands
## `todo`
**Blob** supports the creation of `todo` tasks which consist of solely the task description!\
Example: `todo CS2103T Quiz`\
Expected Output: 
```
Got it. I've added this task:
[T][] CS2103T Quiz
Now you have 1 tasks in the list.
```

## `deadline`
**Blob** supports the creation of `deadline` tasks which consist of the task description, as well as a date & time (in `YYYY-MM-DD HH:mm` format) to indicate when it ends!\
Example: `deadline CS2100 Assignment /by 2024-09-10 13:00`\
Expected Output: 
```
Got it. I've added this task:
[D][] CS2100 Assignment (by: Sep 10 13:00)
Now you have 2 tasks in the list.
```

## `event`
Lastly, **Blob** supports the creation of `event` tasks which consist of the task description, as well as 2 instances f date & time (in `YYYY-MM-DD HH:mm` format) to indicate when the task starts & ends!\
Example: `event ST2334 Midterms /from 2024-10-10 16:15 /to 2024-10-10 17:30`\
Expected Output: 
```
Got it. I've added this task:
[E][] ST2334 Midterms (from: Oct 10 16:15 to: Oct 10 17:30)
Now you have 3 tasks in the list.
```

## `list`
**Blob** allows you to view all existing tasks, completed and not completed!
Example: `list`\
Expected Output:
```
Here are the tasks in your list:
1. [T][] CS2103T Quiz
2. [D][] CS2100 Assignment (by Sep 10 13:00)
3. [E][] ST2334 Midterms (from: Oct 10 16:15 to: Oct 10 17:30)
```

## `mark`
**Blob** keeps tracks of tasks you've completed through the use of the `mark` command with the relevant task number!
Example: `mark 1`
Expected Output:
```
Nice! I've marked this task as done:
[T][X] CS2103T Quiz
```

## `unmark`
This command removes the completion 'X' check on any existing task when paired with the relevant task number!
Example: `unmark 1`
```
OK, I've marked this task as not done yet:
[T][] CS2103T Quiz
```

## `find`
**Blob** has the ability to sieve out certain tasks that contain the corresponding keyword you provide!
Example: `find CS2100`
Expected Output:
```
Here are the matching tasks in your list:
1. [D][] CS2100 Assignment (by: Sep 10 13:00)
```

## Extension Feature: `tag`
This command provides a more visual categorisation to your existing tasks by providing customisable hashtags to tasks of your choice!
Example: `tag 3 exam`
Expected Output:
```
OK, I've tagged this task as suchL
[E][] ST2334 Midterms (from: Oct 10 16:15 to: Oct 10 17:30) - #exam
```

## Support
If you encounter any bugs, do feel free to drop our team an email at teamblob@gmail.com!



