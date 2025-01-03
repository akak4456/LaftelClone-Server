package com.jo.laftelcloneserver.test.mapper;

import com.jo.laftelcloneserver.test.dto.TestDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TestMapper {
    List<TestDTO> selectTestMapperList();
}
