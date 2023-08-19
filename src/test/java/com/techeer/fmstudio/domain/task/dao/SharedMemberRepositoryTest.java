package com.techeer.fmstudio.domain.task.dao;

import static com.techeer.fmstudio.domain.member.domain.MemberInterest.BACKEND;
import static org.assertj.core.api.Assertions.assertThat;

import com.techeer.fmstudio.domain.task.dto.mapper.SharedMemberMapper;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import com.techeer.fmstudio.domain.member.dao.MemberRepository;
import com.techeer.fmstudio.domain.member.domain.MemberEntity;
import com.techeer.fmstudio.domain.member.domain.MemberInterest;
import com.techeer.fmstudio.domain.member.domain.MemberStatus;
import com.techeer.fmstudio.domain.task.domain.SharedMember;
import com.techeer.fmstudio.domain.task.domain.Task;
import java.time.LocalDateTime;
import java.util.ArrayList;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;


@Slf4j
@AutoConfigureTestDatabase(replace = Replace.NONE)
@DataJpaTest
class SharedMemberRepositoryTest {

  @Autowired
  SharedMemberRepository sharedMemberRepository;

  @Test
  void CrossJoin_쿼리문() {
    long startTime = System.currentTimeMillis();
    sharedMemberRepository.findSharedMemberByMemberEntityNickName1("member-986212");

    long stopTime = System.currentTimeMillis();

    long elapsedTime = stopTime - startTime;
    log.info("실행 시간 : " + elapsedTime);
  }

  @Test
  void Join_쿼리문() {
    long startTime = System.currentTimeMillis();

    sharedMemberRepository.findSharedMemberByMemberEntityNickName("member-786211");

    long stopTime = System.currentTimeMillis();

    long elapsedTime = stopTime - startTime;
    log.info("실행 시간 : " + elapsedTime);
  }



}