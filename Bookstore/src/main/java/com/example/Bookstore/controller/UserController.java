package com.example.Bookstore.controller;

import com.example.Bookstore.constants.UserType;
import com.example.Bookstore.dto.AuthDTO;
import com.example.Bookstore.dto.SubscriptionDTO;
import com.example.Bookstore.dto.UserCreationDTO;
import com.example.Bookstore.dto.UserDTO;
import com.example.Bookstore.exceptions.ApiExceptionResponse;
import com.example.Bookstore.functionalities.EmailService;
import com.example.Bookstore.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/user")
@CrossOrigin
@Validated
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

    public static final String ACCOUNT_SID = "AC0fe25a2b504b9e15e3f7b3b04feb385b";
    public static final String AUTH_TOKEN = "e20d9a3dcdd3295202e548c694812eb2";

    @Operation(summary = "Get a list of all users")
    @GetMapping("/getAll")
    public ResponseEntity getAllUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findAll());
    }

    @Operation(summary = "Get a user by id")
    @GetMapping("/getById")
    public ResponseEntity getUserById(@RequestParam Long id){
        return ResponseEntity.status(HttpStatus.OK).body(userService.findUserByID(id));
    }

    @Operation(summary = "Get a user by email")
    @GetMapping("/getByEmail")
    public ResponseEntity getUserByEmail(@RequestParam String email) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findUserByEmail(email));
    }

    @Operation(summary = "Get if a user is subscribed or not")
    @GetMapping("/getSubscription")
    public ResponseEntity getSubscription(@RequestParam String email) {
        try {
            boolean isSubscribed = userService.isUserSubscribed(email);
            return ResponseEntity.ok(isSubscribed);
        } catch (Exception e) {
            // Log the error
            e.printStackTrace();
            // Return an appropriate error response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error fetching subscription status");
        }
    }

    @Operation(summary = "Save a user when the account is created")
    @PostMapping("/save")
    public ResponseEntity saveNewUser(@Valid @RequestBody UserCreationDTO user) throws ApiExceptionResponse {
        user.setType(UserType.CLIENT);
        String body = "User "+ user.getEmail() + " has created a new account at " + LocalDateTime.now();
        emailService.sendEmail("zaharieoanadenisa@gmail.com", "Sing Up Confirmation", body);
        return ResponseEntity.status(HttpStatus.OK).body(userService.saveUser(user));
    }

    @Operation(summary = "Update the subscription of a user")
    @PutMapping("/subscribe")
    public ResponseEntity updateSubscription(@RequestBody SubscriptionDTO dto) {
        userService.setSubscribe(dto.getEmail(), dto.isOk());
        return ResponseEntity.status(HttpStatus.OK).body("Subscription updated");
    }

    @PutMapping("/changePass")
    public ResponseEntity changePassword(@RequestBody AuthDTO user){
        UserDTO dto = userService.changePassword(user.getEmail(), user.getPassword());

//        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
//        Message.creator(
//                        new PhoneNumber("+40745882928"),
//                        new PhoneNumber("+18507265957"),
//                "Your password was successfully changed to \" + user.getPassword()")
//                        .create();

        return ResponseEntity.status(HttpStatus.OK).body("Password changed");
    }

    @PutMapping("/addBook")
    public ResponseEntity addBookToCart(@RequestParam String email, @RequestParam String title) {
        userService.addBookToCart(email, title);
        return ResponseEntity.status(HttpStatus.OK).body("Book added to cart");
    }

    @PutMapping("removeBook")
    public ResponseEntity removeBookFromCart(@RequestParam String email, @RequestParam String title) {
        userService.removeBookFromCart(email, title);
        return ResponseEntity.status(HttpStatus.OK).body("Book removed from cart");
    }

    @GetMapping("/getCart")
    public ResponseEntity getCart(@RequestParam String email) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getCart(email));
    }

    @GetMapping("/getTotal")
    public ResponseEntity getTotal(@RequestParam String email) throws ApiExceptionResponse {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getTotal(email));
    }

    @Operation(summary = "Delete a user (set it as inactive)")
    @PutMapping("/delete")
    public ResponseEntity deleteUser(@RequestBody UserDTO user){
        userService.deleteUser(user);
        return ResponseEntity.status(HttpStatus.OK).body("User deleted");
    }

    @PutMapping("/logout")
    public ResponseEntity logout(@RequestParam String email) {
        userService.logout(email);
        return ResponseEntity.status(HttpStatus.OK).body("Log out successfully");
    }

}
