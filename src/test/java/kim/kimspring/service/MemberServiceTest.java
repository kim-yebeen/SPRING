package kim.kimspring.service;

import kim.kimspring.domain.Member;
import kim.kimspring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach(){
        //ㅁㅔ모리 멤버 리포지토리를 만들어주고
        memberRepository = new MemoryMemberRepository();
        //member service repository di, 의존관계 주입
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }

    @Test
    //테스트는 과감하게 한글로 바꿔도 됌
    public void 회원가입() throws Exception {

        //Given 어떤 상황이 주어졌는데
        Member member = new Member();
        member.setName("hello");

        //When 이거를 실행했을 때
        Long saveId = memberService.join(member);//저장된 멤버를 확인

        //Then 이렇게 동작해야 돼
        //레포지토리에있는게 맞느냐?
        Member findMember = memberRepository.findById(saveId).get();
        assertEquals(member.getName(), findMember.getName());
    }

    @Test
    //예외 처리
    public void 중복_회원_예외() throws Exception{
        //Given 똑같은 이름의  회원 설정 -> 예외가 터짐
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring"); //증복회원

        //When - try catch 문을 사용할 수도 있으나 요즘에는 예외처리를 unchecked하게 하려고함
        //assertThrows 라는 좋은 머시기가 잇음
        memberService.join(member1); //문제 없음

        IllegalStateException e = assertThrows(IllegalStateException.class,
                () -> memberService.join(member2)); //예외가 발생해야 한다.

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }

}