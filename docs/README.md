# **ChatBuddy User Guide**

## **Introduction**

Welcome to **ChatBuddy**! This application is designed to help you manage your tasks with a simple and intuitive interface. You can easily add, list, mark, unmark, update, and delete tasks using various commands. ChatBuddy also provides reminders for deadlines and events, helping you stay organized.

---

## **Features**

Here’s a summary of what ChatBuddy can do:

### **1. Task Management**
- Add tasks with deadlines, events, and simple to-dos.
- Mark tasks as completed or incomplete.
- List all tasks, with options for filtering based on dates or completion status.
- Delete tasks from the list.
- Update task descriptions or dates.

### **2. User-friendly Interface**
- GUI with avatars for interaction.
- Tasks displayed in an easy-to-read format.

---

## **Command Format**

- Commands follow a format where **UPPERCASE** are parameters you supply.
- Square brackets `[ ]` indicate optional inputs.
- The order of parameters can vary unless specified otherwise.

### **Command List**

1. **Add To-Do Task**:

    ```bash
    todo NAME
    ```

    Example:
    ```bash
    todo Read a book
    ```

2. **Add Deadline Task**:

    ```bash
    deadline NAME /by DATE
    ```

    Example:
    ```bash
    deadline Submit assignment /by 2023-09-30
    ```

3. **Add Event Task**:

    ```bash
    event NAME /from DATE /to DATE
    ```

    Example:
    ```bash
    event Attend seminar /from 2024-09-18 /to 2024-09-19
    ```

4. **List All Tasks**:

    ```bash
    list
    ```

    This command lists all tasks with their status (completed or not) and any associated dates.

5. **Mark Task as Done**:

    ```bash
    mark INDEX
    ```

    Example:
    ```bash
    mark 2
    ```

6. **Unmark Task**:

    ```bash
    unmark INDEX
    ```

    Example:
    ```bash
    unmark 2
    ```

7. **Delete Task**:

    ```bash
    delete INDEX
    ```

    Example:
    ```bash
    delete 3
    ```

8. **Update Task Description or Date**:

    ```bash
    update INDEX description NEW_DESCRIPTION
    update INDEX date NEW_DATE
    ```

    Example:
    ```bash
    update 5 description Finish presentation
    update 5 date 2024-09-30
    ```

9. **Exit the Application**:

    ```bash
    bye
    ```

    This command exits the ChatBuddy application.

---

## **Important Notes**

1. **Task Identifiers**:
    - When referencing tasks (for updating, marking, or deleting), use their **index** in the list.

2. **Date Format**:
    - Use the format `yyyy-MM-dd` when specifying dates for deadlines and events.

3. **Multiple Optional Parameters**:
    - You can specify multiple tags or optional inputs where needed. For example, tasks can have multiple mark, unmark or delete.
   ```bash
   mark 1 3 4 9
    ```

---

## **Tips for Using ChatBuddy**

1. **Realistic Data**: When adding tasks, try to input real data. Instead of "Test task", use actual task names like "Complete homework" or "Prepare for exam".
2. **Navigation**: Use the `list` command regularly to see the updated task statuses.

---
