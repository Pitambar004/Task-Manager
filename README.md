# Task Manager CLI

A simple and intuitive command-line task management application built with Java. This project demonstrates core Object-Oriented Programming (OOP) principles and provides a practical solution for managing tasks through a terminal interface.

## 📋 Description

Task Manager CLI is a lightweight, console-based application that helps you organize and track your tasks efficiently. It provides a Trello-like experience in the terminal, allowing you to create, update, delete, and organize tasks with priorities and deadlines. All data is automatically persisted to a JSON file, ensuring your tasks are saved between sessions.

This project is perfect for:
- Learning Java OOP concepts
- Understanding basic design patterns
- Practicing file I/O operations
- Building CLI applications

## ✨ Features

- ✅ **Add Tasks** - Create new tasks with title, description, priority, and deadline
- ✅ **Edit Tasks** - Update task details anytime
- ✅ **Delete Tasks** - Remove tasks by ID
- ✅ **Task Status Management** - Move tasks between stages: To Do → In Progress → Done
- ✅ **View Tasks** - Display all tasks or filter by status
- ✅ **Sort Tasks** - Sort by priority (HIGH → MEDIUM → LOW) or deadline (earliest first)
- ✅ **Data Persistence** - Automatic saving to JSON file
- ✅ **User-Friendly CLI** - Simple menu-driven interface

## 🏗️ Project Structure

```
task-manager/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── model/          # Task, Priority, Status classes
│   │   │   ├── manager/        # TaskManager (Singleton Pattern)
│   │   │   ├── utils/          # JsonHandler for file operations
│   │   │   └── Main.java       # CLI Interface & Entry Point
│   │   └── resources/
│   │       └── tasks.json      # Data persistence file
│   └── pom.xml                 # Maven configuration
└── README.md
```

## 🛠️ Technologies Used

- **Java 11+** - Core programming language
- **Maven** - Build automation and dependency management
- **Gson** - JSON serialization/deserialization library

## 📦 Prerequisites

Before running this project, make sure you have:

- **Java JDK 11 or higher** installed
- **Maven 3.6+** installed
- A terminal/command prompt

### Check Installation

```bash
java -version
mvn -version
```

## 🚀 Getting Started

### 1. Clone the Repository

```bash
git clone <your-repo-url>
cd task-manager
```

### 2. Build the Project

```bash
mvn clean compile
```

### 3. Run the Application

```bash
mvn exec:java
```

Or compile and run manually:

```bash
mvn compile
java -cp target/classes:target/dependency/* Main
```

## 📖 Usage Guide

Once the application starts, you'll see a menu with the following options:

1. **Add Task** - Create a new task
   - Enter task title (required)
   - Enter description
   - Select priority (LOW, MEDIUM, HIGH)
   - Enter deadline (format: yyyy-MM-dd)

2. **View All Tasks** - Display all tasks in the system

3. **View Tasks by Status** - Filter tasks by:
   - TODO
   - IN_PROGRESS
   - DONE

4. **Edit Task** - Update an existing task
   - Enter task ID
   - Modify fields (press Enter to keep current value)

5. **Delete Task** - Remove a task by ID

6. **Move Task** - Change task status (move between stages)

7. **Sort Tasks** - Sort by:
   - Priority (HIGH → MEDIUM → LOW)
   - Deadline (earliest first)

8. **Exit** - Close the application

## 💾 Data Persistence

All tasks are automatically saved to `tasks.json` in the project root directory. The file is created automatically on first run. Your data persists between sessions, so you won't lose your tasks when you close the application.

## 🎓 Concepts Demonstrated

This project demonstrates:

- **OOP Principles**
  - Encapsulation (private fields, public methods)
  - Abstraction (hiding implementation details)
  - Classes and Objects

- **Design Patterns**
  - Singleton Pattern (TaskManager ensures single instance)

- **Core Java Concepts**
  - Collections (ArrayList, List)
  - File I/O operations
  - JSON serialization/deserialization
  - Exception handling
  - Enums (Priority, Status)
  - UUID generation

## 📝 Example Usage

```
========================================
   Welcome to Task Manager CLI
========================================

--- Main Menu ---
1. Add Task
2. View All Tasks
3. View Tasks by Status
4. Edit Task
5. Delete Task
6. Move Task (Change Status)
7. Sort Tasks
8. Exit

Enter your choice: 1

--- Add New Task ---
Enter task title: Complete Java Project
Enter task description: Finish the task manager application
Select priority:
1. LOW
2. MEDIUM
3. HIGH
Enter choice (1-3): 3
Enter deadline (yyyy-MM-dd): 2024-12-31

Task added successfully!
Task ID: 550e8400-e29b-41d4-a716-446655440000
```

## 🤝 Contributing

Contributions are welcome! Feel free to:
- Report bugs
- Suggest new features
- Submit pull requests
- Improve documentation

## 📄 License

This project is open source and available for educational purposes.

## 👨‍💻 Author

Created as a learning project to practice Java OOP concepts and CLI application development.

---

**Note**: This is a simple CLI application designed for learning purposes. For production use, consider adding features like:
- Task categories/tags
- Search functionality
- Export/import capabilities
- Task reminders
- GUI version
