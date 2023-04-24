package org.example.BusinessLogic;

import org.example.Model.Client;
import org.example.Model.Server;

import java.util.List;

public class TimeStrategy implements Strategy
{
    private int currentminTime(Server s)
    {
        int time=0;
        for(Client c: s.getTasks())
            time+=c.tservice;
        return time;
    }
    @Override
    public void addC(List<Server> servers, Client t)
    {
        int min=1000000;
        Server minS=new Server();
        for(Server s: servers)
        {
            int ct=currentminTime(s);
            if(min>ct)
            {
                min = ct;
                minS=s;
            }
        }
        //for(Server s: servers)
          //  if(min==currentminTime(s))
            //{
        minS.addTask(t);
            //}
    }

}
