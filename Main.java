import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        // JFrame
        JFrame frame = new JFrame("Flight Booking Management");
        frame.setLayout(null);

        // JLabels
        JLabel namelb = new JLabel("Name:");
        JLabel emaillb = new JLabel("Email-id:");
        JLabel agelb = new JLabel("Age:");
        JLabel ContactInformationlb = new JLabel("Contact No:");
        JLabel PaymentMethodlb = new JLabel("Payment Method:");
        JLabel SeatPreferenceslb = new JLabel("Seat Preferences:");
        JLabel TravelInsurancelb = new JLabel("Travel Insurance:");
        JLabel BaggageAllowancelb = new JLabel("Baggage Allowance:");


        // JTextFields
        JTextField nametf = new JTextField();
        JTextField emailtf = new JTextField();
        JTextField agetf = new JTextField();
        JTextField ContactInformationtf = new JTextField();
        JTextField PaymentMethodtf = new JTextField();
        JTextField SeatPreferencestf = new JTextField();
        JTextField TravelInsurancetf = new JTextField();
        JTextField BaggageAllowancetf = new JTextField();


        //JButton
        JButton exitbtn = new JButton("Exit");
        JButton bookbtn = new JButton("Book your seat");
        JButton clearbtn = new JButton("Clear");
        // Setting bounds for components
        namelb.setBounds(30, 30, 250, 30);
        emaillb.setBounds(30, 60, 250, 30);
        agelb.setBounds(30, 90, 250, 30);
        ContactInformationlb.setBounds(30, 120, 250, 30);
        SeatPreferenceslb.setBounds(300, 30, 250, 30);
        TravelInsurancelb.setBounds(300, 60, 250, 30);
        BaggageAllowancelb.setBounds(300, 90, 250, 30);
        PaymentMethodlb.setBounds(300, 120, 250, 30);

        nametf.setBounds(100, 30, 180, 27);
        emailtf.setBounds(100, 60, 180, 27);
        agetf.setBounds(100, 90, 180, 27);
        ContactInformationtf.setBounds(100, 120, 180, 27);
        SeatPreferencestf.setBounds(420, 30, 200, 27);
        TravelInsurancetf.setBounds(420, 60, 200, 27);
        BaggageAllowancetf.setBounds(420, 90, 200, 27);
        PaymentMethodtf.setBounds(420, 120, 200, 27);
        exitbtn.setBounds(70, 190, 120, 30);
        bookbtn.setBounds(210, 190, 120, 30);
        clearbtn.setBounds(360, 190, 120, 30);
        // Adding components to the frame
        frame.add(namelb);
        frame.add(emaillb);
        frame.add(agelb);
        frame.add(ContactInformationlb);
        frame.add(PaymentMethodlb);
        frame.add(SeatPreferenceslb);
        frame.add(TravelInsurancelb);
        frame.add(BaggageAllowancelb);

        frame.add(nametf);
        frame.add(emailtf);
        frame.add(agetf);
        frame.add(ContactInformationtf);
        frame.add(PaymentMethodtf);
        frame.add(SeatPreferencestf);
        frame.add(TravelInsurancetf);
        frame.add(BaggageAllowancetf);
        frame.add(exitbtn);
        frame.add(bookbtn);
        frame.add(clearbtn);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setSize(700, 500);


        exitbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

        clearbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Clear the form
                nametf.setText("");
                emailtf.setText("");
                agetf.setText("");
                ContactInformationtf.setText("");
                SeatPreferencestf.setText("");
                TravelInsurancetf.setText("");
                BaggageAllowancetf.setText("");
                PaymentMethodtf.setText("");
            }
        });

        bookbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Validate all fields are filled before proceeding
                if (nametf.getText().isEmpty() || emailtf.getText().isEmpty() ||
                        agetf.getText().isEmpty() || ContactInformationtf.getText().isEmpty() ||
                        SeatPreferencestf.getText().isEmpty() || TravelInsurancetf.getText().isEmpty() ||
                        BaggageAllowancetf.getText().isEmpty() || PaymentMethodtf.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Please fill in all details before booking.");
                }
                else {
                    String url = "jdbc:mysql://localhost:3306/flightbooking";
                    String username = "root";
                    String password = "";


                    try {
                        Connection connection = DriverManager.getConnection(url, username, password);

                        System.out.println("Db is connected");
                        String insertquery = " insert into flightbooking(name,email,age,ContactInformation,SeatPreferences,TravelInsurance,BaggageAllowance,PaymentMethod)"
                                + " values (?,?,?,?,?,?,?,?)";
                        PreparedStatement preparedStmt = connection.prepareStatement(insertquery);
                        preparedStmt.setString(1, nametf.getText());
                        preparedStmt.setString(2, emailtf.getText());
                        preparedStmt.setString(3, agetf.getText());
                        preparedStmt.setString(4, ContactInformationtf.getText());
                        preparedStmt.setString(5, SeatPreferencestf.getText());
                        preparedStmt.setString(6, TravelInsurancetf.getText());
                        preparedStmt.setString(7, BaggageAllowancetf.getText());
                        preparedStmt.setString(8, PaymentMethodtf.getText());
                        preparedStmt.execute();
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex+"Db is not connetced");
                    }
                    }

                }
            });


        }
    }







