package com.example.final_project.service.impl;

import com.example.final_project.dto.HotelDto;
import com.example.final_project.entity.Hotel;
import com.example.final_project.repository.HotelRepository;
import com.example.final_project.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class HotelServiceImpl implements HotelService {
    @Autowired
    private HotelRepository hotelRepository;

    @Override
    public Hotel createHotel(HotelDto hotelDto) {
            Hotel hotel = new Hotel();

            hotel.setName(hotelDto.getName());

            hotelRepository.save(hotel);

            return hotel;
    }

    @Override
    public Hotel getHotel(Long id) {
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rooms with " + id + " id not found"));
        System.out.println(hotel.toString());
        return hotel;
    }

    @Override
    public List<Hotel> getAllHotels() {
        List<Hotel> answers = hotelRepository.findAll();
        answers.sort(Comparator.comparingLong(Hotel::getId));
        return answers;
    }

    @Override
    public Hotel updateHotel(Long id, HotelDto hotelDto) {
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Отель с id " + id + " не найден"));
        hotel.setName(hotelDto.getName());
        return hotelRepository.save(hotel);
    }

    @Override
    public Hotel deleteHotel(Long id) {
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Отель с id " + id + " не найден"));
        hotelRepository.delete(hotel);
        return hotel;
    }
}
