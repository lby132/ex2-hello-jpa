package hellojpa;

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
            final Team team = new Team();
            team.setName("teamA");
            em.persist(team);

            final Member2 member = new Member2();
            member.setUsername("member1");
            member.changeTeam(team);
            em.persist(member);

            //team.addMember(member);

            em.flush();
            em.clear();

            final Member2 findMember = em.find(Member2.class, member.getId());
            final List<Member2> members = findMember.getTeam().getMembers();

            for (Member2 m : members) {
                System.out.println("m.getUsername() = " + m.getUsername());
            }

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
