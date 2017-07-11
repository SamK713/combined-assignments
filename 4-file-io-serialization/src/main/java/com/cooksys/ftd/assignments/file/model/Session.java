package com.cooksys.ftd.assignments.file.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Session {
    private String location;
    private String startDate;
    private Instructor instructor;
    private List<Student> students;
    public Session(){
    	
    }
    
    public Session(String location, String startDate, Instructor instructor, List students){
    	this.location = location;
    	this.startDate = startDate;
    	this.instructor = instructor;
    	this.students = students;
    }
    

	@XmlAttribute
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
    @XmlAttribute
    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
    @XmlElement
    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }
    @XmlElement
    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
