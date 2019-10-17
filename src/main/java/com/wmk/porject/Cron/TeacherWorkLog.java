package com.wmk.porject.Cron;

import com.wmk.porject.bean.Subsidy;
import com.wmk.porject.bean.Teacher;
import com.wmk.porject.bean.WorkLog;
import com.wmk.porject.mapper.subsidymapper.SubsidyMapper;
import com.wmk.porject.mapper.teachermapper.TeacherMapper;
import com.wmk.porject.mapper.worklogmapper.WorkLogMapper;
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
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author Sunth
 * @DateTime 2019-05-25 14:16
 * 描述
 */
@Configuration
@EnableScheduling
public class TeacherWorkLog implements SchedulingConfigurer {
    @Autowired
    private TeacherMapper teacherMapper;
    @Autowired
    private WorkLogMapper workLogMapper;
    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.addTriggerTask(new Runnable() {

            @Override
            public void run() {
                DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String date= DateUtil.formatDate(new Date(),"yyyy-MM-dd");
                List<Teacher> list=teacherMapper.selectlistTea();
                Calendar calendar2 = Calendar.getInstance();
                SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
                calendar2.add(Calendar.DATE, 1);
                String three_days_after = sdf2.format(calendar2.getTime());
                try {
                    for (Teacher teacher:list){
                        WorkLog workLog=new WorkLog();
                        workLog.settId(IDUtils.createID());
                        workLog.setLogType("0");
                        workLog.setLogFlag("0");
                        workLog.setLogDate(three_days_after);
                        workLog.setLogUserId(teacherMapper.selectUserIdByTeaId(teacher.getTeaId()).toString());
                        workLogMapper.insertSelective(workLog);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Trigger() {

            @Override
            public Date nextExecutionTime(TriggerContext triggerContext) {

                return new CronTrigger("00 00 23  * * ?").nextExecutionTime(triggerContext);
            }
        });
    }
}
