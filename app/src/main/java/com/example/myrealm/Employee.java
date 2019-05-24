package com.example.myrealm;

import io.realm.RealmObject;

public class Employee extends RealmObject {
  private   int reg;
  private   String name;

    public int getReg() {
        return reg;
    }

    public void setReg(int reg) {
        this.reg = reg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
