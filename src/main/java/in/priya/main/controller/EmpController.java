package in.priya.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import in.priya.main.entity.Employee;
import in.priya.main.service.EmpService;
import jakarta.servlet.http.HttpSession;

@Controller
public class EmpController {

    @Autowired
    private EmpService service;

    // Mapping for the home page
    @GetMapping("/")
    public String home(HttpSession session, Model model) {
        // Retrieve the success or error message from the session
        String successMsg = (String) session.getAttribute("msg");
        String errorMsg = (String) session.getAttribute("error");

        // Add messages to the model
        model.addAttribute("msg", successMsg);
        model.addAttribute("error", errorMsg);

        // Clear messages from the session
        session.removeAttribute("msg");
        session.removeAttribute("error");
        
        List<Employee> emp=service.getAllEmp();
        model.addAttribute("emp",emp);

        return "index";  // Returns the home page view (index.jsp or index.html)
    }

    // Mapping to show the "Add Employee" form
    @GetMapping("/addemp")
    public String addEmpForm() {
        return "add_emp";  // Returns the add employee page view (add_emp.jsp or add_emp.html)
    }

    // Handles employee registration
    @PostMapping("/register")
    public String empRegister(@ModelAttribute Employee e, RedirectAttributes redirectAttributes, HttpSession session) {
        try {
            // Adding employee to the service
            service.addEmp(e);

            // Success message set in session
            session.setAttribute("msg", "Employee Added Successfully!");
            return "redirect:/";  // Redirects to the home page
        } catch (Exception ex) {
            // Log the error
            ex.printStackTrace();
            // Error message set in session
            session.setAttribute("error", "Employee registration failed. Please try again.");
            return "redirect:/addemp";  // Redirects back to the form in case of error
        }
    }
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable int id,Model m)
    {
    	Employee e=service.getEmpBYId(id);
    	m.addAttribute("emp", e);
    	return "edit";
    }
    @PostMapping("/update")
    public String updateEmp(@ModelAttribute Employee e,HttpSession session)
    {
    	service.addEmp(e);
    	session.setAttribute("msg","Employee Data Update Successfully... ");
    	return "redirect:/";
    }
    @GetMapping("/delete/{id}")
    public String deleteEmp(@PathVariable int id,HttpSession session)
    {
    	service.deleteEmp(id);
    	session.setAttribute("msg","Employee Data Deleted Successfully... ");
    	return "redirect:/";
    }
}
