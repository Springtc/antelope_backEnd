package com.pronghorn.coffee.common.repository;

import com.pronghorn.coffee.common.entity.CardInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CardInfoRepository extends JpaRepository<CardInfo, Integer> {
    List<CardInfo> getByIsAvailableEquals(String isAvailable);
}
