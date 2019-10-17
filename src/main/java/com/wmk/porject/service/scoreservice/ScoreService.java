package com.wmk.porject.service.scoreservice;

import com.alibaba.fastjson.JSON;
import com.wmk.porject.bean.Score;
import com.wmk.porject.bean.Teacher;
import com.wmk.porject.bean.User;
import com.wmk.porject.mapper.scoremapper.ScoreMapper;
import com.wmk.porject.mapper.studentmapper.StudentMapper;
import com.wmk.porject.mapper.teachermapper.TeacherMapper;
import com.wmk.porject.request.ScoreReq;
import com.wmk.porject.request.ScoreRequest;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Sunth
 * @DateTime 2019-04-18 20:35
 * 描述
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ScoreService {
    @Autowired
    private TeacherMapper teacherMapper;
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private ScoreMapper scoreMapper;

    /**
     * 查询成绩
     * @param user
     * @return
     * @throws Exception
     */
    public List<ScoreRequest>selectScoreForClass(User user,String sExamId)throws Exception{
        List<ScoreRequest>list=null;
        if(user.getRoleIds().contains("4")){
          Teacher teacher= teacherMapper.selectClassNumByLoginName(user.getUsername());
          List<String>stringList=studentMapper.selectByStuClass(teacher.getTeaCalss());
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("list",stringList);
            paramMap.put("sExamId",sExamId);
          list=scoreMapper.selectScoreForClass(paramMap);
        }
        return list;
    }

    /**
     * 单科成绩
     * @param scoreReq
     * @return
     * @throws Exception
     */
    public  List<ScoreReq>scorelist(ScoreReq scoreReq)throws Exception{
        return scoreMapper.scorelist(scoreReq);
    }
    /**
     * 全科成绩
     * @param stuClass
     * @param sExamId
     * @return
     */
    public List<ScoreRequest>selectScorelist(String stuClass,String sExamId)throws Exception{
        return scoreMapper.selectScorelist(stuClass,sExamId);
    }
    /**
     * 修改成绩
     * @param list
     * @return
     * @throws Exception
     */
    public int update(List<Score>list)throws Exception{
        int num=0;
        for(Score score:list){
            scoreMapper.updateByPrimaryKeySelective(score);
        }
        return num;
    }
}
