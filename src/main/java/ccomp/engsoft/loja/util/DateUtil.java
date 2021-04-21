package ccomp.engsoft.loja.util;

import java.time.LocalDate;

public class DateUtil {


    public static LocalDate menorDasDatas(LocalDate a, LocalDate b){
        if(a == null || b == null){
            return dataNaoNula(a, b);
        }

        if(a.compareTo(b) <= 0){
            return a;
        }
        return b;
    }

    public static LocalDate maiorDasDatas(LocalDate a, LocalDate b){
        if(a == null || b == null){
            return dataNaoNula(a, b);
        }

        if(a.compareTo(b) > 0){
            return a;
        }
        return b;
    }

    public static LocalDate dataNaoNula(LocalDate a, LocalDate b){
        if(a == null && b == null){
            return null;
        }
        if(a == null){
            return b;
        }

        return a;

    }

}
