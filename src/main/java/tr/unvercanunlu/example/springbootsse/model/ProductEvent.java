package tr.unvercanunlu.example.springbootsse.model;

import tr.unvercanunlu.example.springbootsse.structure.Event;
import tr.unvercanunlu.example.springbootsse.structure.EventPriority;
import tr.unvercanunlu.example.springbootsse.structure.EventStatus;
import tr.unvercanunlu.example.springbootsse.structure.EventType;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class ProductEvent extends Event {

    public ProductEvent() {
        Product product = new Product();
        product.setId(12345L);
        product.setName("product");
        product.setPrice(100.0d);
        product.setQuantity(5);
        product.setCreatedDate(LocalDateTime.now().minus(10, ChronoUnit.DAYS));
        product.setUpdatedData(LocalDateTime.now());

        this.setName("product");
        this.setPriority(EventPriority.LOW);
        this.setType(EventType.NOTIFICATION);
        this.setStatus(EventStatus.SUCCESS);
        this.setDescription("description");
        this.setTimestamp(LocalDateTime.now());
        this.setMetadata(product);
    }

    private static class Product {

        private Long id;
        private String name;
        private Integer quantity;
        private Double price;
        private LocalDateTime createdDate;
        private LocalDateTime updatedData;

        public Product() {
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getQuantity() {
            return quantity;
        }

        public void setQuantity(Integer quantity) {
            this.quantity = quantity;
        }

        public Double getPrice() {
            return price;
        }

        public void setPrice(Double price) {
            this.price = price;
        }

        public LocalDateTime getCreatedDate() {
            return createdDate;
        }

        public void setCreatedDate(LocalDateTime createdDate) {
            this.createdDate = createdDate;
        }

        public LocalDateTime getUpdatedData() {
            return updatedData;
        }

        public void setUpdatedData(LocalDateTime updatedData) {
            this.updatedData = updatedData;
        }

        @Override
        public String toString() {
            return "Product{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", quantity=" + quantity +
                    ", price=" + price +
                    ", createdDate=" + createdDate +
                    ", updatedData=" + updatedData +
                    '}';
        }
    }
}
