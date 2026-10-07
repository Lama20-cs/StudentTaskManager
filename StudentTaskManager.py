tasks = []

def add_task():
    task = input("Enter task: ")
    tasks.append({"task": task, "completed": False})
    print("Task added successfully.")

def view_tasks():
    if not tasks:
        print("No tasks available.")
        return

    for i, task in enumerate(tasks, 1):
        status = "Completed" if task["completed"] else "Pending"
        print(f"{i}. {task['task']} - {status}")

def complete_task():
    view_tasks()
    if tasks:
        number = int(input("Enter task number: "))
        if 1 <= number <= len(tasks):
            tasks[number - 1]["completed"] = True
            print("Task completed successfully.")

def delete_task():
    view_tasks()
    if tasks:
        number = int(input("Enter task number: "))
        if 1 <= number <= len(tasks):
            tasks.pop(number - 1)
            print("Task deleted successfully.")

while True:
    print("\n--- Student Task Manager ---")
    print("1. Add Task")
    print("2. View Tasks")
    print("3. Complete Task")
    print("4. Delete Task")
    print("5. Exit")

    choice = input("Choose an option: ")

    if choice == "1":
        add_task()
    elif choice == "2":
        view_tasks()
    elif choice == "3":
        complete_task()
    elif choice == "4":
        delete_task()
    elif choice == "5":
        print("Goodbye!")
        break
    else:
        print("Invalid choice.")