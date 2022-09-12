package com.uce.edu.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.uce.edu.demo.repository.modelo.VehiculoVip;
import com.uce.edu.demo.service.IVehiculoService;

@SpringBootApplication
public class ProyectoFinalPa2DvApplication implements CommandLineRunner{

	@Autowired
	private IVehiculoService iVehiculoService;
	
	public static void main(String[] args) {
		SpringApplication.run(ProyectoFinalPa2DvApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		List<VehiculoVip> vp= this.iVehiculoService.reporteVehiculosVip(1, 2022);
		
		vp.forEach(s -> System.out.println(s));
	}

}
