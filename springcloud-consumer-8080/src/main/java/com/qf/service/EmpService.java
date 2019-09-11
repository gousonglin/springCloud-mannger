package com.qf.service;

import com.qf.fallbackService.EmpServiceFallback;
import com.qf.pojo.Employee;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient(name="springcloud-provider", fallback = EmpServiceFallback.class)
public interface EmpService {

    @RequestMapping("/employee")
    public List<Employee> getAll();

}
