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
        System.out.println("Developer: Mpho Vincent Makgwe");
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
