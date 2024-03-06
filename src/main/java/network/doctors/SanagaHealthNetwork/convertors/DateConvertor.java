package network.doctors.SanagaHealthNetwork.convertors;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Component
public class DateConvertor implements Converter<String, LocalDate> {
    private static final String DATE_PATTERN = "yyyy-MM-dd";

    @Override
    public LocalDate convert(String source) {
        if (source == null || source.isEmpty())
            return null;

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_PATTERN);
            return LocalDate.parse(source, formatter);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid date format. Expected format: " + DATE_PATTERN, e);
        }
    }
}
