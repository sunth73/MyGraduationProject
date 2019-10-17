package com.wmk.porject.service.classsevice;

import com.wmk.porject.bean.TClass;
import com.wmk.porject.bean.Teacher;
import com.wmk.porject.mapper.classmapper.TClassMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Sunth
 * @DateTime 2019-04-22 17:39
 * 描述
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class TClassService {
    @Autowired
    private TClassMapper tClassMapper;

    /**
     * 班级列表
     * @return
     * @throws Exception
     */
    public List<TClass>classList(String tcGradeNum)throws Exception{
        return tClassMapper.classList(tcGradeNum);
    }

    /**
     * 年级列表
     * @return
     * @throws Exception
     */
    public List<TClass>gradeList()throws Exception{
        return tClassMapper.gradeList();
    }
    /**
     * 根据班级查年级
     * @param tcClassNum
     * @return
     */
    public List<TClass>selectGradeNum(String tcClassNum)throws Exception{
        return tClassMapper.selectGradeNum(tcClassNum);
    }

    /**
     * 添加班级
     * @param tClass
     * @return
     * @throws Exception
     */
    public int addClass(TClass tClass)throws Exception{
        return tClassMapper.insertSelective(tClass);
    }

    /**
     * 修改
     * @param tClass
     * @return
     * @throws Exception
     */
    public int update(TClass tClass)throws Exception{
        return tClassMapper.updateByPrimaryKeySelective(tClass);
    }

    /**
     * 删除
     * @param classList
     * @return
     * @throws Exception
     */
    public int deleteclass(List<TClass>classList)throws Exception{
        int deleteNum=0;
        for(TClass tClass:classList){
            tClassMapper.deleteByPrimaryKey(tClass.getTcId());
            deleteNum++;
        }
        return deleteNum;
    }
}
