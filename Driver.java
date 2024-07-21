import java.util.*;

/**
 * The Driver class is the entry point for the Hotel Reservation System.
 * It presents a command-line interface to the user to perform operations such
 * as
 * creating a hotel, viewing hotel details, managing a hotel, and simulating a
 * booking.
 */
public class Driver {
    private static final Scanner scanner = new Scanner(System.in);
    private static final HotelReservationSystem hrs = new HotelReservationSystem();

    /**
     * The main method is the starting point of the application.
     * It displays a main menu to the user and processes user input
     * to perform the four main features of the HRS.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        boolean invalidChoice = false;

        while (true) {
            clearConsole(); // Clears the console for a clean menu display

            // Banner to display the name of the application
            String Banner = """
                    ██╗    ██╗███████╗██╗      ██████╗ ██████╗ ███╗   ███╗███████╗   \s
                    ██║    ██║██╔════╝██║     ██╔════╝██╔═══██╗████╗ ████║██╔════╝   \s
                    ██║ █╗ ██║█████╗  ██║     ██║     ██║   ██║██╔████╔██║█████╗     \s
                    ██║███╗██║██╔══╝  ██║     ██║     ██║   ██║██║╚██╔╝██║██╔══╝     \s
                    ╚███╔███╔╝███████╗███████╗╚██████╗╚██████╔╝██║ ╚═╝ ██║███████╗   \s
                     ╚══╝╚══╝ ╚══════╝╚══════╝ ╚═════╝ ╚═════╝ ╚═╝     ╚═╝╚══════╝   \s
                        ████████╗ ██████╗     ██╗  ██╗██████╗ ███████╗██╗            \s
                        ╚══██╔══╝██╔═══██╗    ██║  ██║██╔══██╗██╔════╝██║            \s
                           ██║   ██║   ██║    ███████║██████╔╝███████╗██║            \s
                           ██║   ██║   ██║    ██╔══██║██╔══██╗╚════██║╚═╝            \s
                           ██║   ╚██████╔╝    ██║  ██║██║  ██║███████║██╗            \s
                           ╚═╝    ╚═════╝     ╚═╝  ╚═╝╚═╝  ╚═╝╚══════╝╚═╝          \s
                    """;
            System.out.println(Banner);

            // Display an error message if the previous choice was invalid
            if (invalidChoice) {
                System.out.println("Invalid choice. Please try again.\n");
                invalidChoice = false;
            }

            // Display menu options
            System.out.println("[1] – Create Hotel");
            System.out.println("[2] – View Hotel");
            System.out.println("[3] – Manage Hotel");
            System.out.println("[4] – Create Booking");
            System.out.println("[5] – Exit\n");
            System.out.print("Choose an option: ");

            try {
                int choice = scanner.nextInt(); // Read user input
                scanner.nextLine(); // Consume newline character

                // Process the user's choice
                switch (choice) {
                    case 1 -> createHotel(); // Call method to create a hotel
                    case 2 -> viewHotel(); // Call method to view hotel details
                    case 3 -> manageHotel(); // Call method to manage the hotel
                    case 4 -> createBooking(); // Call method to create a booking
                    case 5 -> {
                        System.out.println("Exiting...");
                        return; // Exit the application
                    }
                    default -> invalidChoice = true; // Handle invalid choices
                }
            } catch (InputMismatchException e) {
                invalidChoice = true; // Handle invalid input type
                scanner.nextLine(); // Clear the invalid input
            }
        }
    }

    /**
     * Prompts the user to create a new hotel.
     * The method validates the hotel name input and checks for duplicate
     * hotel names. If the hotel creation is successful, it provides options
     * to create another hotel or return to the main menu.
     */
    public static void createHotel() {
        String hotelName;
        boolean invalidInput = false;
        boolean invalidChoice = false; // Flag to indicate an invalid menu choice
        int choice;
        int numRooms = 1;
        int errorType = 0; // Variable to track the type of input error

        do {
            clearConsole(); // Clears the console for a clean input display
            // Banner to display the hotel creation prompt
            String banner = """
                      _____                    _           _    _         _         _\s
                     / ____|                  | |         | |  | |       | |       | |
                    | |      _ __  ___   __ _ | |_  ___   | |__| |  ___  | |_  ___ | |
                    | |     | '__|/ _ \\ / _` || __|/ _ \\  |  __  | / _ \\ | __|/ _ \\| |
                    | |____ | |  |  __/| (_| || |_|  __/  | |  | || (_) || |_|  __/| |
                     \\_____||_|   \\___| \\__,_| \\__|\\___|  |_|  |_| \\___/  \\__|\\___||_|
                                                                                     \s
                    """;
            System.out.println(banner);

            // Display appropriate error message based on errorType
            if (errorType != 0) {
                switch (errorType) {
                    case 1 -> System.out.println("Hotel name cannot be blank. Try again.");
                    case 2 ->
                            System.out.println("Hotel name has to contain at least one alphabetic character. Try again.");
                    case 3 -> System.out.println("Hotel name already exists. Try again.");
                }
                errorType = 0; // Reset errorType after displaying the error message
            }

            // Prompt the user to enter the hotel name
            System.out.print("Enter hotel name: ");
            hotelName = scanner.nextLine();

            if (hotelName.isEmpty() || hotelName.isBlank() || hotelName.equals("\n"))
                errorType = 1; // Blank hotel name
            else if (!hotelName.matches(".*[a-zA-Z].*"))
                errorType = 2; // No alphabetic characters in the name

            if(errorType == 0) {
                do {
                    clearConsole(); // Clear console for a clean display
                    System.out.println(banner);
                    System.out.println("Enter hotel name: " + hotelName);

                    if (invalidInput) {
                        System.out.println(" ");
                        System.out.println("Number of rooms should be between 1 and 50. Please try again.");
                        invalidInput = false;
                    }

                    System.out.print("Enter number of rooms to add: ");

                    try {
                        numRooms = scanner.nextInt();
                        scanner.nextLine(); //consume newline

                        if (numRooms < 1 || numRooms > 50) {
                            invalidInput = true;
                        }

                    } catch (InputMismatchException e) {
                        invalidInput = true;
                        scanner.nextLine();
                    }
                } while (invalidInput);
            }

            // Attempt to add the hotel
            if (hrs.addHotel(hotelName, numRooms)) {
                do {
                    clearConsole(); // Clear console for a clean display
                    System.out.println(banner);
                    System.out.println("Hotel '" + hotelName + "' was created successfully.\n");

                    // Display invalid choice message if necessary
                    if (invalidChoice) {
                        System.out.println("Invalid choice. Please try again.");
                    }

                    // Provide options to create another hotel or return to main menu
                    System.out.println("[1] Create Another Hotel");
                    System.out.println("[2] Return to Main Menu\n");
                    System.out.print("Choose an option: ");

                    try {
                        choice = scanner.nextInt(); // Read user input for menu choice
                        scanner.nextLine(); // Consume newline character

                        // Process user's choice
                        switch (choice) {
                            case 1 -> errorType = 4; // Set to create another hotel
                            case 2 -> {
                                return; // Return to main menu
                            }
                            default -> invalidChoice = true; // Handle invalid menu choice
                        }
                    } catch (InputMismatchException e) {
                        invalidChoice = true; // Handle invalid input type
                        scanner.nextLine(); // Clear the invalid input
                    }
                } while (invalidChoice); // Repeat menu options if the choice was invalid

            } else {
                // Determine the type of error based on the input
                if (hotelName.isEmpty() || hotelName.isBlank() || hotelName.equals("\n"))
                    errorType = 1; // Blank hotel name
                else if (!hotelName.matches(".*[a-zA-Z].*"))
                    errorType = 2; // No alphabetic characters in the name
                else
                    errorType = 3; // Hotel name already exists
            }

        } while (true); // Loop to retry hotel creation on error
    }

    /**
     * Allows the user to view information about a selected hotel.
     * The user can select a hotel and view various details such as high-level
     * information,
     * available and booked rooms for a date, room information, and reservation
     * information.
     */
    public static void viewHotel() {
        // Banner to display the hotel viewing prompt
        String banner = """
                __      __ _                   _    _         _         _\s
                \\ \\    / /(_)                 | |  | |       | |       | |
                 \\ \\  / /  _   ___ __      __ | |__| |  ___  | |_  ___ | |
                  \\ \\/ /  | | / _ \\\\ \\ /\\ / / |  __  | / _ \\ | __|/ _ \\| |
                   \\  /   | ||  __/ \\ V  V /  | |  | || (_) || |_|  __/| |
                    \\/    |_| \\___|  \\_/\\_/   |_|  |_| \\___/  \\__|\\___||_|
                                                                         \s
                                                                         \s""";

        int choice;
        boolean invalidChoice = false; // Flag to indicate an invalid menu choice
        boolean invalidSelection = false; // Flag to indicate an invalid hotel selection
        do {
            clearConsole(); // Clears the console
            System.out.println(banner);

            // Display an error message if the previous hotel selection was invalid
            if (invalidSelection) {
                System.out.println("Invalid hotel selection. Try again.");
                invalidSelection = false; // Reset the flag
            }

            // Prompt the user to select a hotel
            Hotel hotel = selectHotel();
            if (hotel == null) {
                if (hrs.getHotelList().isEmpty()) {
                    // If no hotels exist, prompt the user to return to the main menu
                    System.out.print("\nPress ENTER to return to main menu");
                    scanner.nextLine();
                    return;
                } else {
                    invalidSelection = true; // Handle invalid hotel selection
                    continue;
                }
            }

            do {

                clearConsole(); // Clear console for a clean display
                System.out.println(banner);
                System.out.println("Selected Hotel: " + hotel.getName());

                // Display an error message if the previous menu choice was invalid
                if (invalidChoice) {
                    System.out.println("Invalid choice. Please try again.");
                    invalidChoice = false; // Reset the flag
                }

                // Display menu options for viewing hotel details
                System.out.println("[1] – View High-Level Hotel Information");
                System.out.println("[2] – View Available and Booked Rooms for a Date");
                System.out.println("[3] – View Room Information");
                System.out.println("[4] – View Reservation Information");
                System.out.println("[5] – Select a New Hotel");
                System.out.println("[6] – Return to Main Menu\n");
                System.out.print("Choose an option: ");
                try {
                    choice = scanner.nextInt(); // Read user input for menu choice
                    scanner.nextLine(); // Consume newline

                    // Process user's choice
                    switch (choice) {
                        case 1 -> viewHighLevelHotelInfo(hotel); // View high-level hotel information
                        case 2 -> viewRoomsByDate(hotel); // View available and booked rooms for a date
                        case 3 -> viewRoomInformation(hotel); // View room information
                        case 4 -> viewReservationInformation(hotel); // View reservation information
                        case 5 -> {
                            /* Loop back to select a new hotel */
                        }
                        case 6 -> {
                            return; // Return to main menu
                        }
                        default -> invalidChoice = true; // Handle invalid menu choice
                    }
                } catch (InputMismatchException e) {
                    invalidChoice = true; // Handle invalid input type
                    scanner.nextLine(); // Clear the invalid input
                }
            }while(invalidChoice);
        } while (true); // Loop to retry hotel selection and viewing on error
    }

    /**
     * Displays the selected hotel's high-level information such as its name,
     * base price of its rooms, total number of rooms it has, total active
     * reservations
     * it currenly has, count of its rooms available for booking, highest
     * possible earnings, and estimated total earnings with respect to reservations.
     *
     * @param hotel The selected hotel
     */
    public static void viewHighLevelHotelInfo(Hotel hotel) {
        // Banner to display the high-level hotel information prompt
        String banner = """
                __      __ _                    _____          __      \s
                \\ \\    / /(_)                  |_   _|        / _|     \s
                 \\ \\  / /  _   ___ __      __    | |   _ __  | |_  ___ \s
                  \\ \\/ /  | | / _ \\\\ \\ /\\ / /    | |  | '_ \\ |  _|/ _ \\\s
                   \\  /   | ||  __/ \\ V  V /    _| |_ | | | || | | (_) |
                    \\/    |_| \\___|  \\_/\\_/    |_____||_| |_||_|  \\___/\s
                                                                       \s
                                                                       \s""";
        System.out.println(banner);
        hrs.viewHotel(hotel); // Call to the hotel reservation system to display high-level information
        System.out.println("\nPress ENTER to return to view hotel menu");
        scanner.nextLine(); // Wait for the user to press ENTER to return
    }

    /**
     * Displays the rooms available for booking within a specified date range.
     *
     * @param hotel The hotel whose rooms are to be checked.
     */
    public static void viewRoomsByDate(Hotel hotel) {
        String banner = """
                __      __ _                   _____                              \s
                \\ \\    / /(_)                 |  __ \\                             \s
                 \\ \\  / /  _   ___ __      __ | |__) | ___    ___   _ __ ___   ___\s
                  \\ \\/ /  | | / _ \\\\ \\ /\\ / / |  _  / / _ \\  / _ \\ | '_ ` _ \\ / __|
                   \\  /   | ||  __/ \\ V  V /  | | \\ \\| (_) || (_) || | | | | |\\__ \\
                    \\/    |_| \\___|  \\_/\\_/   |_|  \\_\\\\___/  \\___/ |_| |_| |_||___/
                             _              _____          _                      \s
                            | |            |  __ \\        | |                     \s
                            | |__   _   _  | |  | |  __ _ | |_  ___               \s
                            | '_ \\ | | | | | |  | | / _` || __|/ _ \\              \s
                            | |_) || |_| | | |__| || (_| || |_|  __/              \s
                            |_.__/  \\__, | |_____/  \\__,_| \\__|\\___|              \s
                                     __/ |                                        \s
                                    |___/                                         \s""";
        boolean invalidInput = false; // Flag to indicate invalid date input
        boolean invalidChoice = false; // Flag to indicate invalid menu choice
        int checkInDate;
        int checkOutDate;
        int choice = 1;

        do {
            clearConsole(); // Clear the console screen
            System.out.println(banner); // Display banner
            System.out.println("Selected Hotel: " + hotel.getName()); // Display selected hotel name
            if (invalidInput) {
                System.out.print("Please enter valid check-in (1-30) and check-out (2-31) dates.\n");
                invalidInput = false;
            }
            try {
                System.out.print("\nEnter Preferred Check-In Date (1-30): ");
                checkInDate = scanner.nextInt(); // Get check-in date
                System.out.print("Enter Preferred Check-Out Date (2-31): ");
                checkOutDate = scanner.nextInt(); // Get check-out date
                scanner.nextLine(); // Consume newline

                if (checkInDate >= checkOutDate || checkInDate < 1 || checkInDate > 30 || checkOutDate > 31) {
                    invalidInput = true; // Set flag if dates are invalid
                } else {
                    do {
                        clearConsole(); // Clear the console screen
                        System.out.println(banner); // Display banner
                        hrs.viewRoomsByDate(hotel, checkInDate, checkOutDate); // Display rooms by date

                        if (invalidChoice) {
                            System.out.println("Invalid choice. Please try again.");
                            invalidChoice = false;
                        }

                        System.out.println("[1] – View Rooms Available in Another Period");
                        System.out.println("[2] – Return to View Hotel Menu\n");
                        System.out.print("Enter your choice: ");
                        try {
                            choice = scanner.nextInt();
                            scanner.nextLine(); // Consume newline

                            switch (choice) {
                                case 1:
                                    break; // Continue to view rooms for another period
                                case 2:
                                    return; // Return to the view hotel menu
                                default:
                                    invalidChoice = true; // Set flag for invalid choice
                            }
                        } catch (InputMismatchException e) {
                            invalidChoice = true; // Set flag for invalid choice
                            scanner.nextLine(); // Consume invalid input
                        }
                    } while (choice != 2 && !invalidChoice);
                }
            } catch (InputMismatchException e) {
                invalidInput = true; // Set flag for invalid date input
                scanner.nextLine(); // Consume invalid input
            }

        } while (choice == 1);
    }

    /**
     * Displays detailed information about a specific room.
     *
     * @param hotel The hotel whose rooms are to be viewed.
     */
    public static void viewRoomInformation(Hotel hotel) {
        String banner = """
                   __      __ _                   _____                               \s
                   \\ \\    / /(_)                 |  __ \\                              \s
                    \\ \\  / /  _   ___ __      __ | |__) | ___    ___   _ __ ___       \s
                     \\ \\/ /  | | / _ \\\\ \\ /\\ / / |  _  / / _ \\  / _ \\ | '_ ` _ \\      \s
                      \\  /   | ||  __/ \\ V  V /  | | \\ \\| (_) || (_) || | | | | |     \s
                       \\/    |_| \\___|  \\_/\\_/   |_|  \\_\\\\___/  \\___/ |_| |_| |_|     \s
                 _____          __                                _    _              \s
                |_   _|        / _|                              | |  (_)             \s
                  | |   _ __  | |_  ___   _ __  _ __ ___    __ _ | |_  _   ___   _ __ \s
                  | |  | '_ \\ |  _|/ _ \\ | '__|| '_ ` _ \\  / _` || __|| | / _ \\ | '_ \\\s
                 _| |_ | | | || | | (_) || |   | | | | | || (_| || |_ | || (_) || | | |
                |_____||_| |_||_|  \\___/ |_|   |_| |_| |_| \\__,_| \\__||_| \\___/ |_| |_|
                                                                                      \s
                                                                                      \s""";

        boolean invalidChoice = false; // Flag to indicate invalid menu choice
        boolean invalidSelection = false; // Flag to indicate invalid room selection
        int choice = 1;

        do {
            clearConsole(); // Clear the console screen
            System.out.println(banner); // Display banner

            if (invalidSelection) {
                System.out.println("Invalid room selection. Please try again.");
                invalidSelection = false; // Reset flag
            }

            Room room = selectRoom(hotel); // Select a room
            if (room == null) {
                if (hotel.getRoomsList().isEmpty()) {
                    System.out.println("Press ENTER to return to view hotel menu");
                    return; // Return to previous menu if no rooms available
                } else {
                    invalidSelection = true; // Set flag for invalid selection
                    continue;
                }
            }

            clearConsole(); // Clear the console screen
            System.out.println(banner); // Display banner
            System.out.println("Selected Hotel: " + hotel.getName() + "\n");
            hrs.viewRoomInfo(room); // Display room info

            if (invalidChoice) {
                System.out.println("Invalid choice. Please try again.");
                invalidChoice = false; // Reset flag
            }

            System.out.println("[1] – View Information of Another Room");
            System.out.println("[2] – Return to View Hotel Menu\n");
            System.out.print("Enter your choice: ");

            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        break; // Continue the loop to view another room
                    case 2:
                        return; // Return to the previous menu
                    default:
                        invalidChoice = true; // Set flag for invalid choice
                }
            } catch (InputMismatchException e) {
                invalidChoice = true; // Set flag for invalid choice
                scanner.nextLine(); // Consume invalid input
            }
        } while (choice == 1);
    }

    public static void viewReservationInformation(Hotel hotel) {
        String banner = """
                __      __ _                     _____                                      _    _              \s
                \\ \\    / /(_)                   |  __ \\                                    | |  (_)             \s
                 \\ \\  / /  _   ___ __      __   | |__) | ___  ___   ___  _ __ __   __ __ _ | |_  _   ___   _ __ \s
                  \\ \\/ /  | | / _ \\\\ \\ /\\ / /   |  _  / / _ \\/ __| / _ \\| '__|\\ \\ / // _` || __|| | / _ \\ | '_ \\\s
                   \\  /   | ||  __/ \\ V  V /    | | \\ \\|  __/\\__ \\|  __/| |    \\ V /| (_| || |_ | || (_) || | | |
                    \\/    |_| \\___|  \\_/\\_/     |_|  \\_\\\\___||___/ \\___||_|     \\_/  \\__,_| \\__||_| \\___/ |_| |_|
                             _____          __                                _    _                            \s
                            |_   _|        / _|                              | |  (_)                           \s
                              | |   _ __  | |_  ___   _ __  _ __ ___    __ _ | |_  _   ___   _ __               \s
                              | |  | '_ \\ |  _|/ _ \\ | '__|| '_ ` _ \\  / _` || __|| | / _ \\ | '_ \\              \s
                             _| |_ | | | || | | (_) || |   | | | | | || (_| || |_ | || (_) || | | |             \s
                            |_____||_| |_||_|  \\___/ |_|   |_| |_| |_| \\__,_| \\__||_| \\___/ |_| |_|             \s
                                                                                                                \s
                                                                                                                \s""";

        boolean invalidSelection = false;
        int choice;

        do {
            clearConsole();
            System.out.println(banner);
            System.out.println("Selected Hotel: " + hotel.getName() + "\n");

            if (invalidSelection) {
                System.out.println("No valid reservation selected. Please try again.");
                invalidSelection = false;
            }

            Reservation reservation = selectReservation(hotel);

            if (reservation == null) {
                if (hotel.getReservationsList().isEmpty()) {
                    System.out.println("There currently are no active reservations in the hotel.");
                    System.out.print("\nPress ENTER to return to Manage Hotel Menu ");
                    scanner.nextLine();
                    return;
                } else {
                    invalidSelection = true;
                    continue;
                }
            }

            clearConsole();
            System.out.println(banner);
            System.out.println("Selected Hotel: " + hotel.getName() + "\n");
            hrs.viewReservationInfo(reservation);

            boolean invalidChoice = false;

            do {
                if (invalidChoice) {
                    System.out.println("Invalid choice. Please try again.");
                    invalidChoice = false;
                }

                System.out.println(" ");
                System.out.println("[1] – View Reservation Information of Other Reservations");
                System.out.println("[2] – Return to Manage Hotel Menu\n");
                System.out.print("Choose an option: ");

                try {
                    choice = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    switch (choice) {
                        case 1:
                            break;
                        case 2:
                            // Return to main menu
                            return;
                        default:
                            invalidChoice = true;
                    }
                } catch (InputMismatchException e) {
                    invalidChoice = true;
                    scanner.nextLine(); // Consume invalid input
                }
            } while (invalidChoice);

        } while (true);
    }

    /**
     * Manages hotel operations including selection, modification, and removal of
     * hotels.
     */
    public static void manageHotel() {
        // Banner for display
        String banner = """
                 __  __                                       _    _         _         _\s
                |  \\/  |                                     | |  | |       | |       | |
                | \\  / |  __ _  _ __    __ _   __ _   ___    | |__| |  ___  | |_  ___ | |
                | |\\/| | / _` || '_ \\  / _` | / _` | / _ \\   |  __  | / _ \\ | __|/ _ \\| |
                | |  | || (_| || | | || (_| || (_| ||  __/   | |  | || (_) || |_|  __/| |
                |_|  |_| \\__,_||_| |_| \\__,_| \\__, | \\___|   |_|  |_| \\___/  \\__|\\___||_|
                                               __/ |                                    \s
                                              |___/                                     \s""";
        int choice;
        boolean invalidChoice = false;
        boolean invalidSelection = false;
        do {
            clearConsole(); // Clear console for better user experience
            System.out.println(banner + "\n");

            if (invalidSelection) {
                System.out.println("\nInvalid hotel selection. Try again.");
                invalidSelection = false;
            }

            Hotel hotel = selectHotel(); // Method to select hotel
            if (hotel == null) {
                if (hrs.getHotelList().isEmpty()) {
                    System.out.print("\nPress ENTER to return to main menu");
                    scanner.nextLine();
                    return;
                } else {
                    invalidSelection = true;
                    continue;
                }
            }

            if (invalidChoice) {
                System.out.println("\nInvalid choice. Please try again.");
                invalidChoice = false;
            }

            clearConsole();
            System.out.println(banner + "\n");
            System.out.println("\nSelected Hotel: " + hotel.getName() + "\n");

            // Display menu options
            System.out.println("[1] – Change hotel name");
            System.out.println("[2] – Add rooms");
            System.out.println("[3] – Remove room");
            System.out.println("[4] – Update base price");
            System.out.println("[5] – Remove reservation");
            System.out.println("[6] – Remove hotel");
            System.out.println("[7] – Select a New Hotel");
            System.out.println("[8] – Return to Main Menu\n");

            System.out.print("Choose an option: ");
            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1 -> changeHotelName(hotel); // Change hotel name
                    case 2 -> addRooms(hotel); // Add rooms to hotel
                    case 3 -> removeRoom(hotel); // Remove a room from hotel
                    case 4 -> updateBasePrice(hotel); // Update the base price of hotel rooms
                    case 5 -> removeReservation(hotel); // Remove a reservation from hotel
                    case 6 -> removeHotel(hotel); // Remove the hotel from the system
                    case 7 -> {
                    } // Select a new hotel
                    case 8 -> {
                        return; // Exit to main menu
                    }
                    default -> invalidChoice = true; // Invalid menu choice
                }
            } catch (InputMismatchException e) {
                invalidChoice = true;
                scanner.nextLine(); // Consume invalid input
            }
        } while (true);
    }

    /**
     * Changes the name of the selected hotel.
     *
     * @param hotel The hotel whose name is to be changed.
     */
    public static void changeHotelName(Hotel hotel) {
        String oldHotelName = hotel.getName();
        String newHotelName;
        boolean invalidChoice = false;
        int choice;
        int errorType = 0;

        do {
            clearConsole(); // Clear console for better user experience
            String banner = """
                               _    _             _         _                  \s
                              | |  | |           | |       | |                 \s
                              | |  | | _ __    __| |  __ _ | |_  ___           \s
                              | |  | || '_ \\  / _` | / _` || __|/ _ \\          \s
                              | |__| || |_) || (_| || (_| || |_|  __/          \s
                               \\____/ | .__/  \\__,_| \\__,_| \\__|\\___|          \s
                     _    _         _ | |     _   _   _                        \s
                    | |  | |       | ||_|    | | | \\ | |                       \s
                    | |__| |  ___  | |_  ___ | | |  \\| |  __ _  _ __ ___    ___\s
                    |  __  | / _ \\ | __|/ _ \\| | | . ` | / _` || '_ ` _ \\  / _ \\
                    | |  | || (_) || |_|  __/| | | |\\  || (_| || | | | | ||  __/
                    |_|  |_| \\___/  \\__|\\___||_| |_| \\_| \\__,_||_| |_| |_| \\___|
                                                                               \s
                                                                               \s""";
            System.out.println(banner);
            if (!(errorType == 0)) {
                if (errorType == 1) {
                    System.out.println("Hotel name cannot be blank. Try again.\n");
                } else if (errorType == 2) {
                    System.out.println("Hotel name has to contain at least one alphabetic character. Try again.\n");
                } else if (errorType == 3) {
                    System.out.println("Hotel name already exists. Try again.\n");
                }
                errorType = 0;
            }

            System.out.println("Current name of hotel: " + oldHotelName);
            System.out.print("Enter new hotel name: ");
            newHotelName = scanner.nextLine();

            if (hrs.updateHotelName(newHotelName, hotel)) { // Update hotel name in the system
                do {
                    clearConsole();
                    System.out.println(banner);
                    System.out.println("Hotel name update successful.");
                    System.out.println(
                            "Hotel '" + oldHotelName + "' has been updated to Hotel '" + newHotelName + "'.\n");

                    if (invalidChoice) {
                        System.out.println("Invalid choice. Please try again.");
                    }
                    System.out.println("[1] Change Hotel Name Again");
                    System.out.println("[2] Return to Manage Hotels Menu\n");
                    System.out.print("Choose an option: ");
                    try {
                        choice = scanner.nextInt();
                        scanner.nextLine(); // Consume newline

                        switch (choice) {
                            case 1 -> errorType = 4; // Change hotel name again
                            case 2 -> {
                                return; // Return to manage hotels menu
                            }
                            default -> invalidChoice = true; // Invalid menu choice
                        }
                    } catch (InputMismatchException e) {
                        invalidChoice = true;
                        scanner.nextLine(); // Consume invalid input
                    }
                } while (invalidChoice);
            } else {
                if (newHotelName.isEmpty() || newHotelName.isBlank() || newHotelName.equals("\n"))
                    errorType = 1; // New hotel name cannot be blank
                else if (!newHotelName.matches(".*[a-zA-Z].*"))
                    errorType = 2; // New hotel name must contain at least one alphabetic character
                else
                    errorType = 3; // New hotel name already exists
            }

        } while (true);
    }

    /**
     * Adds rooms to the selected hotel.
     *
     * @param hotel The hotel to which rooms will be added.
     */
    public static void addRooms(Hotel hotel) {
        // Banner for display
        String banner = """
                              _      _    _____                              \s
                    /\\       | |    | |  |  __ \\                             \s
                   /  \\    __| |  __| |  | |__) | ___    ___   _ __ ___   ___\s
                  / /\\ \\  / _` | / _` |  |  _  / / _ \\  / _ \\ | '_ ` _ \\ / __|
                 / ____ \\| (_| || (_| |  | | \\ \\| (_) || (_) || | | | | |\\__ \\
                /_/    \\_\\\\__,_| \\__,_|  |_|  \\_\\\\___/  \\___/ |_| |_| |_||___/
                                                                             \s
                                                                             \s""";
        boolean invalidInput = false;
        boolean invalidChoice = false;

        while (true) {
            clearConsole(); // Clear console for better user experience
            System.out.println(banner);

            if (invalidInput) {
                System.out.println("Invalid input. Please enter a valid number.\n");
                invalidInput = false;
            }

            System.out.println("Add Rooms to Hotel: " + hotel.getName());
            System.out.print("Enter number of rooms to add (max. " + (50 - hotel.getRoomsList().size()) + "): ");
            try {
                int numRooms = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                if (hotel.addRoom(numRooms)) { // Add rooms to the hotel
                    clearConsole();
                    System.out.println(banner);
                    System.out.println(numRooms + " rooms added to " + hotel.getName() + " successfully.\n");
                } else {
                    clearConsole();
                    System.out.println(banner);
                    System.out.println("Failed to add " + numRooms + " rooms to '" + hotel.getName() + "'.");
                    System.out.println("Only a maximum of " + (50 - hotel.getRoomsList().size())
                            + " can still be added to the hotel.\n");
                }
            } catch (InputMismatchException e) {
                invalidInput = true;
                scanner.nextLine(); // Consume invalid input
                continue;
            }

            boolean validChoice = false;
            while (!validChoice) {
                if (invalidChoice) {
                    System.out.println("Invalid choice. Please try again.");
                    invalidChoice = false;
                }

                // Display options to add more rooms or return to menu
                System.out.println("[1] – Add more rooms");
                System.out.println("[2] – Return to manage hotels menu\n");
                System.out.print("Choose an option: ");
                try {
                    int choice = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    switch (choice) {
                        case 1 -> validChoice = true; // Continue to add more rooms
                        case 2 -> {
                            return; // Return to manage hotels menu
                        }
                        default -> {
                            invalidChoice = true;
                            clearConsole();
                            System.out.println(banner);
                        }
                    }
                } catch (InputMismatchException e) {
                    invalidChoice = true;
                    scanner.nextLine(); // Consume invalid input
                    clearConsole();
                    System.out.println(banner);
                }
            }
        }
    }

    /**
     * Removes a room from the selected hotel.
     *
     * @param hotel The hotel from which a room will be removed.
     */
    public static void removeRoom(Hotel hotel) {
        // Banner for display
        String banner = """
                 _____                                        _____                              \s
                |  __ \\                                      |  __ \\                             \s
                | |__) | ___  _ __ ___    ___ __   __ ___    | |__) | ___    ___   _ __ ___   ___\s
                |  _  / / _ \\| '_ ` _ \\  / _ \\\\ \\ / // _ \\   |  _  / / _ \\  / _ \\ | '_ ` _ \\ / __|
                | | \\ \\|  __/| | | | | || (_) |\\ V /|  __/   | | \\ \\| (_) || (_) || | | | | |\\__ \\
                |_|  \\_\\\\___||_| |_| |_| \\___/  \\_/  \\___|   |_|  \\_\\\\___/  \\___/ |_| |_| |_||___/
                                                                                                 \s
                                                                                                 \s""";
        boolean invalidChoice = false;
        boolean roomRemoved = false;
        boolean invalidInput = false;

        do {
            clearConsole(); // Clear console for better user experience
            System.out.println(banner);

            if (invalidInput) {
                System.out.println("Invalid input. Please select a room from the list.\n");
                invalidInput = false;
            }

            Room room = selectRoom(hotel); // Method to select a room from the hotel
            if (room != null) {
                if (hotel.removeRoom(room)) { // Remove the selected room
                    clearConsole();
                    System.out.println(banner);
                    System.out.println("Room " + room.getName() + " was removed successfully.\n");
                    roomRemoved = true;
                } else {
                    clearConsole();
                    System.out.println(banner);
                    System.out.println("Failed to remove room " + room.getName() + " from " + hotel.getName() + ".");
                    System.out.println("Room " + room.getName() + " has active reservations and cannot be removed.\n");
                    roomRemoved = false;
                }
            } else {
                invalidInput = true;
                continue;
            }

            boolean validChoice = false;
            while (!validChoice) {
                if (invalidChoice) {
                    System.out.println("Invalid choice. Please try again.");
                    invalidChoice = false;
                }

                // Display options to remove another room or return to menu
                System.out.println("[1] – Remove another room");
                System.out.println("[2] – Return to manage hotels menu\n");
                System.out.print("Choose an option: ");
                try {
                    int choice = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    switch (choice) {
                        case 1 -> validChoice = true; // Continue to remove another room
                        case 2 -> {
                            return; // Return to manage hotels menu
                        }
                        default -> invalidChoice = true; // Invalid menu choice
                    }
                } catch (InputMismatchException e) {
                    invalidChoice = true;
                    scanner.nextLine(); // Consume invalid input
                }
            }
        } while (roomRemoved);
    }

    /**
     * Updates the base price of rooms in the selected hotel.
     *
     * @param hotel The hotel for which the base price will be updated.
     */
    public static void updateBasePrice(Hotel hotel) {
        // Banner for display
        String banner = """
                       _    _             _         _               \s
                      | |  | |           | |       | |              \s
                      | |  | | _ __    __| |  __ _ | |_  ___        \s
                      | |  | || '_ \\  / _` | / _` || __|/ _ \\       \s
                      | |__| || |_) || (_| || (_| || |_|  __/       \s
                       \\____/ | .__/  \\__,_| \\__,_| \\__|\\___|       \s
                 ____         | |          _____        _           \s
                |  _ \\        |_|         |  __ \\      (_)          \s
                | |_) |  __ _  ___   ___  | |__) |_ __  _   ___  ___\s
                |  _ <  / _` |/ __| / _ \\ |  ___/| '__|| | / __|/ _ \\
                | |_) || (_| |\\__ \\|  __/ | |    | |   | || (__|  __/
                |____/  \\__,_||___/ \\___| |_|    |_|   |_| \\___|\\___|
                                                                    \s
                                                                    \s""";

        boolean invalidChoice = false; // Flag for invalid menu choices
        boolean invalidInput = false; // Flag for invalid input for base price
        double oldBasePrice = hotel.getBasePrice();
        int choice;

        do {
            clearConsole();
            System.out.println(banner);
            System.out.println("Selected Hotel: " + hotel.getName());
            System.out.println(" ");

            do {
                clearConsole();
                System.out.println(banner);
                System.out.println("Selected Hotel: " + hotel.getName());
                System.out.println(" ");

                if (invalidInput) {
                    System.out.println("Invalid input. Please enter base price greater than or equal to 100.0.");
                    invalidInput = false;
                }

                try {
                    System.out.print("Enter new base price: ");
                    double newBasePrice = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline

                    // Check if base price is updated successfully
                    if (hotel.updateBasePrice(newBasePrice)) {
                        clearConsole();
                        System.out.println(banner);
                        System.out.println("Selected Hotel: " + hotel.getName());
                        System.out.println("\nBase price updated successfully from " + oldBasePrice + " to " + newBasePrice + ".\n");
                    } else {
                        if (newBasePrice < 100.0) {
                            invalidInput = true;
                        } else {
                            clearConsole();
                            System.out.println(banner);
                            System.out.println("Selected Hotel: " + hotel.getName());
                            System.out.println("\nFailed to update base price.");
                            System.out.println("Hotel '" + hotel.getName() + "' has active reservations.\n");
                        }
                    }
                } catch (InputMismatchException e) {
                    invalidInput = true;
                    scanner.nextLine(); // Consume invalid input
                }
            } while (invalidInput); // Repeat if the input was invalid

            boolean validChoice = false;
            while (!validChoice) {
                if (invalidChoice) {
                    System.out.println("Invalid choice. Please try again.");
                    invalidChoice = false;
                }

                // Display options to update base price again or return to menu
                System.out.println("[1] – Update Base Price Again");
                System.out.println("[2] – Return to Main Menu\n");
                System.out.print("Choose an option: ");
                try {
                    choice = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    switch (choice) {
                        case 1 -> validChoice = true; // Continue to update another base price
                        case 2 -> {
                            return; // Return to main menu
                        }
                        default -> invalidChoice = true;
                    }
                } catch (InputMismatchException e) {
                    invalidChoice = true;
                    scanner.nextLine(); // Consume invalid input
                }
            }
        } while (true);
    }

    /**
     * Removes a reservation from the selected hotel.
     *
     * @param hotel The hotel from which a reservation will be removed.
     */
    public static void removeReservation(Hotel hotel) {
        // Banner for display
        String banner = """
                            _____                                               \s
                           |  __ \\                                              \s
                           | |__) | ___  _ __ ___    ___ __   __ ___            \s
                           |  _  / / _ \\| '_ ` _ \\  / _ \\\\ \\ / // _ \\           \s
                           | | \\ \\|  __/| | | | | || (_) |\\ V /|  __/           \s
                           |_|  \\_\\\\___||_| |_| |_| \\___/  \\_/  \\___|           \s
                 _____                                      _    _              \s
                |  __ \\                                    | |  (_)             \s
                | |__) | ___  ___   ___  _ __ __   __ __ _ | |_  _   ___   _ __ \s
                |  _  / / _ \\/ __| / _ \\| '__|\\ \\ / // _` || __|| | / _ \\ | '_ \\\s
                | | \\ \\|  __/\\__ \\|  __/| |    \\ V /| (_| || |_ | || (_) || | | |
                |_|  \\_\\\\___||___/ \\___||_|     \\_/  \\__,_| \\__||_| \\___/ |_| |_|
                                                                                \s
                                                                                \s""";

        boolean invalidChoice = false; // Flag for invalid menu choices
        boolean invalidSelection = false; // Flag for invalid reservation selection
        int choice;

        do {
            clearConsole();
            System.out.println(banner);
            System.out.println("Selected Hotel: " + hotel.getName() + "\n");

            if (invalidSelection) {
                System.out.println("\nNo valid reservation selected. Please try again.");
                invalidSelection = false;
            }

            Reservation reservation = selectReservation(hotel); // Method to select a reservation from the hotel

            if (reservation == null) {
                if (hotel.getReservationsList().isEmpty()) {
                    System.out.println("There currently are no active reservations in the hotel.");
                    System.out.print("\nPress ENTER to return to Manage Hotel Menu ");
                    scanner.nextLine();
                    return;
                } else {
                    invalidSelection = true;
                    continue;
                }
            }

            // Check if reservation is canceled successfully
            if (hotel.cancelReservation(reservation)) {
                clearConsole();
                System.out.println(banner);
                System.out.println("Reservation with ID " + reservation.getReservationID() + " cancelled successfully.\n");
            } else {
                clearConsole();
                System.out.println(banner);
                System.out.println("Failed to cancel reservation with ID " + reservation.getReservationID() + ".\n");
            }

            do {
                if (invalidChoice) {
                    System.out.println("Invalid choice. Please try again.");
                    invalidChoice = false;
                }

                // Display options to remove another reservation or return to menu
                System.out.println("[1] – Remove Another Reservation");
                System.out.println("[2] – Return to Main Menu\n");
                System.out.print("Choose an option: ");
                try {
                    choice = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    switch (choice) {
                        case 1 -> {
                        }
                        case 2 -> {
                            return; // Return to main menu
                        }
                        default -> invalidChoice = true;
                    }
                } catch (InputMismatchException e) {
                    invalidChoice = true;
                    scanner.nextLine(); // Consume invalid input
                }
            } while (invalidChoice);
        } while (true);
    }

    /**
     * Removes a hotel from the hotel reservation system.
     *
     * @param hotel The hotel to be removed.
     */
    public static void removeHotel(Hotel hotel) {
        // ASCII art banner for the remove hotel screen
        String banner = """
                 _____                                       _    _         _         _\s
                |  __ \\                                     | |  | |       | |       | |
                | |__) | ___  _ __ ___    ___ __   __ ___   | |__| |  ___  | |_  ___ | |
                |  _  / / _ \\| '_ ` _ \\  / _ \\\\ \\ / // _ \\  |  __  | / _ \\ | __|/ _ \\| |
                | | \\ \\|  __/| | | | | || (_) |\\ V /|  __/  | |  | || (_) || |_|  __/| |
                |_|  \\_\\\\___||_| |_| |_| \\___/  \\_/  \\___|  |_|  |_| \\___/  \\__|\\___||_|
                                                                                       \s
                                                                                       \s""";
        boolean isValidInput = true;
        do {
            clearConsole();
            System.out.println(banner);
            System.out.println("Selected Hotel: " + hotel.getName() + "\n");

            if (!isValidInput) {
                System.out.println("Invalid input. Please Enter only 'yes' or 'no' and try again.");
            }

            System.out.print("Are you sure you want to remove this hotel? (yes/no): ");
            String confirmation = scanner.nextLine().trim().toLowerCase();

            if (confirmation.equals("yes")) {
                // Attempt to remove the hotel and notify the user of the outcome
                if (hrs.removeHotel(hotel)) {
                    clearConsole();
                    System.out.println(banner);
                    System.out.println("Hotel '" + hotel.getName() + "' removed successfully.\n");
                } else {
                    clearConsole();
                    System.out.println(banner);
                    System.out.println("Hotel '" + hotel.getName() + "' has active reservations and cannot be removed.\n");
                }
                System.out.print("Press ENTER to return to Manage Hotel Menu ");
                scanner.nextLine();
                return;
            } else if (confirmation.equals("no")) {
                clearConsole();
                System.out.println(banner);
                System.out.println("Hotel removal of " + hotel.getName() + " cancelled.\n");
                System.out.print("Press ENTER to return to Manage Hotel Menu ");
                scanner.nextLine();
                return;
            } else {
                isValidInput = false;
            }
        } while (true);
    }

    /**
     * Initiates the process for creating a hotel booking.
     * The method guides the user through selecting a hotel, entering guest
     * information,
     * selecting a room based on availability, and confirming the booking.
     * The process loops until a valid booking is made or the user decides to return
     * to the main menu.
     */
    public static void createBooking() {
        // Banner for the booking interface
        String banner = """
                   _____  _____   ______         _______  ______  \s
                  / ____||  __ \\ |  ____|    /\\ |__   __||  ____| \s
                 | |     | |__) || |__      /  \\   | |   | |__    \s
                 | |     |  _  / |  __|    / /\\ \\  | |   |  __|   \s
                 | |____ | | \\ \\ | |____  / ____ \\ | |   | |____  \s
                  \\_____||_|  \\_\\|______|/_/    \\_\\|_|   |______| \s
                 ____    ____    ____   _  __ _____  _   _   _____\s
                |  _ \\  / __ \\  / __ \\ | |/ /|_   _|| \\ | | / ____|
                | |_) || |  | || |  | || ' /   | |  |  \\| || |  __\s
                |  _ < | |  | || |  | ||  <    | |  | . ` || | |_ |
                | |_) || |__| || |__| || . \\  _| |_ | |\\  || |__| |
                |____/  \\____/  \\____/ |_|\\_\\|_____||_| \\_| \\_____|
                                                                  \s
                                                                  \s""";
        boolean invalidSelection = false;
        boolean invalidInput = false;
        boolean invalidChoice = false;
        boolean result = false;
        int checkInDate = 0;
        int checkOutDate = 0;
        int choice;
        String guestName;
        Room room;

        do {
            clearConsole();
            System.out.println(banner);

            if (invalidSelection) {
                System.out.println("Invalid hotel selection. Try again.");
                invalidSelection = false;
            }

            // Select a hotel
            Hotel hotel = selectHotel();
            if (hotel == null) {
                if (hrs.getHotelList().isEmpty()) {
                    System.out.print("\nPress ENTER to return to main menu");
                    scanner.nextLine();
                    return;
                } else {
                    invalidSelection = true;
                    continue;
                }
            }

            boolean isValidString = true;
            do {
                clearConsole();
                System.out.println(banner);
                System.out.println("Selected Hotel: " + hotel.getName());
                System.out.println(" ");

                if (!isValidString) {
                    System.out.println("Guest Name cannot be blank or contain non-alphabetic characters.");
                    isValidString = true;
                }

                // Enter guest name
                System.out.print("Enter Guest Name: ");
                guestName = scanner.nextLine();

                if (guestName.isEmpty() || guestName.isBlank() || guestName.equals("\n")
                        || !guestName.matches("^[a-zA-Z\\s]+$")) {
                    isValidString = false;
                }
            } while (!isValidString);

            do {
                clearConsole();
                System.out.println(banner);
                System.out.println("Selected Hotel: " + hotel.getName());
                System.out.println("Guest Name: " + guestName);
                System.out.println(" ");

                if (invalidInput) {
                    System.out.println("Please enter valid check-in (1-30) and check-out (2-31) dates.");
                    invalidInput = false;
                }

                try {
                    // Enter check-in and check-out dates
                    System.out.print("Enter Preferred Check-In Date: ");
                    checkInDate = scanner.nextInt();
                    System.out.print("Enter Preferred Check-Out Date: ");
                    checkOutDate = scanner.nextInt();
                    scanner.nextLine();

                    if (checkInDate >= checkOutDate || checkInDate < 1 || checkInDate > 30 || checkOutDate > 31) {
                        invalidInput = true;
                    }
                } catch (InputMismatchException e) {
                    invalidInput = true;
                    scanner.nextLine();
                }
            } while (invalidInput);

            clearConsole();
            System.out.println(banner);
            System.out.println("Selected Hotel: " + hotel.getName());
            System.out.println("Guest Name: " + guestName);

            // Display available rooms for the selected dates
            hrs.viewRoomsByDate(hotel, checkInDate, checkOutDate);
            ArrayList<Room> availableRooms = hrs.availableRooms(hotel, checkInDate, checkOutDate);

            if (availableRooms.isEmpty()) {
                clearConsole();
                System.out.println(banner);
                System.out.println("Selected Hotel: " + hotel.getName());
                System.out.println("Guest Name: " + guestName);
                System.out.println(" ");
                System.out.println("No rooms available on preferred dates. Please try again.");
            } else {
                System.out.print("Press ENTER to select a room ");
                scanner.nextLine();

                do {
                    clearConsole();
                    System.out.println(banner);
                    System.out.println("Selected Hotel: " + hotel.getName());
                    System.out.println("Guest Name: " + guestName);
                    System.out.println(" ");

                    if (invalidSelection) {
                        System.out.println("Hotel reservation failed due to invalid selection. Please try again.");
                        invalidSelection = false;
                    }

                    // Select a room
                    room = selectRoom(hotel);
                    if (room != null) {
                        // Create reservation
                        result = hotel.createReservation(guestName, checkInDate, checkOutDate, room);
                        clearConsole();
                        System.out.println(banner);
                        System.out.println("Selected Hotel: " + hotel.getName());
                        System.out.println("Guest Name: " + guestName);
                        System.out.println(" ");
                        if (result) {
                            System.out.println("Reservation created successfully.");
                            System.out.println(" ");
                            int hotelIndex = hrs.getHotelList().indexOf(hotel);
                            int reservationIndex = hrs.getHotelList().get(hotelIndex).getReservationsList().size() - 1;
                            hrs.viewReservationInfo(
                                    hrs.getHotelList().get(hotelIndex).getReservationsList().get(reservationIndex));
                            System.out.println(" ");
                            System.out.println("Take note of the reservation details.");
                        } else {
                            System.out.println("Failed to reserve room. Check room availability and dates.\n");
                        }
                        System.out.print("Press ENTER to continue ");
                        scanner.nextLine();
                    } else {
                        invalidSelection = true;
                    }
                } while (invalidSelection);

                clearConsole();
                System.out.println(banner);
                System.out.println("Selected Hotel: " + hotel.getName());
                System.out.println("Guest Name: " + guestName);

                // Confirm reservation result
                if (result) {
                    System.out.println("Reservation of Room " + room.getName() + " from Day " + checkInDate + " to Day "
                            + checkOutDate + " successful.");
                } else {
                    System.out.println("Reservation of Room " + room.getName() + " from Day " + checkInDate + " to Day "
                            + checkOutDate + " failed.");
                }
            }

            do {
                System.out.println(" ");

                if (invalidChoice) {
                    System.out.println("Invalid choice. Please try again.");
                    invalidChoice = false;
                }

                // Option to create another reservation or return to main menu
                System.out.println("[1] – Create Another Reservation");
                System.out.println("[2] – Return to Main Menu\n");
                System.out.print("Choose an option: ");
                try {
                    choice = scanner.nextInt();
                    scanner.nextLine();

                    switch (choice) {
                        case 1:
                            break;
                        case 2:
                            return;
                        default:
                            invalidChoice = true;
                    }
                } catch (InputMismatchException e) {
                    invalidChoice = true;
                    scanner.nextLine();
                }
            } while (invalidChoice);

        } while (true);
    }

    /**
     * Prompts the user to select a hotel from the list of available hotels.
     * If no hotels are available, notifies the user and returns null.
     *
     * @return the selected Hotel object, or null if selection is invalid or no
     * hotels are available
     */
    public static Hotel selectHotel() {
        int hotelIndex;

        // Check if the hotel list is empty
        if (hrs.getHotelList().isEmpty()) {
            System.out.println("No hotels currently available. Please create one first.");
            return null;
        }

        // Display available hotels
        System.out.println("Available hotels:");
        for (int i = 0; i < hrs.getHotelList().size(); i++) {
            System.out.println("\t" + (i + 1) + ". " + hrs.getHotelList().get(i).getName());
        }

        // Prompt user to select a hotel
        System.out.print("\nSelect a hotel (1-" + hrs.getHotelList().size() + "): ");
        try {
            hotelIndex = scanner.nextInt() - 1;
            scanner.nextLine(); // Consume newline
        } catch (InputMismatchException e) {
            scanner.nextLine(); // Consume invalid input
            return null;
        }

        // Validate the selected index and return the corresponding hotel
        if (hotelIndex >= 0 && hotelIndex < hrs.getHotelList().size()) {
            return hrs.getHotelList().get(hotelIndex);
        } else {
            return null;
        }
    }

    /**
     * Prompts the user to select a room from the given hotel's list of rooms.
     * If no rooms are available, notifies the user and returns null.
     *
     * @param hotel the Hotel object from which to select a room
     * @return the selected Room object, or null if selection is invalid or no rooms
     * are available
     */
    public static Room selectRoom(Hotel hotel) {
        int roomIndex;

        // Check if the room list is empty
        if (hotel.getRoomsList().isEmpty()) {
            System.out.println("No rooms available.");
            return null;
        }

        // Display available rooms
        System.out.println("Current Rooms:");
        for (int i = 0; i < hotel.getRoomsList().size(); i++) {
            Room room = hotel.getRoomsList().get(i);
            int reservationsCount = room.getReservationsList().size();
            System.out
                    .println("\t" + (i + 1) + ". Room " + room.getName() + " (" + reservationsCount + " reservations)");
        }

        // Prompt user to select a room
        System.out.print("\nSelect a room (1-" + hotel.getRoomsList().size() + "): ");
        try {
            roomIndex = scanner.nextInt() - 1;
            scanner.nextLine(); // Consume newline
        } catch (InputMismatchException e) {
            scanner.nextLine(); // Consume invalid input
            return null;
        }

        // Validate the selected index and return the corresponding room
        if (roomIndex >= 0 && roomIndex < hotel.getRoomsList().size()) {
            return hotel.getRoomsList().get(roomIndex);
        } else {
            return null;
        }
    }

    /**
     * Prompts the user to select a reservation from the given hotel's list of
     * reservations.
     * If no reservations are available, notifies the user and returns null.
     *
     * @param hotel the Hotel object from which to select a reservation
     * @return the selected Reservation object, or null if selection is invalid or
     * no reservations are available
     */
    public static Reservation selectReservation(Hotel hotel) {
        int reservationIndex;

        // Check if the reservation list is empty
        if (hotel.getReservationsList().isEmpty()) {
            return null;
        }

        // Display available reservations
        System.out.println("Available reservations:");
        for (int i = 0; i < hotel.getReservationsList().size(); i++) {
            Reservation reservation = hotel.getReservationsList().get(i);
            System.out.println(
                    "\t" + (i + 1) + ". " + reservation.getReservationID() + " (" + reservation.getGuestName() + ")");
        }

        // Prompt user to select a reservation
        System.out.print("\nSelect a reservation (1-" + hotel.getReservationsList().size() + "): ");
        try {
            reservationIndex = scanner.nextInt() - 1;
            scanner.nextLine(); // Consume newline
        } catch (InputMismatchException e) {
            scanner.nextLine(); // Consume invalid input
            return null;
        }

        // Validate the selected index and return the corresponding reservation
        if (reservationIndex >= 0 && reservationIndex < hotel.getReservationsList().size()) {
            return hotel.getReservationsList().get(reservationIndex);
        } else {
            return null;
        }
    }

    /**
     * Clears the console screen based on the operating system.
     * Uses "cls" command on Windows and "clear" command on Unix-like systems.
     */
    public static void clearConsole() {
        try {
            final String os = System.getProperty("os.name");
            if (os.contains("Windows")) {
                // Clear screen for Windows using "cmd" and "cls" command
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                // Clear screen for Unix-like systems using "sh" and "clear" command
                new ProcessBuilder("sh", "-c", "clear").inheritIO().start().waitFor();
            }
        } catch (final Exception e) {
            // Print stack trace if there's an exception
            e.printStackTrace();
        }
    }
}
