package application;

import models.dao.DaoFactory;
import models.dao.DepartmentDao;
import models.dao.SellerDao;
import models.entities.Department;
import models.entities.Seller;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main2 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        SellerDao sellerDao = DaoFactory.createSellerDao();
        DepartmentDao departmentDao = DaoFactory.createDepartmentDao();

        System.out.println("==== Test 1: department findByID ====");
        Department department = departmentDao.findById(1);
        System.out.println(department);

        System.out.println("==== Test 2: department findAll ====");
        List<Department> departments = departmentDao.findAll();
        for (Department dep : departments) {
            System.out.println(dep);
        }

        System.out.println("==== Test 3: department insert====");
        Department department2 = new Department(null, "Music");
        departmentDao.insert(department2);
        System.out.println("Department inserted! New id = "+ department2.getID());

        System.out.println("==== Test 4: department update====");
        Department department3 = departmentDao.findById(1);
        department3.setName("Comic");
        departmentDao.update(department3);
        System.out.println("Update completed!");

        System.out.println("==== Test 5: department delete====");
        System.out.println("Enter the id of the department you want to delete");
        int id = sc.nextInt();
        departmentDao.deleteById(id);
        System.out.println("Department deleted!");

        sc.close();

    }
}