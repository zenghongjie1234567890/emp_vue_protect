package com.zhj.bean;

public class TEmp {
    private Integer id;

    private String name;

    private String path;

    private Double salary;

    private Integer age;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path == null ? null : path.trim();
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public TEmp(Integer id, String name, String path, Double salary, Integer age) {
        this.id = id;
        this.name = name;
        this.path = path;
        this.salary = salary;
        this.age = age;
    }

    public TEmp() {
    }

    @Override
    public String toString() {
        return "TEmp{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", path='" + path + '\'' +
                ", salary=" + salary +
                ", age=" + age +
                '}';
    }
}