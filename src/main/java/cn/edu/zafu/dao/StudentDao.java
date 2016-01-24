package cn.edu.zafu.dao;

import cn.edu.zafu.model.Student;

/**
 * Created by lizhangqu on 16/1/24.
 */
public class StudentDAO extends BaseDAO<Student> {
    public Student findByKey(String name, int age) {
        return findOne("findByKey", "name", name, "age", age);
    }

    public PaginationResult<Student> queryStudents(int page, int size, String name, int age) {
        return queryPage(page, size, "name", name, "age", age);
    }
}
