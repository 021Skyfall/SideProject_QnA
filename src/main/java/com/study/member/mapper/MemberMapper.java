package com.study.member.mapper;

import com.study.member.dto.MemberDto;
import com.study.member.entity.Member;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MemberMapper {
    Member memberPostDtoToMember(MemberDto.Post requestBody);
    Member memberPatchDtoToMember(MemberDto.Patch requestBody);

    @Mapping(source = "stamp.stampCount", target = "stampCount")
    @Mapping(source = "memberStatus.status", target = "memberStatus")  // enum 전달이 좋은가, human readable한 값 전달이 좋은가?
    MemberDto.Response memberToMemberResponseDto(Member member);
    List<MemberDto.Response> membersToMemberResponseDtos(List<Member> members);
}
