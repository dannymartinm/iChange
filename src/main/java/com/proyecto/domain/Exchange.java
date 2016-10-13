package com.proyecto.domain;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;


/**
 * Created by Usuario on 08/10/2016.
 */
public class Exchange {

    private int idExchange;
    private String zoneEx;
    private boolean isDone;
    private Timestamp dateEx;


    public Exchange(int idExchange, String zoneEx, Timestamp dateEx ){
        this.idExchange = idExchange;
        this.zoneEx = zoneEx;
        this.isDone = false;
        this.dateEx = dateEx;

    }
    public Exchange(){}

    public int getIdExchange() {
        return idExchange;
    }

    public void setIdExchange(int idExchange) {
        this.idExchange = idExchange;
    }

    public String getZoneEx() {
        return zoneEx;
    }

    public void setZoneEx(String zoneEx) {
        this.zoneEx = zoneEx;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public Timestamp getDateEx() {
        return dateEx;
    }

    public void setDateEx(Timestamp dateEx) {
        this.dateEx = dateEx;
    }
}
