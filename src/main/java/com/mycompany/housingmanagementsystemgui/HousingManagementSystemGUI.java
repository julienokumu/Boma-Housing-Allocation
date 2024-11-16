/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.housingmanagementsystemgui;

/**
 *
 * @author Julien Okumu, Michael Wambua, Brian Mawira, Thagana Kiretai, Sang Kimutai 
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

// Class representing a user in the housing management system
class User {
    String username;  // Stores the username of the user
    String password;  // Stores the password of the user
    String allocatedHouse;  // Stores the allocated house for the user

    // Constructor to initialize a User object
    User(String username, String password) {
        this.username = username;
        this.password = password;
        this.allocatedHouse = "None";  // Default value before allocation
    }
}

// Main class for the housing management system with GUI
public class HousingManagementSystemGUI {

    // Map to store registered users (key: username, value: User object)
    private static Map<String, User> users = new HashMap<>();
    private static String currentUsername = "";  // Track the logged-in user

    // List of housing units with details (e.g., price and number of rooms)
    private static String[] housingUnits = {
        "Boma 1 - sh10000 - 2 Rooms",
        "Boma 2 - sh15000 - 3 Rooms",
        "Boma 3 - sh20000 - 4 Rooms"
    };

    // Main method to run the GUI application
    public static void main(String[] args) {
        // Create the main frame for the application
        JFrame frame = new JFrame("Boma Housing Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        // Create and display the main menu panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(4, 1, 10, 10));

        JLabel welcomeLabel = new JLabel("Welcome to Boma Housing Management System", JLabel.CENTER);
        JButton signUpButton = new JButton("Sign Up");
        JButton loginButton = new JButton("Login");
        JButton resetPasswordButton = new JButton("Reset Password");

        mainPanel.add(welcomeLabel);
        mainPanel.add(signUpButton);
        mainPanel.add(loginButton);
        mainPanel.add(resetPasswordButton);

        frame.add(mainPanel, BorderLayout.CENTER);
        frame.setVisible(true);

        // Event listener for Sign Up button
        signUpButton.addActionListener(e -> showSignUpFrame());

        // Event listener for Login button
        loginButton.addActionListener(e -> showLoginFrame(frame));

        // Event listener for Reset Password button
        resetPasswordButton.addActionListener(e -> showResetPasswordFrame());
    }

    // Method to display the Sign-Up window
    private static void showSignUpFrame() {
        JFrame signUpFrame = new JFrame("Sign Up");
        signUpFrame.setSize(300, 200);
        signUpFrame.setLayout(new GridLayout(3, 2, 10, 10));

        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField();
        JButton signUpButton = new JButton("Sign Up");

        signUpFrame.add(usernameLabel);
        signUpFrame.add(usernameField);
        signUpFrame.add(passwordLabel);
        signUpFrame.add(passwordField);
        signUpFrame.add(new JLabel()); // Empty cell
        signUpFrame.add(signUpButton);

        signUpFrame.setVisible(true);

        // Event listener for the Sign-Up button
        signUpButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            if (users.containsKey(username)) {
                JOptionPane.showMessageDialog(signUpFrame, "Username already exists. Please try a different one.");
            } else {
                users.put(username, new User(username, password));
                JOptionPane.showMessageDialog(signUpFrame, "Sign-Up successful!");
                signUpFrame.dispose(); // Close the sign-up window
            }
        });
    }

    // Method to display the Login window
    private static void showLoginFrame(JFrame mainFrame) {
        JFrame loginFrame = new JFrame("Login");
        loginFrame.setSize(300, 200);
        loginFrame.setLayout(new GridLayout(3, 2, 10, 10));

        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField();
        JButton loginButton = new JButton("Login");

        loginFrame.add(usernameLabel);
        loginFrame.add(usernameField);
        loginFrame.add(passwordLabel);
        loginFrame.add(passwordField);
        loginFrame.add(new JLabel()); // Empty cell
        loginFrame.add(loginButton);

        loginFrame.setVisible(true);

        // Event listener for the Login button
        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            if (!users.containsKey(username)) {
                JOptionPane.showMessageDialog(loginFrame, "Username not found. Please sign up first.");
            } else {
                User user = users.get(username);
                if (user.password.equals(password)) {
                    JOptionPane.showMessageDialog(loginFrame, "Login successful! Welcome, " + username + "!");
                    currentUsername = username; // Set the current logged-in user
                    loginFrame.dispose(); // Close the login window
                    mainFrame.dispose(); // Close the main frame
                    showDashboard(); // Open the dashboard
                } else {
                    JOptionPane.showMessageDialog(loginFrame, "Incorrect password. Please try again.");
                }
            }
        });
    }

    // Method to display the Password Reset window
    private static void showResetPasswordFrame() {
        JFrame resetFrame = new JFrame("Reset Password");
        resetFrame.setSize(300, 200);
        resetFrame.setLayout(new GridLayout(3, 2, 10, 10));

        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField();
        JLabel newPasswordLabel = new JLabel("New Password:");
        JPasswordField newPasswordField = new JPasswordField();
        JButton resetButton = new JButton("Reset Password");

        resetFrame.add(usernameLabel);
        resetFrame.add(usernameField);
        resetFrame.add(newPasswordLabel);
        resetFrame.add(newPasswordField);
        resetFrame.add(new JLabel()); // Empty cell
        resetFrame.add(resetButton);

        resetFrame.setVisible(true);

        // Event listener for the Reset Password button
        resetButton.addActionListener(e -> {
            String username = usernameField.getText();
            String newPassword = new String(newPasswordField.getPassword());

            if (!users.containsKey(username)) {
                JOptionPane.showMessageDialog(resetFrame, "Username not found. Please sign up first.");
            } else {
                User user = users.get(username);
                user.password = newPassword;
                JOptionPane.showMessageDialog(resetFrame, "Password reset successful!");
                resetFrame.dispose(); // Close the reset password window
            }
        });
    }

    // Method to display the user dashboard
    private static void showDashboard() {
        JFrame dashboardFrame = new JFrame("User Dashboard");
        dashboardFrame.setSize(400, 300);
        dashboardFrame.setLayout(new BorderLayout());

        JLabel welcomeLabel = new JLabel("Welcome, " + currentUsername + "!", JLabel.CENTER);
        JButton allocateButton = new JButton("Allocate Boma Unit");
        JButton viewProfileButton = new JButton("View Profile");

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 1, 10, 10));
        buttonPanel.add(allocateButton);
        buttonPanel.add(viewProfileButton);

        dashboardFrame.add(welcomeLabel, BorderLayout.NORTH);
        dashboardFrame.add(buttonPanel, BorderLayout.CENTER);

        dashboardFrame.setVisible(true);

        // Event listener for Allocate Housing Unit button
        allocateButton.addActionListener(e -> showHousingSelectionFrame());

        // Event listener for View Profile button
        viewProfileButton.addActionListener(e -> showProfile());
    }

    // Method to display the housing selection window
    private static void showHousingSelectionFrame() {
        JFrame selectionFrame = new JFrame("Select Boma Unit");
        selectionFrame.setSize(300, 200);

        JComboBox<String> housingList = new JComboBox<>(housingUnits);
        JButton allocateButton = new JButton("Allocate");

        selectionFrame.setLayout(new GridLayout(2, 1, 10, 10));
        selectionFrame.add(housingList);
        selectionFrame.add(allocateButton);

        selectionFrame.setVisible(true);

        // Event listener for Allocate button
        allocateButton.addActionListener(e -> {
            String selectedHouse = (String) housingList.getSelectedItem();
            User user = users.get(currentUsername);
            user.allocatedHouse = selectedHouse; // Update the user's allocated house
            JOptionPane.showMessageDialog(selectionFrame, "Boma unit allocated: " + selectedHouse);
            selectionFrame.dispose(); // Close the selection frame
        });
    }

    // Method to display the user's profile
    private static void showProfile() {
        User user = users.get(currentUsername);

        JOptionPane.showMessageDialog(null,
            "Username: " + user.username + "\nAllocated Housing: " + user.allocatedHouse,
            "User Profile",
            JOptionPane.INFORMATION_MESSAGE);
    }
}

