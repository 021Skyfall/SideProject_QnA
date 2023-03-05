package IO.SideQnA.member.service;

import IO.SideQnA.exception.BusinessLogicException;
import IO.SideQnA.exception.ExceptionCode;
import IO.SideQnA.member.entity.Member;
import IO.SideQnA.member.repository.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@AllArgsConstructor
public class MemberService {
    private final MemberRepository repository;

    public Member createMember(Member member) {
        verifyExistsEmail(member.getEmail());

        return repository.save(member);
    }

    public Member updateMember(Member member) {
        Member findMember = findVerifiedMember(member.getMemberId());

        Optional.ofNullable(member.getName())
                .ifPresent(findMember::setName);
        Optional.ofNullable(member.getPhone())
                .ifPresent(findMember::setPhone);
        Optional.ofNullable(member.getMemberStatus())
                .ifPresent(findMember::setMemberStatus);

        return repository.save(findMember);
    }

    public Member findMember(long memberId) {
        return findVerifiedMember(memberId);
    }

    public Page<Member> findMembers(int page, int size) {
        return repository.findAll(PageRequest.of(page, size,
                Sort.by("memberId").descending()));
    }

    public void deleteMember(long memberId) {
        Member findMember = findVerifiedMember(memberId);

        repository.delete(findMember);
    }

    public Member findVerifiedMember(long memberId) {
        Optional<Member> optionalMember =
                repository.findById(memberId);
        Member findMember =
                optionalMember.orElseThrow(() ->
                        new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));
        return findMember;
    }

    private void verifyExistsEmail(String email) {
        Optional<Member> member = repository.findByEmail(email);
        if (member.isPresent())
            throw new BusinessLogicException(ExceptionCode.MEMBER_EXISTS);
    }

    public void verifiedEmailAndPassword(String email, String password) {
        Optional<Member> memberEmail = repository.findByEmail(email);
        Optional<Member> memberPassword = repository.findByPassword(password);
        if (memberEmail.isEmpty() || memberPassword.isEmpty())
            throw new BusinessLogicException(ExceptionCode.CANNOT_FOUND_MEMBER);
    }
}
