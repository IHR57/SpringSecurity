package com.iqbal.securitybasic.controller;

import com.iqbal.securitybasic.model.Notice;
import com.iqbal.securitybasic.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeRepository noticeRepository;

    @GetMapping("/notices")
    public List<Notice> getNotices() {

        return noticeRepository.findAllActiveNotices();
    }
}