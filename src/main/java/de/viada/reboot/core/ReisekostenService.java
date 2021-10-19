package de.viada.reboot.core;

import de.viada.reboot.model.ReisekostenRepresentation;
import de.viada.reboot.model.Zuschlag;
import de.viada.reboot.data.ReisekostenEntity;
import de.viada.reboot.data.ReisekostenRepository;
import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

public class ReisekostenService {

    private BigDecimal zuschlagNah;
    private BigDecimal zuschlagFern;

    private ReisekostenRepository reisekostenRepository;

    public ReisekostenService(BigDecimal zuschlagNah, BigDecimal zuschlagFern, ReisekostenRepository reisekostenRepository) {
        this.zuschlagNah = zuschlagNah;
        this.zuschlagFern = zuschlagFern;
        this.reisekostenRepository = reisekostenRepository;
    }

    public void addReisekosten(ReisekostenRepresentation rk) {
        reisekostenRepository.addReisekosten(
                convertReisekostenToReisekostenEntity(rk)
        );
    }

    public List<ReisekostenRepresentation> getAllReisekosten() {
        return reisekostenRepository.findAllReisekosten()
                .stream()
                .map(this::convertReisekostenEntityToReisekosten)
                .collect(Collectors.toList());
    }

    /**
     * Convert Reisekosten Representation into Reisekosten Entity.
     *
     * @param rk Reisekosten
     * @return ReisekostenEntity
     */
    private ReisekostenEntity convertReisekostenToReisekostenEntity(ReisekostenRepresentation rk) {
        return new ReisekostenEntity(
                rk.getId(),
                rk.getZeitraumAb(),
                rk.getZeitraumBis(),
                rk.getEinreichDatum(),
                rk.getErwartetBrutto(),
                rk.getZuschlagart()
        );
    }

    /**
     * Convert ReisekostenEntity into Representation of Reisekosten.
     *
     * @param re ReisekostenEntity
     * @return Reisekosten Representation
     */
    private ReisekostenRepresentation convertReisekostenEntityToReisekosten(ReisekostenEntity re) {
        return new ReisekostenRepresentation(
                re.getId(),
                re.getZeitraumAb(),
                re.getZeitraumBis(),
                re.getEinreichDatum(),
                re.getErwartetBrutto(),
                re.getZuschlagart(),
                getZuschlagForZuschlagsart(re.getZuschlagart())
                        .multiply(new BigDecimal(ChronoUnit.DAYS.between(re.getZeitraumAb(), re.getZeitraumBis()) + 1))
        );
    }

    private BigDecimal getZuschlagForZuschlagsart(Zuschlag zuschlag) {
        BigDecimal effectiveZuschlag = new BigDecimal("0.00");

        switch (zuschlag) {
            case FERN:
                effectiveZuschlag = zuschlagFern;
                break;
            case NAH:
                effectiveZuschlag = zuschlagNah;
                break;
            case KEIN:
            default:
                break;
        }

        return effectiveZuschlag;
    }

}
