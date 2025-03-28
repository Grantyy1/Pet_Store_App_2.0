package petstore.App;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * Provides methods for getting different types of input from the console.
 * @author Grant Peverett
 * @since March 28, 2025
 * @version 1.0 beta
 * @see <a href="https://github.com/Grantyy1/Pet_Store_App_2.0/blob/main/petstore/App/Input.java">GitHub Repository</a>
 */
public class Input {
    /**
     * Scanner object for reading input from the console.
     */
    public static Scanner sc = new Scanner(System.in);

    /**
     * Gets a line of text from the user without validation.
     * @param prompt The message to display to the user.
     * @return The entered text.
     */
    public static String getLine(String prompt) {
        System.out.print(prompt);
        return Input.sc.nextLine();
    } // end of getLine

    /**
     * Gets a non-empty string from the user with validation.
     * @param prompt The message to display to the user.
     * @return The validated non-empty string.
     */
    public static String getString(String prompt) {
        String userInput;

        System.out.print(prompt);

        while (true) {
            userInput = Input.sc.nextLine();
            userInput = userInput.trim();

            if (userInput.isEmpty()) {
                System.out.print("Invalid input! Please enter a value: ");
            } else {
                break;
            }
        }

        return userInput;
    } // end of getString

    /**
     * Gets integer value from the user with validation.
     * @param prompt The message to display to the user.
     * @return the users input integer.
     */
    public static int getInt(String prompt) {
        int userInput;

        System.out.print(prompt);

        // if the data in the buffer is a valid integer
        // then break out of the validation loop
        while (!Input.sc.hasNextInt()) {
            System.out.print("Invalid input! Please enter a number: ");
            Input.sc.next();  // clear the data in the input buffer
        } // end of while

        userInput = Input.sc.nextInt();
        Input.sc.nextLine(); // consume newline left-over

        return userInput;
    } // end of getInt

    /**
     * Get integer range from the user.
     * @param prompt The message to display to the user.
     * @param low lowest integer value.
     * @param high highest integer value.
     * @return the integer range inputted by the user.
     */
    public static int getIntRange(String prompt, int low, int high) {
        int userInput;

        System.out.print(prompt);

        while (true) {
            if (Input.sc.hasNextInt()) {         // if the data in the buffer is a valid integer
                userInput = Input.sc.nextInt();  // then store the integer in userInput

                // if the userInput is within the valid range
                // then break out of the validation loop
                if (userInput >= low && userInput <= high) {
                    break;
                } // end of if
            } else {
                Input.sc.next();  // clear the data in the input buffer
            } // end of if-else

            System.out.printf("Invalid input! Please enter a number between (%d - %d): ", low, high);
        } // end of while

        Input.sc.nextLine(); // consume newline left-over

        return userInput;
    } // end of getIntRange

    /**
     * Gets a double from the user with validation.
     * @param prompt The message to display to the user.
     * @return The validated double.
     */
    public static double getDouble(String prompt) {
        double userInput;

        System.out.print(prompt);

        // if the data in the buffer is a valid double
        // then break out of the validation loop
        while (!Input.sc.hasNextDouble()) {
            System.out.print("Invalid input! Please enter a decimal number: ");
            Input.sc.next();  // clear the data in the input buffer
        } // end of while

        userInput = Input.sc.nextDouble();
        Input.sc.nextLine(); // consume newline left-over

        return userInput;
    } // end of getDouble

    /**
     * Gets a double from the user within a specified range.
     * @param prompt The message to display to the user.
     * @param low The minimum acceptable value (inclusive).
     * @param high The maximum acceptable value (inclusive).
     * @return The validated double within the specified range.
     */
    public static double getDoubleRange(String prompt, double low, double high) {
        double userInput;

        System.out.print(prompt);

        while (true) {
            if (Input.sc.hasNextDouble()) {         // if the data in the buffer is a valid double
                userInput = Input.sc.nextDouble();  // then store the double in userInput

                // if the userInput is within the valid range
                // then break out of the validation loop
                if (userInput >= low && userInput <= high) {
                    break;
                } // end of if
            } else {
                Input.sc.next();  // clear the data in the input buffer
            } // end of if-else

            System.out.printf("Invalid input! Please enter a number between (%.2f - %.2f): ", low, high);
        } // end of while

        Input.sc.nextLine(); // consume newline left-over

        return userInput;
    } // end of getDoubleRange

    /**
     * Gets a date string from the user in MM-dd-yyyy format with validation.
     * @param prompt The message to display to the user.
     * @return The validated date string in MM-dd-yyyy format.
     */
    public static String getDate(String prompt) {
        String userInput;

        System.out.print(prompt);

        while (true) {
            userInput = Input.sc.nextLine();

            try {
                LocalDate.parse(userInput, DateTimeFormatter.ofPattern("MM-dd-yyyy"));
                break;
            } catch (Exception e) {
                System.out.print("Invalid input! Please enter a valid date (MM-DD-YYYY): ");
            }
        }

        return userInput;
    } // end of getDate

    /**
     * Gets a boolean value from the user using various yes/no inputs.
     * Accepts "yes", "y", "true", "t", "1" for true and "no", "n", "false", "f", "0" for false.
     * @param prompt The message to display to the user
     * @return The boolean value (true or false)
     */
    public static boolean getBoolean(String prompt) {
        String userInput;

        System.out.print(prompt);

        while (true) {
            userInput = Input.sc.nextLine().trim().toLowerCase();

            if (userInput.equals("yes") || userInput.equals("y") || userInput.equals("true") || userInput.equals("t") || userInput.equals("1")) {
                return true;
            } else if (userInput.equals("no") || userInput.equals("n") || userInput.equals("false") || userInput.equals("f") || userInput.equals("0")) {
                return false;
            } else {
                System.out.print("Invalid input! Please enter yes/no: ");
            }
        }
    } // end of getBoolean
} // end of Input class
