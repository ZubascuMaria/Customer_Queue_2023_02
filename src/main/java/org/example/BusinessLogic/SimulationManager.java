package org.example.BusinessLogic;

import org.example.Model.Client;
import org.example.Model.Server;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class SimulationManager implements Runnable{
public int timeLimit;
public int numberOfServers;
public int numberOfClients;
public double avgTime=0.0;
private Scheduler scheduler;
public BlockingQueue<Client> generatedTasks;
public FileWriter output;

public SimulationManager(String filename,int numberOfClients, int numberOfServers, int minArrivalTime, int maxArrivalTime, int minProcessingTime, int maxProcessingTime, int timeLimit, Strategy strategy)
{
    try {
        output = new FileWriter(filename);
    } catch (IOException e) {
        throw new RuntimeException(e);
    }
    this.numberOfClients=numberOfClients;
    this.numberOfServers=numberOfServers;
    this.timeLimit=timeLimit;
    this.scheduler=new Scheduler(numberOfServers,strategy);
    generateNRandomTasks(minArrivalTime,maxArrivalTime,minProcessingTime,maxProcessingTime);
}

    private void generateNRandomTasks(int minArrivalTime, int maxArrivalTime, int minProcessingTime, int maxProcessingTime)
    {
        this.generatedTasks=new ArrayBlockingQueue<>(this.numberOfClients);
        for(int i=0;i<this.numberOfClients;i++)
        {
            Client client=new Client(minArrivalTime,maxArrivalTime,minProcessingTime,maxProcessingTime);
            this.generatedTasks.add(client);
        }
        this.generatedTasks.stream().sorted();
    }
    @Override
    public void run()
    {
        for(Client c: generatedTasks)
            avgTime+=c.tservice;

         avgTime/=numberOfClients;

        int currentTime=0;
        while(currentTime<=this.timeLimit)
        {
            if (generatedTasks.isEmpty())
            {
                boolean b=true;
                for(Server s: scheduler.getServers())
                    if(!s.getTasks().isEmpty()) b=false;
                if(b==true)
                {

//                    try {
//                        output.write("Average time: "+this.avgTime+"\n");
//                    } catch (IOException e) {
//                        throw new RuntimeException(e);
//                    }
                    //System.out.println("Averrage time:"+this.avgTime);
                     break;
                }
            }
            try {
                output.write("Current time: "+currentTime+"\n");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            //System.out.println("Current time"+currentTime);
            for(Server s: scheduler.getServers())
                //System.out.println(s.getTasks());
            {
                try {
                    output.write(s.getTasks()+"\n");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            for(Client c: generatedTasks)
                if(c.tarrival==currentTime)
                {
                    scheduler.addTask(c);
                    generatedTasks.remove(c);
                }
            currentTime++;
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            output.write("Average time: "+this.avgTime+"\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            output.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
