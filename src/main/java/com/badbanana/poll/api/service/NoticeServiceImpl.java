package com.badbanana.poll.api.service;

import com.badbanana.poll.api.model.Notice;
import com.badbanana.poll.api.model.User;
import com.badbanana.poll.api.repository.NoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeServiceImpl implements NoticeService {

    private final NoticeRepository noticeRepository;

    @Autowired
    public NoticeServiceImpl(NoticeRepository noticeRepository) {
        this.noticeRepository = noticeRepository;
    }
    @Override
    public  Notice addNotice(Notice notice){
        return noticeRepository.save(notice);
    }
    @Override
    public Notice findNoticeById(Long id) {
        return noticeRepository.findNoticeByNoticeId(id);
    }

    @Override
    public List<Notice> findNoticeByUer(User user) {
        return noticeRepository.findNoticesByUser(user);
    }


    public void deleteNoticeByNoticeID(Long noticeId) {
         noticeRepository.deleteByNoticeId(noticeId);
    }

    @Override
    public void deleteNotce(Notice notice) {
         noticeRepository.delete(notice);
    }

    @Override
    public List<Notice> findNoticesByStatus(String status) {
        return noticeRepository.findAllByStatus(status);
    }
}
