package com.cybersoft.osahaneat.entity.keys;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Embeddable
public class KeyOrderItem implements Serializable {
    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "food_id")
    private Long foodId;
}
