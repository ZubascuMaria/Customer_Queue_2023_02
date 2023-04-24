package org.example;

import org.example.BusinessLogic.SimulationManager;
import org.example.BusinessLogic.TimeStrategy;

public class Main {
    public static void main(String[] args) {

        SimulationManager s= new SimulationManager("test3.txt",1000, 20, 10, 100, 3, 9, 200,new TimeStrategy());
        Thread thread=new Thread(s);
        thread.start();
    }
}