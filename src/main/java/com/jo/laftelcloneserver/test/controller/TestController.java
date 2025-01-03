package com.jo.laftelcloneserver.test.controller;

import com.jo.laftelcloneserver.test.dto.TestDTO;
import com.jo.laftelcloneserver.test.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/test")
@RestController
@RequiredArgsConstructor
public class TestController {
    private final TestService testService;
    @GetMapping("")
    public List<TestDTO> getTestDTOList() {
        return testService.getTestDTOList();
    }
}
