package com.wmk.porject.service.subjectservice;

import com.wmk.porject.bean.Subject;
import com.wmk.porject.mapper.subjectmapper.SubjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Sunth
 * @DateTime 2019-05-12 18:11
 * 描述
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SubjectService {
    @Autowired
    private SubjectMapper subjectMapper;

    /**
     * 课程列表
     * @return
     * @throws Exception
     */
    public List<Subject> sublict()throws Exception{
        return subjectMapper.sublict();
    }
}
