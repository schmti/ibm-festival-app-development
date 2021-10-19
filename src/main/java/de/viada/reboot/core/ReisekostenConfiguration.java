package de.viada.reboot.core;

import de.viada.reboot.data.ReisekostenRepository;
import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ReisekostenConfiguration {

    @Bean
    public ReisekostenRepository createReisekostenRepository() {
        return new ReisekostenRepository();
    }

    @Bean
    public ReisekostenService createReisekostenService(@Value("${projektzuschlag.nah}") BigDecimal projektzuschlagNah,
                                                       @Value("${projektzuschlag.fern}") BigDecimal projektzuschlagFern,
                                                       ReisekostenRepository reisekostenRepository) {

        return new ReisekostenService(projektzuschlagNah, projektzuschlagFern, reisekostenRepository);
    }

}
