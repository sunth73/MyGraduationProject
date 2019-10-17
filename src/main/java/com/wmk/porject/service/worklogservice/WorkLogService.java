package com.wmk.porject.service.worklogservice;

import com.wmk.porject.bean.WorkLog;
import com.wmk.porject.mapper.worklogmapper.WorkLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Sunth
 * @DateTime 2019-05-21 20:44
 * 描述
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class WorkLogService {
    @Autowired
    private WorkLogMapper workLogMapper;

    /**
     * 日报列表
     * @param workLog
     * @param workLog
     * @return
     * @throws Exception
     */
    public List<WorkLog>logList(WorkLog workLog)throws Exception{
        return workLogMapper.logList(workLog);
    }

    /**
     * 查看详情
     * @param tId
     * @return
     * @throws Exception
     */
    public WorkLog selectworkLog(String tId)throws Exception{
        return workLogMapper.selectByPrimaryKey(tId);
    }

    /**
     * 修改
     * @param workLog
     * @return
     * @throws Exception
     */
    public int update(WorkLog workLog)throws Exception{
        return workLogMapper.updateByPrimaryKeySelective(workLog);
    }
}
