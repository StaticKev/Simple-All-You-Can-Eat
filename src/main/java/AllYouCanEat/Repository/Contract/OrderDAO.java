package AllYouCanEat.Repository.Contract;

import AllYouCanEat.Entity.Contract.Staff;

public interface OrderDAO<T extends Staff> {

    int insertItem(T item);

}