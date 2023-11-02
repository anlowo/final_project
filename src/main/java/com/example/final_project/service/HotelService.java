package com.example.final_project.service;

import com.example.final_project.dto.HotelDto;
import com.example.final_project.entity.Hotel;

import java.util.List;

public interface HotelService {
//    Hotel createHotel(HotelDto hotelDto, Long roomId);

    Hotel createHotel(HotelDto hotelDto);

    Hotel getHotel(Long id);

    List<Hotel> getAllHotels();

    Hotel updateHotel(Long id, HotelDto hotelDto);

    Hotel deleteHotel(Long id);
}
