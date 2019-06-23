package sql.dao;

import sql.models.Company;

public interface ICompanyDAO {
    Company selectCompanyInfomation(int customerId);
}
