package com.example.kodlamacarki;

public class Soru {
    private String soruMetni;
    private String[] secenekler;
    private int dogruSecenekIndex;

    public Soru(String soruMetni, String[] secenekler, int dogruSecenekIndex) {
        this.soruMetni = soruMetni;
        this.secenekler = secenekler;
        this.dogruSecenekIndex = dogruSecenekIndex;
    }

    public String getSoruMetni() {
        return soruMetni;
    }

    public String[] getSecenekler() {
        return secenekler;
    }

    public int getDogruSecenekIndex() {
        return dogruSecenekIndex;
    }
}
