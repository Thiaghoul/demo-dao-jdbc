package models.dao;

import db.DB;
import models.dao.impl.SellerDaoJDBC;
import models.entities.Seller;

import java.sql.Connection;

public class DaoFactory {

    public static SellerDao createSellerDao(){
        return new SellerDaoJDBC(DB.getConnection());
    }
}
