package com.wmk.porject.service.studentservice;

import com.alibaba.fastjson.JSON;
import com.wmk.porject.bean.Student;
import com.wmk.porject.bean.User;
import com.wmk.porject.mapper.studentmapper.StudentMapper;
import com.wmk.porject.mapper.usermapper.UserMapper;
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
 * @DateTime 2019-04-14 17:31
 * 描述
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class StudentService {
    @Autowired
    private StudentMapper studentMapper;
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
     * 学生列表查询
     * @param student
     * @return
     * @throws Exception
     */
    public List<Student> stuList(Student student)throws Exception{
        return studentMapper.stuList(student);
    }

    /**
     * 查询学生信息
     * @param stuId
     * @return
     * @throws Exception
     */
    public Student selectStu(String stuId)throws Exception{
        return studentMapper.selectByPrimaryKey(stuId);
    }
    /**
     * 查询学生信息by userid
     * @param userid
     * @return
     * @throws Exception
     */
    public Student selectByUserId(Integer userid)throws Exception{
        return studentMapper.selectByUserId(userid);
    }
    /**
     * 添加学生
     * @param student
     * @return
     * @throws Exception
     */
    public int addStudent(Student student) throws Exception{
        String username=getUserName(student.getStuName());
        User user=new User();
        user.setPassword("123456");
        user.setEmpname(student.getStuName());
        user.setUsername(username);
        user.setUsermail(student.getStuEmail());
        user.setUserphone(student.getStuPhone());
        user.setUsertype(2);
        user.setRoleid(9);
        user.setRolename("学生");
        user.setCreatetime(new Date());
        int stuNum=0;
       int userNum= userMapper.insertSelective(user);
        if(userNum>0){
            student.setStuUname(username);
            student.setStuId(IDUtils.createID());
           stuNum=studentMapper.insertSelective(student);
       }
        return stuNum;
    }

    /**
     * 学生导入
     * @param stuList
     * @return
     * @throws Exception
     */
    public Map<String,Object> ImportStu(List<Student> stuList)throws Exception{
        Map<String,Object>map=new HashMap<>();
        List<Student>list=new ArrayList<>();
        int stuNum=0;
        for(Student student:stuList){
            if(student.getStuName()==null||"".equals(student.getStuName())){
                list.add(student);
            }else {
                String username=getUserName(student.getStuName());
                User user=new User();
                user.setPassword("123456");
                user.setEmpname(student.getStuName());
                user.setUsername(username);
                user.setUsermail(student.getStuEmail());
                user.setUserphone(student.getStuPhone());
                user.setUsertype(2);
                user.setRoleid(9);
                user.setRolename("学生");
                user.setCreatetime(new Date());
                int userNum= userMapper.insertSelective(user);
                if(userNum>0){
                    student.setStuUname(username);
                    student.setStuId(IDUtils.createID());
                    studentMapper.insertSelective(student);
                    stuNum++;
                }
            }
        }
        map.put("successNum",stuNum);
        map.put("error",list);
        return map;
    }

    /**
     * 获取学生姓名
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
                String str=NumberUtil.getNum(s);
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
     * 修改学生信息
     * @param student
     * @return
     * @throws Exception
     */
    public int updateStu(Student student)throws Exception{
        User user=new User();
        user.setUsername(student.getStuUname());
        user.setUsermail(student.getStuEmail());
        user.setUserphone(student.getStuPhone());
        user.setUpdatetime(new Date());
        int userNum=userMapper.updateByEmpname(user);
        int stuNum=0;
        if(userNum>0){
            stuNum=studentMapper.updateByPrimaryKeySelective(student);
        }
        return stuNum;
    }

    /**
     * 删除学生
     * @param stuList
     * @return
     * @throws Exception
     */
    public int deleteStu(List<Student> stuList)throws Exception{
        String js= JSON.toJSONString(stuList);
        int deleteNum=0;
        for(Student stu:stuList){
            studentMapper.deleteByPrimaryKey(stu.getStuId(),stu.getStuUname());
            deleteNum++;
        }
        return deleteNum;
    }

    /**
     * 根据登录名获取学生信息
     * @param stuUname
     * @return
     * @throws Exception
     */
    public Student selectByStuUname(String stuUname)throws Exception{
        return studentMapper.selectByStuUname(stuUname);
    }
}
