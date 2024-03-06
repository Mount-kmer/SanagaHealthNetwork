package network.doctors.SanagaHealthNetwork.convertors;

import java.time.LocalTime;


public class TimeFormatter {

    public static LocalTime formatTime(String localTime) {
        String[] timeParts = localTime.split(":");
        int hours = Integer.parseInt(timeParts[0]);
        int minutes = Integer.parseInt(timeParts[1]);
        return LocalTime.of(hours, minutes);
    }
}
