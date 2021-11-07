package com.first.project.services;


import com.first.project.classes_for_requestbody.sell;
import org.springframework.beans.factory.annotation.Autowired;
import com.first.project.entities.car;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class car_services  {



    @Autowired
      private repositoris repositoris;


    public boolean foundcar(Integer id){
        return repositoris.car_existsById(id);


    }






    /////////

    Map<String, sell> cars2sell=new HashMap<String,sell>();
  //  Map<String,String> trans_result=new HashMap<String,String>();






    /////////













    @Transactional(readOnly = true,isolation= Isolation.READ_COMMITTED)
    public boolean foundcar_by_id(int id){
        return repositoris.car_existsById(id);
    }

    @Transactional(isolation= Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED,readOnly = false,timeout = 5,rollbackFor = Exception.class)
    public boolean add_new(car car,int seatnumber){

        //if(car.getOwner_name()!=null  ){return true;}

        car.setSeatnumber(seatnumber);
        repositoris.car_save(car);

        return true;
    }

   // @Transactional(isolation= Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED,readOnly = false,timeout = 5,rollbackFor = Exception.class)
  /**  void add_from_edit(car car,int seatnumber){

        car.setSeatnumber(seatnumber);

        add_new(car, 0);

    }
**/
  @Transactional(isolation= Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED,readOnly = false,timeout = 5,rollbackFor = Exception.class)
  public void edit_car(int carid,car car){

      car c=car_geton(carid);

      if(car.getSeatnumber()==0){car.setSeatnumber(c.getSeatnumber());}

      if(car.getName().equals(" ")){car.setName(c.getName());}
      if(car.getPrice()==0){car.setPrice(c.getPrice());}

        del_car(carid);
        add_new(car,car.getSeatnumber());
    }

    void add_from_edit(car car){

    }

    @Transactional(isolation= Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED,readOnly = false,timeout = 5,rollbackFor = Exception.class)
    public car car_geton(int carid){
      return repositoris.car_getOne(carid);
    }

    @Transactional(isolation= Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED,readOnly = false,timeout = 5,rollbackFor = Exception.class)
    public boolean edit_all_car(car car){

        //return true;
        String name=car.getName();
        List<car> list_car_from_data=repositoris.car_findAll();

        List<car> temp = new ArrayList<>();

        List<car> temp_to_del = new ArrayList<>();

        boolean change=false;
        for (car c:list_car_from_data){
            //return true;
            if(c.getName().equals(name)){
                //return true;
                //car.setSeatnumber(c.getSeatnumber());
                //car.setId(c.getId());
                c.setPrice(car.getPrice());

                temp.add(c);

                temp_to_del.add(c);
                change=true;
                //return false;
            }

        }

        if(change) {


            repositoris.car_deleteall(temp_to_del);
            repositoris.car_saveall(temp);
        return true;
        }
        return false;

    }

    car newexpectedprice(car car,float profit){

        float defualt_price;
        defualt_price= (car.getPrice())+((car.getPrice())*profit)/100;
        car.setPrice(defualt_price);
        return car;
    }

    @Transactional(isolation= Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED,readOnly = false,timeout = 5,rollbackFor = Exception.class)
    public void del_car(int carid)
    {
        repositoris.car_deleteById(carid);

    }

    @Transactional(isolation= Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED,readOnly = true,timeout = 5,rollbackFor = Exception.class)
    public List<car> viewnotsell(float profit){

        List<car> list_car_from_data=repositoris.car_findAll();

        List<car> temp = new ArrayList<>();

        for (car c:list_car_from_data){

            if(c.getOwner_name().equals(" ")){

                c=newexpectedprice(c,profit);

                temp.add(c);
            }
        }

        return temp;

    }





    @Transactional(isolation= Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED,readOnly = false,timeout = 5,rollbackFor = Exception.class)
    public boolean sell(int car_id, String customer_name, float price) throws Exception {


        //sell


            if (exist_dont_have_owner(car_id)) {

                car c = repositoris.car_getOne(car_id);
                c.setOwner_name(customer_name);
              //  c.setDefault_price(c.getPrice() + ((c.getPrice() * sale_percentage) / 100));
                c.setDate(11); ////new date
                c.setPrice(price);
                return true;
            }
            else {return false;}


    }

    @Transactional(isolation= Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED,readOnly = true,timeout = 5)
    public boolean exist_dont_have_owner(int id) throws Exception {


        if(!repositoris.car_exist_dont_have_owner(id)){
            return false;
        }
        else {
            return true;
        }

  }



/**
    @Transactional(isolation= Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED,readOnly = false,timeout = 20,rollbackFor = Exception.class)
    @Async
    public void makereadythreadforsell(String jwtstring,float sale_percentage) throws InterruptedException {

        System.out.println("start thread"+jwtstring);
        while (!cars2sell.containsKey(jwtstring)){
        Thread.sleep(500);}


        sell_class car= cars2sell.get(jwtstring);
        cars2sell.remove(jwtstring);

        sell(car.getCar_id(),car.getDate(),car.getCustomer_name(),car.getPrice(),sale_percentage);


    }



    public boolean realsell(String jwtstring,sell_class info) throws InterruptedException {

//        trans_result.put(jwtstring,"ready");
        cars2sell.put(jwtstring,info);
        Thread.sleep(2000);


        if(!(cars2sell.containsKey(jwtstring))){
            return true;
        }
        else{

            cars2sell.remove(jwtstring);
            return false;

        }

    }
**/


}
