package sql.dao;

import sql.models.Customer;

public interface ICustomerDAO {
    Customer selectCustomerInformation(int customerId);
}
