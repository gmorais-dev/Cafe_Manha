package com.desafio.wl.demo.Services;

import com.desafio.wl.demo.Repository.CafeManhaRepository;
import org.springframework.stereotype.Service;

@Service
public class CafeManhaService {
    private  CafeManhaRepository CafeManhaRepository;

    public CafeManhaService(CafeManhaRepository CafeManhaRepository) {
        this.CafeManhaRepository = CafeManhaRepository;
    }







}
