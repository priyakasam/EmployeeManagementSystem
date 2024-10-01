package in.priya.main.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import in.priya.main.entity.Employee;
import in.priya.main.repository.Emprepo;
@Service
public class EmpService {
	@Autowired
	private Emprepo repo;
	public void addEmp(Employee e)
	{
		repo.save(e);
	}
    public List<Employee> getAllEmp()
    {
    	
		return repo.findAll();
    	
    }
    public Employee getEmpBYId(int id)
    {
    	Optional<Employee> e= repo.findById(id); 
    	if(e.isPresent()) {
    		return e.get();
    	}
    	return null;
    }
    public void deleteEmp(int id)
    {
    	repo.deleteById(id);
    }
}
