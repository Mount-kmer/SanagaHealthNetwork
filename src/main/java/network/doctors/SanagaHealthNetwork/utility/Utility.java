package network.doctors.SanagaHealthNetwork.utility;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class Utility {
    public static String getSiteURL(HttpServletRequest request){
        String  url = request.getRequestURI();
        return url.replace(request.getServletPath(), "");
    }


    public String formatDate(LocalDate date) throws ParseException {
        String dateValue = date.toString();

        LocalDate localDate = LocalDate.parse(dateValue);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String weekNumber = new SimpleDateFormat("dd").format(dateFormat.parse(dateValue));
        String dayOfWeek = new SimpleDateFormat("EEEE").format(dateFormat.parse(dateValue));
        String monthName = new SimpleDateFormat("MMMM").format(dateFormat.parse(dateValue));
        String year = new SimpleDateFormat("yyyy").format(dateFormat.parse(dateValue));
        return dayOfWeek + ", " + "  " + monthName + " " + " " + weekNumber + " " + year;
    }
}
