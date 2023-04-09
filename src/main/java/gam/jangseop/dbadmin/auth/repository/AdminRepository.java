package gam.jangseop.dbadmin.auth.repository;

import gam.jangseop.dbadmin.auth.domain.Admin;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AdminRepository {

    @PersistenceContext
    private EntityManager em;

    public Long save(Admin admin) {
        em.persist(admin);
        return admin.getId();
    }

    public Admin findOne(Long id) {
        return em.find(Admin.class, id);
    }

    public Admin findOneByAccount(String account) {
        List<Admin> findAdmins = em.createQuery("select a from Admin a where a.account = :account", Admin.class)
                .setParameter("account", account)
                .getResultList();

        if (findAdmins.isEmpty()) return null;
        return findAdmins.get(0);
    }

    public List<Admin> findAll() {
        return em.createQuery("select a from Admin a", Admin.class)
                .getResultList();
    }
}