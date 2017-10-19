package com.badbanana.poll.api.controllers;
import com.badbanana.poll.api.model.License;
import com.badbanana.poll.api.model.Notice;
import com.badbanana.poll.api.model.User;
import com.badbanana.poll.api.service.LicenseService;
import com.badbanana.poll.api.service.MailService;
import com.badbanana.poll.api.service.NoticeService;
import com.badbanana.poll.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/notice")
public class NoticeController {
    private final NoticeService noticeService;
    private final UserService userService;
    private final MailService mailService;
    private final LicenseService licenseService;

    @Autowired
    public NoticeController(NoticeService noticeService, UserService userService,
                            MailService mailService, LicenseService licenseService) {
        this.noticeService = noticeService;
        this.userService = userService;
        this.mailService = mailService;
        this.licenseService = licenseService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> addNewNotice(@RequestBody Notice notice) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findUserByUsername(username).get();
        System.out.println(notice.toString());
        License license = licenseService.findLicenseByNumber(notice.getLicenseNumber());
        license.setNoticed(true);
        licenseService.addLicense(license);
        notice.setLic(license);
        notice.setUser(user);
        notice.setEmail("comp9322simpleservice@gmail.com");
        notice.setAmount("200");
        noticeService.addNotice(notice);
        System.out.println(notice + "========================");
        mailService.sendMail(notice.getEmail(), "Renew Your Driver License",
                "http://localhost:4200/driver/"+notice.getToken()+"/"+notice.getNoticeId());
        return new ResponseEntity<>(notice, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/total",method = RequestMethod.POST)
    public ResponseEntity<?> getNoticesByUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findUserByUsername(username).get();
        List<Notice> notices;
        notices = noticeService.findNoticeByUer(user);
        System.out.println(notices);
        return new ResponseEntity<>(notices, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getNoticeById(@PathVariable Long id) {
        Notice notice = noticeService.findNoticeById(id);
        return new ResponseEntity<>(notice, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<?> updateNotice(@RequestBody Notice notice) {
        Notice notice1 = noticeService.findNoticeById(notice.getNoticeId());
        System.out.println(notice);
        notice1.setAddress(notice.getAddress());
        notice1.setStatus(notice.getStatus());
        notice1.setEmail(notice.getEmail());
        notice1.setAmount(notice.getAmount());
        notice1.setRejectionReason(notice.getRejectionReason());
        notice1.setPayDate(notice1.getPayDate());
        Notice noti = noticeService.addNotice(notice1);
        return new ResponseEntity<>(noti, HttpStatus.OK);
    }
    @Transactional
    @RequestMapping(method =RequestMethod.DELETE)
    public ResponseEntity<?> deleteNotice(@RequestParam (value = "noticeId", required = true) String noticeId) {
        System.out.println(noticeId);
        long val = Long.valueOf(noticeId);
        noticeService.deleteNoticeByNoticeID(val);
        return new ResponseEntity<>(val, HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getNoticesByStatus(@RequestParam(value = "status", required = true) String status)
    {
        List<Notice> notices = noticeService.findNoticesByStatus(status);
        return new ResponseEntity<>(notices, HttpStatus.OK);
    }





}
