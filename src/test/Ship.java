package test;

import lombok.Data;

/**
 * @author hood  2020/8/19
 */
@Data
public class Ship {
    private String name;

    public static void main(String[] args) {
        Akagi akagi = new Akagi();
        akagi.setName("akagi");
        System.out.println(akagi.toString());
    }
    @Override
    public String toString() {
        return "Ship: name: " + name;
    }
}

@Data
class Carrier extends Ship {
    protected String name;
    @Override
    public String toString() {
        return "Carrier: name: " + name + super.toString();
    }
}

@Data
class Akagi extends Carrier {
    public String name;

    @Override
    public String toString() {
        return "Akagi: name: " + name + super.toString();
    }
}


