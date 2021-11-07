package com.first.project.services;


import com.first.project.entities.car;
import com.first.project.entities.parameters;
import com.first.project.classes_for_requestbody.sell;
import com.first.project.entities.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
public class Request  {

    @Autowired

    car_services cars;

    private final  user_services users;


    @Autowired
    parameters_services parameters;

    @Autowired
    public Request(user_services users) {
        this.users = users;
    }






    public  Float getpara() throws InterruptedException {

        return  parameters.getbyname("profit");

    }
    public  Float getpara2() throws InterruptedException {

        return  parameters.getbyname("seatnumber");

    }




    //for user

/////

/**
    public Collection<user>  getAllUsers(){
        return user_services.getUsers();
    }
**/
    ////


public user getuser(String UserName){

    return users.getbyname(UserName);
}
    public boolean add_user(user user) throws UnsupportedEncodingException, NoSuchAlgorithmException {

    //    return user.getId();
        //int id=user.getId();
       //return id;
 //       return  users.founduser(id);


        if(!users.findbyname(user.getUsername())){
            users.add_new_user(user);
            return true;}
        else{return false;}

    }

    public boolean login_user(user user) throws Exception {

   return users.log_in_user(user);

    }












    ///for car


    public boolean  if_existcar(int id){

        try {
            if (cars.exist_dont_have_owner(id)) {
                return true;
            } else {

                return false;
            }
        }catch (Exception e){

            return false;
        }


    }


    public boolean add_new_car(car car) throws InterruptedException {


        if(car.getSeatnumber()==0) {

            int seats = (int) parameters.getbyname("seatnumber");


            if (seats == 0) { return false; }

            return cars.add_new(car,seats);

        }
        else {
            return cars.add_new(car,car.getSeatnumber());
        }


    }

    public boolean edit_all_exited_car( car car){

        //return true;
        return cars.edit_all_car(car);


    }

    public boolean edit_exited_car(int carid,car car) throws Exception {

    try {
        if (cars.exist_dont_have_owner(carid)) {
            cars.edit_car(carid,car);
            return true;
        } else {

            return false;
        }
    }catch (Exception e){

        return false;
    }
    }


    public boolean del_exited_car(int carid) {

        if(cars.foundcar(carid)){
            cars.del_car(carid);
            return true;
        }
        else{return false;}
    }

    public List<car> view_all_cars_not_sell() throws InterruptedException {

        float p=parameters.getbyname("profit");
        return cars.viewnotsell(p);

    }
/**
    public List<car> view_and_make_thread_for_selling_car(String jwtstring) throws InterruptedException {

        //make a thread

        float p=parameters.getbyname("profit");

        cars.makereadythreadforsell(jwtstring,p);


        //view
        System.out.println("END FUN !!");
        return view_all_cars_not_sell();

    }

    public boolean sell_car(String jwtstring,sell_class info) throws InterruptedException {

            return cars.realsell(jwtstring,info);

    }
**/




public boolean sell_car(sell info) throws Exception {




    return cars.sell(info.getCar_id(),info.getCustomer_name(),info.getPrice());

}










        ////for parameters

    public boolean add_parameters(parameters parameter){

        if(!parameters.foundparameter(parameter.getId())){
        return parameters.add_parameter(parameter);}
        else {
            return false;

        }

    }



    public boolean edit_parameters(parameters parameter) {
        if(parameters.foundparameter(parameter.getId())){
         return parameters.change_parameter(parameter);

        }
        return false;
    }

}