
package ProjectButterfly_core;

import java.io.Serializable;
import java.util.Calendar;


public class Date implements Serializable {
    /*
     * Atributos:
     * dia: Dia do ano
     * mes: Mês do ano
     * ano: Ano
     */

    private int day;
    private int month;
    private int year;

    Calendar now = Calendar.getInstance();

    private final int DAY_DEFAULT = 1;
    private final int MONTH_DEFAULT = 1;
    private final int YEAR_DEFAULT = 1970;

    // Define os vetores a utilizar
    int[][] dayMonth = {
        {0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334, 365},
        {0, 31, 60, 91, 121, 152, 182, 213, 244, 274, 305, 335, 366}
    };
    String[] namesMonth = {null, "janeiro", "fevereiro", "março", "abril", "maio", "junho", "julho", "agosto", "setembro", "outubro", "novembro", "dezembro"};

    String[] daysWeek = {"Domingo", "Segunda-feira", "Terca-feira", "Quarta-feira", "Quinta-feira", "Sexta-feira", "Sabado"};

    int leapYear = 0;
    final int NDAYS_1_1969 = 719177; //N de dias entre 01/01/0001 ate 31/12/1969

    public Date(int day, int month, int year) {

        if (day < DAY_DEFAULT) { //Se o dia for invalido (<1) coloca como valor 1
            this.day = DAY_DEFAULT;
        } else {
            this.day = day;
        }
        if (month < MONTH_DEFAULT) { //Se o mes for invalido (<1) coloca como valor 1
            this.month = MONTH_DEFAULT;
        } else {
            this.month = month;
        }
        if (year < YEAR_DEFAULT) { //Se o ano for invalido (<1970) coloca como valor 1970
            this.year = YEAR_DEFAULT;
        } else {
            this.year = year;
        }

        leapYear(); //Verifica se é bissexto
    }

    public Date() {
        day = now.get(Calendar.DAY_OF_MONTH);
        month = now.get(Calendar.MONTH) + 1; //Janeiro é op mes 0
        year = now.get(Calendar.YEAR);
        leapYear(); //Verifica se é bissexto

    }

    /**
     * @return the dia
     */
    public int getDay() {
        return day;
    }

    /**
     * @param day the dia to set
     */
    public void setDay(int day) {
        this.day = day;
    }

    /**
     * @return the mes
     */
    public int getMonth() {
        return month;
    }

    /**
     * @param month the mes to set
     */
    public void setMonth(int month) {
        this.month = month;
    }

    /**
     * @return the ano
     */
    public int getYear() {
        return year;
    }

    /**
     * @param year the ano to set
     */
    public void setYear(int year) {
        this.year = year;
    }

    private void leapYear() {
        if (year % 4 != 0) {
            leapYear = 0;
        } else {
            leapYear = 1;
        }
    }
    
    private int countDays(){
        int i, totalDays = NDAYS_1_1969;
        for(i = 1970; i<year; i++){ //Conta dias doas anos anteriores
            totalDays += (i%4 == 0) ? 366 : 365;
        }
        
        //conta dias dos meses anteriores
        totalDays += dayMonth[leapYear][month-1];
        
        //conta os dias anteriores deste mes
        totalDays += day;
        
        return totalDays;
    }
    
    public String toString() {
        return year + "/" + month + "/" + day;
    }

    /**
     *
     * @return
     */
    public String dayWeek() {
        int dayWeek;
        dayWeek = countDays()%7;
        return daysWeek[dayWeek];
    }
    
    public boolean MajorData(int newDay, int newMonth, int newYear){
        boolean returnInfo;
        if(newYear < year){ //data inserida é maior
            returnInfo = true; 
        }else if (newYear > year){ //data inserida é menor
            returnInfo = false;
        }else{ //inconclusivo
            if(newMonth < month){ //data inserida é maior
                returnInfo = true; 
            }else if (newMonth > month){ //data inserida é menor
                returnInfo = false;
            }else{ //inconclusivo
                if(newDay < day){ //data inserida é maior
                    returnInfo = true; 
                }else if (newDay > day){ //data inserida é menor
                    returnInfo = false;
                }else{ //são iguais
                    returnInfo = false;
                }
            }
        }
        
        return returnInfo;
    }
    
    public int Diference(int newDay, int newMonth, int newYear){
        int totalDays = countDays();
        Date otherDate = new Date(year, month, day);
        int totalDays1 = otherDate.countDays();

        return Math.abs(totalDays - totalDays1);
    }
}
