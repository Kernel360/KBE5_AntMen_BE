package com.antmen.antwork.common.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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

//    @CreationTimestamp
    @Column(name = "board_created_at", nullable = false, updatable = false)
    private LocalDateTime boardCreatedAt;

//    @UpdateTimestamp
    @Column(name = "board_modified_at", nullable = false)
    private LocalDateTime boardModifiedAt;

    @Column(nullable = false)
    @ColumnDefault("false")
    private Boolean isPinned;

    @Column(nullable = false)
    @ColumnDefault("false")
    private Boolean boardIsDeleted;

    @PrePersist
    public void prePersist() {
        this.boardCreatedAt = (this.boardCreatedAt == null) ? LocalDateTime.now() : this.boardCreatedAt;
        this.boardModifiedAt = (this.boardModifiedAt == null) ? LocalDateTime.now() : this.boardModifiedAt;
        this.isPinned = (this.isPinned == null) ? false : this.isPinned;
        this.boardIsDeleted = (this.boardIsDeleted == null) ? false : this.boardIsDeleted;
    }
}