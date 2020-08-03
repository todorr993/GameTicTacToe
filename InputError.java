package com.company;

public enum InputError {

    INVALID_COORDINATES_FORMAT{
        @Override
        public void printMessage() {
            System.out.println("Invalid input: you must enter the x and y coordinates separated by spaces");
        }
    },
    INVALID_COORDINATES_VALUE{
        @Override
        public void printMessage() {
            System.out.println("Invalid input: those coordinates are outside the playable area");
        }
    },
    INVALID_SPACE{
        @Override
        public void printMessage() {
            System.out.println("Invalid input: that space is already taken");
    }
    };

    abstract void printMessage();

}
