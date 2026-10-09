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
            Member member = new Member();
            member.setUsername("hello");
            member.setHomeAddress(new Address(
                    "서울",
                    "강남대로",
                    "12345"));
            member.setWorkAddress(new Address(
                    "부산",
                    "사리로",
                    "33321"));
            member.setWorkPeriod(new Period(
                    LocalDateTime.now().minusYears(3),
                    LocalDateTime.now()));

            em.persist(member);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
