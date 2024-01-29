package kim.kimspring.service;

import kim.kimspring.domain.Member;
import kim.kimspring.repository.MemberRepository;
import kim.kimspring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//테스트 만들려면 ctrl+shift +t
@Service //MemberService가 빈으로 구현되어 있지 않기 때문에 service로 연결
public class MemberService {

    //회원 서비스를 만드려면 repository가 있어야함
    private final MemberRepository memberRepository;

    //외부에서 넣어주도록 함 의존관계 주입 DI
    //@Autowired
    public MemberService(MemberRepository memberRepository){

        this.memberRepository = memberRepository;
    }
    /**
     * 회원 가입
     */
    public Long join(Member member){

        validateDuplicatMember(member); //중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }
    //같은 이름이 있는 중복 회원 x
    private void validateDuplicatMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m->{
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers(){

        return memberRepository.findAll();
    }
    public Optional<Member> findOne(Long memberId){

        return memberRepository.findById(memberId);
    }


}
