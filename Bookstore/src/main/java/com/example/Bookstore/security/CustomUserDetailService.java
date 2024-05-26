//package com.example.Bookstore.security;
//
//import com.example.Bookstore.repository.UserRepository;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//@Service
//public class CustomUserDetailService implements UserDetailsService {
//
//    private final UserRepository userRepository;
//
//    public CustomUserDetailService(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        final com.example.Bookstore.model.User userAuthFromDB = userRepository.findFirstByEmail(email);
//        if (userAuthFromDB == null) {
//            throw new UsernameNotFoundException(email);
//        }
//
//        UserDetails userDetails = User.withUsername(userAuthFromDB.getEmail()).password(userAuthFromDB.getPassword()).roles(userAuthFromDB.getClass().getSimpleName().toUpperCase()).build();
//        userDetails.getAuthorities().forEach(auth -> {
//            System.out.println(userDetails.getPassword() + "  " + auth.toString());
//        });
//        return userDetails;
//    }
//}