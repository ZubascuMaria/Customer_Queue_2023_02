package org.example.Model;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicInteger;

public class Server implements Runnable{
    private BlockingQueue <Client> tasks;
    private AtomicInteger waitingPeriod=new AtomicInteger();
    public Server()
    {
        this.tasks=new LinkedBlockingDeque<>();
        this.waitingPeriod.set(0);
    }
    public void addTask(Client newTask)
    {
        tasks.add(newTask);
        waitingPeriod.getAndAdd(newTask.tservice);
    }

    @Override
    public void run()
    {
        while(true)
        {
            if(!this.tasks.isEmpty())
            {
                while(!this.tasks.isEmpty())
                {
                try {
                    Client currentClient = this.tasks.peek();
                    while (currentClient.tservice != 0) {
                        Thread.sleep(1000);
                        currentClient.tservice--;
                    }
                    this.tasks.take();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            }else {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
    public int getWaitingTime()
    {
        return this.waitingPeriod.get();
    }
    public BlockingQueue <Client>  getTasks()
    {
        return this.tasks;
    }
    public String toString()
    {
        String s="{";
        for(Client c: tasks)
           s=s+" ,"+c.toString();
        s+="}";
        return s;
    }
}
