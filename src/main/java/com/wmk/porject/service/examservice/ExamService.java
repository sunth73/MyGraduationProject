package com.wmk.porject.service.examservice;

import com.wmk.porject.bean.*;
import com.wmk.porject.mapper.exammapper.ExamMapper;
import com.wmk.porject.mapper.scoremapper.ScoreMapper;
import com.wmk.porject.mapper.studentmapper.StudentMapper;
import com.wmk.porject.mapper.subjectmapper.SubjectMapper;
import com.wmk.porject.util.IDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Sunth
 * @DateTime 2019-05-12 10:30
 * 描述
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ExamService {
    @Autowired
    private ExamMapper examMapper;

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private SubjectMapper subjectMapper;
    @Autowired
    private ScoreMapper scoreMapper;

    /**
     * 考试列表
     * @param exam
     * @return
     * @throws Exception
     */
    public List<Exam> examList(Exam exam)throws Exception{
        return examMapper.examList(exam);
    }

    /**
     * 添加考试
     * @param exam
     * @return
     * @throws Exception
     */
    public int addExam(Exam exam)throws Exception{
        List<Student> stulist=studentMapper.studentListByGrade(exam.geteClass());
        List<Subject> subList=subjectMapper.sublict();
        exam.seteId(IDUtils.createID());
        for(Student student:stulist){
            for(Subject subject:subList){
                Score score=new Score();
                score.setRecId(IDUtils.createID());
                score.setsExamId(exam.geteId());
                score.setsStuId(student.getStuId());
                score.setsSubCode(subject.getSubCode());
                scoreMapper.insertSelective(score);
            }
        }
        return examMapper.insertSelective(exam);
    }

    /**
     * 修改考试
     * @param exam
     * @return
     * @throws Exception
     */
    public int updateExam(Exam exam)throws Exception{
        return examMapper.updateByPrimaryKeySelective(exam);
    }
    /**
     * 删除
     * @param list
     * @return
     * @throws Exception
     */
    public int delete(List<Exam>list)throws Exception{
        int deleteNum=0;
        for(Exam exam:list){
            examMapper.deleteByPrimaryKey(exam.geteId());
            deleteNum++;
        }
        return deleteNum;
    }
}
