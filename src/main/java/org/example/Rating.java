package org.example;

import lombok.Getter;

@Getter
public enum Rating {
    XUAT_SAC(9.0, "Xuat Sac"),
    GIOI(8.0, "Gioi"),
    KHA(6.5, "Kha"),
    TRUNG_BINH(5.0, "Trung Binh"),
    YEU(0.0, "Yeu");

    private final double score;
    private final String description;

    Rating(double score, String description) {
        this.score = score;
        this.description = description;
    }

    public static Rating getRating(double score) {
        for (Rating rating : Rating.values()) {
            if (score >= rating.score) {
                return rating;
            }
        }
        return null;
    }


}