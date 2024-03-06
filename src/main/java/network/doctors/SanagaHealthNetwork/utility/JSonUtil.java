package network.doctors.SanagaHealthNetwork.utility;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class JSonUtil {

    private static final ObjectMapper objectMapper = createObjectMapper();
    private static Logger logger;

    private static ObjectMapper createObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        return  mapper;
    }


    public String convertToJson(Object data) {
        try {
            return objectMapper.writeValueAsString(data);
        }catch (IOException e) {
            logger.log(Level.SEVERE,"Error converting data to JSON", e);
            return "{}";

        }
    }
}
