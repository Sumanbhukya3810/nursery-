package com.in.entity;



import org.springframework.validation.annotation.Validated;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class Fertilizer {
@Id
@GeneratedValue
private Long fertilizerId;

@Column(nullable = false)
private String fertilizerName;

@Column(nullable = false)
@Enumerated(EnumType.STRING)
private FertilizerType type;
@Column(nullable=false)
private double fertilizerPrice;
@Column(name="fertilizerDescription",nullable=false)
private String fertilizerDescription;
}

