package eHealth.entity;

import java.util.ArrayList;

public class Questionnaire {

    private  String gender;
    private  String age;
    private ArrayList<String> location;
    private  String pain;
    private  String pain_date;
    private  String pain_amount;
    private  String notizen;
    private  String addtext;
    private  ArrayList<String> bodypart;
    private  ArrayList<String> symptom;

    public Questionnaire(String gender, String age, ArrayList<String> location, String pain, String pain_date, String pain_amount, String notizen, String addtext, ArrayList<String> bodypart, ArrayList<String> symptom) {
        this.gender = gender;
        this.age = age;
        this.location = location;
        this.pain = pain;
        this.pain_date = pain_date;
        this.pain_amount = pain_amount;
        this.notizen = notizen;
        this.addtext = addtext;
        this.bodypart = bodypart;
        this.symptom = symptom;
    }

    public String getNotizen() {
        return notizen;
    }

    public void setNotizen(String notizen) {
        this.notizen = notizen;
    }

    public String getAddtext() {
        return addtext;
    }

    public void setAddtext(String addtext) {
        this.addtext = addtext;
    }

    public ArrayList<String> getSymptom() {
        return symptom;
    }

    public void setSymptom(ArrayList<String> symptom) {
        this.symptom = symptom;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public ArrayList<String> getLocation() {
        return location;
    }

    public void setLocation(ArrayList<String> location) {
        this.location = location;
    }

    public String getPain() {
        return pain;
    }

    public void setPain(String pain) {
        this.pain = pain;
    }

    public String getPain_date() {
        return pain_date;
    }

    public void setPain_date(String pain_date) {
        this.pain_date = pain_date;
    }

    public String getPain_amount() {
        return pain_amount;
    }

    public void setPain_amount(String pain_amount) {
        this.pain_amount = pain_amount;
    }

    public ArrayList<String> getBodypart() {
        return bodypart;
    }

    public void setBodypart(ArrayList<String> bodypart) {
        this.bodypart = bodypart;
    }
}
