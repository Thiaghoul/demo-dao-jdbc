package models.dao;

import models.dao.impl.SellerDaoJDBC;
import models.entities.Seller;

public class DaoFactory {

    public static SellerDao createSellerDao(){
        return new SellerDaoJDBC();
    }
}
