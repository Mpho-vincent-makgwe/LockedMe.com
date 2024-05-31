# LockedMe.com

### Project Structure
- `src/`
  - `com/lockedme/`
    - `LockedMeApp.java`
    - `FileManager.java`

### `FileManager.java`
This class handles file operations like retrieving, adding, deleting, and searching files.

```java
package com.lockedme;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FileManager {
    private final String rootDirectory;

    public FileManager(String rootDirectory) {
        this.rootDirectory = rootDirectory;
    }

    public List<String> getAllFiles() {
        File directory = new File(rootDirectory);
        List<String> fileList = new ArrayList<>();
        if (directory.isDirectory()) {
            for (File file : directory.listFiles()) {
                if (file.isFile()) {
                    fileList.add(file.getName());
                }
            }
        }
        Collections.sort(fileList);
        return fileList;
    }

    public boolean addFile(String fileName) {
        File file = new File(rootDirectory, fileName);
        try {
            return file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteFile(String fileName) {
        File file = new File(rootDirectory, fileName);
        return file.exists() && file.delete();
    }

    public boolean searchFile(String fileName) {
        File file = new File(rootDirectory, fileName);
        return file.exists();
    }
}
```

### `LockedMeApp.java`
This class handles the user interface and interaction logic.

```java
package com.lockedme;

import java.util.List;
import java.util.Scanner;

public class LockedMeApp {
    private static final String ROOT_DIRECTORY = "files";

    public static void main(String[] args) {
        FileManager fileManager = new FileManager(ROOT_DIRECTORY);
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            showMainMenu();
            int choice = getChoice(scanner);

            switch (choice) {
                case 1:
                    displayFiles(fileManager);
                    break;
                case 2:
                    handleFileOperations(fileManager, scanner);
                    break;
                case 3:
                    running = false;
                    System.out.println("Exiting the application. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }

    private static void showMainMenu() {
        System.out.println("\nWelcome to LockedMe.com");
        System.out.println("Developer: Your Name");
        System.out.println("\nMain Menu:");
        System.out.println("1. Retrieve all files in ascending order");
        System.out.println("2. File operations (Add, Delete, Search)");
        System.out.println("3. Exit");
        System.out.print("Enter your choice: ");
    }

    private static int getChoice(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.next();
        }
        return scanner.nextInt();
    }

    private static void displayFiles(FileManager fileManager) {
        List<String> files = fileManager.getAllFiles();
        if (files.isEmpty()) {
            System.out.println("The directory is empty.");
        } else {
            System.out.println("Files in ascending order:");
            for (String file : files) {
                System.out.println(file);
            }
        }
    }

    private static void handleFileOperations(FileManager fileManager, Scanner scanner) {
        boolean backToMainMenu = false;

        while (!backToMainMenu) {
            showFileOperationsMenu();
            int choice = getChoice(scanner);

            switch (choice) {
                case 1:
                    addFile(fileManager, scanner);
                    break;
                case 2:
                    deleteFile(fileManager, scanner);
                    break;
                case 3:
                    searchFile(fileManager, scanner);
                    break;
                case 4:
                    backToMainMenu = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void showFileOperationsMenu() {
        System.out.println("\nFile Operations Menu:");
        System.out.println("1. Add a file");
        System.out.println("2. Delete a file");
        System.out.println("3. Search for a file");
        System.out.println("4. Return to main menu");
        System.out.print("Enter your choice: ");
    }

    private static void addFile(FileManager fileManager, Scanner scanner) {
        System.out.print("Enter the name of the file to add: ");
        String fileName = scanner.next();
        if (fileManager.addFile(fileName)) {
            System.out.println("File added successfully.");
        } else {
            System.out.println("Failed to add file.");
        }
    }

    private static void deleteFile(FileManager fileManager, Scanner scanner) {
        System.out.print("Enter the name of the file to delete: ");
        String fileName = scanner.next();
        if (fileManager.deleteFile(fileName)) {
            System.out.println("File deleted successfully.");
        } else {
            System.out.println("File not found.");
        }
    }

    private static void searchFile(FileManager fileManager, Scanner scanner) {
        System.out.print("Enter the name of the file to search: ");
        String fileName = scanner.next();
        if (fileManager.searchFile(fileName)) {
            System.out.println("File found.");
        } else {
            System.out.println("File not found.");
        }
    }
}
```

### Instructions to Run the Application

1. **Set Up the Project:**
   - Open Eclipse/IntelliJ and create a new Java project.
   - Create the package `com.lockedme`.
   - Add the `FileManager.java` and `LockedMeApp.java` files to the `com.lockedme` package.

2. **Set Up Git and GitHub:**
   - Initialize a local Git repository in the project directory.
   - Create a new GitHub repository and connect it to the local repository.
   - Push the initial project setup to GitHub.

3. **Run the Application:**
   - Compile and run the `LockedMeApp.java` file.
   - Follow the command-line instructions to interact with the application.

### Documentation

Include the following in your documentation:
- Project and developer details.
- Sprints planned and tasks achieved in each sprint.
- Algorithms and flowcharts of the application.
- Core concepts used in the project.
- GitHub repository link to verify the project completion.

### Conclusion and USPs

- **Enhanced Application:** Ensure the application is user-friendly with robust error handling and navigation features. Optimize for performance and scalability.
- **Unique Selling Points:** Easy-to-use command-line interface, efficient file operations with sorting and searching capabilities, detailed documentation, and clean code structure.
