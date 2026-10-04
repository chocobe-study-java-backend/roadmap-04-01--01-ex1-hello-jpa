package hellojpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.List;

public class JpaMain {

    static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            // // Member 생성하기
            // Member member = new Member();
            // member.setId(2L);
            // member.setName("HelloB");
            // em.persist(member);
            //
            // tx.commit();

            // ---

            // // Member 조회하기
            // Member findMember = em.find(Member.class, 1L);
            // System.out.println("findMember = " + findMember);

            // ---

            // // Member 삭제하기
            // Member findMember = em.find(Member.class, 2L);
            // em.remove(findMember);
            //
            // tx.commit();

            // ---

            // // Member 수정하기
            // Member findMember = em.find(Member.class, 1L);
            // findMember.setName("HelloJPA");
            //
            // tx.commit();

            // ---

            // Member 목록 조회
            List<Member> result = em.createQuery("""
                    select m from Member as m
                    """, Member.class)
                    .getResultList();

            for (Member member : result) {
                System.out.println("member.name = " + member.getName());
            }
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        // tx.commit();
        // em.close();

        emf.close();
    }
}
