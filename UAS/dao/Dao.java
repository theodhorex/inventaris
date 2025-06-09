package org.week12.util;

import java.util.List;

public interface Dao <ObjectType, IdType>{
    List<ObjectType> getAllDataCatatan();
    boolean addCatatan(ObjectType catatan);
    boolean updateCatatan(ObjectType catatanOld, ObjectType catatanNew);
    boolean deleteCatatan(ObjectType catatan);
}
