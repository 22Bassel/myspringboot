package com.first.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import com.first.project.entities.parameters;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service

public class parameters_services {

    @Autowired
    repositoris repositoris;

    @Transactional(isolation= Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED,readOnly = true,timeout = 5,rollbackFor = Exception.class)
    public  boolean foundparameter(String id){
        return repositoris.parameter_existsById(id);
    }

    @Transactional(isolation= Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED,readOnly = false,timeout = 5,rollbackFor = Exception.class)
    public  boolean change_parameter(parameters parameters){
        delete_parameter(parameters.getId());
        add_parameter(parameters);
        return true;
    }

    @Transactional(isolation= Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED,readOnly = false,timeout = 5,rollbackFor = Exception.class)
    public boolean add_parameter(parameters parameters){
         return repositoris.parameter_save(parameters);
        //return true;
    }

    @Transactional(isolation= Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED,readOnly = false,timeout = 5,rollbackFor = Exception.class)
    void delete_parameter(String id){
        repositoris.parameter_deleteById(id);
    }



   @Cacheable(value = "parameters",key = "'parameter'+#name")
    public float getbyname(String name) throws InterruptedException {
        Thread.sleep(1000);
        parameters parameters= repositoris.parameter_getOne(name);
        if(parameters==null){return 0;}
        return parameters.getValue();
    }
}
