/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elearningbackend.service;

import com.elearningbackend.utility.Paginator;
import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author c1508l3694
 */
public abstract class AbstractUserService<D, K extends Serializable, E> extends AbstractService<D, K, E>{
    public AbstractUserService(JpaRepository<E, K> repository, Paginator<E, D> paginator) {
        super(repository, paginator);
    }
}