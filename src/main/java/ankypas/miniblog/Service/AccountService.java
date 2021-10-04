package ankypas.miniblog.Service;

import ankypas.miniblog.Dao.User;
import ankypas.miniblog.Dao.UserOverview;

public interface AccountService{
    int loginByName(String name, String password);
    int loginByPhone(String phone, String password);
    int register(User user);
    String getUserNameByUserId(int userId);
    UserOverview getUserOverviewByUserId(int userId);
    boolean editPassword(String password, String name);
}
