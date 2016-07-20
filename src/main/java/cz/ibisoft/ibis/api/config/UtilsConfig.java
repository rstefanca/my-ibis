package cz.ibisoft.ibis.api.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * @author Richard Stefanca
 */

@Configuration
public class UtilsConfig {

    @Bean
    @Primary
    public ObjectMapper objectMapper() {
        /*
            pro pouziti "snake notation", tj. jednotliva slova jsou oddelena podtrzitkem

            pr.:

            "nastaveni_uctu": {
                "preferovana_komunikace": "MOBILNI_APLIKACE",
                "doba_uchovani": 1,
                "pristup_na_identifikatory": "PREDEPSANE_VYDANE",
                "zpusob_pristupu": "KARTA_PACIENTA"
            }

         */
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
        return objectMapper;
    }
}
