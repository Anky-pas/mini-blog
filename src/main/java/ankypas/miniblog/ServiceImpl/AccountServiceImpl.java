package ankypas.miniblog.ServiceImpl;

import ankypas.miniblog.Dao.User;
import ankypas.miniblog.Dao.UserOverview;
import ankypas.miniblog.Mapper.UserMapper;
import ankypas.miniblog.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService{

    @Autowired
    private UserMapper userMapper;

    /**
     * 通过用户名登录
     * @param name
     * @param password
     * @return
     */
    @Override
    public int loginByName(String name, String password) {
        try{
            if(userMapper.countByNameAndPassword(name, password) == 0) return -1;
            int id = userMapper.getUserIdByNameAndPassword(name, password);
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
            if(userMapper.countByPhoneAndPassword(phone, password) == 0) return -1;
            int id = userMapper.getUserIdByPhoneAndPassword(phone, password);
            return id;
        }catch (Exception e){
            return -1;
        }
    }

    @Override
    public UserOverview getUserOverviewByUserId(int userId) {
        try{
            return userMapper.getUserOverviewByUserId(userId);
        }catch (Exception e){
            return null;
        }
    }

    /**
     * 注册
     * @param user
     * @return
     * 0：注册成功
     * 1：用户名已被使用
     * 2：手机号已被绑定
     * 3：用户名已被使用、手机号已被绑定
     */
    @Override
    public int register(User user){
        int nameTag = userMapper.countByName(user.getName());
        int phoneTag = userMapper.countByPhone(user.getPhone());
        if((nameTag == 0) && (phoneTag == 0)){
            userMapper.add(user);
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
            userMapper.editPassword(password,name);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public String getUserNameByUserId(int userId) {
        try{
            if(userMapper.countByUserId(userId) == 0) return null;
            return userMapper.getUserNameByUserId(userId);
        }catch (Exception e){
            return null;
        }
    }
}
