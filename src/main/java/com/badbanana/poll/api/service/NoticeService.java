package com.badbanana.poll.api.service;

import com.badbanana.poll.api.model.License;
import com.badbanana.poll.api.model.Notice;
import com.badbanana.poll.api.model.User;

import java.util.List;

public interface NoticeService {

    Notice addNotice(Notice notice);
    Notice findNoticeById(Long id);
    List<Notice> findNoticesByStatus(String status);
    List<Notice> findNoticeByUer(User user);
    void deleteNoticeByNoticeID(Long noticeId);
    void deleteNotce(Notice notice);
}
