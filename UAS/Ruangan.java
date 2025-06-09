package org.ukdw.data;

import java.io.Serializable;

public class Ruangan implements Serializable {

    private int id;
    private String name;
    private int idGedung;

    /**
     * Stores the following parameters.
     * @param id     id ruangan
     * @param name     Name of the room.
     * @param idGedung Name of the building the room is in.

     */
    public Ruangan(int id, String name, int idGedung) {
        this.id = id;
        this.name = name;
        this.idGedung = idGedung;
    }

    /**
     * Returns the roomName.
     *
     * @return roomName.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the buildingName.
     *
     * @return buildingName
     */
    public int getIdGedung() {
        return idGedung;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIdGedung(int idGedung) {
        this.idGedung = idGedung;
    }

    public Object isGedung() {
        return null;
    }
}
