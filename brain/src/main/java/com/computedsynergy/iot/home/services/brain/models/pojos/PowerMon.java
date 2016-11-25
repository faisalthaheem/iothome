package com.computedsynergy.iot.home.services.brain.models.pojos;

import java.util.Date;

/**
 *
 * @author Faisal Thaheem
 */

public class PowerMon {
    private int id;
    private double reading;
    private Date readat;

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the reading
     */
    public double getReading() {
        return reading;
    }

    /**
     * @param reading the reading to set
     */
    public void setReading(double reading) {
        this.reading = reading;
    }

    /**
     * @return the readat
     */
    public Date getReadat() {
        return readat;
    }

    /**
     * @param readat the readat to set
     */
    public void setReadat(Date readat) {
        this.readat = readat;
    }
    
    
}
