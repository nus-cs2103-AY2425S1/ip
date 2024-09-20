
---

# KillJoy - Your Personal Assistant

```
 _   ___ _ _   ___             
| | / (_) | | |_  |            
| |/ / _| | |   | | ___  _   _ 
|    \| | | |   | |/ _ \| | | |
| |\  \ | | /\__/ / (_) | |_| |
\_| \_/_|_|_\____/ \___/ \__, |
                          __/ |
                          |___/
```

Meet **KillJoy**—your sassy, tech-savvy sidekick who's always three steps ahead. Need something done? Relax, KillJoy’s already on it. With a personality as sharp as its skills, this assistant doesn’t just follow orders—it owns them. Whether it’s organizing your tasks or throwing some banter your way, KillJoy is here to ensure life’s never boring.

### Features
- **Task Management**: Create, list, mark, unmark, delete, and now archive your tasks with ease.
- **Playful Personality**: KillJoy isn't just functional—it's fun! Expect witty banter along the way.
- **Flexible Input Parsing**: Handles different task types like TODOs, DEADLINES, and EVENTS.
- **Task Archiving**: Automatically saves completed tasks in a dedicated archive folder, with unique file names to avoid overwriting.
- **Interactive Command-Line Interface**: Simple, text-based interface for seamless interaction.

Upon starting, KillJoy will greet you and await your commands. Use the available commands to manage your tasks, archive old ones, and interact with your assistant.

### Commands
- **Add Tasks**:
  - TODO: `todo <description>`
  - DEADLINE: `deadline <description> /by <date/time>`
  - EVENT: `event <description> /from <start time> /to <end time>`

- **Task Management**:
  - List tasks: `list`
  - Mark a task: `mark <task number>`
  - Unmark a task: `unmark <task number>`
  - Delete a task: `delete <task number>`

- **Task Search**:
  - Find a task: `find <keyword>`
  
- **Task Archiving**:
  - Archive tasks: `archive`
  - load archived tasks: `load <archive file name with extension>`


- **Exit**:
  - Quit KillJoy: `bye`

### Archiving Feature
KillJoy now allows you to archive your tasks in a dedicated folder. Each archive file gets a unique name, so you can keep a complete history of all your tasks without overwriting previous archives. Archiving happens automatically when tasks are completed and ready to be stored for later reference.

---
