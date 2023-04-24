package org.example.BusinessLogic;

import org.example.Model.Client;
import org.example.Model.Server;
import java.util.ArrayList;
import java.util.List;

public class Scheduler {
    private List<Server> servers;
    private int maxNoServers;
    private Strategy strategy;

    public Scheduler(int maxNoServers, Strategy strategy)
    {
        this.maxNoServers=maxNoServers;
        this.servers= new ArrayList<Server>();
        this.strategy=strategy;
        for(int i=0;i<maxNoServers;i++)
        {
            Server server=new Server();
            Thread serverThread=new Thread(server);
            this.servers.add(server);
            serverThread.start();
        }
    }
    public void changeStrategy(SelectionPolicy policy)
    {
        if(policy==SelectionPolicy.SHORTEST_TIME)
            strategy=new TimeStrategy();
        if(policy==SelectionPolicy.SHORTEST_QUEUE)
            strategy= new ShortestQueueStrategy();
    }

    public void addTask(Client t)
    {
       strategy.addC(this.servers,t);
    }
    public List<Server> getServers()
    {
        return this.servers;
    }

}
