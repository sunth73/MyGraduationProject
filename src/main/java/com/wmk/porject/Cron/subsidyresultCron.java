package com.wmk.porject.Cron;

import com.wmk.porject.bean.*;
import com.wmk.porject.mapper.subsidyapplicationmapper.SubsidyApplicationMapper;
import com.wmk.porject.mapper.subsidymapper.SubsidyMapper;
import com.wmk.porject.mapper.subsidyresult.SubsidyResultMapper;
import com.wmk.porject.util.IDUtils;
import com.wmk.porject.util.utilty.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author Sunth
 * @DateTime 2019-04-21 16:51
 * 描述
 */
@Configuration
@EnableScheduling
public class subsidyresultCron implements SchedulingConfigurer {
    @Autowired
    private SubsidyMapper subsidyMapper;
    @Autowired
    private SubsidyApplicationMapper subsidyApplicationMapper;
    @Autowired
    private SubsidyResultMapper subsidyResultMapper;
    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.addTriggerTask(new Runnable() {

            @Override
            public void run() {
                DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String date= DateUtil.formatDate(new Date(),"yyyy-MM-dd");
                List<Subsidy> list=subsidyMapper.subsidylistByState();
                try {
                    int num=0;
                    int aa=0;
                    for (Subsidy subsidy:list){
                        if(sdf.parse(date).getTime()>sdf.parse(subsidy.getTsuEndDate()).getTime()){
                            List<SubsidyApplication> list1 = subsidyApplicationMapper.newlist(subsidy.getTsuId(),subsidy.getTsuNum());
                            for(SubsidyApplication subsidyApplication:list1){
                                SubsidyResult subsidyResult=new SubsidyResult();
                                subsidyResult.setId(IDUtils.createID());
                                subsidyResult.setTsClass(subsidyApplication.gettClassNum());
                                subsidyResult.setTsTsuId(subsidy.getTsuId());
                                subsidyResult.setTsStuName(subsidyApplication.getStuName());
                                subsidyResult.setTsMoney(subsidy.getTsuMoney());
                                subsidyResult.setTsStuId(subsidyApplication.gettStuCardNum());
                                subsidyResultMapper.insertSelective(subsidyResult);
                                num++;
                            }
                            subsidy.setTsuState("1");
                            subsidyMapper.updateByPrimaryKeySelective(subsidy);
                            aa++;
                        }
                    }
                    System.out.println("========="+num+"    "+aa);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }, new Trigger() {

            @Override
            public Date nextExecutionTime(TriggerContext triggerContext) {

                return new CronTrigger("00 00 00  * * ?").nextExecutionTime(triggerContext);
            }
        });
    }
}
