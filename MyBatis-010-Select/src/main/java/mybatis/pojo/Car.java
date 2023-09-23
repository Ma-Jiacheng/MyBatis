package mybatis.pojo;

public class Car {
    private Long id;
    private String car_num;
    private String brand;
    private Double guide_price;
    private String product_time;
    private String car_type;

    public Car() {
    }

    public Car(Long id, String car_num, String brand, Double guide_price, String product_time, String car_type) {
        this.id = id;
        this.car_num = car_num;
        this.brand = brand;
        this.guide_price = guide_price;
        this.product_time = product_time;
        this.car_type = car_type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCar_num() {
        return car_num;
    }

    public void setCar_num(String car_num) {
        this.car_num = car_num;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Double getGuide_price() {
        return guide_price;
    }

    public void setGuide_price(Double guide_price) {
        this.guide_price = guide_price;
    }

    public String getProduct_time() {
        return product_time;
    }

    public void setProduct_time(String product_time) {
        this.product_time = product_time;
    }

    public String getCar_type() {
        return car_type;
    }

    public void setCar_type(String car_type) {
        this.car_type = car_type;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", car_num='" + car_num + '\'' +
                ", brand='" + brand + '\'' +
                ", guide_price=" + guide_price +
                ", product_time='" + product_time + '\'' +
                ", car_type='" + car_type + '\'' +
                '}';
    }
}
