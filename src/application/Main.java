package application;

import models.dao.DaoFactory;
import models.dao.SellerDao;

import models.entities.Department;
import models.entities.Seller;

import java.util.Date;
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

        System.out.println("==== Test 3: seller findByAll====");
        sellerList = sellerDao.findAll();
        for (Seller s : sellerList) {
            System.out.println(s);
        }

        System.out.println("==== Test 4: seller insert====");
        Seller newSeller = new Seller(null, "Greg", "greg@gmail.com", new Date(), 4000.0, department);
        sellerDao.insert(newSeller);
        System.out.println("Inserted! new id = " + newSeller.getId());

    }
}