package com.pluralsight;

public class Book {
    private int id;
    private String isbn;
    private String title;
    private boolean isCheckedOut;
    private String checkedOutTo;

    public Book(int id, String isbn, String title) {
        this.id = id;
        this.isbn = isbn;
        this.title = title;
        this.isCheckedOut = false;
        this.checkedOutTo = "";
    }

    public int getId() {
        return id;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public boolean isCheckedOut() {
        return isCheckedOut;
    }

    public String getCheckedOutTo() {
        return checkedOutTo;
    }

    public void checkOut(String name) {
        if (!isCheckedOut) {
            isCheckedOut = true;
            checkedOutTo = name;
            System.out.println(" yes " + title + "\" checked out to " + name + ".");
        } else {
            System.out.println(" No That book is already checked out.");
        }
    }

    public void checkIn() {
        if (isCheckedOut) {
            System.out.println("book \"" + title + "\" returned by " + checkedOutTo + ".");
            isCheckedOut = false;
            checkedOutTo = "";
        } else {
            System.out.println(" That book isn’t checked out.");
        }
    }

    @Override
    public String toString() {
        return String.format("ID: %d | ISBN: %s | Title: %s", id, isbn, title);
    }

    public String checkedOutInfo() {
        return String.format("ID: %d | ISBN: %s | Title: %s | Checked out to: %s",
                id, isbn, title, checkedOutTo);
    }
}