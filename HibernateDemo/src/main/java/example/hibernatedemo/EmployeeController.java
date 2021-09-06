package example.hibernatedemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Transactional
public class EmployeeController {
    @Autowired
    private ManageEmployee manageEmployee;
    @GetMapping("/index")
    public String hello(){
        return "hello";
    }
    @PostMapping("/addPerson")
    public String add(@RequestBody Employee employee){
        manageEmployee.addEmployee(employee);
        return "success adding employee!";
    }
    @GetMapping("/getList")
    public List<Employee> getList(){
        return manageEmployee.listEmployees();
    }
    @GetMapping("/deleteAll")
    public String deleteAll(){
        manageEmployee.deleteAllEmployees();
        return "delete success!";
    }

}
