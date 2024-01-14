package com.example.qlphattu.services;

import com.example.qlphattu.model.PhatTu;
import com.example.qlphattu.model.UserDetailsCustom;
import com.example.qlphattu.repository.IPhatTuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private IPhatTuRepository phatTuRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<PhatTu> findByTenTaiKhoan = phatTuRepository.findByTenTaiKhoan(username);
        return new UserDetailsCustom(findByTenTaiKhoan.get());
    }
}
