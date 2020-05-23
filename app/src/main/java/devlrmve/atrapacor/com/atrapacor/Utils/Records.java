package devlrmve.atrapacor.com.atrapacor.Utils;

import java.util.Date;

/**
 * Created by Marco on 06/01/2016.
 */
public class Records {
    String email_Record;
    Date date_Record;
    int id_Record;

    public Records(String email_Record, Date date_Record, int id_Record) {
        this.email_Record = email_Record;
        this.date_Record = date_Record;
        this.id_Record = id_Record;
    }

    public String getEmail_Record() {
        return email_Record;
    }

    public void setEmail_Record(String email_Record) {
        this.email_Record = email_Record;
    }

    public Date getDate_Record() {
        return date_Record;
    }

    public void setDate_Record(Date date_Record) {
        this.date_Record = date_Record;
    }

    public int getId_Record() {
        return id_Record;
    }

    public void setId_Record(int id_Record) {
        this.id_Record = id_Record;
    }


}
