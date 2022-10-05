import java.util.HashMap;
import java.util.Map;

public class zad3 {
    public static void getAge(double lifeTime, String namePlanet){
        Map<String, Double> map = new HashMap<String, Double>();
        map.put("Ziemia", 1.0);
        map.put("Merkury", 0.2408467);
        map.put("Wenus",0.61519726);
        map.put("Mars",1.8808158);
        map.put("Jowisz",11.862615);
        map.put("Saturn",29.447498);
        map.put("Uran",84.016846);
        map.put("Neptun",164.79132);

        System.out.println(Math.round(lifeTime / (31557600 / map.get(namePlanet)) * 100.0) / 100.0);
    }

    public static void main(String[] args) {
        getAge(1000000000, "Ziemia");
    }
}
