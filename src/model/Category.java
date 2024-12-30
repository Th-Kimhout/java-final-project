package model;


import lombok.Data;

@Data
public class Category {
    private int id;
    private String category_name;

    public Category() {
    }

    public Category(int id, String category_name) {
        this.id = id;
        this.category_name = category_name;
    }

    @Override
    public String toString() {
        return "ID =" + id +
                "\n Category's Name = " + category_name;
    }
}
