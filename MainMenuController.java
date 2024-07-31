import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuController {
    private MainMenuView view;
    private MainMenuModel model;

    public MainMenuController(MainMenuView view, MainMenuModel model) {
        this.view = view;
        this.model = model;

        // Initialize the view with the number of hotels
        updateHotelCount();

        // Add action listeners to buttons
        this.view.getManageHotelsButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                manageHotels();
            }
        });

        this.view.getCreateReservationButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createReservation();
            }
        });

        this.view.getCreateHotelButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createHotel(model.getHotelReservationSystem());
            }
        });
    }

    private void manageHotels() {
        if (model.areThereNoHotels()) {
            JOptionPane.showMessageDialog(view, "No hotels available to manage.", "Manage Hotels Error", JOptionPane.ERROR_MESSAGE);
        } else {
            SelectHotelModel selectHotelModel = new SelectHotelModel(model.getHotelReservationSystem());
            SelectHotelView selectHotelView = new SelectHotelView();
            SelectHotelController selectHotelController = new SelectHotelController(selectHotelView, selectHotelModel, view);
            selectHotelView.setVisible(true);
            view.setVisible(false);
        }
    }

    private void createReservation() {
        if (model.areThereNoHotels()) {
            JOptionPane.showMessageDialog(view, "No hotels available.", "Reservation Error", JOptionPane.ERROR_MESSAGE);
        } else if (model.areThereNoAvailableRooms()) {
            JOptionPane.showMessageDialog(view, "No rooms available.", "Reservation Error", JOptionPane.ERROR_MESSAGE);
        } else {
            CreateReservationModel createReservationModel = new CreateReservationModel(model.getHotelReservationSystem());
            CreateReservationView createReservationView = new CreateReservationView();
            CreateReservationController createReservationController = new CreateReservationController(createReservationView, createReservationModel, view);
            createReservationView.setVisible(true);
            view.setVisible(false);
        }
    }


    private void createHotel(HotelReservationSystem hrs) {
        CreateHotelModel createHotelModel = new CreateHotelModel(hrs);
        CreateHotelView createHotelView = new CreateHotelView();
        CreateHotelController createHotelController = new CreateHotelController(createHotelView, createHotelModel, view);
        createHotelView.setVisible(true);
        view.setVisible(false);
    }

    private void updateHotelCount() {
        view.setHotelCount(model.getNumberOfOperatingHotels());
    }
}
