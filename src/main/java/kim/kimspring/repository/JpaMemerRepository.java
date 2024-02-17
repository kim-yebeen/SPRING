package kim.kimspring.repository;

import jakarta.persistence.EntityManager;
import kim.kimspring.domain.Member;

import java.util.List;
import java.util.Optional;

public class JpaMemerRepository implements MemberRepository{

    private final EntityManager em;

    public JpaMemberRepository(EntityManager em){
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        return null;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<Member> findByName(String name) {
        return Optional.empty();
    }

    @Override
    public List<Member> findAll() {
        return null;
    }
}
