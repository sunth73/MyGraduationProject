//package com.wmk.porject.Cron;
//
//import com.wmk.porject.bean.Teacher;
//import com.wmk.porject.bean.WorkLog;
//import com.wmk.porject.mapper.teachermapper.TeacherMapper;
//import com.wmk.porject.mapper.worklogmapper.WorkLogMapper;
//import com.wmk.porject.util.IDUtils;
//import com.wmk.porject.util.utilty.DateUtil;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.scheduling.Trigger;
//import org.springframework.scheduling.TriggerContext;
//import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.scheduling.annotation.SchedulingConfigurer;
//import org.springframework.scheduling.config.ScheduledTaskRegistrar;
//import org.springframework.scheduling.support.CronTrigger;
//import org.springframework.stereotype.Component;
//
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.List;
//
///**
// * @author Sunth
// * @DateTime 2019-05-25 14:16
// * 描述
// */
//@Configuration
//@EnableScheduling
//public class Test1 implements SchedulingConfigurer {
//    @Override
//    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
//        taskRegistrar.addTriggerTask(new Runnable() {
//
//            @Override
//            public void run() {
//                Calendar calendar2 = Calendar.getInstance();
//                SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                calendar2.add(Calendar.DATE, 0);
//                String three_days_after = sdf2.format(calendar2.getTime());
//                System.out.println("=================="+three_days_after);
//            }
//        }, new Trigger() {
//
//            @Override
//            public Date nextExecutionTime(TriggerContext triggerContext) {
//
//                return new CronTrigger("0/5 * * * * ?").nextExecutionTime(triggerContext);
//            }
//        });
//    }
//}
