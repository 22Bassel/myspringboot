package com.first.project.services;

import com.first.project.entities.car;
import com.first.project.entities.parameters;
import com.first.project.entities.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service

public class repositoris {

    @Autowired
    com.first.project.repositories.repository_car repository_car;



 private final com.first.project.repositories.repository_user repository_user;


    @Autowired
    public repositoris(com.first.project.repositories.repository_user repository_user) {
        this.repository_user = repository_user;
    }


    @Autowired
    com.first.project.repositories.repository_parameter repository_parameter;
    @Autowired
    com.first.project.repositories.repository_Request repository_request;



    /// for car





    public boolean car_existsById(int id){

      return   repository_car.existsById(id);

    }


    public void car_save(car car){

        repository_car.save(car);

    }

    public void car_deleteById(int id){
        repository_car.deleteById(id);

    }

    public List<car> car_findAll(){
        return (List<car>) repository_car.findAll();
    }

//    public car car_getOne(int car_id){return repository_car.getOne(car_id); }




    public car car_getOne(int car_id) {

        car c=repository_car.getOne(car_id);

        if(c!=null){
                return c;

            }

        return null;
    }




    public void car_deleteall(List<car> cars){

        repository_car.deleteAll(cars);

    }

    public void car_saveall(List<car> cars){
        repository_car.saveAll(cars);
    }






    public boolean car_exist_dont_have_owner(int id) throws Exception {

        if(repository_car.existsById(id)) {
            car a_car = repository_car.getOne(id);


            if (a_car.getOwner_name().equals(" ")) {
                return true;
            } else {
                throw new Exception("it has an owner");
            }

        }
        else {
            return false;
        }
    }








    //for user
///////



    public Collection<user> getAllUsers() {
        return repository_user.findAll();
    }

/**
    public user create(UserCreateForm form) {
        user user = new user();
        user.getUsername(form.getName());
        user.setPassword(new BCryptPasswordEncoder().encode(form.getPassword()));
        user.setRoleId(form.getRole());
        return repository_user.save(user);
    }
**/
    ///////


//    public user user_getOne(String name){
//        return repository_user.getOne(name);
//    }

    public boolean user_existsById(Integer id ){
        return true;
        //return repository_user.existsById(id);
    }

    public void user_save(user user)
    {

        repository_user.save(user);

    }


    public user user_getOne(String username) {

        List<user> alluser= (List<user>) repository_user.findAll();

        for( user u:alluser){
            if(u.getUsername().equals(username)){
                return u;

            }
        }
        return null;
    }

    public boolean user_existbyname(String name){

        List<user> alluser= (List<user>) repository_user.findAll();

        for( user u:alluser){
            if(u.getUsername().equals(name)){
                return true;

            }
        }
        return false;

    }












    ///for parameters



    public boolean parameter_existsById(String id){
        return repository_parameter.existsById(id);
    }

    public boolean parameter_save(parameters parameter){
        repository_parameter.save(parameter);
        return true;
    }

    public void parameter_deleteById(String id){
        repository_parameter.deleteById(id);
    }

    public parameters parameter_getOne(String id) {

       parameters p=repository_parameter.getOne(id);
        if(p==null){
        return null;}
        else {
            return p;
        }
/**
        return new parameters("parameters p",4);**/
    }

//    public parameters parameter_getOne(String key){
//        return repository_parameter.getOne(key);
//    }

    }

