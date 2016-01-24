package cn.edu.zafu.service;

import cn.edu.zafu.dao.PaginationResult;
import cn.edu.zafu.dao.StudentDAO;
import cn.edu.zafu.model.Student;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by lizhangqu on 16/1/24.
 */

@Component
public class StudentService {

    @Resource
    StudentDAO studentDAO;


    public PaginationResult<Student> queryStudents(int page, String name, int age) {
        return studentDAO.queryStudents(page, 20, name, age);
    }

    public Student findStudnetById(int id) {
        return studentDAO.findById(id);
    }
}
