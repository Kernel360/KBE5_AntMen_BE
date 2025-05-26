package com.antmen.antwork.common.infra.repository;

import com.antmen.antwork.common.domain.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
    public List<Board> findAllByBoardType(String boardType);
}
