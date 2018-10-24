package com.pink.pinkpineaple;

public class Profile {


    private String name;
    private int age;
    private int imageId;
    private String bio;
    private String[] moviePreferences; //im sure the type will change eventually.

    public Profile(String nme){
        this.name = nme;
    }

    public void setName(String newName){
        this.name = newName;
    }

    public String getName(){
        return this.name;
    }

    public void setAge(int newAge){
        this.age = newAge;
    }

    public int getAge(){
        return this.age;
    }

    public void setBio(String newBio){
        this.bio = newBio;
    }

    public String getBio(){ return this.bio;}

    public void setImageId(int id) {
        this.imageId = id;
    }

    public int getImageId() {
        return imageId;
    }


}
