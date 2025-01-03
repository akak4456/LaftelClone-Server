package com.jo.laftelcloneserver.test.service;

import com.jo.laftelcloneserver.test.dto.TestDTO;
import com.jo.laftelcloneserver.test.mapper.TestMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TestService {
    private final TestMapper testMapper;

    public List<TestDTO> getTestDTOList() {
        return testMapper.selectTestMapperList();
    }
}
