package com.codewise.internship;

interface Service {

    void put(ID clientId, double money);

    double get(ID clientId);
}
