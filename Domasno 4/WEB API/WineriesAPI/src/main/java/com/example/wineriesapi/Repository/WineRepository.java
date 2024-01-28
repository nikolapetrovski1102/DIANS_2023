package com.example.wineriesapi.Repository;

import com.example.wineriesapi.Model.Wineries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WineRepository extends JpaRepository<Wineries, Long> {
}
