package com.bilgeadam.repository;

import com.bilgeadam.dto.request.ManagerRegisterAddressRequestDto;
import com.bilgeadam.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAddressRepository extends JpaRepository<Address,Long> {


}
