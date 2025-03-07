package Models;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CanneSucre extends Seed {
    private int price_canneSucre = 150;
    private String name_canneSucre = "Canne à sucre";
    private int total_canneSucre;
    private int time_to_grow_canneSucre = 180;
    private int give_money_canneSucre = 300;

    public CanneSucre(int total_canneSucre) {
        this.total_canneSucre = total_canneSucre;
    }

    public int getPrice_canneSucre() {
        return price_canneSucre;
    }

    public String getName_canneSucre() {
        return name_canneSucre;
    }

    public int getTotal_canneSucre() {
        return total_canneSucre;
    }

    public int getTime_to_grow_canneSucre() {
        return time_to_grow_canneSucre;
    }

    public int getGive_money_canneSucre() {
        return give_money_canneSucre;
    }

    public void plantCanneSucre(ImageView imageView) {
        changeImage(imageView, "/img/plant_canne_sucre.png");
        startCooldown(imageView, time_to_grow_canneSucre);
    }
}