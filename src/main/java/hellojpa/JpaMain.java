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
            Team team1 = new Team();
            team1.setName("teamA");
            em.persist(team1);

            Team team2 = new Team();
            team2.setName("teamB");
            em.persist(team2);

            Member member1 = new Member();
            member1.setUsername("member1");
            member1.changeTeam(team1);
            em.persist(member1);

            Member member2 = new Member();
            member2.setUsername("member2");
            member2.changeTeam(team2);
            em.persist(member2);

            em.flush();
            em.clear();

            // Member findMember = em.find(Member.class, member.getId());
            // System.out.println("m = " + findMember.getTeam().getClass());
            //
            // System.out.println("===============");
            // findMember.getTeam().getName();
            // System.out.println("===============");

            /* NOTE:
            SQL: SELECT * FROM Member;

            Member#team의 FetchType을 EAGER로 설정했으므로, Team 조회 SQL도 추가 실행한다.
            SQL: SELECT * FROM Team WHERE TEAM_ID = ?;
             */
            List<Member> members = em.createQuery("""
                            select m from Member m
                            """, Member.class)
                    .getResultList();

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
