package de.viada.reboot.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

public class ReisekostenRepresentation {

    private Integer id;
    private LocalDate zeitraumAb;
    private LocalDate zeitraumBis;
    private LocalDate einreichDatum;
    private BigDecimal erwartetBrutto;
    private Zuschlag zuschlagart;
    private BigDecimal erwartetZuschlag;

    public ReisekostenRepresentation(Integer id, LocalDate zeitraumAb, LocalDate zeitraumBis, LocalDate einreichDatum,
                                     BigDecimal erwartetBrutto, Zuschlag zuschlagart, BigDecimal erwartetZuschlag) {
        this.id = id;
        this.zeitraumAb = zeitraumAb;
        this.zeitraumBis = zeitraumBis;
        this.einreichDatum = einreichDatum;
        this.erwartetBrutto = erwartetBrutto;
        this.zuschlagart = Optional.ofNullable(zuschlagart).orElse(Zuschlag.KEIN);
        this.erwartetZuschlag = erwartetZuschlag;
    }

    public Integer getId() {
        return Optional.ofNullable(id).orElse(0);
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getZeitraumAb() {
        return zeitraumAb;
    }

    public void setZeitraumAb(LocalDate zeitraumAb) {
        this.zeitraumAb = zeitraumAb;
    }

    public LocalDate getZeitraumBis() {
        return zeitraumBis;
    }

    public void setZeitraumBis(LocalDate zeitraumBis) {
        this.zeitraumBis = zeitraumBis;
    }

    public LocalDate getEinreichDatum() {
        return einreichDatum;
    }

    public void setEinreichDatum(LocalDate einreichDatum) {
        this.einreichDatum = einreichDatum;
    }

    public BigDecimal getErwartetBrutto() {
        return erwartetBrutto;
    }

    public void setErwartetBrutto(BigDecimal erwartetBrutto) {
        this.erwartetBrutto = erwartetBrutto;
    }

    public Zuschlag getZuschlagart() {
        return zuschlagart;
    }

    public void setZuschlagart(Zuschlag zuschlagart) {
        this.zuschlagart = zuschlagart;
    }

    public BigDecimal getErwartetZuschlag() {
        return erwartetZuschlag;
    }

    public void setErwartetZuschlag(BigDecimal erwartetZuschlag) {
        this.erwartetZuschlag = erwartetZuschlag;
    }

    public ReisekostenRepresentation withZuschlag(BigDecimal zuschlag) {
        this.setErwartetZuschlag(zuschlag);
        return this;
    }
}
