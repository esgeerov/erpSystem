package com.samir.erpSystem.repository;



import com.samir.erpSystem.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientRepositry extends JpaRepository<Client, Long> {
        List<Client> findAllByActive(Integer active);
}
