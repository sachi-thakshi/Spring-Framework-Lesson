package lk.ijse.gdse.controller;

import jakarta.servlet.annotation.MultipartConfig;
import lk.ijse.gdse.dto.CustomerDTO;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
@MultipartConfig
public class CustomerController {
    // save customer - form data -> x-www-form-urlencoded
    // CID -> Customer ID
    // CName -> Customer Name
    // CAddress -> Customer Address
    @PostMapping
    public String saveCustomer(@ModelAttribute CustomerDTO customerDTO){
        System.out.println(customerDTO.getCID());
        System.out.println(customerDTO.getCName());
        System.out.println(customerDTO.getCAddress());
        return "Customer Saved";
    }

    // save customer -> query parameter
    @PostMapping(params = {"CID","CName","CAddress"})
    public String saveCustomerQueryParam(@RequestParam ("CID")String CID,
                                         @RequestParam ("CName")String CName,
                                         @RequestParam ("CAddress")String CAddress){
        System.out.println(CID);
        System.out.println(CName);
        System.out.println(CAddress);
        return "Customer Saved";
    }

    //save customer -> path variable
    @GetMapping(path = "saveWithPathVariable/{CID}/{CName}/{CAddress}")
    public String saveCustomerPathVariables(@PathVariable ("CID")String CID,
                                            @PathVariable ("CName")String CName,
                                            @PathVariable ("CAddress")String CAddress){
        System.out.println(CID);
        System.out.println(CName);
        System.out.println(CAddress);
        return "Customer Saved ";
    }

    // save customer -> JSON
    @PostMapping(path = "saveWithJSON" ,consumes = MediaType.APPLICATION_JSON_VALUE)
    public String saveCustomerWithJSON(@RequestBody CustomerDTO customerDTO){
        System.out.println(customerDTO.getCID());
        System.out.println(customerDTO.getCName());
        System.out.println(customerDTO.getCAddress());
        return "Customer Saved with JSON";
    }

    // return JSON Object
    @GetMapping(path = "getCustomer" ,produces = MediaType.APPLICATION_JSON_VALUE)
    public CustomerDTO getCustomer(){
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setCID("2");
        customerDTO.setCName("Tharusha");
        customerDTO.setCAddress("Galle");

        System.out.println(customerDTO.getCID());
        System.out.println(customerDTO.getCName());
        System.out.println(customerDTO.getCAddress());
        return customerDTO;
    }
}
