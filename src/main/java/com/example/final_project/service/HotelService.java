package com.example.final_project.service;

import com.example.final_project.dto.HotelDto;
import com.example.final_project.entity.Hotel;

public interface HotelService {
    Hotel createHotel(HotelDto hotelDto, Long roomId);
}
