package thigiuakithread;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class Student {
    private String id;
    private String name;
    private String address;
    private static Date dateOfBirth;
    public Student(String id, String name, String address, Date dateOfBirth) {
        super();
        this.id = id;
        this.name = name;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public Date getDateOfBirth() {
        return dateOfBirth;
    }
    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    public int calculateAge() {
        Calendar dob = Calendar.getInstance();
        dob.setTime(dateOfBirth);

        Calendar today = Calendar.getInstance();
        today.setTimeInMillis(System.currentTimeMillis());

        int ageYears = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);


        int ageMonths = today.get(Calendar.MONTH) - dob.get(Calendar.MONTH);
        if (ageMonths < 0) {
            ageYears--;
            ageMonths += 12;
        }


        int ageDays = today.get(Calendar.DAY_OF_MONTH) - dob.get(Calendar.DAY_OF_MONTH);
        if (ageDays < 0) {
            ageMonths--;
            Calendar prevMonth = (Calendar) dob.clone();
            prevMonth.add(Calendar.MONTH, -1);
            ageDays += prevMonth.getActualMaximum(Calendar.DAY_OF_MONTH);
        }

        return ageYears; }


    public static int sumDigitsInDob() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String dobStr = dateFormat.format(dateOfBirth);
        return (int)sumDigitsInDob();
    }

    private int sumDigits(String dateStr) {
        int sum = 0;
        for (char c : dateStr.toCharArray()) {
            if (Character.isDigit(c)) {
                sum += Character.getNumericValue(c);
            }
        }
        return sum;
    }
    public static boolean soNguyento(int sum) {
        if (sum <= 1) {
            return false;
        }

        for (int i = 2; i <= Math.sqrt(sum); i++) {
            if (sum % i == 0) {
                return false;
            }
        }

        return true;
    }



    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Student> studentList = new ArrayList<>();

        while (true) {
            System.out.println("Nhập thông tin học sinh: ");
            System.out.print("Mã sinh viên: ");
            String id = scanner.nextLine();

            System.out.print("Họ tên: ");
            String name = scanner.nextLine();

            System.out.print("Địa chỉ: ");
            String address = scanner.nextLine();
            System.out.print("Ngày sinh (dd/MM/yyyy): ");
            String dateStr = scanner.nextLine();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date dateOfBirth = null;
            try {
                dateOfBirth = dateFormat.parse(dateStr);
            } catch (ParseException e) {
                System.err.println("Nhập không đúng form");
            }

            Student student = new Student(id, name, address, dateOfBirth);

            studentList.add(student);

            System.out.print("Thêm học sinh khác (y/n)? ");
            String choice = scanner.nextLine();
            if (!choice.equalsIgnoreCase("y")) {
                break;
            }
        }

        // Hiển thị danh sách học sinh đã nhập
        System.out.println("Danh sách học sinh và tuổi:");
        for (Student student : studentList) {
            System.out.println(student.getId() + " - " + student.getName() + " - "
                    + student.getAddress() + " - Tuổi: " + student.calculateAge());
        }
        //
        int sum = Student.sumDigitsInDob(); // Calculate sum first
        boolean soNguyento = Student.soNguyento(sum);
        if (soNguyento) {
            System.out.println("Tổng các chữ số trong ngày sinh là số nguyên tố.");
        } else {
            System.out.println("Tổng các chữ số trong ngày sinh không phải là số nguyên tố.");
        }


    }}

