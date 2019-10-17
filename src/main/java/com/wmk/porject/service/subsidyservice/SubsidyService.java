package com.wmk.porject.service.subsidyservice;

import com.wmk.porject.bean.*;
import com.wmk.porject.mapper.studentsubsidymapper.StudentSubsidyMapper;
import com.wmk.porject.mapper.subsidyapplicationmapper.SubsidyApplicationMapper;
import com.wmk.porject.mapper.subsidymapper.SubsidyMapper;
import com.wmk.porject.mapper.subsidyresult.SubsidyResultMapper;
import com.wmk.porject.util.IDUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * @author Sunth
 * @DateTime 2019-04-20 16:33
 * 描述
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SubsidyService {
    @Autowired
    private SubsidyMapper subsidyMapper;
    @Autowired
    private SubsidyApplicationMapper subsidyApplicationMapper;
    @Autowired
    private SubsidyResultMapper subsidyResultMapper;
    @Autowired
    private StudentSubsidyMapper studentSubsidyMapper;

    /**
     * 助学金项目列表
     * @return
     * @throws Exception
     */
    public List<Subsidy> subsidylist()throws Exception{
        return subsidyMapper.subsidylist();
    }
    /**
     * 助学金项目列表(截止期筛选)
     * @return
     * @throws Exception
     */
    public List<Subsidy> subsidylistforapplication()throws Exception{
        return subsidyMapper.subsidylistforapplication();
    }

    /**
     * 助学金申请列表
     * @param tTsuId
     * @param tClassNum
     * @return
     * @throws Exception
     */
    public List<SubsidyApplication>subsidyappList(String tTsuId,String tClassNum)throws Exception{
        return subsidyApplicationMapper.subsidyappList(tTsuId,tClassNum);
    }

    /**
     * 助学金投票
     * @param subsidyApplication
     * @return
     * @throws Exception
     */
    public int updateSubsidyApplication(SubsidyApplication subsidyApplication)throws Exception{
        SubsidyApplication subsidyApplication1=subsidyApplicationMapper.selectByPrimaryKey(subsidyApplication.getId());
        if("".equals(subsidyApplication1.gettGrade())||subsidyApplication1.gettGrade()==null){
            subsidyApplication.settGrade("1");
        }else{
            int grade=Integer.parseInt(subsidyApplication1.gettGrade())+1;
            subsidyApplication.settGrade(String.valueOf(grade));
        }
        StudentSubsidy studentSubsidy=new StudentSubsidy();
        studentSubsidy.setRecId(IDUtils.createID());
        studentSubsidy.settUserId(subsidyApplication.getUserId());
        studentSubsidy.settSubsidyId(subsidyApplication.gettTsuId());
        studentSubsidy.settStuCardNum(subsidyApplication.gettStuCardNum());
        int add=studentSubsidyMapper.insert(studentSubsidy);
        int num=0;
        if(add>0){
            num=subsidyApplicationMapper.updateByPrimaryKeySelective(subsidyApplication);
        }
        return num;
    }

    /**
     * 查询助学金详情
     * @param tsuId
     * @return
     * @throws Exception
     */
    public Subsidy selectSubsidy(String tsuId)throws Exception{
        return subsidyMapper.selectByPrimaryKey(tsuId);
    }

    /**
     * 助学金获取名单
     * @param tsTsuId
     * @return
     * @throws Exception
     */
    public List<SubsidyResult>subsidresultyList(String tsTsuId)throws Exception{
        return subsidyResultMapper.subsidresultyList(tsTsuId);
    }

    /**
     * 助学金申请
     * @param subsidyApplication
     * @return
     * @throws Exception
     */
    public int addSubsidyApplication(SubsidyApplication subsidyApplication)throws Exception{
        return subsidyApplicationMapper.insertSelective(subsidyApplication);
    }

    /**
     * 防止重复提交
     * @param tTsuId
     * @param tStuCardNum
     * @return
     * @throws Exception
     */
    public boolean selectBytTsuIdAndtStuCardNum( String tTsuId, String tStuCardNum)throws Exception{
        List<SubsidyApplication> list=subsidyApplicationMapper.selectBytTsuIdAndtStuCardNum(tTsuId,tStuCardNum);
        if(list.size()==0){
            return true;
        }else {
            return false;
        }
    }

    /**
     * 限制投票次数
     * @param tUserId
     * @param tSubsidyId
     * @return
     * @throws Exception
     */
    public boolean touPiaoCiShu (String tUserId, String tSubsidyId)throws Exception{
        List<StudentSubsidy>stusubList=studentSubsidyMapper.stusubList(null,tUserId,tSubsidyId);
        if(stusubList.size()<3){
            return true;
        }else {
            return false;
        }
    }

    /**
     * 只能给每个人头一次
     * @param tUserId
     * @param tSubsidyId
     * @return
     * @throws Exception
     */
    public boolean touPiaoNum (String tStuCardNum,String tUserId, String tSubsidyId)throws Exception{
        List<StudentSubsidy>stusubList=studentSubsidyMapper.stusubList(tStuCardNum,tUserId,tSubsidyId);
        if(stusubList.size()<1){
            return true;
        }else {
            return false;
        }
    }

    /**
     * 助学金管理-列表
     * @param subsidy
     * @return
     * @throws Exception
     */
    public  List<Subsidy>subsidylistforlist(Subsidy subsidy) throws Exception{
            return subsidyMapper.subsidylistforlist(subsidy);
    }
    /**
     * 添加
     * @param subsidy
     * @return
     * @throws Exception
     */
    public int add(Subsidy subsidy)throws Exception{
        return subsidyMapper.insertSelective(subsidy);
    }

    /**
     * 修改
     * @param subsidy
     * @return
     * @throws Exception
     */
    public int update(Subsidy subsidy)throws Exception{
        return subsidyMapper.updateByPrimaryKeySelective(subsidy);
    }

    /**
     * 删除
     * @param list
     * @return
     * @throws Exception
     */
    public int delete(List<Subsidy>list)throws Exception{
        int deleteNum=0;
        for(Subsidy subsidy:list){
            subsidyMapper.deleteByPrimaryKey(subsidy.getTsuId());
            deleteNum++;
        }
        return deleteNum;
    }
}
