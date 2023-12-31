package com.company.oop.taskmanagement.models.enums;

public enum StorySize {
    SMALL,
    MEDIUM,
    LARGE;

    @Override
    public String toString(){
        switch(this){
            case SMALL:
                return "Small";

            case MEDIUM:
                return "Medium";
            case LARGE:
                return  "Large";
            default:
                return "";
        }
    }
}
