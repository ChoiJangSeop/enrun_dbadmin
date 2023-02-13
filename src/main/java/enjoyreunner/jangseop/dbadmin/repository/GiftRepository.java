package enjoyreunner.jangseop.dbadmin.repository;

import enjoyreunner.jangseop.dbadmin.domain.Gift;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class GiftRepository {

    @PersistenceContext
    EntityManager em;

    public Long save(Gift gift) {
        em.persist(gift);
        return gift.getId();
    }

    public Gift findOne(Long id) {
        return em.find(Gift.class, id);
    }
}
