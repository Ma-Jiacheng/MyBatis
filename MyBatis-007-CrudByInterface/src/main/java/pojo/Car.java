package pojo;

//封装汽车相关信息

public class Car {
    //数据库表中的字段，应当和pojo类的属性一一对应
    //建议使用包装类，可以防止null出现问题
    private Long id;
    private String carNum;
    private String brand;
    private Double guidePrice;
    private String productTime;
    private String carType;

    public Car() {
    }

    public Car(Long id, String carNum, String brand, Double guidePrice, String productTime, String carType) {
        this.id = id;
        this.carNum = carNum;
        this.brand = brand;
        this.guidePrice = guidePrice;
        this.productTime = productTime;
        this.carType = carType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCarNum() {
        return carNum;
    }

    public void setCarNum(String carNum) {
        this.carNum = carNum;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Double getGuidePrice() {
        return guidePrice;
    }

    public void setGuidePrice(Double guidePrice) {
        this.guidePrice = guidePrice;
    }

    public String getProductTime() {
        return productTime;
    }

    public void setProductTime(String productTime) {
        this.productTime = productTime;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", car_num='" + carNum + '\'' +
                ", brand='" + brand + '\'' +
                ", guide_price=" + guidePrice +
                ", product_time='" + productTime + '\'' +
                ", car_type='" + carType + '\'' +
                '}';
    }
}
