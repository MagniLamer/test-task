package com.andrii.testtask.repositories;

import com.andrii.testtask.entity.CurrencyRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyRequestRepository  extends JpaRepository<CurrencyRequest,Long> {
}
