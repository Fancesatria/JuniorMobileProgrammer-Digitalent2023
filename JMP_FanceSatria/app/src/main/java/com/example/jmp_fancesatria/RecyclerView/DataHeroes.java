package com.example.jmp_fancesatria.RecyclerView;

import java.util.ArrayList;

public class DataHeroes {
    public static String[][] data = new String[][]{
            {"Captain America", "Marvel Comics", "https://cdn0.iconfinder.com/data/icons/famous-character-vol-1-colored/48/JD-09-512.png"},
            {"Dead Pool", "Marvel Comics", "https://cdn0.iconfinder.com/data/icons/famous-character-vol-1-colored/48/JD-15-512.png"},
            {"Iron Man", "Marvel Comics", "https://cdn0.iconfinder.com/data/icons/famous-character-vol-1-colored/48/JD-14-512.png"},
            {"Ant Man", "Marvel Comics", "https://cdn0.iconfinder.com/data/icons/famous-character-vol-1-colored/48/JD-10-512.png"},
            {"Bat Man", "DC Comics", "https://cdn0.iconfinder.com/data/icons/famous-character-vol-1-colored/48/JD-22-512.png"},
            {"Thor", "Marvel Comics", "https://cdn0.iconfinder.com/data/icons/famous-character-vol-1-colored/48/JD-20-512.png"},
            {"Hulk", "Marvel Comics", "https://cdn0.iconfinder.com/data/icons/famous-character-vol-1-colored/48/JD-03-512.png"},
            {"Flash", "DC Comics", "https://cdn0.iconfinder.com/data/icons/famous-character-vol-1-colored/48/JD-18-512.png"},
            {"Spiderman", "Marvel Comics", "https://cdn0.iconfinder.com/data/icons/famous-character-vol-1-colored/48/JD-16-512.png"},
    };

    public static ArrayList<ModelHero> getListData() {
        ArrayList<ModelHero> list = new ArrayList<>();
        for (String[] aData : data) {
            ModelHero hero = new ModelHero();
            hero.setName(aData[0]);
            hero.setFrom(aData[1]);
            hero.setPhoto(aData[2]);
            list.add(hero);
        }
        return list;
    }
}
