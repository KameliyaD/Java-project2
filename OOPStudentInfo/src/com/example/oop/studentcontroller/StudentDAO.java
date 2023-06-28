package com.example.oop.studentcontroller;

import com.example.oop.model.Student;
import java.util.List;

public interface StudentDAO {
	
	
	public void save(Student students);
    public void update(Student students);
    public void delete(Student students);
    public Student get(int id);
    public List<Student> list();
    
}
