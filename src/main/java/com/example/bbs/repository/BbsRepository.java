package com.example.bbs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * <pre>
 * com.example.bbs.repository
 *      BbsRepository
 *
 * Class 설명을 입력하세요.
 *
 * </pre>
 *
 * @author junypooh
 * @see
 * @since 2016-11-29 오후 5:12
 */
public interface BbsRepository extends JpaRepository<BbsEntity, Long> {

    public List<BbsEntity> findById(Long id);

    public List<BbsEntity> findByTitleIgnoreCaseContaining(String title);

    public List<BbsEntity> findByContentsIgnoreCaseContaining(String contents);
}
