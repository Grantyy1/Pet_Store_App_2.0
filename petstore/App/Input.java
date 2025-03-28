package petstore.App;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * @author Grant Peverett
 * @since March 28, 2025
 * @version 1.0 beta
 * @see <a href="https://github.com/Grantyy1/Pet_Store_App_2.0/blob/main/petstore/App/Input.java">GitHub Repository</a>
 */
public class Input {

    public static Scanner sc = new Scanner(System.in);

    public static String getLine(String prompt) {
        System.out.print(prompt);
        return Input.sc.nextLine();
    } // end of getLine

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
