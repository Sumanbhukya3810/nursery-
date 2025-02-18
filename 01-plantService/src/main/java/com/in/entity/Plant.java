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

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Plant {
@Id
@GeneratedValue
private Long plantId;

@Column(nullable = false)
private String plantName;

@Column(nullable = false)
@Enumerated(EnumType.STRING)
private PlantType type;
@Column(nullable=false)
private double plantPrice;
@Column(name="PlantDescription",nullable=false)
private String plantDescription;
}
