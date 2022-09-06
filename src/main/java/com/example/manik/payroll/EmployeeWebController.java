package com.example.manik.payroll;

import lombok.AllArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@AllArgsConstructor
@Controller
public class EmployeeWebController {


    private final EmployeeRepository repository;
    private final EmployeeModelAssembler assembler;

    //FORMS
    @GetMapping("/employee_form")
    public String getEmployeeForm(Model model) {
        //default values of form : first, last, role
        Employee employee = new Employee("first", "last", "role");
        model.addAttribute("employee", employee);
        return "employee_form";
    }

    @PostMapping("/employee_form_submit")
    public ModelAndView submitEmployeeForm(@ModelAttribute Employee employee, ModelMap model) {
        EntityModel<Employee> entityModel = assembler.toModel(repository.save(employee));

        model.addAttribute("employee", entityModel.getContent());
        return new ModelAndView("add_employee_result", model);
    }
}
