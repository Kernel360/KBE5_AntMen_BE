package com.antmen.antwork.common.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;

@Entity
@Table(name = "board")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User boardUser;

    @Column(nullable = false)
    private String boardType;

    @Column(nullable = false)
    private String boardTitle;

    @Column(nullable = false)
    private String boardContent;

    @Column(nullable = false, updatable = false)
    private LocalDateTime boardCreatedAt;

    @Column(nullable = false)
    private LocalDateTime boardModifiedAt;

    @Column(nullable = false)
    @ColumnDefault("false")
    private Boolean isPinned;

    @Column(nullable = false)
    @ColumnDefault("false")
    private Boolean boardIsDeleted;
}
