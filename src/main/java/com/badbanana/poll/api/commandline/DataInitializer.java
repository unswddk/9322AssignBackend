package com.badbanana.poll.api.commandline;


import com.badbanana.poll.api.DTO.LicenseDTO;
import com.badbanana.poll.api.DTO.UserDTO;
import com.badbanana.poll.api.model.License;
import com.badbanana.poll.api.service.LicenseService;
import com.badbanana.poll.api.service.UserService;

import com.sun.org.apache.bcel.internal.generic.NEW;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


import java.io.FileReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.Scanner;


/**
 * initial data at application startup.
 */
@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    UserService userService;

//    @Autowired
//    CourseService courseService;


    @Autowired
    LicenseService licenseService;


    @Override
    public void run(String... arg0) throws Exception {
//        addUser();
//        addCourse();
//        addLicense();
    }

    private void addUser() {
        userService.createUser(new UserDTO("295046974@qq.com", "123", "frank", "ROLE_ADMIN", "",null));
        userService.createUser(new UserDTO("ruan.yuji@gmail.com", "123", "yuji", "ROLE_USER", "",null));
        userService.createUser(new UserDTO("test@gmail.com", "123", "test1", "ROLE_USER", "",null));
    }

    public void addLicense() {
        try {
            Scanner in = new Scanner(new FileReader("DriverDB.txt"));
            while (in.hasNextLine()){
                String[] lic = in.nextLine().split("\\|");
                License license = new License();
                license.setName(lic[0].trim());
                license.setAddress(lic[1].trim());
                license.setNumber(lic[2].trim());
                license.setLicenseClass(lic[3].trim());
                license.setEmail("303162101@qq.com");
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                Date date = null;
                try {
                    date = formatter.parse(lic[5]);
                    java.sql.Date sql = new java.sql.Date(date.getTime());
                    System.out.println(sql);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                license.setExpiryDate(date);
                license.setNoticed(false);
                System.out.println(license);
                licenseService.addLicense(license);
            }
            in.close();
        }catch (Exception e){
            System.err.println("[error] " + e.getMessage());
        }
    }

//    private void addCourse() {
//        try {
//            String csvFile = "./csv/all.csv";
//            FileInputStream fis = new FileInputStream(csvFile);
//            InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
//            CSVReader reader = new CSVReader(isr);
//
//            for (String[] row; (row = reader.readNext()) != null; ) {
//
//                Course course = new Course();
//                course.setCode(row[0].trim());
//                course.setName(row[1].trim());
//                course.setFaculty(row[2].trim());
//                course.setSchool(row[3].trim());
//                course.setCareer(row[4].trim());
//                course.setCredit("6");
//                course.setRequirements(row[5].trim());
//                course.setDescription(row[6].trim());
//                course.setLikes(0);
//                Tag tag1 = new Tag();
//                tag1.setName(course.getCode().replaceAll("\\d+", ""));
//                Tag tag2 = new Tag();
//                tag2.setName(course.getCareer());
//                List<Tag> tags = new ArrayList<>();
//                tags.add(tag1);
//                tags.add(tag2);
//                course.setTags(tags);
//
//                System.out.println(course);
//
//                courseService.addCourse(course);
//            }
//
//            reader.close();
//            isr.close();
//            fis.close();
//
//        } catch (Exception e) {
//            System.err.println("[error] " + e.getMessage());
//        }
//    }

}