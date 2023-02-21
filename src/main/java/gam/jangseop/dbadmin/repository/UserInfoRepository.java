package gam.jangseop.dbadmin.repository;

import gam.jangseop.dbadmin.domain.UserInfo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserInfoRepository {

    @PersistenceContext
    EntityManager em;

    public Long save(UserInfo userInfo) {
        em.persist(userInfo);
        return userInfo.getId();
    }

    public UserInfo findOne(Long id) {
        return em.find(UserInfo.class, id);
    }

    public UserInfo findOneByNickname(String nickName) {
        List<UserInfo> resultList = em.createQuery("SELECT u FROM UserInfo u WHERE u.nickname = :nickname", UserInfo.class)
                .setParameter("nickname", nickName)
                .getResultList();

        if (resultList.size() == 0) return null;
        return resultList.get(0);
    }

    public List<UserInfo> findAll() {
        return em.createQuery("SELECT u FROM UserInfo u", UserInfo.class)
                .getResultList();
    }
}
