package org.example.BusinessLogic;

import org.example.Model.Client;
import org.example.Model.Server;

import java.util.List;

public class ShortestQueueStrategy implements Strategy{
    @Override
    public void addC(List<Server> servers, Client t)
    {
        int min=100000000;
        Server minS=new Server();
        for(Server s: servers)
            if(s.getTasks().size()<min)
            {
                min = s.getTasks().size();
                minS=s;
            }
        minS.addTask(t);

    }
}
