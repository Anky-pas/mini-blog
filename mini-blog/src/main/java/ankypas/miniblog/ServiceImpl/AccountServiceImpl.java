package ankypas.miniblog.ServiceImpl;

import ankypas.miniblog.Dao.Account;
import ankypas.miniblog.Mapper.AccountMapper;
import ankypas.miniblog.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService{

    @Autowired
    private AccountMapper accountMapper;

    /**
     * 通过用户名登录
     * @param name
     * @param password
     * @return
     */
    @Override
    public int loginByName(String name, String password) {
        try{
            if(accountMapper.countByNameAndPassword(name, password) == 0) return -1;
            int id = accountMapper.getUserIdByNameAndPassword(name, password);
            return id;
        }catch (Exception e){
            return -1;
        }
    }

    /**
     * 通过手机号登录
     * @param phone
     * @param password
     * @return
     */
    @Override
    public int loginByPhone(String phone, String password) {
        try{
            if(accountMapper.countByPhoneAndPassword(phone, password) == 0) return -1;
            int id = accountMapper.getUserIdByPhoneAndPassword(phone, password);
            return id;
        }catch (Exception e){
            return -1;
        }
    }

    /**
     * 注册
     * @param account
     * @return
     * 0：注册成功
     * 1：用户名已被使用
     * 2：手机号已被绑定
     * 3：用户名已被使用、手机号已被绑定
     */
    @Override
    public int register(Account account){
        int nameTag = accountMapper.countByName(account.getName());
        int phoneTag = accountMapper.countByPhone(account.getPhone());
        if((nameTag == 0) && (phoneTag == 0)){
            accountMapper.add(account);
            return 0;
        }
        else if((nameTag == 1) && (phoneTag == 0)){
            return 1;
        }
        else if((nameTag == 0) && (phoneTag == 1)){
            return 2;
        }
        else{
            return 3;
        }
    }

    @Override
    public boolean editPassword(String password, String name) {
        try{
            accountMapper.editPassword(password,name);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public String getUserNameByUserId(int userId) {
        try{
            if(accountMapper.countByUserId(userId) == 0) return null;
            return accountMapper.getUserNameByUserId(userId);
        }catch (Exception e){
            return null;
        }
    }
}
