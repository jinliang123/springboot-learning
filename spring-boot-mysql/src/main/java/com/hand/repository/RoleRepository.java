package com.hand.repository;

import com.hand.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by jinliang on 2017/9/23.
 * 角色 Repository
 */
@Repository
public interface RoleRepository extends JpaRepository<Role,Long>{
}
