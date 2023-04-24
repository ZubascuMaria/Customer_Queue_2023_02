package org.example.BusinessLogic;

import org.example.Model.Client;
import org.example.Model.Server;

import java.util.List;

public interface Strategy {
    public void addC(List<Server>servers, Client t);
}
