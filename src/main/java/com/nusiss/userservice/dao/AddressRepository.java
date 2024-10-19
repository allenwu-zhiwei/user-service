package com.nusiss.userservice.dao;

import com.nusiss.userservice.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer> {
    // 这里可以定义自定义查询方法（如果需要）
}