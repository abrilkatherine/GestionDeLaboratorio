package main.uade.edu.ar.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    private DateUtil(){
    }

    public static Date getFecha(String fecha) {
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return formatoFecha.parse(fecha);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

}
