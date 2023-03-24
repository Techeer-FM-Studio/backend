package com.techeer.fmstudio.domain.task.entity;

import com.techeer.fmstudio.global.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "shared_member")
public class SharedMember extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shared_member_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id", nullable = false)
    private Task task;

    // TODO : Member 엔티티가 구현되면 변경
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "test_member_id", nullable = false)
    private TestMember testMember;
}
