package IO.SideQnA.member.controller;

import IO.SideQnA.member.dto.MemberPatchDto;
import IO.SideQnA.member.dto.MemberPostDto;
import IO.SideQnA.member.entity.Member;
import IO.SideQnA.member.mapper.MemberMapper;
import IO.SideQnA.member.service.MemberService;
import IO.SideQnA.dto.MultiResponseDto;
import IO.SideQnA.dto.SingleResponseDto;
import IO.SideQnA.member.entity.Stamp;
import IO.SideQnA.utils.UriCreator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/members")
@AllArgsConstructor
@Validated
@Slf4j
public class MemberController {
    private final static String MEMBER_DEFAULT_URL = "/members";
    private final MemberService service;
    private final MemberMapper mapper;

    @PostMapping
    public ResponseEntity postMember(@Validated @RequestBody MemberPostDto memberPostDto) {
        Member member = mapper.memberPostDtoToMember(memberPostDto);

        member.setStamp(new Stamp());

        Member createdMember = service.createMember(member);

        URI location = UriCreator.createUri(MEMBER_DEFAULT_URL, createdMember.getMemberId());

        return ResponseEntity.created(location).build();
    }

    @PatchMapping("/{member-id}")
    public ResponseEntity patchMember(@PathVariable("member-id") @Positive long memberId,
                                      @Validated @RequestBody MemberPatchDto memberPatchDto) {
        memberPatchDto.setMemberId(memberId);
        Member member = service.updateMember(mapper.memberPatchDtoToMember(memberPatchDto));

        return new ResponseEntity<>(new SingleResponseDto<>(mapper.memberToMemberResponseDto(member)), HttpStatus.OK);
    }

    @GetMapping("/{member-id}")
    public ResponseEntity getMember(@PathVariable("member-id") @Positive long memberId) {
        Member member = service.findMember(memberId);

        return new ResponseEntity<>(new SingleResponseDto<>(mapper.memberToMemberResponseDto(member)),HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getMembers(@Positive @RequestParam int page,
                                     @Positive @RequestParam int size) {
        Page<Member> memberPage = service.findMembers(page -1, size);
        List<Member> members = memberPage.getContent();

        return new ResponseEntity<>(new MultiResponseDto<>(mapper.membersToMemberResponseDtos(members),
                memberPage), HttpStatus.OK);
    }

    @DeleteMapping("/{member-id}")
    public ResponseEntity deleteMember(@PathVariable("member-id") @Positive long memberId) {
        service.deleteMember(memberId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
