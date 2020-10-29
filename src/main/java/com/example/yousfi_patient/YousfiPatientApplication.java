package com.example.yousfi_patient;

import com.example.yousfi_patient.dao.PatientRepo;
import com.example.yousfi_patient.entities.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@SpringBootApplication
public class YousfiPatientApplication implements CommandLineRunner {
    @Autowired
    private PatientRepo patientRepo;

    public static void main(String[] args) {
        SpringApplication.run(YousfiPatientApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Calendar cal1 = Calendar.getInstance();
        cal1.set(1977, Calendar.AUGUST, 16);
        Calendar cal2 = Calendar.getInstance();
        cal1.set(1990, Calendar.JULY, 21);
        Calendar cal3 = Calendar.getInstance();
        cal1.set(2019, Calendar.DECEMBER, 6);
        Calendar cal4 = Calendar.getInstance();
        cal1.set(1982, Calendar.AUGUST, 8);


        patientRepo.save(new Patient(null,"Paris","Eric",cal1.getTime(),95,false));
        patientRepo.save(new Patient(null,"Bensalem","Malek",cal2.getTime(),99,false));
        patientRepo.save(new Patient(null,"Paris","Ethan",cal3.getTime(),95,false));
        patientRepo.save(new Patient(null,"Do","John",cal4.getTime(),75,true));

        patientRepo.findAll().forEach(p->{
            System.out.println(p.toString());
        });

    }
}
