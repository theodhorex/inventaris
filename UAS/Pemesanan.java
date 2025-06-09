package org.ukdw.data;

import java.io.Serializable;

public class Pemesanan implements Serializable {

    private int id;
    private String userEmail;
    private int idRuangan;
    private String checkInDate;
    private String checkOutDate;
    private String checkInTime;
    private String checkOutTime;

    /**
     * Reservation Model.
     *
     * @param userEmail    Email ID for Booking a Reservation(Identifier).
     * @param idRuangan    Room Name.
     * @param checkInDate  Check in Date.
     * @param checkOutDate Check out Date
     * @param checkInTime  Check in time.
     * @param checkOutTime Check out time.
     */
    public Pemesanan(int id, String userEmail, int idRuangan,
                     String checkInDate, String checkOutDate,
                     String checkInTime, String checkOutTime) {
        this.id = id;
        this.userEmail = userEmail;
        this.idRuangan = idRuangan;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.checkInTime = checkInTime;
        this.checkOutTime = checkOutTime;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns the checkInDate.
     *
     * @return CheckInDate.
     */
    public String getCheckInDate() {
        return this.checkInDate;
    }

    /**
     * Returns the CheckOutDate.
     *
     * @return CheckOutDate.
     */
    public String getCheckOutDate() {
        return this.checkOutDate;
    }

    /**
     * Returns the email.
     *
     * @return email.
     */
    public String getUserEmail() {
        return userEmail;
    }

    /**
     * Returns rooms.
     *
     * @return room.
     */
    public int getIdRuangan() {
        return idRuangan;
    }

    /**
     * Returns the checkInTime.
     *
     * @return checkInTime.
     */
    public String getCheckInTime() {
        return checkInTime;
    }

    /**
     * Returns the checkOutTime.
     *
     * @return CheckOutTime
     */
    public String getCheckOutTime() {
        return checkOutTime;
    }


    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public void setIdRuangan(int idRuangan) {
        this.idRuangan = idRuangan;
    }

    public void setCheckInDate(String checkInDate) {
        this.checkInDate = checkInDate;
    }

    public void setCheckOutDate(String checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public void setCheckInTime(String checkInTime) {
        this.checkInTime = checkInTime;
    }

    public void setCheckOutTime(String checkOutTime) {
        this.checkOutTime = checkOutTime;
    }

}

