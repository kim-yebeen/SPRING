package kim.kimspring.repository;

import kim.kimspring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach()
    {
        repository.clearStore();
    }

    @Test
    public void save()
    {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();
          // System.out.println("(result==member) = " + (result==member));
        // Assertions.assertEquals(member,null);
        assertThat(result).isEqualTo(member);

    }
    @Test
    public void findByName()
    {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        //when findbyname이 잘 동작하는지 확인, spring2로 넣으면 오류발생
        Member result = repository.findByName("spring1").get();

        //then
        assertThat(result).isEqualTo(member1);
    }

    //모든 테스트는 순서가 적힌대로가 아님 findAll이 제일 먼저 실행될 경우 오류 발생
    // test하나 끝날 때마다 clear를 해야함.
    @Test
    public void findAll()
    {
        //given
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        //findall이니까 두개 정도는 넣어줘야지
        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        //when
        List<Member> result = repository.findAll();

        //then 사이즈는 2개여야 한다. 3개이면 에러 발생
        assertThat(result.size()).isEqualTo(2);
    }

}
