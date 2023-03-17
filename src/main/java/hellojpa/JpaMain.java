package hellojpa;

import org.hibernate.Hibernate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        final EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        final EntityManager em = emf.createEntityManager();

        final EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

//            Child child1 = new Child();
//            Child child2 = new Child();
//
//            Parent parent = new Parent();
//            parent.addChild(child1);
//            parent.addChild(child2);
//
//            em.persist(parent);
//
//            em.flush();
//            em.clear();
//
//            Parent parent1 = em.find(Parent.class, parent.getId());
//            em.remove(parent1);

            final Member2 member2 = new Member2();
            member2.setUsername("member1");
            member2.setHomeAddress(new Address("2", "3", "4"));

            member2.getFavoriteFoods().add("치킨");
            member2.getFavoriteFoods().add("족발");

            member2.getAddressHistory().add(new AddressEntity("ol1", "sdd", "22"));
            member2.getAddressHistory().add(new AddressEntity("ol2", "sdd", "33"));

            em.persist(member2);

            em.flush();
            em.clear();

            System.out.println("==================START===================");
            final Member2 findMember = em.find(Member2.class, member2.getId());
//            final Address a = findMember.getHomeAddress();
//            findMember.setHomeAddress(new Address("newCity", a.getStreet(), a.getZipcode()));
//
//            findMember.getFavoriteFoods().remove("치킨");
//            findMember.getFavoriteFoods().add("한식");

            findMember.getAddressHistory().remove(new AddressEntity("old1", "street", "10000"));
            findMember.getAddressHistory().add(new AddressEntity("newCity1", "street", "10000"));

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
