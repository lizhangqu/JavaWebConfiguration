package cn.edu.zafu.controller;

import cn.edu.zafu.dao.PaginationResult;
import cn.edu.zafu.model.Student;
import cn.edu.zafu.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * Created by lizhangqu on 16/1/24.
 */
@Controller
@RequestMapping("student")
public class StudentController {

    @Resource
    StudentService studentService;


    @RequestMapping("list")
    public ModelAndView list(@RequestParam(defaultValue = "1") int page, String name, int age, Model model){
        PaginationResult<Student> result = studentService.queryStudents(page, name, age);
        model.addAttribute("result", result);
        return new ModelAndView("student/index");
    }

    @ResponseBody
    @RequestMapping(value = "get/{id}", method = RequestMethod.GET)
    public Student get(@PathVariable int id) {
        Student student = studentService.findStudnetById(id);
        return student;
    }
}
