import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller for managing updates to hotel attributes, including changing the hotel name,
 * adding and removing rooms, updating base price, and managing reservations.
 */
public class UpdateHotelAttributesController {
    private final UpdateHotelAttributesView view; // The view associated with this controller
    private final UpdateHotelAttributesModel model; // The model that holds hotel data
    private final SelectHotelController selectHotelController; // Controller for selecting hotels
    private final SelectHotelView selectHotelView; // View for selecting hotels

    /**
     * Constructs an UpdateHotelAttributesController with the specified view, model,
     * and controllers.
     *
     * @param view                  The view associated with this controller
     * @param model                 The model containing hotel data
     * @param selectHotelController The controller for hotel selection
     * @param selectHotelView       The view for hotel selection
     */
    public UpdateHotelAttributesController(UpdateHotelAttributesView view,
                                           UpdateHotelAttributesModel model,
                                           SelectHotelController selectHotelController,
                                           SelectHotelView selectHotelView) {
        this.view = view;
        this.model = model;
        this.selectHotelController = selectHotelController;
        this.selectHotelView = selectHotelView;

        // Add action listeners to buttons
        this.view.getChangeHotelNameButton().addActionListener(e -> {
            changeHotelName(); // Call the method to change hotel name
        });

        this.view.getAddRoomsButton().addActionListener(e -> {
            addRoomsToHotel(); // Call the method to handle adding rooms
        });

        this.view.getRemoveRoomButton().addActionListener(e -> {
            removeRoomsFromHotel(); // Call the method to handle removing rooms
        });

        this.view.getUpdateBasePriceButton().addActionListener(e -> updateBasePrice());

        this.view.getRemoveReservationButton().addActionListener(e -> removeReservation());

        this.view.getRemoveHotelButton().addActionListener(e -> removeHotel());

        this.view.getSetDatePriceModifierButton().addActionListener(e -> setDatePriceModifier());

        // Add action listeners for bottom buttons
        this.view.getBackToSelectionButton().addActionListener(e -> backToSelectionPage());

        this.view.getBackToMainMenuButton().addActionListener(e -> backToMainMenu());
    }

    /**
     * Changes the name of the selected hotel based on user input.
     * Displays a dialog for the user to enter a new hotel name and updates the model if valid.
     */
    private void changeHotelName() {
        String currentHotelName = model.getSelectedHotelName(); // Get the current hotel name
        String newHotelName = JOptionPane.showInputDialog(view, "Enter a new name for the hotel:", currentHotelName);

        // Check if the new hotel name is valid
        if (newHotelName != null && model.isHotelNameValid(newHotelName)) {
            // Update the hotel name in the model
            if (model.getHrs().updateHotelName(newHotelName, model.getHotel())) { // Use the passed hrs
                JOptionPane.showMessageDialog(view, "Hotel name updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                selectHotelController.refreshHotelList(); // Refresh the hotel list in the selection view
            } else {
                JOptionPane.showMessageDialog(view, "Failed to update hotel name. It may already exist.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(view, "Invalid hotel name. Please ensure it starts with a letter and is not empty.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Adds rooms to the selected hotel based on user input.
     * Prompts the user to select the type and number of rooms to add.
     */
    private void addRoomsToHotel() {
        int currentRoomCount = model.getHotel().getRoomsList().size(); // Get the current room count

        // Check if the hotel already has 50 rooms
        if (currentRoomCount >= 50) {
            JOptionPane.showMessageDialog(view, "The hotel already has 50 rooms. Cannot add more rooms.", "Error", JOptionPane.ERROR_MESSAGE);
            return; // Exit the method if the room limit is reached
        }

        String[] roomTypes = {"STANDARD", "DELUXE", "EXECUTIVE"}; // Define room types
        String roomType = (String) JOptionPane.showInputDialog(
                view,
                "Select the type of room to add:",
                "Add Room",
                JOptionPane.QUESTION_MESSAGE,
                null,
                roomTypes,
                roomTypes[0] // Default selection
        );

        if (roomType != null) { // User didn't cancel
            int remainingRooms = 50 - currentRoomCount; // Calculate remaining room capacity
            String message = "Enter the number of rooms to add (max. " + remainingRooms + "):";

            String numberOfRoomsString = JOptionPane.showInputDialog(view, message);

            if (numberOfRoomsString != null) { // User didn't cancel
                try {
                    int numberOfRooms = Integer.parseInt(numberOfRoomsString); // Parse input to integer
                    // Validate the number of rooms to add
                    if (numberOfRooms > 0 && numberOfRooms <= remainingRooms) {
                        // Call the method to add rooms
                        if (model.getHrs().addRoomsToHotel(model.getHotel(), numberOfRooms, Room.RoomType.valueOf(roomType))) {
                            JOptionPane.showMessageDialog(view, "Successfully added " + numberOfRooms + " " + roomType + " rooms!");
                        } else {
                            JOptionPane.showMessageDialog(view, "Failed to add rooms. Please check your input.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(view, "Please enter a valid number of rooms (1 to " + remainingRooms + ").");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(view, "Invalid number format. Please enter a number.");
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(view, "Invalid room type selected.");
                }
            }
        }
    }

    /**
     * Removes selected rooms from the hotel, prompting the user to select which rooms to remove.
     */
    private void removeRoomsFromHotel() {
        List<Room> availableRooms = model.getHotel().getRoomsList(); // Get available rooms

        // Check if there are no rooms in the hotel
        if (availableRooms.isEmpty()) {
            JOptionPane.showMessageDialog(view, "No rooms available for removal. The hotel has no rooms.", "Error", JOptionPane.ERROR_MESSAGE);
            return; // Exit if there are no rooms
        }

        // Filter rooms without reservations
        availableRooms = availableRooms.stream()
                .filter(room -> room.getReservationsList().isEmpty())
                .toList();

        // Check if all rooms have active reservations
        if (availableRooms.isEmpty()) {
            JOptionPane.showMessageDialog(view, "No rooms available for removal. All rooms have active reservations.", "Error", JOptionPane.ERROR_MESSAGE);
            return; // Exit if no rooms can be removed
        }

        // Create a checkbox for each available room
        JCheckBox[] checkboxes = new JCheckBox[availableRooms.size()];
        for (int i = 0; i < availableRooms.size(); i++) {
            checkboxes[i] = new JCheckBox(availableRooms.get(i).getName());
        }

        // Create a panel with a GridLayout to wrap checkboxes
        JPanel panel = new JPanel(new GridLayout(0, 10)); // 0 rows, 10 columns
        for (JCheckBox checkbox : checkboxes) {
            panel.add(checkbox);
        }

        // Display the checkboxes in a dialog
        int result = JOptionPane.showConfirmDialog(view, panel, "Select Rooms to Remove", JOptionPane.OK_CANCEL_OPTION);
        if (result != JOptionPane.OK_OPTION) {
            return; // User cancelled the dialog
        }

        // Collect selected rooms
        List<Room> roomsToRemove = new ArrayList<>();
        for (int i = 0; i < checkboxes.length; i++) {
            if (checkboxes[i].isSelected()) {
                roomsToRemove.add(availableRooms.get(i)); // Add selected rooms to the list
            }
        }

        if (roomsToRemove.isEmpty()) {
            JOptionPane.showMessageDialog(view, "No rooms selected for removal.", "Warning", JOptionPane.WARNING_MESSAGE);
            return; // Exit if no rooms were selected
        }

        // Remove the selected rooms from the hotel
        List<Room> removedRooms = model.getHotel().removeRooms(roomsToRemove);

        if (removedRooms.isEmpty()) {
            JOptionPane.showMessageDialog(view, "No rooms were removed. Please check your selection.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(view, "Successfully removed " + removedRooms.size() + " room(s) from the hotel.");
        }
    }

    /**
     * Updates the base price for all rooms in the selected hotel.
     * The method checks if there are any rooms in the hotel and if there are active reservations
     * before prompting the user for a new base price.
     */
    private void updateBasePrice() {
        // Check if there are any rooms in the hotel
        if (model.getHotel().getRoomsList().isEmpty()) {
            JOptionPane.showMessageDialog(view, "There are no rooms in the hotel to update the base price.", "Error", JOptionPane.ERROR_MESSAGE);
            return; // Exit if there are no rooms
        }

        // Check if there is at least one active reservation
        if (!model.getHotel().getReservationsList().isEmpty()) {
            JOptionPane.showMessageDialog(view, "Cannot update the base price. There is at least one active reservation.", "Error", JOptionPane.ERROR_MESSAGE);
            return; // Exit if there are active reservations
        }

        // Prompt user for the new base price
        Double newBasePrice = promptForNewBasePrice();
        if (newBasePrice == null) {
            return; // User cancelled or input was invalid
        }

        // Update the base price for all rooms
        if (model.getHrs().updateHotelRoomBasePrice(model.getHotel(), newBasePrice)) {
            JOptionPane.showMessageDialog(view, "Base price updated successfully for all rooms!");
        } else {
            JOptionPane.showMessageDialog(view, "Failed to update base price. Please ensure the price is valid (minimum PHP100).", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Prompts the user for a new base price for all rooms and validates the input.
     *
     * @return the new base price entered by the user, or null if the input was invalid or cancelled.
     */
    private Double promptForNewBasePrice() {
        // Prompt user for the new base price
        String newBasePriceString = JOptionPane.showInputDialog(view, "Enter the new base price for all rooms (minimum PHP100):");
        if (newBasePriceString != null) { // User didn't cancel
            try {
                double newBasePrice = Double.parseDouble(newBasePriceString);
                if (newBasePrice < 100.0) {
                    JOptionPane.showMessageDialog(view, "The base price must be at least PHP100.", "Error", JOptionPane.ERROR_MESSAGE);
                    return null; // Invalid base price
                }
                return newBasePrice; // Return the valid new base price
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(view, "Invalid number format. Please enter a valid base price.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        return null; // User cancelled or invalid input
    }

    /**
     * Removes the selected hotel after confirming the user's choice.
     * If there are active reservations in the hotel, the removal will not be allowed.
     */
    private void removeHotel() {
        // Get the selected hotel
        Hotel selectedHotel = model.getHotel(); // Adjust this line to get the currently selected hotel

        // Check if a hotel is selected
        if (selectedHotel == null) {
            JOptionPane.showMessageDialog(view, "Please select a hotel to remove.", "Error", JOptionPane.ERROR_MESSAGE);
            return; // Exit if no hotel is selected
        }

        // Confirmation dialog for hotel removal
        int confirmation = JOptionPane.showConfirmDialog(
                view,
                "Are you sure you want to remove the hotel: " + selectedHotel.getName() + "?",
                "Confirm Removal",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );

        if (confirmation != JOptionPane.YES_OPTION) {
            return; // Exit if the user cancels the operation
        }

        // Attempt to remove the hotel
        if (model.getHrs().removeHotel(selectedHotel)) {
            JOptionPane.showMessageDialog(view, "Hotel removed successfully.");
            view.dispose();
            selectHotelController.goBackToMainMenu();
        } else {
            JOptionPane.showMessageDialog(view, "Cannot remove the hotel. There are active reservations.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Removes a reservation based on the user's input.
     * Prompts the user for the Reservation ID and verifies if it exists.
     */
    private void removeReservation() {
        // Check if the selected hotel has any reservations
        if (model.getHotel().getReservationsList().isEmpty()) {
            JOptionPane.showMessageDialog(view, "There are currently no reservations in the selected hotel.", "Error", JOptionPane.ERROR_MESSAGE);
            return; // Exit if there are no reservations
        }

        // Prompt user for the Reservation ID to remove
        String reservationId = JOptionPane.showInputDialog(view, "Enter the Reservation ID to remove:");

        if (reservationId == null || reservationId.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Reservation ID cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
            return; // Exit if no input
        }

        // Retrieve the reservation based on the ID
        Reservation reservation = model.getReservationById(reservationId);
        if (reservation == null) {
            JOptionPane.showMessageDialog(view, "No reservation found with ID: " + reservationId, "Error", JOptionPane.ERROR_MESSAGE);
            return; // Exit if the reservation does not exist
        }

        // Attempt to cancel the reservation
        boolean success = model.cancelReservation(reservation);
        if (success) {
            JOptionPane.showMessageDialog(view, "Reservation removed successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(view, "Failed to remove the reservation. It may not exist in the selected hotel.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Sets a date price modifier for a specific check-in date.
     * Prompts the user for a check-in date and the corresponding price modifier.
     */
    private void setDatePriceModifier() {
        // Prompt user to select a check-in date
        String[] dates = new String[30];
        for (int i = 0; i < 30; i++) {
            dates[i] = String.valueOf(i + 1); // Create array of dates from 1 to 30
        }

        // Show input dialog for date selection
        String selectedDateString = (String) JOptionPane.showInputDialog(
                view,
                "Select a check-in date (1-30):",
                "Set Date Price Modifier",
                JOptionPane.QUESTION_MESSAGE,
                null,
                dates,
                dates[0] // Default selection
        );

        if (selectedDateString == null) {
            return; // User cancelled
        }

        int selectedDate = Integer.parseInt(selectedDateString);

        // Prompt user for the price modifier
        String modifierString = JOptionPane.showInputDialog(view, "Enter the price modifier (0.5 to 1.5):");

        if (modifierString != null) { // User didn't cancel
            try {
                double modifier = Double.parseDouble(modifierString);
                if (modifier < 0.5 || modifier > 1.5) {
                    JOptionPane.showMessageDialog(view, "Price modifier must be between 0.5 and 1.5.", "Error", JOptionPane.ERROR_MESSAGE);
                    return; // Exit if the modifier is out of range
                }

                // Set the date price modifier in the hotel
                model.getHotel().setDatePriceModifier(selectedDate, modifier);
                JOptionPane.showMessageDialog(view, "Price modifier set successfully for date " + selectedDate + ".");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(view, "Invalid number format. Please enter a valid price modifier.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Closes the current view and returns to the hotel selection page.
     */
    private void backToSelectionPage() {
        view.dispose(); // Close the current view
        selectHotelView.setVisible(true); // Show the selection view again
    }

    /**
     * Closes the current view and returns to the main menu.
     */
    private void backToMainMenu() {
        view.dispose(); // Close the current view
        selectHotelController.goBackToMainMenu(); // Show the main menu
    }
}
