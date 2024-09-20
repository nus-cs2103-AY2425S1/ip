# Duke User Guide

// Update the title above to match the actual product name

// Product screenshot goes here

// Product intro goes here

## **Client Management Commands**

### 1. **List All Clients**
- **Command:** `list_client`
- **Description:** Displays a list of all registered clients in the system.
- **Usage Example:**
'''
list_client
'''
- **Expected Output:** A list of all clients, showing their names and IDs.

---

### 2. **Add a New Client**
- **Command:** `client`
- **Description:** Adds a new client to the system with relevant information.
- **Usage Example:**
'''
client John Doe 92345678 john.doe@example.com
'''
- **Expected Output:** "Client John Doe added successfully."

---

### 3. **Delete a Client**
- **Command:** `delete_client`
- **Description:** Deletes an existing client by ID.
- **Usage Example:**
'''
delete_client 2
'''
- **Expected Output:** "Client with ID 2 deleted successfully."

---

## **Task Management Commands**

### 4. **List All Tasks**
- **Command:** `list_task`
- **Description:** Displays a list of all tasks.
- **Usage Example:**
'''
list_task
'''
- **Expected Output:** A list of all tasks, showing their descriptions and statuses (completed or not).

---

### 5. **Find a Task**
- **Command:** `find_task`
- **Description:** Searches for a task using a keyword.
- **Usage Example:**
'''
find_task report
'''
- **Expected Output:** A list of tasks containing the keyword "report".

---

### 6. **Mark a Task as Complete**
- **Command:** `mark`
- **Description:** Marks a task as complete using its ID.
- **Usage Example:**
'''
mark 3
'''
- **Expected Output:** "Task 3 marked as complete."

---

### 7. **Unmark a Task (Mark as Incomplete)**
- **Command:** `unmark`
- **Description:** Marks a task as incomplete using its ID.
- **Usage Example:**
'''
unmark 3
'''
- **Expected Output:** "Task 3 marked as incomplete."

---

### 8. **Delete a Task**
- **Command:** `delete_task`
- **Description:** Deletes a task from the task list by ID.
- **Usage Example:**
'''
delete_task 5
'''
- **Expected Output:** "Task 5 deleted successfully."

---

## **Task Creation Commands**

### 9. **Add a Todo Task**
- **Command:** `todo`
- **Description:** Adds a new Todo task.
- **Usage Example:**
'''
todo Buy groceries
'''
- **Expected Output:** "Todo task 'Buy groceries' added successfully."

---

### 10. **Add a Deadline Task**
- **Command:** `deadline`
- **Description:** Adds a new task with a deadline.
- **Usage Example:**
'''
deadline Submit report /by 2024-09-30
'''
- **Expected Output:** "Deadline task 'Submit report' added with a deadline of 2024-09-30."

---

### 11. **Add an Event Task**
- **Command:** `event`
- **Description:** Adds a new event task with a date and time.
- **Usage Example:**
'''
event Team meeting /at 2024-09-20 14:00
'''
- **Expected Output:** "Event 'Team meeting' added for 2024-09-20 at 14:00."

---

## **Greeting Commands**

### 12. **Say Goodbye**
- **Command:** `bye`
- **Description:** Exits the application with a goodbye message.
- **Usage Example:**
'''
bye
'''
- **Expected Output:** "Goodbye! See you next time."

---
