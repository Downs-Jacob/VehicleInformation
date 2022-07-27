package com.company;

import com.company.services.ChooseFile;
import com.itextpdf.text.DocumentException;
import com.opencsv.exceptions.CsvException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.Dimension;

import java.io.IOException;

// this class handles the creation of a user interactive window
// and deployment of buttons that handle the logic of converting
// the csv fil chosen by user into a pdf.
public class Gui {
    public static void createAndShowGUI() {
        //Create and set up the Jframe window.
        JFrame frame = new JFrame("Vehicle Information CSV Converter");
        frame.setLayout(new FlowLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Add window label to the frame
        JLabel label = new JLabel("Vehicle Information CSV Converter");
        frame.getContentPane().add(label);
        label.setText("Choose a Vehicle Information CSV file to convert");
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);

        //add button that will handle the print to pdf method
        JButton button = new JButton();
        button.setSize(new Dimension(50, 50));
        button.setLocation(200, 200);
        button.setBounds(50, 100, 80, 30);
        button.setText("Create PDF");
        button.addActionListener(e -> {
            try {
                // when button clicked run the pdf generator method and pass the user's chosen csv
                // file into the generator
                String chosenFile = ChooseFile.userFileChoice();
                new CreatePdf().pdfGenerator(chosenFile);
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (CsvException ex) {
                ex.printStackTrace();
            } catch (DocumentException ex) {
                ex.printStackTrace();
            }
            //close window
            frame.setVisible(false);
        });
        frame.add(button);

        //add button for printing the csv output into terminal
        JButton button2 = new JButton();
        button2.setSize(new Dimension(50, 50));
        button2.setLocation(200, 200);
        button2.setBounds(50, 100, 80, 30);
        button2.setText("Output to Terminal");
        button2.addActionListener(e -> {
            try {
                new CsvReader();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            } catch (CsvException csvException) {
                csvException.printStackTrace();
            }
            //close window
            frame.setVisible(false);
            });
        frame.add(button2);

        //Display the window
        frame.setPreferredSize(new Dimension(400, 100));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

