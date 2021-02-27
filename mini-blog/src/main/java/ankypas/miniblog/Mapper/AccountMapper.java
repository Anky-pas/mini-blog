package ankypas.miniblog.Mapper;

import ankypas.miniblog.Dao.Account;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface AccountMapper {
    int countByName(String name);
    int countByPhone(String phone);
    int countByUserId(int userId);
    int countByNameAndPassword(String name, String password);
    int countByPhoneAndPassword(String phone, String password);
    int getUserIdByNameAndPassword(String name, String password);
    int getUserIdByPhoneAndPassword(String phone, String password);
    String getUserNameByUserId(int userId);
    void add(Account account);
    void editPassword(String password, String userName);
}
