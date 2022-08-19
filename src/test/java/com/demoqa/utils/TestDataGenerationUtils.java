package com.demoqa.utils;

import com.demoqa.testData.TestData;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TestDataGenerationUtils {
    static Random random = new Random();

    public static String genderGeneration() {
        int randomGender = random.nextInt(3);
        String gender = randomGender == 0 ? "Male" : (randomGender == 1 ? "Female" : "Other");
        return gender;
    }

    public static String[] hobbiesGeneration() {
        return randomStringArrayGeneration(TestData.allHobbies);
    }

    public static String[] subjectsGeneration() {
        return randomStringArrayGeneration(TestData.allSubjects);
    }

    public static String[] randomStringArrayGeneration(String[] inputStrings) {
        List<String> result = new ArrayList<>();
        for (String item : inputStrings) {
            if (random.nextBoolean()) {
                result.add(item);
            }
        }
        return result.toArray(new String[0]);
    }
}
