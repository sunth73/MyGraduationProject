package com.wmk.porject.service.dormservice;

import com.wmk.porject.bean.Dorm;
import com.wmk.porject.bean.DormScore;
import com.wmk.porject.mapper.dormmapper.DormMapper;
import com.wmk.porject.mapper.dormscoremapper.DormScoreMapper;
import com.wmk.porject.request.DormScoreRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Sunth
 * @DateTime 2019-04-17 16:10
 * 描述
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class DormService {
    @Autowired
    private DormMapper dormMapper;

    @Autowired
    private DormScoreMapper dormScoreMapper;

    /**
     * 管理员查询
     * @param dormScoreRequest
     * @return
     * @throws Exception
     */
    public List<DormScoreRequest> selectDormByAdmin(DormScoreRequest dormScoreRequest)throws Exception{
        return dormMapper.selectDormByAdmin(dormScoreRequest);
    }

    /**
     * 教师查询
     * @param dormScoreRequest
     * @return
     * @throws Exception
     */
    public List<DormScoreRequest>selectDormByTeacher(DormScoreRequest dormScoreRequest)throws Exception{
        return dormMapper.selectDormByTeacher(dormScoreRequest);
    }

    /**
     * 学生查询
     * @param dormScoreRequest
     * @return
     * @throws Exception
     */
    public List<DormScoreRequest>selectDormByStudent(DormScoreRequest dormScoreRequest)throws Exception{
        return dormMapper.selectDormByStudent(dormScoreRequest);
    }
    /**
     *查询
     * @param tdsId
     * @return
     */
    public DormScoreRequest selectScore(String tdsId)throws Exception{
        return dormMapper.selectScore(tdsId);
    }

    /**
     * 保存评价
     * @param dormScore
     * @return
     * @throws Exception
     */
    public int savePingJia(DormScore dormScore)throws Exception{
        return dormScoreMapper.updateByPrimaryKeySelective(dormScore);
    }

    /**
     * 宿舍列表
     * @return
     * @throws Exception
     */
    public List<Dorm>dromList()throws Exception{
        return dormMapper.dromList2();
    }

    /**
     * 添加宿舍
     * @param dorm
     * @return
     * @throws Exception
     */
    public int addDorm(Dorm dorm)throws Exception{
        return dormMapper.insertSelective(dorm);
    }

    /**
     * 修改宿舍
     * @param dorm
     * @return
     * @throws Exception
     */
    public int updateDorm(Dorm dorm)throws Exception{
        return dormMapper.updateByPrimaryKeySelective(dorm);
    }
    /**
     * 删除
     * @param dormList
     * @return
     * @throws Exception
     */
    public int deleteclass(List<Dorm>dormList)throws Exception{
        int deleteNum=0;
        for(Dorm dorm:dormList){
            dormMapper.deleteByPrimaryKey(dorm.getDormId());
            deleteNum++;
        }
        return deleteNum;
    }
}
