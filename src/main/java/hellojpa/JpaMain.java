package hellojpa;

import jakarta.persistence.*;

import java.util.List;

public class JpaMain {

    static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Member member = new Member();
            member.setUsername("member1");
            member.setHomeAddress(new Address("서울", "강남대로", "12333"));

            member.getFavoriteFoods().add("치킨");
            member.getFavoriteFoods().add("족발");
            member.getFavoriteFoods().add("피자");

            member.getAddressHistory().add(new AddressEntity("old1", "street1", "zc-1"));
            member.getAddressHistory().add(new AddressEntity("old2", "street2", "zc-2"));

            em.persist(member);

            em.flush();
            em.clear();

            System.out.println("================ START ================");
            Member findMember = em.find(Member.class, member.getId());

            // 1. 임베디드 타입 수정하기
            // findMember.setHomeAddress(new Address("부산", "사리로", "11111"));

            // 2. String 값 타입 컬렉션 수정하기
            // // "치킨" => "한식" 변경하기
            // findMember.getFavoriteFoods().remove("치킨");
            // findMember.getFavoriteFoods().add("한식");

            // 3. @Embedded 값 타입 컬렉션 수정하기
            // // "old1" => "new1" 변경하기
            // findMember.getAddressHistory().remove(new Address("old1", "street1", "zc-1"));
            // findMember.getAddressHistory().add(new Address("new1", "street1", "zc-1"));

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
