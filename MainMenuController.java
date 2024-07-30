import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuController {
    private MainMenuView view;
    private HotelReservationSystem model;

    public MainMenuController(MainMenuView view, HotelReservationSystem model) {
        this.view = view;
        this.model = model;

        this.view.getCreateHotelButton().addActionListener(new CreateHotelListener());
        this.view.getViewHotelButton().addActionListener(new ViewHotelListener());
        this.view.getManageHotelButton().addActionListener(new ManageHotelListener());
        this.view.getSimulateBookingButton().addActionListener(new SimulateBookingListener());
    }

    class CreateHotelListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            //implement functionality
        }
    }

    class ViewHotelListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            //implement functionality
        }
    }

    class ManageHotelListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            //implement functionality
        }
    }

    class SimulateBookingListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            //implement functionality
        }
    }
}