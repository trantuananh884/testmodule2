package com.company;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Controller {
    Scanner sc = new Scanner(System.in);
    ArrayList<Contact> contacts = new ArrayList<>();

    public void showAll() {
        if (contacts.isEmpty()) {
            System.out.println("Danh bạ rỗng");
        }
        for (Contact contact : contacts) {
            System.out.println(contact.toString());
        }
    }

    public void add() {
        try {
            boolean checksdt = false;
            String sdt = null;
            do {
                System.out.println("Nhập số điện thoại");
                String sdtpattern = "^(09|01[2|6|8|9])+([0-9]{8})\\b";
                sdt = sc.nextLine();
                Pattern pattern = Pattern.compile(sdtpattern);
                Matcher matcher = pattern.matcher(sdt);
                if (matcher.find()) {
                    checksdt = true;
                } else {
                    System.out.println("Vui lòng nhập lại số điện thoại ");
                }
            } while (!checksdt);
            System.out.println("Nhập nhóm ");
            String nhom = sc.nextLine();
            System.out.println("Nhập họ tên");
            String ten = sc.nextLine();
            System.out.println("Nhập giới tính");
            String gioitinh = sc.nextLine();
            System.out.println("Nhập địa chỉ");
            String diachi = sc.nextLine();
            String dob = null;
            boolean checkNgaysinh = false;
            do {
                System.out.println("Nhập ngày sinh");
                String dobPattern = "^(0[1-9]|1[012])[-/.](0[1-9]|[12][0-9]|3[01])[-/.](19|20)\\d\\d$";
                dob = sc.nextLine();
                Pattern pattern = Pattern.compile(dobPattern);
                Matcher matcher = pattern.matcher(dob);
                if (matcher.find()) {
                    checkNgaysinh = true;
                } else {
                    System.out.println("Nhập lại ngày sinh ");
                }
            } while (!checkNgaysinh);
            String email = null;
            boolean checkemail = false;
            do {
                System.out.println("Nhập email");
                String emailPattern = "^[a-z][a-z0-9_\\.]{5,32}@[a-z0-9]{2,}(\\.[a-z0-9]{2,4}){1,2}$";
                email = sc.nextLine();
                Pattern pattern = Pattern.compile(emailPattern);
                Matcher matcher = pattern.matcher(email);
                if (matcher.find()) {
                    checkemail = true;
                } else {
                    System.out.println("Nhập lại email");
                }
            } while (!checkemail);
            Contact contact = FactoryContact.getContact();
            contact.setName(ten);
            contact.setAddress(diachi);
            contact.setEmail(email);
            contact.setGroup(nhom);
            contact.setNumber(sdt);
            contact.setGender(gioitinh);
            contact.setDob(dob);
            contacts.add(contact);
        } catch (Exception e) {
            System.out.println(" Nhập sai định dạng quay lại menu");
        }
    }

    public void search() {
        try {
            System.out.println("Nhập sdt cần tìm");
            String sdt = sc.nextLine();
            for (Contact contact : contacts) {
                if (contact.getNumber().equals(sdt)) {
                    System.out.println(contact.toString());
                    return;
                }
            }
            System.out.println("Không tìm được ");
        }catch (Exception e){
            System.out.println("Nhập sai định dạng ");
        }
    }

    public void update() {
        try {
            System.out.println("Nhập sdt cần sửa");
            String sdt = sc.nextLine();
            for (Contact contact : contacts) {
                if (contact.getNumber().equals(sdt)) {
                    contacts.remove(contacts.indexOf(contact));
                    add();
                    return;
                }
            }
            System.out.println("Không tìm được danh bạ ");
        }catch (Exception e){
            System.out.println("Nhập sai, vui lòng nhập lại");
        }
    }

    public void delete() {
        try {
            System.out.println("nhap sdt can xoa");
            String sdt = sc.nextLine();
            for (Contact contact : contacts) {
                if (contact.getNumber().equals(sdt)) {
                    contacts.remove(contacts.indexOf(contact));
                    return;
                }
            }
            System.out.println("Không tìm được danh bạ với sdt trên");
        }catch (Exception e){
            System.out.println("Nhập sai định dạng quay lại menu");
        }
    }

    public void docTuFile() {
        contacts = IO.readFileToListContact();
    }

    public void vietRaFile() {
        IO.writeContactsToFile(contacts);
    }
}
