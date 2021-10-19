package de.viada.reboot.data;


import de.viada.reboot.model.Zuschlag;
import java.math.BigDecimal;
import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ReisekostenEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDate zeitraumAb;
    private LocalDate zeitraumBis;
    private LocalDate einreichDatum;
    private BigDecimal erwartetBrutto;
    private Zuschlag zuschlagart;

    public ReisekostenEntity() {
        // for JPA
    }

    public ReisekostenEntity(int id, LocalDate zeitraumAb, LocalDate zeitraumBis, LocalDate einreichDatum,
                             BigDecimal erwartetBrutto, Zuschlag zuschlagart) {
        this.id = id;
        this.zeitraumAb = zeitraumAb;
        this.zeitraumBis = zeitraumBis;
        this.einreichDatum = einreichDatum;
        this.erwartetBrutto = erwartetBrutto;
        this.zuschlagart = zuschlagart;
    }

    public int getId() {
        return id;
    }

    public LocalDate getZeitraumAb() {
        return zeitraumAb;
    }

    public LocalDate getZeitraumBis() {
        return zeitraumBis;
    }

    public LocalDate getEinreichDatum() {
        return einreichDatum;
    }

    public BigDecimal getErwartetBrutto() {
        return erwartetBrutto;
    }

    public Zuschlag getZuschlagart() {
        return zuschlagart;
    }
}

