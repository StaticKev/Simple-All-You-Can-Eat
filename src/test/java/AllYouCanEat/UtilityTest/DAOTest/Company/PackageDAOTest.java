package AllYouCanEat.UtilityTest.DAOTest.Company;

import AllYouCanEat.Entity.Company.Package;
import AllYouCanEat.Repository.CompanyDAO.PackageDAO;
import AllYouCanEat.Utility.MyConnection;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;
import java.util.List;

public class PackageDAOTest {

    @Test
    void packageDAOTest() {

        DataSource dataSource = MyConnection.getDataSource();

        PackageDAO packageDAO = new PackageDAO(dataSource);

        List<Package> packages = packageDAO.retrieveValues();

        System.out.println("Record retrieved = " + packages.size());

        for (Package p : packages) {
            System.out.println(
                    "Package Type = " + p.packageType()
                            + "; Price = " + p.price() +
                            "; Person Count = " + p.personCount()
            );
        }

    }

}
