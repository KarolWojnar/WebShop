package com.webshop.Repository;

import com.webshop.Model.Code;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CodeRepository extends CrudRepository<Code, Long> {
    Optional<Code> findByCode(String name);
}
