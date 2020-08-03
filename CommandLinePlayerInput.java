package com.company;

import java.util.Scanner;

public class CommandLinePlayerInput implements PlayerInput{
    Scanner scanner;

    public CommandLinePlayerInput(){
        scanner=new Scanner(System.in);
    }

    @Override
    public String askForInput() {
        return scanner.nextLine();
    }//end method


}//end class
