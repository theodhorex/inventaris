package org.week12.util;

import org.week12.data.Catatan;

import java.util.List;

public interface ICatatanDao {
    List<Catatan> getAllDataCatatan();
    boolean addCatatan(Catatan catatan);
    boolean updateCatatan(Catatan oldCatatan, Catatan newCatatan);
    boolean deleteCatatan(Catatan catatan);
}
