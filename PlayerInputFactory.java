package com.company;

public class PlayerInputFactory {

    public PlayerInput factoryMethod(String playerName, char sign){
        if(playerName.equalsIgnoreCase("ai"))
            return new AIPlayerInput(sign);
        else return new CommandLinePlayerInput();

    }

}
