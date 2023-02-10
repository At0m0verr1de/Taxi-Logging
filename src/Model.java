public class Model {
    String category, dishname;

    Model(String category, String dishname) {
        this.dishname = dishname;
        this.category = category;
    }

    String getCategory() {
        return category;
    }

    String getDishname() {
        return dishname;
    }
}
