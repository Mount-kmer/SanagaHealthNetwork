package network.doctors.SanagaHealthNetwork.convertors;

import network.doctors.SanagaHealthNetwork.model.Gender;
import org.springframework.core.convert.converter.Converter;

import java.util.Locale;

public class StringToEnumConvertor implements Converter<String, Gender> {
    @Override
    public Gender convert(String s) {
        if(s.toUpperCase(Locale.forLanguageTag("Male")).equals("MALE")){
            return Gender.MALE;
        }
        else if(s.toUpperCase(Locale.forLanguageTag("Female")).equals("FEMALE")){
            return Gender.FEMALE;
        }
        else{
            return Gender.OTHER;
        }
    }
}
