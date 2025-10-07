package com.pluralsight;

import java.util.Scanner;

import static java.lang.System.*;

public class LibraryApp {
    private static final Book[] books = new Book[20];
    private static final Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        seedData();
        boolean running = true;

        while (running) {
            out.println("\n📚 NEIGHBORHOOD LIBRARY 📚");
            out.println("-----------------------------");
            out.println("1. Show Available Books");
            out.println("2. Show Checked Out Books");
            out.println("3. Exit");
            out.print("Choose an option: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> showAvailableBooks();
                case "2" -> showCheckedOutBooks();
                case "3" -> {
                    out.println("\nGoodbye! Support your local library!");
                    running = false;
                }
                default -> out.println("\nInvalid choice. Try again.");
            }
        }
    }

    private static void seedData() {
        for (int i = 0; i < books.length; i++) {
            books[i] = new Book(
                    i + 1,
                    "ISBN-" + (1000 + i),
                    "Book Title " + (i + 1)
            );
        }
    }

    private static void showAvailableBooks() {
        out.println("\nAVAILABLE BOOKS:");
        out.println("----------------");
        boolean anyAvailable = false;
        for (Book book : books) {
            if (!book.isCheckedOut()) {
                out.println(book);
                anyAvailable = true;
            }
        }

        if (!anyAvailable) {
            out.println("No books are currently available.");
            return;
        }

        out.print("\nEnter the ID of a book to check out, or X to return: ");
        String input = scanner.nextLine();

        if (input.equalsIgnoreCase("X")) return;

        try {
            int id = Integer.parseInt(input);
            Book book = findBookById(id);
            if (book != null && !book.isCheckedOut()) {
                out.print("Enter your name: ");
                String name = scanner.nextLine();
                book.checkOut(name);
            } else {
                out.println("\nInvalid ID or book already checked out.");
            }
        } catch (NumberFormatException e) {
            out.println("\nInvalid input.");
        }
    }

    private static void showCheckedOutBooks() {
        out.println("\nCHECKED OUT BOOKS:");
        out.println("------------------");
        boolean anyCheckedOut = false;
        for (Book book : books) {
            if (book.isCheckedOut()) {
                out.println(book.checkedOutInfo());
                anyCheckedOut = true;
            }
        }

        if (!anyCheckedOut) {
            out.println("No books are currently checked out.");
            return;
        }

        out.print("\nEnter C to check in a book or X to return: ");
        String input = scanner.nextLine();

        if (input.equalsIgnoreCase("C")) {
            out.print("Enter the ID of the book to check in: ");
            try {
                int id = Integer.parseInt(scanner.nextLine());
                Book book = findBookById(id);
                if (book != null && book.isCheckedOut()) {
                    book.checkIn();
                } else {
                    out.println("\nInvalid ID or that book isn't checked out.");
                }
            } catch (NumberFormatException e) {
                out.println("\nInvalid input.");
            }
        }
    }

    private static Book findBookById(int id) {
        for (Book book : books) {
            if (book.getId() == id) {
                return book;
            }
        }
        return null;
    }
}