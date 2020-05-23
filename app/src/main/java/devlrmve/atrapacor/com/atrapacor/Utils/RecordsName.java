package devlrmve.atrapacor.com.atrapacor.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marco on 07/01/2016.
 */
public class RecordsName {
    int id_record;
    String name_record;
    String description;

    public RecordsName(int id_record, String name_record, String description) {
        this.id_record = id_record;
        this.name_record = name_record;
        String[] photo_name_record = new String[]{
                "ic_mole",
                "ic_fox",
                "ic_predator",
                "ic_",
        };
        this.description = description;
    }

    public int getId_record() {
        return id_record;
    }

    public void setId_record(int id_record) {
        this.id_record = id_record;
    }

    public String getName_record() {
        return name_record;
    }

    public void setName_record(String name_record) {
        this.name_record = name_record;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static List<RecordsName> createRecordList(ArrayList<RecordsName> list) {
        List<RecordsName> records = list;
        return records;
    }
}
