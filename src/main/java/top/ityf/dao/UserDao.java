package top.ityf.dao;

import top.ityf.domain.User;
import top.ityf.domain.queryVo;

import java.util.List;

/**
 * ClassName:UserDao
 * Package: top.ityf.dao
 * Description: 用户的持久层接口
 *
 * @Date: 2019/11/19 8:27
 * @Author: YanFei
 */
public interface UserDao {
    /**
     * 查询所有用户
     * */
    List<User> findAll();

    /**
     * 保存用户
     * */
    void saveUser(User user);

    /**
     * 更新用户
     * */
    void updateUser(User user);

    /**
     * 根据ID删除用户
     * */
    void deleteUser(Integer userId);

    /**
     * 根据Id查询用户
     * */
    User findById(Integer userId);

    /**
     * 根据名称模糊查询用户信息
     * */
    List<User> findByName(String username);

    /**
     * 查询总用户数
     * */
    int findTotal();

    /**
     * 根据queryVo中的条件模糊查询用户
     * */
    List<User> findUserByVo(queryVo vo);
}
