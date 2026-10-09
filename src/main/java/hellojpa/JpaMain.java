package hellojpa;

import jakarta.persistence.*;

import java.time.LocalDateTime;

public class JpaMain {

    static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Address address1 = new Address("서울", "강남대로", "12345");

            Member member1 = new Member();
            member1.setUsername("member1");
            member1.setHomeAddress(address1);
            em.persist(member1);

            member1.setHomeAddress(new Address("판교", address1.getStreet(), address1.getZipcode()));

            Address address2 = new Address(address1.getCity(), address1.getStreet(), address1.getZipcode());

            Member member2 = new Member();
            member2.setUsername("member2");
            member2.setHomeAddress(address2);
            em.persist(member2);

            // member1.getHomeAddress().setCity("부산");

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
