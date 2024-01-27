package kim.kimspring.Controller;

import kim.kimspring.repository.MemberRepository;
import kim.kimspring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

//spring 컨테이너가 스프링 창에 뜰 때, 멤버 컨트롤러 생성을 해서 넣어논다.

@Controller
public class MemberController {

    // new 키워드를 통해 사용할 수 없음
    //private final MemberService memberService = new MemberService();

    private final MemberService memberService;
    //spring 컨테이너에 등록하고 쓰면 된다.
    //생성자 단축기 alt + insert

    @Autowired //spring컨테이너에서 가져온다.
    //오류발생 MemberService는 그냥 자바 클래스이므로 -> MemberService.java에 @Service를 추가
    //MemoryMemberRepository에도 @Repository 해주기
    //MemberService의 생성자에 @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
