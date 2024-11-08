package application;

import models.dao.DaoFactory;
import models.dao.SellerDao;
import models.entities.Department;
import models.entities.Seller;

import java.util.Date;


public class Main {
    public static void main(String[] args) {

        Department obj = new Department(1, "books");

        Seller seller = new Seller(21, "Bob", "bob@gmail.com", new Date(), 3000.0, obj);

        SellerDao sellerDao = DaoFactory.createSellerDao();
        System.out.println(seller);

    }
}