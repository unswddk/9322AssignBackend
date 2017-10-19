package com.badbanana.poll.api.repository;


import com.badbanana.poll.api.model.License;
import com.badbanana.poll.api.model.Notice;
import com.badbanana.poll.api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface NoticeRepository extends JpaRepository<Notice, Long> {

    List<Notice> findAllByStatus(String status);
    List<Notice> findAll();
    void deleteByNoticeId(Long noticeId);
    @Override
    void delete(Notice notice);
    Notice findNoticeByNoticeId(Long noticeId);
    List<Notice> findNoticesByUser(User user);
}
