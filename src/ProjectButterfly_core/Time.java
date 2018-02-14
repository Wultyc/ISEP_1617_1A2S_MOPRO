
package ProjectButterfly_core;

import java.io.Serializable;


public class Time  implements Serializable{
    private int hour;
    private int minute;
    private int second;

    private final int DEFAULT = 0;

    public Time(int hour, int minute, int second) {
        if (hour < 0 || minute < 0 || second < 0) {
            this.hour = DEFAULT;
            this.minute = DEFAULT;
            this.second = DEFAULT;
        } else {
            this.hour = hour;
            this.minute = minute;
            this.second = second;
        }
    }

    /**
     * @return the hora
     */
    public int getHour() {
        return hour;
    }

    /**
     * @param hour the hora to set
     */
    public void setHour(int hour) {
        this.hour = hour;
    }

    /**
     * @return the minuto
     */
    public int getMinute() {
        return minute;
    }

    /**
     * @param minute the minuto to set
     */
    public void setMinute(int minute) {
        this.minute = minute;
    }

    /**
     * @return the segundo
     */
    public int getSecond() {
        return second;
    }

    /**
     * @param second the segundo to set
     */
    public void setSecond(int second) {
        this.second = second;
    }

    public String showTime24() {
        return hour + ":" + minute + ":" + second;
    }

    public String showTime12() {
        String indicator;
        if (hour > 12) {
            indicator = "PM";
            hour -= 12;
        } else {
            indicator = "AM";
        }
        return hour + ":" + minute + ":" + second + " " + indicator;
    }

    public void OneMoreSec() {
        second++;
        if (second == 60) {
            second = second - 60;
            minute++;

            if (minute == 60) {
                minute = minute - 60;
                hour++;
                
                if(hour == 24){
                    hour = 0;
                }
            }
        }
    }
}
