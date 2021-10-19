package de.viada.reboot.data;

import de.viada.reboot.model.ReisekostenRepresentation;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class ReisekostenRepository {

    @PersistenceContext
    EntityManager em;

    /**
     * Persists given Reisekosten after converting domain model to Entity.
     * @param re Reisekosten
     */
    public void addReisekosten(ReisekostenEntity re) {
        em.persist(re);
    }

    /**
     * Updates values of given Reisekosten in database after converting domain model to Entity.
     * @param re Reisekosten
     */
    public void updateReisekosten(ReisekostenRepresentation re) {
        em.merge(re);
    }

    /**
     * Finds all persisted ReisekostenEntities and returns them as Reisekosten domain models.
     * @return List of Reisekosten
     */
    public List<ReisekostenEntity> findAllReisekosten() {
        return em.createQuery("SELECT re FROM ReisekostenEntity re", ReisekostenEntity.class)
                .getResultList();
    }
}
