package top.ityf.test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import top.ityf.dao.UserDao;
import top.ityf.domain.User;
import top.ityf.domain.queryVo;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 * ClassName:MybatisTest
 * Package: top.ityf.test
 * Description: 测试mybatis的CRUD操作
 *
 * @Date: 2019/11/19 8:33
 * @Author: YanFei
 */
public class MybatisTest {
    private InputStream in;
    private SqlSession sqlSession;
    private UserDao userDao;

    @Before   //用于在测试方法执行之前执行
    public void init() throws Exception {
        //1、读取配置文件，生成字节输入流
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2、获取SqlSessionFactory对象
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        //3、获取SqlSession对象
        sqlSession = factory.openSession();
        //4、获取dao的代理对象
        userDao = sqlSession.getMapper(UserDao.class);
    }

    @After   //用于在测试方法执行之后执行
    public void destroy() throws Exception {
        //提交事务
        sqlSession.commit();
        //释放资源
        sqlSession.close();
        in.close();
    }

    /**
     * 测试查询所有
     */
    @Test
    public void testFindAll() throws Exception {
        List<User> users = userDao.findAll();
        for (User user : users) {
            System.out.println(user);
        }

    }


    /**
     * 测试保存操作
     */
    @Test
    public void testSave() {
        User user = new User();
        user.setUsername("小龙女");
        user.setAddress("上海市浦东新区");
        user.setSex("女");
        user.setBirthday(new Date());
        System.out.println("保存操作之前：" + user);
        userDao.saveUser(user);
        System.out.println("保存操作之后：" + user);

    }

    /**
     * 测试更新操作
     */
    @Test
    public void testUpdate() {
        User user = new User();
        user.setId(8);
        user.setUsername("mybatis updateuser");
        user.setAddress("上海市青浦区");
        user.setSex("女");
        user.setBirthday(new Date());

        userDao.updateUser(user);

    }

    /**
     * 测试删除操作
     */
    @Test
    public void testDelete() {
        userDao.deleteUser(4);
    }


    /**
     * 测试根据id查询用户操作
     */
    @Test
    public void testFindOne() {
        User user = userDao.findById(8);
        System.out.println(user);
    }


    /**
     * 测试根据name模糊查询用户操作
     */
    @Test
    public void testFindByName() {
        //方法一，采用占位符(#{})的方法，那就要在这里提供百分号。
//        List<User> users=userDao.findByName("%王%");
        //方法二，采用字符串拼接(${})的方法。省略百分号
        List<User> users = userDao.findByName("王");
        for (User user : users)
            System.out.println(user);
    }


    /**
     * 测试查询总记录条数的操作
     */
    @Test
    public void testFindTotal() {
        //聚合查询
        int count = userDao.findTotal();
        System.out.println(count);
    }


    /**
     * 测试根据queryVo作为查询条件的模糊查询用户操作
     */
    @Test
    public void testFindByVo() {
        queryVo vo = new queryVo();
        User user = new User();
        user.setUsername("%王%");
        vo.setUser(user);
        List<User> users = userDao.findUserByVo(vo);
        for (User u : users)
            System.out.println(u);
    }
}
