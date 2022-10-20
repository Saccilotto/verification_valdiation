package com.bcopstein;

public class IllegalNameException extends RuntimeException{
    public IllegalNameException(){
        super("Nome invaido!");
    }
}
