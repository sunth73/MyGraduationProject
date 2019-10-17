package com.wmk.porject.service.teacherservice;

import com.wmk.porject.bean.Teacher;
import com.wmk.porject.bean.User;
import com.wmk.porject.mapper.teachermapper.TeacherMapper;
import com.wmk.porject.mapper.usermapper.UserMapper;
import com.wmk.porject.service.studentservice.StudentService;
import com.wmk.porject.util.IDUtils;
import com.wmk.porject.util.NumberUtil;
import com.wmk.porject.util.PinYinUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @author Sunth
 * @DateTime 2019-04-16 21:30
 * 描述
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class TeacherService {
    @Autowired
    private TeacherMapper teacherMapper;
    @Autowired
    private UserMapper userMapper;
    private static volatile StudentService instance;
    public static StudentService getInstance(){
        if(null==instance){
            synchronized (StudentService.class){
                if(null==instance){
                    instance=new StudentService();
                }
            }
        }
        return instance;
    }

    /**
     * 教师列表查询
     * @param teacher
     * @return
     * @throws Exception
     */
    public List<Teacher> teaList(Teacher teacher)throws Exception{
        return teacherMapper.teaList(teacher);
    }

    /**
     * 查询教师信息
     * @param teaId
     * @return
     * @throws Exception
     */
    public Teacher selectTea(String teaId)throws Exception{
        return teacherMapper.selectByPrimaryKey(teaId);
    }

    /**
     * 添加教师
     * @param teacher
     * @return
     * @throws Exception
     */
    public int addTeacher(Teacher teacher) throws Exception{
        String username=getUserName(teacher.getTeaName());
        User user=new User();
        user.setPassword("123456");
        user.setEmpname(teacher.getTeaName());
        user.setUsername(username);
        user.setUsermail(teacher.getTeaMail());
        user.setUserphone(teacher.getTeaPhone());
        user.setUsertype(2);
        user.setRoleid(4);
        user.setRolename("教师");
        user.setCreatetime(new Date());
        int teaNum=0;
        int userNum= userMapper.insertSelective(user);
        if(userNum>0){
            teacher.setTeaLoginName(username);
            teacher.setTeaId(IDUtils.createID());
            teaNum=teacherMapper.insertSelective(teacher);
        }
        return teaNum;
    }

    /**
     * 教师导入
     * @param teaList
     * @return
     * @throws Exception
     */
    public Map<String,Object> ImportTea(List<Teacher> teaList)throws Exception{
        Map<String,Object>map=new HashMap<>();
        List<Teacher>list=new ArrayList<>();
        int teaNum=0;
        for(Teacher teacher:teaList){
            if(teacher.getTeaName()==null||"".equals(teacher.getTeaName())){
                list.add(teacher);
            }else {
                String username=getUserName(teacher.getTeaName());
                User user=new User();
                user.setPassword("123456");
                user.setEmpname(teacher.getTeaName());
                user.setUsername(username);
                user.setUsermail(teacher.getTeaMail());
                user.setUserphone(teacher.getTeaPhone());
                user.setUsertype(2);
                user.setRoleid(4);
                user.setRolename("教师");
                user.setCreatetime(new Date());
                int userNum= userMapper.insertSelective(user);
                if(userNum>0){
                    teacher.setTeaLoginName(username);
                    teacher.setTeaId(IDUtils.createID());
                    teacherMapper.insertSelective(teacher);
                    teaNum++;
                }
            }
        }
        map.put("successNum",teaNum);
        map.put("error",list);
        return map;
    }

    /**
     * 获取教师姓名
     * @param s1
     * @return
     * @throws Exception
     */
    public String getUserName(String s1)throws Exception{
        String username=null;
        String uname= PinYinUtil.forQuanPinLow(s1);
        List<String>list=userMapper.selectUserName(uname);
        if(list.size()==0){
            username=uname;
        }else {
            List<String>li=new ArrayList<>();
            for(String s:list){
                String str= NumberUtil.getNum(s);
                li.add(str);
            }
            String maxNum= Collections.max(li);
            int number=0;
            if(maxNum!=null && !"".equals(maxNum)){
                number=Integer.parseInt(maxNum)+1;
                username= uname+number;
            }else {
                username= uname+(number+1);
            }
        }
        return username;
    }

    /**
     * 修改教师信息
     * @param teacher
     * @return
     * @throws Exception
     */
    public int updateTea(Teacher teacher)throws Exception{
        User user=new User();
        user.setUsername(teacher.getTeaLoginName());
        user.setUsermail(teacher.getTeaMail());
        user.setUserphone(teacher.getTeaPhone());
        user.setUpdatetime(new Date());
        int userNum=userMapper.updateByEmpname(user);
        int teaNum=0;
        if(userNum>0){
            teaNum=teacherMapper.updateByPrimaryKeySelective(teacher);
        }
        return teaNum;
    }

    /**
     * 删除教师
     * @param teaList
     * @return
     * @throws Exception
     */
    public int deleteTea(List<Teacher> teaList)throws Exception{
        int deleteNum=0;
        for(Teacher teacher:teaList){
            teacherMapper.deleteByPrimaryKey(teacher.getTeaId(),teacher.getTeaLoginName());
            deleteNum++;
        }
        return deleteNum;
    }

    /**
     * 根据登录名获取信息
     * @param teaLoginName
     * @return
     * @throws Exception
     */
    public  Teacher selectClassNumByLoginName(String teaLoginName)throws Exception{
            return  teacherMapper.selectClassNumByLoginName(teaLoginName);
    }
    /**
     * 根据userid获取信息
     * @param userid
     * @return
     * @throws Exception
     */
    public  Teacher selectTeacherByUserId(Integer userid)throws Exception{
        return  teacherMapper.selectTeacherByUserId(userid);
    }
}
