package com.wmk.porject.Cron;

import com.wmk.porject.bean.Dorm;
import com.wmk.porject.bean.DormScore;
import com.wmk.porject.mapper.dormmapper.DormMapper;
import com.wmk.porject.mapper.dormscoremapper.DormScoreMapper;
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

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * @author Sunth
 * @DateTime 2019-04-18 14:17
 * 描述:定时更新宿舍需打分数据
 */
@Configuration
@EnableScheduling
public class DynamicScheduledTask implements SchedulingConfigurer {
    @Autowired
    private DormMapper dormMapper;
    @Autowired
    private DormScoreMapper dormScoreMapper;
    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.addTriggerTask(new Runnable() {

            @Override
            public void run() {
                String date=DateUtil.formatDate(new Date(),"yyyy-MM");
                List<Dorm>list=dormMapper.dromList();
                for (Dorm dorm:list){
                    DormScore dormScore=new DormScore();
                    dormScore.setTdsId(IDUtils.createID());
                    dormScore.setTdsDate(date);
                    dormScore.setTdsDormId(dorm.getDormId());
                    dormScoreMapper.insertSelective(dormScore);
                }
            }
        }, new Trigger() {

            @Override
            public Date nextExecutionTime(TriggerContext triggerContext) {

                return new CronTrigger("0 0 0 1 * ? ").nextExecutionTime(triggerContext);
            }
        });
    }


}
