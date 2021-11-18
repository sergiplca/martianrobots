package com.sergi.martianrobots.model;

import javax.persistence.*;

@Entity
@Table(name = "scents")
public class Scent {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "x_coord")
    private int xCoord;

    @Column(name = "y_coord")
    private int yCoord;

    @Column(name = "orientation")
    private String orientation;

    public Scent(int xCoord, int yCoord, String orientation) {
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        this.orientation = orientation;
    }

    public int getxCoord() {
        return xCoord;
    }

    public void setxCoord(int xCoord) {
        this.xCoord = xCoord;
    }

    public int getyCoord() {
        return yCoord;
    }

    public void setyCoord(int yCoord) {
        this.yCoord = yCoord;
    }

    public String getOrientation() {
        return orientation;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }

    @Override
    public String toString() {
        return "Scent [xCoord=" + xCoord + ", yCoord=" + yCoord + ", orientation=" + orientation + "]";
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof Scent) {
            var scent = (Scent) object;
            return this.getxCoord() == scent.getxCoord()
                    && this.getyCoord() == scent.getyCoord()
                    && this.getOrientation().equals(scent.getOrientation());
        }
        return false;
    }
}
