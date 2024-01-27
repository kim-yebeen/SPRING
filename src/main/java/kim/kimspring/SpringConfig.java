package kim.kimspring;

import kim.kimspring.repository.MemberRepository;
import kim.kimspring.repository.MemoryMemberRepository;
import kim.kimspring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
    //스프링 빈에 등록하라는 얘기
    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }
    @Bean
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }
}
