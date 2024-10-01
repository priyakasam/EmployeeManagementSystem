package in.priya.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.priya.main.entity.Employee;
@Repository
public interface  Emprepo extends JpaRepository<Employee,Integer>{

}
