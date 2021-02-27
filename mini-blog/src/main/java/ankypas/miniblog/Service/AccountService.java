package ankypas.miniblog.Service;

import ankypas.miniblog.Dao.Account;

public interface AccountService{
    int loginByName(String name, String password);
    int loginByPhone(String phone, String password);
    int register(Account account);
    String getUserNameByUserId(int userId);
    boolean editPassword(String password, String name);
}
