package com.hand.repository;

import com.hand.domain.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by jinliang on 2017/9/23.
 * 部门 repository
 */
@Repository
public interface DepartmentRepository extends JpaRepository<Department,Long> {
}
