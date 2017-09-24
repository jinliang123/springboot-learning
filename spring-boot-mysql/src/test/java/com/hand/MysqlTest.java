package com.hand;



import com.hand.domain.Department;
import com.hand.domain.Role;
import com.hand.domain.User;
import com.hand.repository.DepartmentRepository;
import com.hand.repository.RoleRepository;
import com.hand.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import java.util.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {JpaConfiguration.class})
public class MysqlTest {
    private static Logger logger = LoggerFactory.getLogger(MysqlTest.class);

    @Autowired
    UserRepository userRepository;
    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    RoleRepository roleRepository;

    @Before
    public void initData(){
        userRepository.deleteAll();
        roleRepository.deleteAll();
        departmentRepository.deleteAll();

        Department department = new Department();
        department.setName("开发部");
        departmentRepository.save(department);
        Assert.notNull(department.getId(),"[Assertion failed] - this argument is required; it must not be null");

        Role role = new Role();
        role.setName("admin");
        roleRepository.save(role);
        Assert.notNull(role.getId(),"[Assertion failed] - this argument is required; it must not be null");

        User user = new User();
        user.setName("user");
        user.setCreatedate(new Date());
        user.setDepartment(department);

        List<Role> roles = roleRepository.findAll();
        Assert.notNull(roles,"[Assertion failed] - this argument is required; it must not be null");
        user.setRoles(roles);

        userRepository.save(user);
        Assert.notNull(user.getId(),"[Assertion failed] - this argument is required; it must not be null");
    }

    @Test
    public void findPage(){
        Pageable pageable = new PageRequest(0, 10, new Sort(Sort.Direction.ASC, "id"));
        Page<User> page = userRepository.findAll(pageable);
        Assert.notNull(page,"[Assertion failed] - this argument is required; it must not be null");
        for(User user : page.getContent()) {
            logger.info("====user==== user name:{}, department name:{}, role name:{}",
                    user.getName(), user.getDepartment().getName(), user.getRoles().get(0).getName());
        }
    }

    //@Test
    public void test(){
        User user1 = userRepository.findByNameLike("u%");
        Assert.notNull(user1,"[Assertion failed] - this argument is required; it must not be null");

        User user2 = userRepository.readByName("user");
        Assert.notNull(user2,"[Assertion failed] - this argument is required; it must not be null");

        List<User> users = userRepository.getByCreatedateLessThan(new Date());
        Assert.notNull(users,"[Assertion failed] - this argument is required; it must not be null");
    }
}