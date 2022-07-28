package com.company;

import java.io.IOException;

public class Main {

    //main method builds the window frame where all logic takes place. The buttons call the methods to convert
    //CSV to pdf or to print to terminal
    public static void main(String[] args) throws IOException {
       new Gui().createAndShowGUI();

    }

}
