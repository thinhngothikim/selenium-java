package org.example;

import lombok.Getter;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@Getter
public class HocSinh {
    /*    Viết chương trình để tạo một lớp học sinh
Giải thích đề bài

Bài tập yêu cầu bạn viết một chương trình để tạo một lớp HocSinh.
Lớp này sẽ chứa các thuộc tính và phương thức để mô tả và thao tác với đối tượng học sinh.
Các thuộc tính cơ bản của học sinh có thể bao gồm tên, tuổi, điểm trung bình,
và các phương thức để hiển thị thông tin học sinh hoặc tính toán các giá trị nào đó.
   chuong trinh xep loai theo diem trung binh:
   - diem trung binh >= 9.0: xuat sac
   - diem trung binh >= 8.0: gioi
   - diem trung binh >= 6.5: kha
   - diem trung binh >= 5.0: trung binh
   - diem trung binh < 5.0: yeu
*/
    private String id;
    private String name;
    private int age;
    private double score;

    public HocSinh(String name, int age, double score) {
        this.id = "TVN-AK48-" + ThreadLocalRandom.current().nextInt(1000, 9000);
        // random student id with prefix "TVN-AK48"
        this.name = name;
        this.age = age;
        this.score = score;
    }

    public void info() {
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Score: " + score);
        System.out.println("Rating: " +rate().getDescription());
    }

    public Rating rate() {

        if (score < 0 || score > 10) {
            throw new IllegalArgumentException("Invalid mark");
        }
        if (score < 5) {
            return Rating.YEU;
        } else if (score < 6.5) {
            return Rating.TRUNG_BINH;
        } else if (score < 8) {
            return Rating.KHA;
        } else if (score < 9) {
            return Rating.GIOI;
        } else {
            return Rating.XUAT_SAC;
        }


    }

    public static String randomName() {
        String[] names = {"Huong", "Anh", "Mai", "Doti", "Hoa", "Nhu", "Hong", "Khanh", "Tien", "Phuong"};
        return names[ThreadLocalRandom.current().nextInt(names.length)];
    }

    public static int randomAge() {
        return ThreadLocalRandom.current().nextInt(18, 26);
    }

    public static double randomScore() {
        return Math.round(ThreadLocalRandom.current().nextDouble(1.0, 10.0) * 10.0) / 10.0;
    }

    //tao ds hs voi 10 ban
    public static void main(String[] args) {
        List<HocSinh> classroom = new ArrayList<HocSinh>();
        //tao 1 danh sach hoc sinh voi 10 ban
        //random score from 1.0 to 10.0 in double
        //random age from 18 to 25 in int
        //random name from array
        //{"Huong", "Anh", "Mai", Doti", "Hoa", "Nhu", "Hong", "Khanh", "Tien", "Phuong"}

        for (int i = 0; i < 10; i++) {
            classroom.add(new HocSinh(randomName(), randomAge(), randomScore()));
        }

        classroom.stream().forEach(HocSinh::info);
        System.out.println("====================================");

        //in thong tin cac hs xuat sac
        // classroom.stream().filter(hs -> hs.rate() == (Rating.XUAT_SAC))
        //        .forEach(HocSinh::info);

        //in ten hs >= 20 tuoi
        /* classroom.stream().filter(hs -> hs.getAge() >= 20)
                .map(HocSinh::getName)
                .forEach(System.out::println);

         */

        classroom.stream()
                .min(Comparator.comparing(HocSinh::getScore))
                .get().info();
    }
}
