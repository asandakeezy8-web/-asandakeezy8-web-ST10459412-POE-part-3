package com.mycompany.main;

public class Login {
    private final String firstName;
    private final String lastName;
    private String username;
    private String password;
    private String cell;

    public Login(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public boolean checkUserName(String u) {
        return u != null && u.contains("_") && u.length() <= 5;
    }

    public boolean checkPasswordComplexity(String p) {
        return p != null && p.length() >= 8 && p.matches(".*[A-Z].*")
                && p.matches(".*\\d.*") && p.matches(".*[^A-Za-z0-9].*");
    }

    public boolean checkCellPhoneNumber(String c) {
        return c != null && c.matches("^\\+27\\d{9}$");
    }

    public String registerUser(String u, String p, String c) {
        if (!checkUserName(u)) return "Username is not correctly formatted.";
        if (!checkPasswordComplexity(p)) return "Password is not correctly formatted.";
        if (!checkCellPhoneNumber(c)) return "Cell phone number is incorrectly formatted.";
        username = u; password = p; cell = c;
        return "User registered successfully.";
    }

    public boolean loginUser(String u, String p) {
        return username != null && username.equals(u) && password.equals(p);
    }

    public String returnLoginStatus(String u, String p) {
        return loginUser(u, p)
                ? "Welcome " + firstName + " " + lastName + ", it is great to see you again."
                : "Username or password incorrect, please try again.";
    }
}
