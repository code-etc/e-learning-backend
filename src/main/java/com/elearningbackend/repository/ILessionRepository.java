package com.elearningbackend.repository;

import com.elearningbackend.entity.Lession;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ILessionRepository extends CrudRepository<Lession, String>{
}
