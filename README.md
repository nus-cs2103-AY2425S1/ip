# Niko Chatbot

Welcome to the **Niko Chatbot** repository! Niko is a feature-rich and user-friendly chatbot designed to help you manage tasks, set reminders, and keep track of your schedule effortlessly. Whether you're a student, professional, or someone looking to organize daily activities, Niko is here to assist you.

## Table of Contents

- [Features](#features)
- [User Guide](#user-guide)
- [Installation](#installation)
- [Usage](#usage)
- [Commands](#commands)
- [Examples](#examples)
- [Error Handling](#error-handling)
- [Contributing](#contributing)
- [License](#license)
- [Support](#support)

## Features

Niko Chatbot is packed with a variety of features to enhance your productivity and task management. Below is a detailed overview of each feature:

### 1. **Task Management**

Niko allows you to efficiently manage your tasks through various commands:

- **Add Tasks**: Create different types of tasks such as Todos, Deadlines, and Events.
   - **Todo**: Simple tasks without any deadlines.
   - **Deadline**: Tasks with specific due dates and times.
   - **Event**: Tasks that occur within a specific time frame.

- **List Tasks**: View all your current tasks in an organized manner.

- **Mark Tasks as Done**: Update the status of your tasks upon completion.

- **Unmark Tasks**: Revert a task's status from done to not done.

- **Delete Tasks**: Remove tasks that are no longer needed from your list.

- **Find Tasks**: Search for specific tasks using keywords to quickly locate them.

### 2. **Persistent Storage**

- **Save & Load**: Automatically save your tasks to a local file and load them upon restarting the chatbot, ensuring that your data is preserved between sessions.

### 3. **Robust Error Handling**

Niko is designed to handle a wide range of errors gracefully, providing clear and actionable feedback to users:

- **Command Format Errors**: Detects and informs users about incorrect command formats, such as missing parameters or using invalid characters.

- **Environment Issues**: Handles scenarios where expected files are missing, access is denied, or file contents are corrupted.

- **Data Consistency**: Ensures that task data remains consistent, such as preventing duplicate tasks or ensuring that event dates are logically correct.

### 4. **User Interface**

- **Command-Line Interface (CLI)**: Interact with Niko through a simple and intuitive command-line interface.

- **Graphical User Interface (GUI)**: For a more user-friendly experience, Niko can be extended with a GUI using JavaFX, allowing for visual interaction with tasks and commands.

### 5. **Customization**

- **Themes**: Customize the appearance of the chatbot through themes, enhancing the visual appeal to suit your preferences.

- **Flexible Date Formats**: Supports multiple date and time formats to accommodate different user needs.

### 6. **Extensibility**

- **Modular Design**: Niko is built with a modular architecture, making it easy to add new features or integrate with other applications.

- **API Integration**: Potential to integrate with external APIs for added functionalities like calendar syncing or notification services.

### 7. **Accessibility**

- **Responsive Design**: Ensures that the chatbot interface adapts to different screen sizes and devices, providing a seamless experience across platforms.

## User Guide

For a comprehensive guide on how to use Niko Chatbot, including detailed instructions and examples, please visit the [Niko Chatbot User Guide](https://publicmain.github.io/ip/).



## Installation

### Prerequisites

- **Java 11** or higher installed on your system.
- **Git** installed for cloning the repository.

### Steps

1. **Clone the Repository**

   ```bash
   git clone https://github.com/your-username/niko-chatbot.git
