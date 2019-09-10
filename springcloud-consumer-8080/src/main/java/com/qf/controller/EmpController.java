package com.qf.controller;

import com.qf.pojo.Employee;
import com.qf.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/emp")
public class EmpController {

//    @Autowired
//    private RestTemplate restTemplate;

    @Resource
    private EmpService empService;

    @RequestMapping
    public Object getAll() {


//        List<Employee> list = restTemplate.getForObject("http://localhost:8001/employee", List.class);
//        List<Employee> list = restTemplate.getForObject("http://springcloud-provider/employee", List.class);

        List<Employee> list = empService.getAll();
        return list;
    }
}
