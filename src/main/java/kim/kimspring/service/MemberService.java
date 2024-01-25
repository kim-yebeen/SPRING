package kim.kimspring.service;

import kim.kimspring.domain.Member;
import kim.kimspring.repository.MemberRepository;
import kim.kimspring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

//테스트 만들려면 ctrl+shift +t
public class MemberService {

    private final MemberRepository memberRepository = new MemoryMemberRepository();

    /**
     * 회원 가입
     */
    public Long join(Member member){

        validateDuplicatMember(member); //중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

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
