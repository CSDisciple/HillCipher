package com.csdisciple.hill_cipher;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@RestController
@RequestMapping("/cipher")
class MainController {
    @Autowired
    private HillCipherService hillCipherService;
    Logger logger = Logger.getLogger(MainController.class.getName());
    @GetMapping(value = "/encrypt", produces = "application/json")

    public @ResponseBody ResponseTransfer getEncryptedMessage(@RequestParam String message) {
        String response =  "";
        try{
            response = hillCipherService.encrypt(message);
        }catch(Exception e){

        }
        return new ResponseTransfer(response);
    }

    @GetMapping(value = "/decrypt", produces = "application/json")

    public @ResponseBody ResponseTransfer getEncryptedMessageNumber(@RequestParam String message) {
        String response =  "";
        try{
           response = hillCipherService.decrypt(message);
        }catch(Exception e){
            throw new RuntimeException(e);
        }
        return new ResponseTransfer(response);
    }

    @GetMapping(value = "/generatekey", produces = "application/json")

    public @ResponseBody ResponseTransfer getRandomKey(@RequestParam int size) throws Exception {
        return new ResponseTransfer(hillCipherService.generateRandomKey(size));
    }

}
