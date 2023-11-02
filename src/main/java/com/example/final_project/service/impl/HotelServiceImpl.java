package com.example.final_project.service.impl;

import com.example.final_project.dto.HotelDto;
import com.example.final_project.entity.Hotel;
import com.example.final_project.entity.Room;
import com.example.final_project.repository.HotelRepository;
import com.example.final_project.repository.RoomRepository;
import com.example.final_project.service.HotelService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class HotelServiceImpl implements HotelService {
    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Override
    public Hotel createHotel(HotelDto hotelDto, Long roomId) {
        Optional<Room> roomOptional = roomRepository.findById(roomId);

        if (roomOptional.isPresent()) {
            Room room = roomOptional.get();

            Hotel hotel = new Hotel();
            hotel.setName(hotelDto.getName());

            hotel.getRoom().add(room);

            hotelRepository.save(hotel);

            return hotel;
        } else {
            throw new EntityNotFoundException("Room with ID " + roomId + " not found");
        }
    }

//    @Override
//    public Hotel readHotel(Long id) {
//        Hotel hotel = hotelRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Rooms with " + id + " id not found"));
//        System.out.println(hotel.toString());
//        return hotel;
//    }
//
//    @Override
//    public List<Answer> readAllAnswer() {
//        List<Answer> answers = answerRepository.findAll();
//        answers.sort(Comparator.comparingLong(Answer::getId));
//        return answers;
//    }
//
//    @Override
//    public Answer updateAnswer(Long id, String answerText) {
//        Answer answer = answerRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Ответ с id " + id + " не найден"));
//        answer.setAnswerText(answerText);
//        answerRepository.save(answer);
//        return answer;
//    }
//
//    @Override
//    public Answer deleteAnswer(Long id) {
//        Answer answer = answerRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Ответ с id " + id + " не найден"));
//        answerRepository.delete(answer);
//        return answer;
//    }
}
