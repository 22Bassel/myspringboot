package com.first.project.controllers;

import com.first.project.classes_for_requestbody.authresponse;
import com.first.project.classes_for_requestbody.car_for_edit;
import com.first.project.entities.parameters;
import com.first.project.classes_for_requestbody.sell;
import com.first.project.entities.user;
import com.first.project.services.MyUserDetailsService;
import com.first.project.services.jwt;
import com.first.project.entities.car;
import com.first.project.services.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;


@Controller
//@RequestMapping(value = "/cars")
public class main_controller {

    /**
     * DYAA
     */
    @Autowired
    private Request service;


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailsService UserDetailsService;

    @Autowired
    private jwt jwtU;






    @GetMapping("/backtomain")
    public String back_to_main(){
        return "main";
    }

    @GetMapping(value = "/")
    public String main(Model model){
        model.addAttribute("user",new user());
        return "login";
    }


      @PostMapping(value = "/auth")
    public String createauth(@ModelAttribute user a, Model m, HttpServletResponse response) throws Exception{

        try {

            ////////////////////////////////////////

            service.login_user(a);

            /**authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(a.getUsername(), a.getPassword())
            );**/
        } catch (BadCredentialsException e) {
            throw new Exception("incorrect");
        }
        final UserDetails userDetails= UserDetailsService.loadUserByUsername(a.getUsername());

        final String jwt =jwtU.generatetoken(userDetails);

         ResponseEntity.ok(new authresponse(jwt));

         String token="Bearer "+jwt;
         System.out.println(token);
         Cookie cookie =new Cookie("Authorization" ,jwtU.generatetoken(userDetails));
            cookie.setSecure(false);
            cookie.setMaxAge(60*60*24);
            cookie.setHttpOnly(true);

         response.addCookie(cookie);


    return "main";
    }










    //for user




/////////////////////////////////////
/////// show
////////////////////////////////////




    @GetMapping("/showadduser")
    public String show_add_user(Model model){
        model.addAttribute("user",new user());
        return "users/adduser";

    }






/////////////////////////////////////
///////do
/////////////////////////////////////






    @PostMapping("/checkadduser")
    public String add_user(@ModelAttribute user user) throws  UnsupportedEncodingException, NoSuchAlgorithmException {
        //return user;

        if(service.add_user(user)){
            return "done";
        }
        else {
            return "wrong";
        }
    }










    //for car



    //////////////////////////////////////
    //show
    /////////////////////////////////////





    @GetMapping("/showaddcar")
    public String show_add_car(Model model){
        model.addAttribute("car",new car());
        return "cars/addcar";

    }

    @GetMapping("/showdelcar")
    public String show_del_car(Model model){
        model.addAttribute("car",new car());
        return "cars/delcar";

    }


    @GetMapping("/showeditecar")
    public String show_edite_car(Model model){
        model.addAttribute("car",new car_for_edit());
        return "cars/editecar";

    }

    @PostMapping("/showsellcar")
    public String show_sell_car(@RequestParam int id,Model model){
        sell s=new sell();

        s.setCar_id(id);

        model.addAttribute("info",s);

        return "cars/sellcar";

    }





    //////////////////////
    //do
    /////////////////////


    @PostMapping("/checkdeletecar")
    public String del_car(@ModelAttribute car car) {

        if(service.del_exited_car(car.getId())){
            return "done";
        }
        else {
            return "wrong";
        }
    }





    @PostMapping("/checkaddcar")
    public String add_car(@ModelAttribute car car) throws InterruptedException {

         if(service.add_new_car(car)){

             return "done";
         }
         else {
             return "wrong";
         }
    }


    /**
    @PostMapping("/checkedit_allcar_name")
    public boolean edit_car(@RequestBody car car ){
        return service.edit_all_exited_car(car);
    }

     **/

    @PostMapping("/checkeditcar")
    public String edit_car(@ModelAttribute car_for_edit car) throws Exception {

        car c=new car();
        c.setName(car.getName());
        c.setPrice(car.getPrice());
        c.setSeatnumber(car.getSeatnumber());

        if(service.edit_exited_car(car.getId(),c)){
            return "done";
        }
        else {
            return "wrong";
        }


    }






@GetMapping("/viewcars2sell")
public String viewcars(Model model) throws InterruptedException {
    List<car> allcars= service.view_all_cars_not_sell();

    model.addAttribute("allcars",allcars);
    return "/cars/viewallcars";
}






    @PostMapping("/checksellcarreal")
    public String sell_car(@ModelAttribute sell info,@RequestParam int id) throws Exception {

        info.setCar_id(id);
        System.out.println("id"+info.getCar_id());
       try {

            if(service.sell_car(info)){
                return "done";
            }
            else {
                return "wrong";
            }

    }catch (Exception e){

    return e.toString();
    }
    }












    ///for parameters

    @GetMapping("/showeditaparameter")
    public String show_edit_parameter(Model model){
        model.addAttribute("parameter",new parameters());
        return "/parameters/editparameter";

    }

    @GetMapping("/showaddaparameter")
    public String show_add_parameter(Model model){
        model.addAttribute("parameter",new parameters());
        return "/parameters/addparameter";
}

    @PostMapping("/addparameters")
    public String add_parameter(@ModelAttribute parameters parameter) {
        if(service.add_parameters(parameter)){
            return "done";

        }else {

            return "wrong";

        }
    }


    @PostMapping("/editparameters")
    public String edit_parameter(@ModelAttribute parameters parameter) {
         if(service.edit_parameters(parameter)){

             return "done";
         }
         else {
             return "wrong";
         }

    }



}
