package kim.kimspring;


import kim.kimspring.repository.*;
//import kim.kimspring.repository.JdbcTemplateMemberRepository;
import kim.kimspring.service.MemberService;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import jakarta.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private final DataSource dataSource;
    private final EntityManager em;

    public SpringConfig(DataSource dataSource, EntityManager em) {
        this.dataSource = dataSource;
        this.em = em;
    }

    /*
    private DataSource dataSource;

   // @Autowired
    public SpringConfig(DataSource dataSource){
        this.dataSource = dataSource;
    }
    //스프링 빈에 등록하라는 얘기
    */
    @Bean
    public MemberService memberService(){

        return new MemberService(memberRepository());
    }
    @Bean
    public MemberRepository memberRepository(){
        return new JpaMemerRepository(em);
        //return new JdbcTemplateMemberRepository(dataSource);
       // return new JdbcMemberRepository(dataSource);
        //return new MemoryMemberRepository();
    }

}
