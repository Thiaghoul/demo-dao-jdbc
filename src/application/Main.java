package application;

import models.dao.DaoFactory;
import models.dao.SellerDao;

import models.entities.Department;
import models.entities.Seller;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        SellerDao sellerDao = DaoFactory.createSellerDao();

        System.out.println("==== Test 1: seller findByID ====");
        Seller seller = sellerDao.findById(3);
        System.out.println(seller);

        System.out.println("==== Test 2: seller findByDepartment ====");
        Department department = new Department(2,null);
        List<Seller> sellerList = sellerDao.findByDepartment(department);
        for (Seller s : sellerList) {
            System.out.println(s);
        }

    }
}