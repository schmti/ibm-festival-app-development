package de.viada.reboot.rest;


import de.viada.reboot.model.ReisekostenRepresentation;
import de.viada.reboot.core.ReisekostenService;
import de.viada.reboot.model.Zuschlag;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import io.micrometer.core.annotation.Timed;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reisekosten")
@Timed
public class ReisekostenController {

    private final ReisekostenService rkService;
    private final Counter counter;

    public ReisekostenController(ReisekostenService rkService, MeterRegistry mr) {

        this.rkService = rkService;
        counter = mr.counter("testGauge");
        counter.increment();
    }

    @GetMapping(value = "/demo", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ReisekostenRepresentation> getDemoReisekosten() {
        ReisekostenRepresentation demo = new ReisekostenRepresentation(
                35,
                LocalDate.parse("2018-07-20"),
                LocalDate.parse("2018-07-21"),
                LocalDate.now(),
                new BigDecimal("265.43"),
                Zuschlag.KEIN,
                new BigDecimal("0.00")
        );
        return ResponseEntity.ok(demo);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ReisekostenRepresentation>> getAllReisekosten() {
        List<ReisekostenRepresentation> allRk =
                this.rkService.getAllReisekosten();

        return ResponseEntity.ok(allRk);
    }

    @Transactional
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity postReisekosten(@RequestBody ReisekostenRepresentation reisekostenRepresentation) {
        rkService.addReisekosten(reisekostenRepresentation);
        return ResponseEntity.ok().build();
    }
}
