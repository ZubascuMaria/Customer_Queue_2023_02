package org.example.Model;
import java.util.Random;
public class Client {
    private Integer id;
    public Integer tarrival;
    public Integer tservice;
    private static Integer ID_MOD=0;

    public Client(Integer miat, Integer maat, Integer mist, Integer mast)
    {
        this.id=ID_MOD;
        this.ID_MOD++;
        this.tarrival=generateRand(miat, maat);
        this.tservice=generateRand(mist,mast);
        System.out.println("ID:"+this.id+" tarrival:"+this.tarrival+" tservice:"+this.tservice);


    }
    private static Integer generateRand(Integer min,Integer max)
    {
        return (int) ((Math.random() * (max - min)) + min);
    }
    public String toString()
    {
        return "["+this.id+" "+this.tarrival+" "+this.tservice+"]";
    }

}
