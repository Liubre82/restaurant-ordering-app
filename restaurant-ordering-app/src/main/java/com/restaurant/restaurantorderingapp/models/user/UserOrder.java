package com.restaurant.restaurantorderingapp.models.user;

import jakarta.persistence.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "user_orders")
public class UserOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String userOrderId;

    @OneToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(targetEntity = UserAddress.class)
    @JoinColumn(name = "user_address_id")
    private UserAddress userAddress;

    @OneToMany(mappedBy = "userOrder", cascade = CascadeType.REMOVE)
    private List<UserFoodItem> userFoodItems;

    @UpdateTimestamp
    @Column(name = "order_created_at")
    private LocalDateTime orderCreatedAt;

    @Column(name = "subtotal")
    private BigDecimal subtotal;

    @Column(name = "total_sales_tax_cost")
    private BigDecimal totalSalesTaxCost;

    @Column(name = "total_order_cost")
    private BigDecimal totalOrderCost;

    @Column(name = "order_notes")
    private String orderNotes;

    public String getUserOrderId() {
        return userOrderId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UserAddress getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(UserAddress userAddress) {
        this.userAddress = userAddress;
    }

    public List<UserFoodItem> getUserFoodItems() {
        return userFoodItems;
    }

    public void setUserFoodItems(List<UserFoodItem> userFoodItems) {
        this.userFoodItems = userFoodItems;
    }

    public LocalDateTime getOrderCreatedAt() {
        return orderCreatedAt;
    }

    public void setOrderCreatedAt(LocalDateTime orderCreatedAt) {
        this.orderCreatedAt = orderCreatedAt;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public BigDecimal getTotalSalesTaxCost() {
        return totalSalesTaxCost;
    }

    public void setTotalSalesTaxCost(BigDecimal totalSalesTaxCost) {
        this.totalSalesTaxCost = totalSalesTaxCost;
    }

    public BigDecimal getTotalOrderCost() {
        return totalOrderCost;
    }

    public void setTotalOrderCost(BigDecimal totalOrderCost) {
        this.totalOrderCost = totalOrderCost;
    }

    public String getOrderNotes() {
        return orderNotes;
    }

    public void setOrderNotes(String orderNotes) {
        this.orderNotes = orderNotes;
    }
}
