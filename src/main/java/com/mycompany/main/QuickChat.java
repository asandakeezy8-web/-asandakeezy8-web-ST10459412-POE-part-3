package com.mycompany.main;

import java.util.Scanner;

public class QuickChat {

    public static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        Scanner sc = SCANNER;

        // ---------- Part 1: registration + login ----------
        System.out.print("Enter First Name: ");
        String firstName = sc.nextLine();
        System.out.print("Enter Last Name: ");
        String lastName = sc.nextLine();

        Login login = new Login(firstName, lastName);

        String registrationResult;
        do {
            System.out.print("Enter Username (must contain _ and <=5 chars): ");
            String username = sc.nextLine();
            System.out.print("Enter Password (8 chars, capital, number, special): ");
            String password = sc.nextLine();
            System.out.print("Enter SA Cell Number (e.g +27831234567): ");
            String cell = sc.nextLine();
            registrationResult = login.registerUser(username, password, cell);
            System.out.println(registrationResult);
        } while (!registrationResult.contains("successfully"));

        boolean loggedIn = false;
        System.out.println("\n--- LOGIN ---");
        for (int attempt = 0; attempt < 3 && !loggedIn; attempt++) {
            System.out.print("Enter Username: ");
            String u = sc.nextLine();
            System.out.print("Enter Password: ");
            String p = sc.nextLine();
            System.out.println(login.returnLoginStatus(u, p));
            loggedIn = login.loginUser(u, p);
        }
        if (!loggedIn) {
            System.out.println("Too many failed login attempts. Goodbye.");
            return;
        }

        // ---------- Part 2: QuickChat ----------
        System.out.println("\nWelcome to QuickChat.");

        int limit = askForCount(sc);
        int entered = 0;
        boolean running = true;

        while (running) {
            System.out.println("\n1) Send Messages");
            System.out.println("2) Show recently sent messages");
            System.out.println("3) Quit");
            System.out.println("4) Stored Messages"); // <-- PART 3 NEW
            System.out.print("Choose an option: ");
            String choice = sc.nextLine().trim();

            switch (choice) {
                case "1":
                    if (entered >= limit) {
                        System.out.println("You have already entered your " + limit + " message(s).");
                        break;
                    }
                    captureMessage(sc);
                    entered++;
                    if (entered == limit) {
                        System.out.println("\nTotal messages sent: " + Message.totalSentCount());
                    }
                    break;
                case "2":
                    System.out.println(new Message("", "").printMessages());
                    // printMessages uses static list, dummy object is fine
                    break;
                case "3":
                    running = false;
                    break;
                case "4":
                    storedMessagesMenu(sc); // <-- PART 3
                    break;
                default:
                    System.out.println("Please choose 1, 2, 3 or 4.");
            }
        }
        System.out.println("Total messages sent: " + Message.totalSentCount());
        System.out.println("Goodbye.");
    }

    // ---------- PART 3: Stored Messages Sub-Menu ----------
    private static void storedMessagesMenu(Scanner sc) {
        boolean inMenu = true;
        while (inMenu) {
            System.out.println("\n--- STORED MESSAGES ---");
            System.out.println("a) Display sender and recipient of all stored messages");
            System.out.println("b) Display longest stored message");
            System.out.println("c) Search for a Message ID");
            System.out.println("d) Search for all messages for a particular recipient");
            System.out.println("e) Delete a message using Message Hash");
            System.out.println("f) Display a report of all stored messages");
            System.out.println("g) Back to main menu");
            System.out.print("Choose an option: ");
            String opt = sc.nextLine().trim().toLowerCase();

            switch (opt) {
                case "a":
                    System.out.println(Message.displaySendersAndRecipients());
                    break;
                case "b":
                    System.out.println("Longest stored message: " + Message.getLongestStoredMessage());
                    break;
                case "c":
                    System.out.print("Enter Message ID to search: ");
                    String id = sc.nextLine().trim();
                    System.out.println(Message.searchByMessageIDFull(id));
                    break;
                case "d":
                    System.out.print("Enter recipient to search (e.g +27838884567): ");
                    String recip = sc.nextLine().trim();
                    System.out.println(Message.searchByRecipient(recip));
                    break;
                case "e":
                    System.out.print("Enter Message Hash to delete: ");
                    String hash = sc.nextLine().trim();
                    System.out.println(Message.deleteByHash(hash));
                    break;
                case "f":
                    System.out.println(Message.displayReport());
                    break;
                case "g":
                    inMenu = false;
                    break;
                default:
                    System.out.println("Please choose a, b, c, d, e, f or g.");
            }
        }
    }

    private static int askForCount(Scanner sc) {
        while (true) {
            System.out.print("How many messages do you wish to enter? ");
            try {
                int n = Integer.parseInt(sc.nextLine().trim());
                if (n >= 1) return n;
            } catch (NumberFormatException ignored) { }
            System.out.println("Please enter a whole number of at least 1.");
        }
    }

    private static void captureMessage(Scanner sc) {
        String recipient;
        while (true) {
            System.out.print("Enter recipient cell number (with international code, e.g. +27718693002): ");
            recipient = sc.nextLine().trim();
            String result = Message.validateRecipient(recipient);
            System.out.println(result);
            if (result.equals("Cell phone number successfully captured.")) break;
        }

        String text;
        while (true) {
            System.out.print("Enter your message (max 250 characters): ");
            text = sc.nextLine();
            if (text.trim().isEmpty()) {
                System.out.println("Message cannot be empty.");
                continue;
            }
            String result = Message.validateLength(text);
            System.out.println(result);
            if (result.equals("Message ready to send.")) break;
        }

        Message msg = new Message(recipient, text);
        String outcome = msg.SentMessage();
        System.out.println(outcome);
        if (outcome.equals("Message successfully sent.")) {
            System.out.println("\n" + msg.getDetails());
        }
    }
}