package hellojpa;

import jakarta.persistence.*;
import org.hibernate.Hibernate;

import java.time.LocalDateTime;
import java.util.List;

public class JpaMain {

    static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Child child1 = new Child();
            child1.setName("child1");

            Child child2 = new Child();
            child1.setName("child2");

            Parent parent = new Parent();
            parent.setName("parentA");
            parent.addChild(child1);
            parent.addChild(child2);

            // em.persist(child1);
            // em.persist(child2);
            em.persist(parent);

            em.flush();
            em.clear();

            Parent findParent = em.find(Parent.class, parent.getId());
            System.out.println("findParent.getName() = " + findParent.getName());
            System.out.println("findParent.getChildList().size() = " + findParent.getChildList().size());

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
