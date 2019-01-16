package com.example.rpereira.saveopenjson;

public class Person {

    private Long mId;
    private String mName;
    private String mLastname;
    private int mAge;

    private Person(Long mId, String mName, String mLastname, int mAge) {
        this.mId = mId;
        this.mName = mName;
        this.mLastname = mLastname;
        this.mAge = mAge;
    }

    static class Builder {

        private Long id;
        private String name;
        private String lastname;
        private int age;

        Builder setId(Long id) {
            this.id = id;
            return this;
        }

        Builder setName(String name) {
            this.name = name;
            return this;
        }

        Builder setLastname(String lastname) {
            this.lastname = lastname;
            return this;
        }

        Builder setAge(int age) {
            this.age = age;
            return this;
        }

        Person build() {
            return new Person(id, name, lastname, age);
        }

    }

    public Long getmId() {
        return mId;
    }

    public String getmName() {
        return mName;
    }

    public String getmLastname() {
        return mLastname;
    }

    public int getmAge() {
        return mAge;
    }
}
