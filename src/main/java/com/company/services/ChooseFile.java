package com.company.services;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

//class that handles the logic that allows a user to choose a csv file that can
//be converted into pdf
public class ChooseFile {
    public static String userFileChoice(){
        //start new chooser
        JFileChooser fileChooser = new JFileChooser();
        //limit the types of files allowed to be chosen for conversion
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "CSV File", "csv");
        fileChooser.setFileFilter(filter);
        //
        int returnVal = fileChooser.showOpenDialog(null);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
        }
        //sets the selected file's path which can then be sent to the pdf generator for conversion
        String chosenFile = fileChooser.getSelectedFile().getAbsolutePath();
        return chosenFile;
    }
}

