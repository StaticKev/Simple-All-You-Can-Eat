package AllYouCanEat.Repository.Contract;

import AllYouCanEat.Entity.Contract.Company;

import java.util.List;

public interface CompanyRecord<T extends Company> {

    List<T> retrieveValues();

}
