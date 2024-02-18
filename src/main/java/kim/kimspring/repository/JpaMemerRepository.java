package kim.kimspring.repository;

import jakarta.persistence.EntityManager;
import kim.kimspring.domain.Member;

import java.util.List;
import java.util.Optional;

public class JpaMemerRepository implements MemberRepository{

    //jpa를 쓰려면 entitymanager를 주입받아야 한다.
    private final EntityManager em;

    public JpaMemerRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
       Member member = em.find(Member.class, id);
       return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name= :name", Member.class)
                .setParameter("name", name)
                .getResultList();

        return result.stream().findAny();

    }

    @Override
    public List<Member> findAll() {

        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }
}
