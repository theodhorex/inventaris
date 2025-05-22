package org.week12.dao;

import org.week12.data.Catatan;
import java.util.List;

public interface CatatanDao {
    List<Catatan> getAll();
    boolean add(Catatan catatan);
    boolean update(Catatan oldCatatan, Catatan newCatatan);
    boolean delete(Catatan catatan);
}
