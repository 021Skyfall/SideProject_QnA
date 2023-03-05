package IO.SideQnA.member.mapper;

import IO.SideQnA.member.dto.MemberPatchDto;
import IO.SideQnA.member.dto.MemberPostDto;
import IO.SideQnA.member.dto.MemberResponseDto;
import IO.SideQnA.member.entity.*;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MemberMapper {
    Member memberPostDtoToMember(MemberPostDto memberPostDto);
    Member memberPatchDtoToMember(MemberPatchDto memberPatchDto);

    MemberResponseDto memberToMemberResponseDto(Member member);
    List<MemberResponseDto> membersToMemberResponseDtos(List<Member> members);
}
