package org.ukdw.managers;
import org.ukdw.data.Pemesanan;

import java.util.ArrayList;

public class PemesananManager {
    private ArrayList<Pemesanan> ListPemesanan;
    private int id = 0;
    public PemesananManager() {
        ListPemesanan = new ArrayList<>();
    }

    public boolean editPemesanan(int id, String userEmail, int idRuangan, String checkInDate, String checkInTime, String checkOutDate, String checkOutTime) {
//        update from pemesanan where id = id values (id, email, idRuangan, checkInDate, checkInTime, checkOutDate, checkOutTime);
        for (Pemesanan pemesanan : ListPemesanan) {
            if (pemesanan.getId() == id) {
                pemesanan.setId(id);
                pemesanan.setUserEmail(userEmail);
                pemesanan.setIdRuangan(idRuangan);
                pemesanan.setCheckInDate(checkInDate);
                pemesanan.setCheckInTime(checkInTime);
                pemesanan.setCheckOutDate(checkOutDate);
                pemesanan.setCheckOutTime(checkOutTime);
                return true;
            }
        }
        return false;
    }

    public boolean deletePemesanan(int id) {
//        delete from pemesanan where id = id;
        for (Pemesanan pemesanan : ListPemesanan) {
            if (pemesanan.getId() == id) {
                ListPemesanan.remove(pemesanan);
                return true;
            }
        }
        return false;
    }

    public boolean addPemesanan(String userEmail, int idRuangan, String checkInDate, String checkInTime, String checkOutDate, String checkOutTime) {
        Pemesanan pemesanan = new Pemesanan(id, userEmail, idRuangan, checkInDate, checkInTime, checkOutDate, checkOutTime);
        this.id = id + 1;
        return ListPemesanan.add(pemesanan);
//        add to db values(userEmail, idRuangan, checkInDate, checkInTime, checkOutDate, checkOutTime);
    }

    public ArrayList<Pemesanan> allPemesanan() {
        return ListPemesanan;
    }
}
