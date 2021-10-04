package ankypas.miniblog.Mapper;

import ankypas.miniblog.Dao.User;
import ankypas.miniblog.Dao.UserOverview;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserMapper {
    int countByName(String name);
    int countByPhone(String phone);
    int countByUserId(int userId);
    int countByNameAndPassword(String name, String password);
    int countByPhoneAndPassword(String phone, String password);
    int getUserIdByNameAndPassword(String name, String password);
    int getUserIdByPhoneAndPassword(String phone, String password);
    String getUserNameByUserId(int userId);
    UserOverview getUserOverviewByUserId(int userId);
    void add(User user);
    void editPassword(String password, String userName);
}
